package cn.bugstack.domain.rebate.model.entity;

import cn.bugstack.domain.rebate.model.valobj.TaskStateVO;
import cn.bugstack.types.event.BaseEvent;
import cn.bugstack.domain.rebate.event.SendRebateMessageEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ts
 * @description
 * @create: 2024/12/30 10:32
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {
    /** 活动ID */
    private String userId;
    /** 消息主题 */
    private String topic;
    /** 消息编号 */
    private String messageId;
    /** 消息主体 */
    private BaseEvent.EventMessage<SendRebateMessageEvent.RebateMessage> message;
    /** 任务状态；create-创建、completed-完成、fail-失败 */
    private TaskStateVO state;
}
