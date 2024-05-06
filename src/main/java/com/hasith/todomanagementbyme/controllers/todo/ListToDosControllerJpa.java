package com.hasith.todomanagementbyme.controllers.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class ListToDosControllerJpa {

    private final ToDoService toDoService;

    private final TodoRepository todoRepository;

    public ListToDosControllerJpa(ToDoService toDoService, TodoRepository todoRepository) {
        this.toDoService = toDoService;
        this.todoRepository = todoRepository;
    }

    @RequestMapping(path = "list-todos")
    public String listTodos(ModelMap modelMap) {
        modelMap.put("todos", todoRepository.findByUsername(getLoggedInUsername()));
        return "list-todos";
    }

    @RequestMapping(path = "add-todo")
    public String addNewTodo(ModelMap modelMap) {
        Todo toDo = new Todo(0, (String) modelMap.get("username"), "", LocalDate.now().plusYears(1), false);
        modelMap.put("todo", toDo);
        return "add-todo";
    }

    @RequestMapping(path = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result) {

        if (result.hasErrors()) {
            return "add-todo";
        }
//        Adding to Using JPA with Todo Object
//        toDoService.addToDo(getLoggedInUsername(), toDo.getDescription(), toDo.getTargetDate(), toDo.isDone());

        todo.setUsername(getLoggedInUsername());
        todoRepository.save(todo);
        return "redirect:list-todos";
    }

    @RequestMapping(path = "delete-todo")
    public String deleteTodo(@RequestParam("id") int id) {
        todoRepository.deleteById(id);
//        toDoService.deleteByid(id);
        return "redirect:list-todos";
    }

    @RequestMapping(path = "update-todo")
    public String updateTodo(@RequestParam("id") int id, ModelMap modelMap) {
//        Todo toDoToBeUpdated = toDoService.findById(id);
        Todo toDoToBeUpdated = todoRepository.findById(id).get();
        modelMap.addAttribute("todo", toDoToBeUpdated);
        return "add-todo";
    }

    @RequestMapping(path = "update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap modelMap, @Valid Todo toDo, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println("validation error detected");
            return "add-todo";
        }
        toDo.setUsername(getLoggedInUsername());
//        toDo.setTargetDate(LocalDate.now().plusYears(1));
        todoRepository.save(toDo);
        return "redirect:list-todos";
    }

    private String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }


}
