package cn.bugstack.domain.strategy.service.rule.chain.impl;

import cn.bugstack.domain.strategy.service.armory.StrategyArmoryDispatch;
import cn.bugstack.domain.strategy.service.rule.chain.AbstractLogicChain;
import cn.bugstack.domain.strategy.service.rule.chain.ILogicChain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("default")
public class DefalutLogicChain extends AbstractLogicChain{
    @Resource
    StrategyArmoryDispatch strategyDispatch;
    @Override
    protected String ruleModel() {
        return "default";
    }

    @Override
    public Integer logic(String userId, Long strategyId) {
        return strategyDispatch.getRandomAwardId(strategyId);
    }
}
