package com.yia0507.boot_edu_full_stack_250811.domain;

import jakarta.persistence.*;
        import lombok.*;

        import java.time.LocalDate;

@Entity
@Table(name = "tbl_todo")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

    private String title;

    private String writer;

    private boolean complete;

    private LocalDate dueDate;

    public void change(String title, boolean complete, LocalDate dueDate) {
        this.title = title;
        this.complete = complete;
        this.dueDate = dueDate;
    }
}