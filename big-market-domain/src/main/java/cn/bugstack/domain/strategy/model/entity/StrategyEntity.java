package cn.bugstack.domain.strategy.model.entity;

import cn.bugstack.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class StrategyEntity {
    /**  抽奖策略ID */
    private Long strategyId;
    /**  抽奖描述*/
    private String strategyDesc;

    private String ruleModels;

    public String[] ruleModes(){
        if(StringUtils.isBlank(ruleModels)) return null;
        return ruleModels.split(Constants.SPLIT);
    }

    public String getRuleWeight(){
        String[] ruleModes = this.ruleModes();
        for(String ruleModel: ruleModes){
            if("rule_weight".equals(ruleModel)) return ruleModel;
        }
        return null;
    }
}
