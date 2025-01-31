package com.javanauta.taskscheduler.controller;

import com.javanauta.taskscheduler.business.TaskService;
import com.javanauta.taskscheduler.business.dto.TaskDTO;
import com.javanauta.taskscheduler.insfrastruture.enums.NotificationStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO,
                                                @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(taskService.saveTask(token, taskDTO));
    }

    @GetMapping("/events")
    public ResponseEntity<List<TaskDTO>> findListTaskByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dateCreated,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dateUpdated,
            @RequestParam NotificationStatusEnum statusEnum
    ){

        return ResponseEntity.ok(taskService.findTaskSchedulerByPeriod(
                dateCreated, dateUpdated, statusEnum
        ));
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findListByEmail(
            @RequestHeader("Authorization") String token){
        List<TaskDTO> tasks = taskService.findTasksByEmail(token);

        return ResponseEntity.ok(tasks);
    }

}
