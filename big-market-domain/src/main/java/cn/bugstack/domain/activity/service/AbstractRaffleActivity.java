package cn.bugstack.domain.activity.service;

import cn.bugstack.domain.activity.model.aggregate.CreateOrderAggregate;
import cn.bugstack.domain.activity.model.entity.*;
import cn.bugstack.domain.activity.repository.IActivityRepository;
import cn.bugstack.domain.activity.service.rule.IActionChain;
import cn.bugstack.domain.activity.service.rule.factory.DefaultActivityChainFactory;
import cn.bugstack.types.enums.ResponseCode;
import cn.bugstack.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 抽奖活动抽象类，定义标准的流程
 * @create 2024-03-16 08:42
 */
@Slf4j
public abstract class AbstractRaffleActivity extends  RaffleActivitySupport implements IRaffleOrder {


    public AbstractRaffleActivity(IActivityRepository activityRepository, DefaultActivityChainFactory defaultActivityChainFactory) {
        super(activityRepository, defaultActivityChainFactory);
    }

    @Override
    public String createSkuRechargeOrder(SkuRechargeEntity skuRechargeEntity) {
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
        boolean success = actionChain.action(activitySkuEntity, activityEntity, activityCountEntity);
        // 创建聚合订单
        CreateOrderAggregate createOrderAggregate = buildOrderAggregate(skuRechargeEntity, activitySkuEntity, activityEntity, activityCountEntity);
        // 保存聚合订单
        doSaveOrder(createOrderAggregate);
        // 返回聚合订单的Id
        return createOrderAggregate.getActivityOrderEntity().getOrderId();
    }

    protected abstract void doSaveOrder(CreateOrderAggregate createOrderAggregate);

    protected abstract CreateOrderAggregate buildOrderAggregate(SkuRechargeEntity skuRechargeEntity
            , ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity
            , ActivityCountEntity activityCountEntity);


    @Override
    public ActivityOrderEntity createRaffleActivityOrder(ActivityShopCartEntity activityShopCartEntity) {
        return null;
    }

}
