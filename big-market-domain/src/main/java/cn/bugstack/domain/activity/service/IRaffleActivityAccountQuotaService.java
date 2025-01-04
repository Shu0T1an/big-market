package cn.bugstack.domain.activity.service;

import cn.bugstack.domain.activity.model.entity.ActivityAccountEntity;
import cn.bugstack.domain.activity.model.entity.DeliveryOrderEntity;
import cn.bugstack.domain.activity.model.entity.SkuRechargeEntity;
import cn.bugstack.domain.activity.model.entity.UnpaidActivityOrderEntity;

/**
 * @author: ts
 * @description
 * @create: 2024/12/25 15:09
 */
public interface IRaffleActivityAccountQuotaService {

    UnpaidActivityOrderEntity createOrder(SkuRechargeEntity skuRechargeEntity);

    Integer queryRaffleActivityAccountDayPartakeCount(Long activityId, String userId);

    ActivityAccountEntity queryActivityAccountEntity(Long activityId, String userId);

    Integer queryRaffleActivityAccountPartakeCount(Long activityId, String userId);

    void updateOrder(DeliveryOrderEntity deliveryOrderEntity);
}
