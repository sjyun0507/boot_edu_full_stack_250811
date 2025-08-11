package com.yia0507.boot_edu_full_stack_250811.repository;

import com.yia0507.boot_edu_full_stack_250811.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
