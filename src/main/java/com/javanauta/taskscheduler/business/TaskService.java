package com.javanauta.taskscheduler.business;

import com.javanauta.taskscheduler.business.dto.TaskDTO;
import com.javanauta.taskscheduler.business.mapper.TaskConverter;
import com.javanauta.taskscheduler.business.mapper.TaskUpdateConverter;
import com.javanauta.taskscheduler.insfrastruture.entity.TaskEntity;
import com.javanauta.taskscheduler.insfrastruture.enums.NotificationStatusEnum;
import com.javanauta.taskscheduler.insfrastruture.exceptions.ResourceNotFoundException;
import com.javanauta.taskscheduler.insfrastruture.repository.TaskRepository;
import com.javanauta.taskscheduler.insfrastruture.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;
    private final JwtUtil jwtUtil;
    private final TaskUpdateConverter taskUpdateConverter;

    public TaskDTO saveTask(String token, TaskDTO taskDTO) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        taskDTO.setDateCreated(LocalDateTime.now());
        taskDTO.setNotificationStatusEnum(NotificationStatusEnum.PENDENT);
        taskDTO.setUserEmail(email);

        TaskEntity taskEntity = taskConverter.toTaskEntity(taskDTO);

        return taskConverter.toTaskDTO(taskRepository.save(taskEntity));
    }

    public List<TaskDTO> findTaskSchedulerByPeriod(LocalDateTime startDate,
                                                   LocalDateTime endDate,
                                                   NotificationStatusEnum statusEnum) {
        return taskConverter.toListTaskDTO(
                taskRepository.findByDateEventBetweenAndNotificationStatusEnum(
                        startDate, endDate, statusEnum
                ));
    }

    public List<TaskDTO> findTasksByEmail(String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        List<TaskEntity> taskEntityList = taskRepository.findByUserEmail(email);

        return taskConverter.toListTaskDTO(taskEntityList);
    }

    public void deleteTaskById(String id) {
        try {
            taskRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException
                    ("Error while deleting task by id, id not found ", e.getCause());
        }
    }

    public TaskDTO updateStatus(NotificationStatusEnum statusEnum, String id){
        try {
            TaskEntity entity = taskRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Task not found " + id));

            entity.setNotificationStatusEnum(statusEnum);
            return taskConverter.toTaskDTO(taskRepository.save(entity));
        }catch (ResourceNotFoundException e){
            throw  new ResourceNotFoundException
                    ("Error on update status task " + e.getCause());
        }
    }

    public TaskDTO updateTask(TaskDTO dto, String id){
        try {
            TaskEntity entity = taskRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Task not found " + id));

            taskUpdateConverter.updateTasks(dto, entity);
            return taskConverter.toTaskDTO(taskRepository.save(entity));

        }catch (ResourceNotFoundException e){
            throw  new ResourceNotFoundException
                    ("Error on update task " + e.getCause());
        }
    }
}
