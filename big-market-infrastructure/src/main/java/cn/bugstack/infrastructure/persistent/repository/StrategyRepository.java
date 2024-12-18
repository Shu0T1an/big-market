package cn.bugstack.infrastructure.persistent.repository;

import cn.bugstack.domain.strategy.model.entity.StrategyAwardEntity;
import cn.bugstack.domain.strategy.model.entity.StrategyEntity;
import cn.bugstack.domain.strategy.model.entity.StrategyRuleEntity;
import cn.bugstack.domain.strategy.repository.IStrategyRepository;
import cn.bugstack.infrastructure.persistent.dao.IStrategyAwardDao;
import cn.bugstack.infrastructure.persistent.dao.IStrategyDao;
import cn.bugstack.infrastructure.persistent.dao.IStrategyRuleDao;
import cn.bugstack.infrastructure.persistent.po.Strategy;
import cn.bugstack.infrastructure.persistent.po.StrategyAward;
import cn.bugstack.infrastructure.persistent.po.StrategyRule;
import cn.bugstack.infrastructure.persistent.redis.IRedisService;
import cn.bugstack.types.common.Constants;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Repository
public class StrategyRepository implements IStrategyRepository {
    @Resource
    private IStrategyAwardDao strategyAwardDao;
    @Resource
    private IRedisService redisService;
    @Resource
    private IStrategyDao strategyDao;
    @Resource
    private IStrategyRuleDao strategyRuleDao;
    @Override
    public List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId) {
        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_KEY + strategyId;
        List<StrategyAwardEntity> strategyAwardEntities  = redisService.getValue(cacheKey);
        if(strategyAwardEntities != null && strategyAwardEntities.isEmpty()) return strategyAwardEntities;

        List<StrategyAward> strategyAwards = strategyAwardDao.queryStrategyAwardListByStrategyId(strategyId);
        strategyAwardEntities = new ArrayList<>(strategyAwards.size());
        for(StrategyAward strategyAward : strategyAwards){
             StrategyAwardEntity strategyAwardEntity = StrategyAwardEntity.builder()
                          .strategyId(strategyAward.getStrategyId())
                          .awardId(strategyAward.getAwardId())
                          .awardCount(strategyAward.getAwardCount())
                          .awardCountSurplus(strategyAward.getAwardCountSurplus())
                          .awardRate(strategyAward.getAwardRate())
                          .build();
            strategyAwardEntities.add(strategyAwardEntity);
        }

        redisService.setValue(cacheKey,strategyAwardEntities);
        return strategyAwardEntities;

    }

    @Override
    public void storeStrategyAwardSearchRateTable(String key, Integer rateRange, Map<Integer, Integer> shuffleStrategyAwardSearchRateTable) {
        // 根据策略ID 将概率范围的大小 持久化到redis中
        redisService.setValue(Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + key,rateRange);
        Map<Integer,Integer> cacheRateTable = redisService.getMap(Constants.RedisKey.STRATEGY_RATE_TABLE_KEY + key);
        cacheRateTable.putAll(shuffleStrategyAwardSearchRateTable);
    }

    @Override
    public int getRateRange(Long strategyId) {
      return getRateRange(String.valueOf(strategyId));
    }

    @Override
    public Integer getStrategyAwardAssemble(String key, int ratekey) {
        return redisService.getFromMap(Constants.RedisKey.STRATEGY_RATE_TABLE_KEY + key,ratekey);
    }

    @Override
    public StrategyEntity queryStrategyEntityByStrategyId(Long strategyId) {


        String cacheKey = Constants.RedisKey.STRATEGY_KEY + strategyId;
        StrategyEntity strategyEntity = redisService.getValue(cacheKey);
        if(strategyEntity != null) return strategyEntity;

        Strategy strategy = strategyDao.queryStrategyEntityByStrategyId(strategyId);
        strategyEntity =  StrategyEntity.builder()
                  .strategyId(strategy.getStrategyId())
                  .strategyDesc(strategy.getStrategyDesc())
                  .ruleModels(strategy.getRuleModels())
                  .build();

        redisService.setValue(cacheKey,strategyEntity);
        return strategyEntity;
    }

    @Override
    public StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel) {
        StrategyRule strategyRuleReq = new StrategyRule();
        strategyRuleReq.setStrategyId(strategyId);
        strategyRuleReq.setRuleModel(ruleModel);
        StrategyRule strategyRuleRes = strategyRuleDao.queryStrategyRule(strategyRuleReq);
        return StrategyRuleEntity.builder()
                .strategyId(strategyRuleRes.getStrategyId())
                .awardId(strategyRuleRes.getAwardId())
                .ruleType(strategyRuleRes.getRuleType())
                .ruleModel(strategyRuleRes.getRuleModel())
                .ruleValue(strategyRuleRes.getRuleValue())
                .ruleDesc(strategyRuleRes.getRuleDesc())
                .build();
    }

    @Override
    public int getRateRange(String key) {
        return  redisService.getValue(Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + key);

    }

    @Override
    public String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel) {
        StrategyRule strategyRule = new StrategyRule();
        strategyRule.setStrategyId(strategyId);
        strategyRule.setAwardId(awardId);
        strategyRule.setRuleModel(ruleModel);
        return strategyRuleDao.queryStrategyRuleValue(strategyRule);
    }


}
