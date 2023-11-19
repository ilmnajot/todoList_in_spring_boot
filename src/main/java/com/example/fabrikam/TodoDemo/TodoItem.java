package com.example.fabrikam.TodoDemo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoItem {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String category;
    private String name;
    private boolean complete;

    public TodoItem(String category, String name) {
        this.category   = category;
        this.name = name;
    }
}