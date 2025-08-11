package com.yia0507.boot_edu_full_stack_250811.controller;

import com.yia0507.boot_edu_full_stack_250811.domain.PageRequestDTO;
import com.yia0507.boot_edu_full_stack_250811.domain.PageResponseDTO;
import com.yia0507.boot_edu_full_stack_250811.dto.TodoDTO;
import com.yia0507.boot_edu_full_stack_250811.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/{tno}")
    public TodoDTO getOne(@PathVariable("tno") Long tno){
        TodoDTO todoDTO = todoService.get(tno);
        return todoDTO;
    }


    @GetMapping("/list")
    public PageResponseDTO<TodoDTO> getList(@RequestParam(name="page", defaultValue = "1") int page,
                                            @RequestParam(name="size", defaultValue = "10") int size) {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(page)
                .size(size)
                .build();

        PageResponseDTO<TodoDTO> responseDTO = todoService.getList(pageRequestDTO);
        return responseDTO;
    }

}
