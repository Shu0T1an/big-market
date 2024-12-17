package cn.bugstack.domain.strategy.service.armory;

import cn.bugstack.domain.strategy.model.entity.StrategyAwardEntity;
import cn.bugstack.domain.strategy.repository.IStrategyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Slf4j
@Service
public class StrategyArmory implements IStrategyArmory{
    @Resource
    IStrategyRepository repository;
    @Override
    public void assembleLotteryStrategy(Long strategyId) {
        // 查询策略配置
        List<StrategyAwardEntity> strategyAwardEntities  = repository.queryStrategyAwardList(strategyId);
        log.info("查询策略完毕");
        // 获取最小的概率
        BigDecimal minAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
        log.info("最小的概率为：{}", minAwardRate);
        //获取概率值的总和
        BigDecimal totalAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        log.info("概率值的总和：{}", totalAwardRate);
        // 获取概率范围  =  概率总和/最小概率
        BigDecimal rateRange = totalAwardRate.divide(minAwardRate,0, RoundingMode.CEILING);
        log.info("概率范围为：{}", rateRange);
        // 生成概率表
        ArrayList<Integer> strategyAwardSearchRateTables = new ArrayList<>(rateRange.intValue());
        for(StrategyAwardEntity strategyAward: strategyAwardEntities){
            Integer awardId = strategyAward.getAwardId(); // 奖品ID
            BigDecimal awardRate = strategyAward.getAwardRate(); // 奖品概率
            // 为每一个奖品生成对应数量的概率区间 等于 奖品概率 * 概率范围 ，次数为计算出的概率区间数量。
            for(int i = 0; i < rateRange.multiply(awardRate).setScale(0, RoundingMode.CEILING).intValue();i++){
                strategyAwardSearchRateTables.add(awardId);
            }
        }
        // 打乱集合
        Collections.shuffle(strategyAwardSearchRateTables);
        log.info("打乱集合");
        // 存储到Map中
        Map<Integer, Integer> shuffleStrategyAwardSearchRateTable = new LinkedHashMap<>();
        for (int i = 0; i < strategyAwardSearchRateTables.size(); i++) {
            shuffleStrategyAwardSearchRateTable.put(i, strategyAwardSearchRateTables.get(i));
        }

        // 存储到Redis中 持久化
        repository.storeStrategyAwardSearchRateTable(strategyId,shuffleStrategyAwardSearchRateTable.size(),shuffleStrategyAwardSearchRateTable);
        log.info("已经持久化");
    }

    @Override
    public Integer getRandomAwardId(Long strategyId) {
        int rateRange = repository.getRateRange(strategyId);
        return repository.getStrategyAwardAssemble(strategyId,new Random().nextInt(rateRange));
    }
}
