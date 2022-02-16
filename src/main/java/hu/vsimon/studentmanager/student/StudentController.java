package hu.vsimon.studentmanager.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String homePage(Model model, @RequestParam(required = false, name = "page") Optional<Integer> optionalPage) {
        int pageNumber = optionalPage.orElse(1);
        if(pageNumber < 1)
            return "redirect:/";

        Page<Student> page = studentService.findPage(pageNumber, 5);

        if(pageNumber > page.getTotalPages())
            return "redirect:/";

        List<Student> students = page.getContent();
        model.addAttribute("students", students);
        model.addAttribute("pageCount", page.getTotalPages());
        model.addAttribute("studentCount", page.getTotalElements());
        model.addAttribute("currentPage", page.getNumber()+1);
        return "index";
    }

    @GetMapping("/new_student")
    public String newStudentPage(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "student";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editStudentPage(@PathVariable long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "student";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable long id) {
        studentService.deleteStudentById(id);
        return "redirect:/";
    }
}
