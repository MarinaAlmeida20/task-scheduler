package com.javanauta.taskscheduler.controller;

import com.javanauta.taskscheduler.business.TaskService;
import com.javanauta.taskscheduler.business.dto.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
