package cn.bugstack.domain.strategy.service.rule.tree;

import cn.bugstack.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * @author:ts
 * @date:2024/12/20 9:11
 */
public interface ILogicTreeNode {
    DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId);
}
