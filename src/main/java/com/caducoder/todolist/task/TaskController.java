package com.caducoder.todolist.task;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;

	@PostMapping
	public ResponseEntity<TaskModel> createTask(@RequestBody TaskModel taskModel, HttpServletRequest req) {
		UUID userId = (UUID) req.getAttribute("idUser");
		return ResponseEntity.ok(taskService.create(taskModel, userId));
	}
	
	@GetMapping
	public ResponseEntity<List<TaskModel>> list(HttpServletRequest req){
		UUID userId = (UUID) req.getAttribute("idUser");
		return ResponseEntity.ok(taskService.findByIdUser(userId));
	}
}
