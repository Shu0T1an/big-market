package cn.bugstack.domain.task.service;

import cn.bugstack.domain.task.model.entity.TaskEntity;
import cn.bugstack.domain.task.model.repository.ITaskRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author: ts
 * @description
 * @create: 2024/12/27 9:58
 */
@Service
public class TaskService implements ITaskService{
    @Resource
    private ITaskRepository taskRepository;
    @Override
    public List<TaskEntity> queryNoSendMessageTaskList() {
        return taskRepository.queryNoSendMessageTaskList();
    }

    @Override
    public void sendMessage(TaskEntity taskEntity) {
        taskRepository.sendMessage(taskEntity);
    }

    @Override
    public void updateTaskSendMessageCompleted(String userId, String messageId) {
        taskRepository.updateTaskSendMessageCompleted(userId,messageId);
    }

    @Override
    public void updateTaskSendMessageFail(String userId, String messageId) {
        taskRepository.updateTaskSendMessageFail(userId,messageId);
    }
}
