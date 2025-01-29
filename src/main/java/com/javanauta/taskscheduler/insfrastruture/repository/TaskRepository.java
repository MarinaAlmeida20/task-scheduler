package com.javanauta.taskscheduler.insfrastruture.repository;

import com.javanauta.taskscheduler.insfrastruture.entity.TaskEntity;
import com.javanauta.taskscheduler.insfrastruture.enums.NotificationStatusEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<TaskEntity, String> {

    List<TaskEntity> findByDateEventBetweenAndNotificationStatusEnum(LocalDateTime startDate,
                                                                     LocalDateTime finalDate,
                                                                     NotificationStatusEnum status);

    List<TaskEntity> findByUserEmail(String email);
}
