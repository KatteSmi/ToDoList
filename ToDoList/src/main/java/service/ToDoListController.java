package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.model.ToDoList;
import service.model.ToDoListRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ToDoListController
{

    @Autowired
    private ToDoListRepository toDoListRepository;

    @PostMapping("/toDoList/")
    public int add ( ToDoList toDoList) {
        ToDoList newToDoList = toDoListRepository.save(toDoList);
        return newToDoList.getId();
    }

    @GetMapping("/toDoList/")
    public List<ToDoList> list() {
        Iterable<ToDoList> toDoListIterable = toDoListRepository.findAll();
        ArrayList<ToDoList> toDoLists = new ArrayList<>();
        for (ToDoList toDoList : toDoListIterable) {
            toDoLists.add(toDoList);
        }
        return toDoLists;
    }

    @GetMapping("/toDoList/{id}")
    public ResponseEntity read(@PathVariable int id) {

        Optional<ToDoList> optionalToDoList = toDoListRepository.findById(id);
        if (!optionalToDoList.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalToDoList.get(), HttpStatus.OK);
    }

    @DeleteMapping("/toDoList/{id}")
    public void delete(@PathVariable int id) {
        toDoListRepository.deleteById(id);
    }
}


