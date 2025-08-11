package com.yia0507.boot_edu_full_stack_250811.service;


import com.yia0507.boot_edu_full_stack_250811.domain.PageRequestDTO;
import com.yia0507.boot_edu_full_stack_250811.domain.PageResponseDTO;
import com.yia0507.boot_edu_full_stack_250811.domain.Todo;
import com.yia0507.boot_edu_full_stack_250811.dto.TodoDTO;
import com.yia0507.boot_edu_full_stack_250811.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long add(TodoDTO todoDTO) {
        Todo todo = modelMapper.map(todoDTO, Todo.class);
        return todoRepository.save(todo).getTno();
    }

    @Override
    public TodoDTO get(Long tno) {
        return modelMapper.map(todoRepository.findById(tno).orElseThrow(), TodoDTO.class);
    }

    @Override
    public void modify(TodoDTO todoDTO) {
        Optional<Todo> todoOptional = todoRepository.findById(todoDTO.getTno());
        Todo todo = todoOptional.orElseThrow();

        todo.change(todoDTO.getTitle(), todoDTO.isComplete(), todoDTO.getDueDate());
        todoRepository.save(todo);
    }

    @Override
    public void remove(Long tno) {
        todoRepository.deleteById(tno);
    }

    @Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage()-1,
                pageRequestDTO.getSize(),
                Sort.by("tno").descending());
        Page<Todo> result = todoRepository.findAll(pageable);

        List<TodoDTO> dtoList = result.getContent().stream().map(
                todo->modelMapper.map(todo,TodoDTO.class)
        ).toList();

        long totalCount = result.getTotalElements();

        PageResponseDTO<TodoDTO> responseDTO = PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(totalCount)
                .build();
        return responseDTO;
    }
}
