package cn.bugstack.domain.activity.service;

import cn.bugstack.domain.activity.model.entity.SkuRechargeEntity;

/**
 * @author: ts
 * @description
 * @create: 2024/12/25 15:09
 */
public interface IRaffleActivityAccountQuotaService {

    String createOrder(SkuRechargeEntity skuRechargeEntity);

    Integer queryRaffleActivityAccountDayPartakeCount(Long activityId, String userId);
}
