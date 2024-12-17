package cn.bugstack.domain.strategy.service.armory;

import cn.bugstack.domain.strategy.model.entity.StrategyAwardEntity;

import java.util.List;

public interface IStrategyArmory {

    public boolean assembleLotteryStrategy(Long strategyId);


    public void assembleLotteryStrategy(String key, List<StrategyAwardEntity> strategyAwardEntities);

}
