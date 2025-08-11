package com.yia0507.boot_edu_full_stack_250811.service;

import com.yia0507.boot_edu_full_stack_250811.domain.PageRequestDTO;
import com.yia0507.boot_edu_full_stack_250811.domain.PageResponseDTO;
import com.yia0507.boot_edu_full_stack_250811.dto.TodoDTO;

public interface TodoService {
    Long add(TodoDTO todoDTO);
    TodoDTO get(Long tno);
    void modify(TodoDTO todoDTO);
    void remove(Long tno);

    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);

}
