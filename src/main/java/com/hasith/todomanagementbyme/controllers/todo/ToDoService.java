package com.hasith.todomanagementbyme.controllers.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ToDoService {
    private static List<Todo> todos = new ArrayList<>();
    private static int todoCount = 0;

    static {
        todos.add(new Todo(++todoCount, "hasith", "-Learn Springboot", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todoCount, "hasith", "- Learn AWS", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++todoCount, "hasith", "- Learn Artificial Intelligence", LocalDate.now().plusYears(3), false));
    }

//    public List<ToDo> findByName(String username) {
//        return toDos;
//    }

    public List<Todo> findByUsername(String username){
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(getLoggedInUsername());
        return todos.stream().filter(predicate).toList();
    }

    public void addToDo(String username, String description, LocalDate targetDate, boolean done) {
        Todo toDo = new Todo(++todoCount, username, description, targetDate, done);
        todos.add(toDo);
    }

    public void deleteByid(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        Todo toDo = todos.stream().filter(predicate).findFirst().get();
        System.out.println(toDo);
        return toDo;
    }

    public void updateTodo(@Valid Todo toDo) {
        deleteByid(toDo.getId());
        todos.add(toDo);
    }

    private String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
