package service.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.model.ToDoList;
import service.model.ToDoListRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class DefaultController {

  @Autowired
  private
  ToDoListRepository toDoListRepository;

  @Value("${someParameter.value}")
  private Integer someParameter;

  @RequestMapping(name = "/")
  public String index(Model model) {
    Iterable<ToDoList> allCases = toDoListRepository.findAll();
    List<ToDoList> toDoLists = new ArrayList<>((Collection<? extends ToDoList>) allCases);
    model.addAttribute("toDoLists", toDoLists);
    model.addAttribute("toDoListsSize", toDoLists.size());
    model.addAttribute("someParameter", someParameter);
    return "index";
  }
}
