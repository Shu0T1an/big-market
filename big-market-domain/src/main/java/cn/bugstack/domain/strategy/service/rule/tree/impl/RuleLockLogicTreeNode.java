package cn.bugstack.domain.strategy.service.rule.tree.impl;

import cn.bugstack.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.bugstack.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import cn.bugstack.domain.strategy.service.rule.tree.ILogicTreeNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author:ts
 * @date:2024/12/20 9:13
 */
@Slf4j
@Component("rule_lock")
public class RuleLockLogicTreeNode implements ILogicTreeNode {
    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId) {
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.ALLOW)
                .build();
    }
}
