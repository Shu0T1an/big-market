package cn.bugstack.domain.strategy.service.rule;

import cn.bugstack.domain.strategy.model.entity.RaffleAwardEntity;
import cn.bugstack.domain.strategy.model.entity.RaffleFactorEntity;

// 抽奖策略接口
public interface IRaffleStrategy {
     RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity);
}
