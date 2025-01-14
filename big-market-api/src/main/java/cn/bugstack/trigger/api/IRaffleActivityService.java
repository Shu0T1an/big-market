package cn.bugstack.trigger.api;

import cn.bugstack.trigger.api.dto.*;
import cn.bugstack.trigger.api.response.Response;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: ts
 * @description
 * @create: 2024/12/27 14:57
 */
public interface IRaffleActivityService {

    /**
     * 活动装配，数据预热缓存
     * @param activityId 活动ID
     * @return 装配结果
     */
    Response<Boolean> armory(Long activityId);

    /**
     * 活动抽奖接口
     * @param request 请求对象
     * @return 返回结果
     */
    Response<ActivityDrawResponseDTO> draw(ActivityDrawRequestDTO request);


    @RequestMapping(value = "calendar_sign_rebate", method = RequestMethod.POST)
    Response<Boolean> calendarSignRebate(@RequestParam String userId);

    @RequestMapping(value = "is_calendar_sign_rebate", method = RequestMethod.POST)
    Response<Boolean> isCalendarSignRebate(String userId);

    @RequestMapping(value = "query_user_activity_account", method = RequestMethod.POST)
    Response<UserActivityAccountResponseDTO> queryUserActivityAccount(UserActivityAccountRequestDTO request);

    /**
     * 查询sku商品集合
     *
     * @param activityId 活动ID
     * @return 商品集合
     */
    Response<List<SkuProductResponseDTO>> querySkuProductListByActivityId(Long activityId);

    /**
     * 查询用户积分值
     *
     * @param userId 用户ID
     * @return 可用积分
     */
    Response<BigDecimal> queryUserCreditAccount(String userId);

    /**
     * 积分支付兑换商品
     *
     * @param request 请求对象「用户ID、商品ID」
     * @return 兑换结果
     */
    Response<Boolean> creditPayExchangeSku(SkuProductShopCartRequestDTO request);

}
