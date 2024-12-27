package cn.bugstack.domain.task.service;

import cn.bugstack.domain.task.model.entity.TaskEntity;

import java.util.List;

/**
 * @author: ts
 * @description
 * @create: 2024/12/27 9:58
 */
public interface ITaskService {
    List<TaskEntity> queryNoSendMessageTaskList();

    void sendMessage(TaskEntity taskEntity);

    void updateTaskSendMessageCompleted(String userId, String messageId);

    void updateTaskSendMessageFail(String userId, String messageId);
}
