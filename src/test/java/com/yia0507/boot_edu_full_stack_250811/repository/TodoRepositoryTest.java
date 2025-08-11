package com.yia0507.boot_edu_full_stack_250811.repository;

import com.yia0507.boot_edu_full_stack_250811.domain.Todo;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
;import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Log4j2
@SpringBootTest
class TodoRepositoryTest {
    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void insert() {
        IntStream.rangeClosed(1,100).forEach(i -> {
            Todo todo = Todo.builder()
                    .title("title"+ i)
                    .writer("user00")
                    .dueDate(LocalDate.of(2025, 12, 31))
                    .build();
            todoRepository.save(todo);
        });
    }
    @Test
    public void findOne() {
        Long tno = 100L;
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        log.info(todo.toString());
    }

    @Test
    public void update() {
        Long tno = 100L;
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();

        todo.change("updated title", true,LocalDate.now());
        log.info(todoRepository.save(todo));
    }

    @Test
    public void delete() {
        Long tno = 90L;
        todoRepository.deleteById(tno);
    }

    @Test
    public void testPaging(){
        Pageable pageable = PageRequest.of(2, 10, Sort.by("tno").descending());
        Page<Todo> result = todoRepository.findAll(pageable);

        List<Todo> todoList = result.getContent();
        todoList.forEach(todo->log.info(todo.toString()));
    }
}