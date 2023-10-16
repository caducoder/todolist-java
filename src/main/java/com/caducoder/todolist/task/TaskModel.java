package com.caducoder.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "tb_tasks")
public class TaskModel {

	@Id @GeneratedValue(generator = "UUID")
	private UUID id;
	private String description;
	
	@Column(length = 50)
	private String title;
	private LocalDateTime start_at;
	private LocalDateTime end_at;
	private String priority;
	
	@CreationTimestamp
	private LocalDateTime created_at;
	
	private UUID idUser;
	
	public TaskModel() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) throws Exception {
		if(title.length() > 50) {
			throw new IllegalArgumentException("Título não pode ser maior que 50 caracteres.");
		}
		this.title = title;
	}

	public LocalDateTime getStart_at() {
		return start_at;
	}

	public void setStart_at(LocalDateTime start_at) {
		this.start_at = start_at;
	}

	public LocalDateTime getEnd_at() {
		return end_at;
	}

	public void setEnd_at(LocalDateTime end_at) {
		this.end_at = end_at;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public UUID getIdUser() {
		return idUser;
	}

	public void setIdUser(UUID idUser) {
		this.idUser = idUser;
	}
	
	
}
