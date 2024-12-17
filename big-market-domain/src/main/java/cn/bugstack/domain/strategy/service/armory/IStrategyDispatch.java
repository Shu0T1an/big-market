package cn.bugstack.domain.strategy.service.armory;

public interface IStrategyDispatch {
    public Integer getRandomAwardId(Long strategyId);
    public Integer getRandomAwardId(Long strategyId,String ruleWeightValue);

}
