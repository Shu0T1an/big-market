package cn.bugstack.test.domain;

import cn.bugstack.domain.strategy.service.armory.IStrategyArmory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyArmory {
    @Resource
    private IStrategyArmory strategyArmory;
    @Test
    public void test_startegyArmory() {
        strategyArmory.assembleLotteryStrategy(100001L);
    }

    @Test
    public void test_getRandomAwardId() {

        for (int i = 0; i  < 100;i++) {
            log.info("测试结果：{}", strategyArmory.getRandomAwardId(100001L));
        }

    }
}
