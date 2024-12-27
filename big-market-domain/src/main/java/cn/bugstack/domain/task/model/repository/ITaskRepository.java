package cn.bugstack.domain.task.model.repository;

import cn.bugstack.domain.task.model.entity.TaskEntity;

import java.util.List;

/**
 * @author: ts
 * @description
 * @create: 2024/12/27 10:10
 */
public interface ITaskRepository {
    List<TaskEntity> queryNoSendMessageTaskList();

    void sendMessage(TaskEntity taskEntity);

    void updateTaskSendMessageCompleted(String userId, String messageId);

    void updateTaskSendMessageFail(String userId, String messageId);
}
