package com.example.demo.mapper;

import com.example.demo.dto.TodoDTO;
import com.example.demo.model.Todo;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-27T16:35:46+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class TodoMapperImpl implements TodoMapper {

    @Override
    public Todo todoDTOtoEntity(TodoDTO todoDTO) {
        if ( todoDTO == null ) {
            return null;
        }

        Todo todo = new Todo();

        todo.setId( todoDTO.getId() );
        todo.setTitle( todoDTO.getTitle() );
        todo.setDescription( todoDTO.getDescription() );
        todo.setCompleted( todoDTO.isCompleted() );

        return todo;
    }
}
