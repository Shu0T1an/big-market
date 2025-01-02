package cn.bugstack.domain.award.service;

import cn.bugstack.domain.award.model.entity.DistributeAwardEntity;
import cn.bugstack.domain.award.model.entity.UserAwardRecordEntity;

/**
 * @author: ts
 * @description
 * @create: 2024/12/27 9:30
 */
public interface IAwardService {
    void saveUserAwardRecord(UserAwardRecordEntity userAwardRecordEntity);

    void distributeAward(DistributeAwardEntity distributeAwardEntity);
}
