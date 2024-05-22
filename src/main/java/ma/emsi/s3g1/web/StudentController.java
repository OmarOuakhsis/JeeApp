package ma.emsi.s3g1.web;

import ma.emsi.s3g1.entities.Student;
import ma.emsi.s3g1.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping(path = "/index")
    public String allStudents(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "3") int size,
                              @RequestParam(name = "search", defaultValue = "") String searchName) {

        Page<Student> pageStudents = studentRepository.findByFullNameContains(searchName, PageRequest.of(page, size));

        int[] pages = new int[pageStudents.getTotalPages()];
        for (int i = 0; i < pages.length; i++)
            pages[i] = i;

        model.addAttribute("pageStudents", pageStudents.getContent());
        model.addAttribute("tabPages", pages);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", searchName);
        return "students";
    }

    @GetMapping(path = "/create")
    public String createStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "formAddStudent";
    }

    @PostMapping(path = "/save")
    public String saveStudent(Model model, Student s,
                              @RequestParam(name = "currentPage", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "3") int size,
                              @RequestParam(name = "searchName", defaultValue = "") String search) {
        studentRepository.save(s);
        return "redirect:/index?page=" + page + "&size=" + size + "&search=" + search;
    }

    @GetMapping(path = "/students/home")
    public String homePage() {
        return "/menu";
    }

    @GetMapping(path = "/delete")
    public String deleteStudent(int page, int size, String search,
                                @RequestParam(name = "id") Integer id) {
        studentRepository.deleteById(id);
        return "redirect:/index?page=" + page + "&size=" + size + "&search=" + search;
    }

    @GetMapping(path = "/edit")
    public String editStudent(Model model, int page, int size, String search, Integer id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) throw new RuntimeException("Erreur");
        model.addAttribute("student", student);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", search);

        return "formEditStudent";
    }
}
