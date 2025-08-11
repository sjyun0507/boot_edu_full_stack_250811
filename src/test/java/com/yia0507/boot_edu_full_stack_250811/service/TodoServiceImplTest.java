package com.yia0507.boot_edu_full_stack_250811.service;

import com.yia0507.boot_edu_full_stack_250811.domain.PageRequestDTO;
import com.yia0507.boot_edu_full_stack_250811.domain.PageResponseDTO;
import com.yia0507.boot_edu_full_stack_250811.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@Log4j2
@SpringBootTest
class TodoServiceImplTest {
    @Autowired
    private TodoService todoService;

    @Test
    void add() {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("test")
                .writer("tester")
                .dueDate(LocalDate.of(2025,12,1))
                .build();
             log.info(todoService.add(todoDTO));
    }

    @Test
    void get(){
        Long tno = 100L;
        log.info(todoService.get(tno));
    }

    @Test
    void modify(){
        Long tno = 100L;
        TodoDTO todo = TodoDTO.builder()
                .tno(tno)
                .title("test")
                .complete(false)
                .dueDate(LocalDate.of(2025,12,1))
                .build();
        todoService.modify(todo);
    }

    @Test
    void remove() {
        Long bno = 101L;
        todoService.remove(bno);
    }

    @Test
    void getList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(2)
                .size(10)
                .build();
        PageResponseDTO<TodoDTO> pageResponseDTO = todoService.getList(pageRequestDTO);
        pageResponseDTO.getDtoList().forEach(log::info);
    }
}











