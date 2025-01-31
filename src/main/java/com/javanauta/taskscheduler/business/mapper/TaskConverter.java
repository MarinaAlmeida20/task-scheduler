package com.javanauta.taskscheduler.business.mapper;

import com.javanauta.taskscheduler.business.dto.TaskDTO;
import com.javanauta.taskscheduler.insfrastruture.entity.TaskEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskConverter {

    TaskEntity toTaskEntity(TaskDTO taskDTO);

    TaskDTO toTaskDTO(TaskEntity taskEntity);

    List<TaskEntity> toListTaskEntity(List<TaskDTO> dtos);

    List<TaskDTO> toListTaskDTO(List<TaskEntity> entities);
}
