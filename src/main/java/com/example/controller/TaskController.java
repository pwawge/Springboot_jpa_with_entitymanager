package com.example.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Task;
import com.example.repository.TaskRepository;

@RestController
public class TaskController {
	private static Logger log = Logger.getLogger("Solution");
	
	@Autowired
	private TaskRepository taskRepository;
	
	@PutMapping(name = "/tasks/{id}",consumes = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<?> updateTask(@RequestBody Task task,@PathVariable Long id){
		log.info("control in updateTask");
		Map<String,Object> responseMap = new HashMap<String,Object>();
		
		if(task!=null && (task.getDescription()==null || task.getDescription().isEmpty()) ) {
			
			responseMap.put("message", "Task description is required");
			responseMap.put("status", HttpStatus.BAD_REQUEST.value());			
			
			return new ResponseEntity<>(responseMap,HttpStatus.BAD_REQUEST);
			
		}
		
		Optional<Task> existTask = taskRepository.findById(id);
		if(existTask ==null || !existTask.isPresent()) {
			responseMap.put("message", "Cannot find task with given id");
			responseMap.put("status", HttpStatus.NOT_FOUND.value());	
			return new ResponseEntity<>(responseMap,HttpStatus.NOT_FOUND);			
		}
		task.setId(id);
		taskRepository.save(task);
		
		responseMap.put("description", task.getDescription());
		responseMap.put("priority", task.getPriority());
		
		return new ResponseEntity<>(responseMap,HttpStatus.OK);
		
	}

}
