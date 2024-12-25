package cn.bugstack.domain.activity.service;

import cn.bugstack.domain.activity.model.entity.ActivityOrderEntity;
import cn.bugstack.domain.activity.model.entity.ActivityShopCartEntity;

/**
 * @author: ts
 * @description
 * @create: 2024/12/25 15:09
 */
public interface IRaffleOrder {
    ActivityOrderEntity createRaffleActivityOrder(ActivityShopCartEntity activityShopCartEntity);
}
