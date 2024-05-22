package com.example.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SearchCriteria;
import com.example.demo.dto.TodoDTO;
import com.example.demo.exception.GlobalExceptionHandler.TodoNotFoundException;
import com.example.demo.mapper.TodoMapper;
import com.example.demo.model.Todo;
import com.example.demo.model.User;
import com.example.demo.repository.TodoRepository;
import com.example.demo.repository.UserRepository;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepo;	
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TodoMapper todoMapper;
	
	/*
	public String addTodo(Todo todo) {
		
		try {
		Todo todo1 = todoRepo.save(todo);
		
		if(todo1 != null) {
			return "Todo created Succesfully";
		} else {
			throw new RuntimeException("failed to save todo");
		}
		} catch(Exception e) {
			throw new RuntimeException("something went wrong " + e.getMessage());
		}
	}  */
	
	/*
	public String addTodo(TodoDTO todoDTO) {
	    try {
	        Todo todo = todoMapper.todoDTOtoEntity(todoDTO);
	        Todo savedTodo = todoRepo.save(todo);
	        
	        if (savedTodo != null) {
	            return "Todo created successfully";
	        } else {
	            throw new RuntimeException("Failed to save todo");
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Something went wrong: " + e.getMessage());
	    }
	} */
	
	
	
	public String addTodo(int userId , TodoDTO todoDTO) {
		
		try {
		
		User user = userRepo.findById(userId);
		
		Todo todo = todoMapper.todoDTOtoEntity(todoDTO);
		
		todo.setUser(user);
		
		todoRepo.save(todo);
		
		if (todo != null) {
	            return "Todo created successfully";
	        } else {
	            throw new RuntimeException("Failed to save todo");
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Something went wrong: " + e.getMessage());
	    }
		
				
	} 



	
	/*
	public String addTodo(Todo todo) {
	    try {
	        // Extract userId from the todo object
	        int userId = todo.getUser().getId(); // Assuming user is already set in the Todo object
	        
	        // Retrieve the user object from the database using userId
	        User user = userRepo.findById(userId);
	        
	        // Check if user is found
	        if (user == null) {
	            throw new RuntimeException("User not found with id: " + userId);
	        }
	        
	        // Set the user object in the todo
	        todo.setUser(user);
	        
	        // Save the todo to the database
	        Todo savedTodo = todoRepo.save(todo);
	        
	        if (savedTodo != null) {
	            return "Todo created successfully";
	        } else {
	            throw new RuntimeException("Failed to save todo");
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Something went wrong: " + e.getMessage());
	    }
	} */

	
	
	
	 public String deleteTodo(int id) {
	        try {
	            todoRepo.deleteById(id);
	            return "Todo Deleted Successfully";
	        } catch (Exception e) {
	            throw new RuntimeException("Failed to delete todo with id "  + e.getMessage());
	        }
	    }
	
	
	
	 public List<Todo> getAllTodo(int userId) {
	        try {
	            return todoRepo.findByUserId(userId);
	        } catch (Exception e) {
	            throw new RuntimeException("Failed to retrieve todos: " + e.getMessage());
	        }
	    }
	
	/*
	public Page<Todo> getAllTodo(Pageable pageable) {
		return todoRepo.findAll(pageable);
	} */
	
	
	 public Optional<Todo> getTodoById(int id) {
	        Optional<Todo> todo = todoRepo.findById(id);
	        if (todo.isEmpty()) {
	            throw new TodoNotFoundException("Todo not found with ID: " + id);
	        }
	        return todo;
	    }

	 
	 
	 public String updateTodo(int id, Todo todo) {
	        try {
	            todo.setId(id);
	            Todo updatedTodo = todoRepo.save(todo);
	            if (updatedTodo != null) {
	                return "Todo updated Successfully";
	            } else {
	                throw new RuntimeException("Failed to update todo with id " + id);
	            }
	        } catch (Exception e) {
	            throw new RuntimeException("Something went wrong while updating todo " + e.getMessage());
	        }
	    }
	 
	 /*
	// Service Layer
	 public List<Todo> searchTodos(Integer id, String title, String description, Boolean completed) {
	     return todoRepo.findBySearchCriteria(id, title, description, completed);
	 } */
	 
	 
	 public List<Todo> searchTodos(Integer userId, Integer id, String title, String description, Boolean completed) {
	        try {
	            
	            List<Todo> userTodos = todoRepo.findByUserId(userId);
	            
	            if (id == null && title == null && description == null && completed == null) {
	                return userTodos;
	            }
	            
	            return todoRepo.findBySearchCriteria(id, title, description, completed);
	        } catch (Exception e) {
	            throw new RuntimeException("Failed to search todos: " + e.getMessage());
	        }
	    } 
	 
	 
	 /*
	 public List<Todo> searchTodos(SearchCriteria searchCriteria) {
		    try {
		        List<Todo> userTodos = todoRepo.findByUserId(searchCriteria.getUserId());
		        
		        if (searchCriteria.isEmpty()) {
		            return userTodos;
		        }
		        
		        return todoRepo.findBySearchCriteria(searchCriteria);
		    } catch (Exception e) {
		        throw new RuntimeException("Failed to search todos: " + e.getMessage());
		    }
		}
		
	*/


	 
}
