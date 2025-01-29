package com.javanauta.taskscheduler.insfrastruture.entity;

import com.javanauta.taskscheduler.insfrastruture.enums.NotificationStatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document("task")
public class TaskEntity {

    @Id
    private String id;
    private String taskName;
    private String description;
    private String userEmail;
    private LocalDateTime dateCreated;
    private LocalDateTime dateEvent;
    private LocalDateTime dateUpdated;
    private NotificationStatusEnum notificationStatusEnum;
}
