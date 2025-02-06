package com.javanauta.taskscheduler.business.mapper;

import com.javanauta.taskscheduler.business.dto.TaskDTO;
import com.javanauta.taskscheduler.insfrastruture.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskUpdateConverter {

    //              if TaskDTO is null, then TaskEntity will be the main
    void updateTasks(TaskDTO dto, @MappingTarget TaskEntity entity);
}
