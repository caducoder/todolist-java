package com.caducoder.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

	@Autowired
	private ITaskRepository taskRepository;
	
	public TaskModel create(TaskModel taskModel, UUID userId) {
		taskModel.setIdUser(userId);
		
		LocalDateTime currentDate = LocalDateTime.now();
		if(currentDate.isAfter(taskModel.getStart_at()) || currentDate.isAfter(taskModel.getEnd_at())) {
			throw new IllegalArgumentException("A data de início / data de término de ser maior que a data atual.");
		}
		
		if(taskModel.getStart_at().isAfter(taskModel.getEnd_at())) {
			throw new IllegalArgumentException("A data de início deve ser antes que a data de término.");
		}
		
		return taskRepository.save(taskModel);
	}
	
	public List<TaskModel> findByIdUser(UUID userId) {
		return taskRepository.findByIdUser(userId);
	}
}
