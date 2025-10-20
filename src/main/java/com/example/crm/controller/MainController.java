package com.example.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.crm.repository.ApplicationRequestRepository;
import com.example.crm.repository.CoursesRepository;
import com.example.crm.repository.OperatorsRepository;
import com.example.crm.model.ApplicationRequest;
import com.example.crm.model.Courses;
import com.example.crm.model.Operators;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    private final ApplicationRequestRepository reqRepo;
    private final CoursesRepository coursesRepo;
    private final OperatorsRepository opRepo;

    public MainController(ApplicationRequestRepository reqRepo, CoursesRepository coursesRepo, OperatorsRepository opRepo) {
        this.reqRepo = reqRepo;
        this.coursesRepo = coursesRepo;
        this.opRepo = opRepo;
    }

    // 🏠 Главная страница
    @GetMapping({"/", "/requests"})
    public String index(Model model) {
        model.addAttribute("requests", reqRepo.findAll());
        model.addAttribute("operators", opRepo.findAll());
        return "index";
    }

    // 📝 Страница добавления заявки
    @GetMapping("/requests/add")
    public String showAddPage(Model model) {
        model.addAttribute("courses", coursesRepo.findAll());
        return "add-request";
    }

    @PostMapping("/requests/add")
    public String addRequest(@RequestParam String userName,
                             @RequestParam String commentary,
                             @RequestParam String phone,
                             @RequestParam Long courseId) {
        Optional<Courses> c = coursesRepo.findById(courseId);
        Courses course = c.orElse(null);
        ApplicationRequest r = new ApplicationRequest();
        r.setUserName(userName);
        r.setCommentary(commentary);
        r.setPhone(phone);
        r.setHandled(false);
        r.setCourse(course);
        reqRepo.save(r);
        return "redirect:/requests";
    }

    @PostMapping("/requests/assign")
    public String assignOperators(@RequestParam Long requestId, @RequestParam(required=false) List<Long> operatorIds) {
        Optional<ApplicationRequest> or = reqRepo.findById(requestId);
        if (or.isPresent()) {
            ApplicationRequest r = or.get();
            if (r.isHandled()) return "redirect:/requests";
            List<Operators> ops = operatorIds == null ? List.of() : opRepo.findAllById(operatorIds);
            r.setOperators(ops);
            r.setHandled(true);
            reqRepo.save(r);
        }
        return "redirect:/requests";
    }

    @PostMapping("/requests/delete")
    public String deleteRequest(@RequestParam Long id) {
        reqRepo.deleteById(id);
        return "redirect:/requests";
    }
}
