package cn.bugstack.domain.activity.service.quota;

import cn.bugstack.domain.activity.model.aggregate.CreateQuotaOrderAggregate;
import cn.bugstack.domain.activity.model.entity.*;
import cn.bugstack.domain.activity.repository.IActivityRepository;
import cn.bugstack.domain.activity.service.IRaffleActivityAccountQuotaService;
import cn.bugstack.domain.activity.service.quota.policy.ITradePolicy;
import cn.bugstack.domain.activity.service.quota.rule.IActionChain;
import cn.bugstack.domain.activity.service.quota.rule.factory.DefaultActivityChainFactory;
import cn.bugstack.types.enums.ResponseCode;
import cn.bugstack.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 抽奖活动抽象类，定义标准的流程
 * @create 2024-03-16 08:42
 */
@Slf4j
public abstract class AbstractRaffleActivityAccountQuota extends RaffleActivityAccountQuotaSupport implements IRaffleActivityAccountQuotaService {

    private final Map<String, ITradePolicy> tradePolicyGroup;

    public AbstractRaffleActivityAccountQuota(IActivityRepository activityRepository, DefaultActivityChainFactory defaultActivityChainFactory, Map<String, ITradePolicy> tradePolicyGroup) {
        super(activityRepository, defaultActivityChainFactory);
        this.tradePolicyGroup = tradePolicyGroup;
    }


    @Override
    public String createOrder(SkuRechargeEntity skuRechargeEntity) {
        //1.参数校验
        String userId = skuRechargeEntity.getUserId();
        Long sku = skuRechargeEntity.getSku();
        String outBusinessNo = skuRechargeEntity.getOutBusinessNo();
        if(sku == null || StringUtils.isBlank(userId)|| StringUtils.isBlank(outBusinessNo)){
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }

        //2. 查询基础信息
        ActivitySkuEntity activitySkuEntity = queryActivitySku(sku);
        // 获取活动实体
        ActivityEntity activityEntity = queryRaffleActivityByActivityId(activitySkuEntity.getActivityId());
        // 获取库存实体
        ActivityCountEntity activityCountEntity = queryRaffleActivityCountByActivityCountId(activitySkuEntity.getActivityCountId());
        // 获取责任链
        IActionChain actionChain = defaultActivityChainFactory.openActionChain();
        // 责任链结果
        actionChain.action(activitySkuEntity, activityEntity, activityCountEntity);
        // 创建聚合订单
        CreateQuotaOrderAggregate createQuotaOrderAggregate = buildOrderAggregate(skuRechargeEntity, activitySkuEntity, activityEntity, activityCountEntity);
        // 5. 交易策略 - 【积分兑换，支付类订单】【返利无支付交易订单，直接充值到账】【订单状态变更交易类型策略】
        ITradePolicy tradePolicy = tradePolicyGroup.get(skuRechargeEntity.getOrderTradeType().getCode());
        tradePolicy.trade(createQuotaOrderAggregate);
        // 返回聚合订单的Id
        return createQuotaOrderAggregate.getActivityOrderEntity().getOrderId();
    }


    protected abstract CreateQuotaOrderAggregate buildOrderAggregate(SkuRechargeEntity skuRechargeEntity
            , ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity
            , ActivityCountEntity activityCountEntity);

}
