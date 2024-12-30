package cn.bugstack.domain.rebate.service;

import cn.bugstack.domain.rebate.model.entity.BehaviorEntity;

import java.util.List;

/**
 * @author: ts
 * @description
 * @create: 2024/12/30 10:46
 */
public interface IBehaviorRebateService {
    List<String> createOrder(BehaviorEntity behaviorEntity);
}
