package com.javanauta.taskscheduler.business.mapper;

import com.javanauta.taskscheduler.business.dto.TaskDTO;
import com.javanauta.taskscheduler.insfrastruture.entity.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskConverter {

    TaskEntity toTaskEntity(TaskDTO taskDTO);

    TaskDTO toTaskDTO(TaskEntity taskEntity);

}
