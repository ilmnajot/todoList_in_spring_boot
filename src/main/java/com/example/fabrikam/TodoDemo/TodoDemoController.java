package com.example.fabrikam.TodoDemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/todos")
public class TodoDemoController {

    private final TodoItemRepository repository;

    public TodoDemoController(TodoItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String index(Model model) {
        ArrayList<TodoItem> todoList = (ArrayList<TodoItem>) repository.findAll();
        model.addAttribute("newitem", new TodoItem());
        model.addAttribute("items", new TodoListViewModel(todoList));
        return "index";
    }

    @PostMapping("/add")
    public String addTodo(@ModelAttribute TodoItem requestItem) {
        TodoItem item = new TodoItem(requestItem.getCategory(), requestItem.getName());
        repository.save(item);
        return "redirect:/";
    }

    @PutMapping("/update")
    public String updateTodo(@ModelAttribute TodoListViewModel requestItems) {
        for (TodoItem requestItem : requestItems.getTodoList() ) {
             TodoItem item = new TodoItem(requestItem.getCategory(), requestItem.getName());
             item.setComplete(requestItem.isComplete());
             item.setId(requestItem.getId());
             repository.save(item);
        }
        return "redirect:/";
    }
}
