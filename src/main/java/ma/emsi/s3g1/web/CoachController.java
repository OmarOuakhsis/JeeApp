package ma.emsi.s3g1.web;

import ma.emsi.s3g1.entities.Coach;
import ma.emsi.s3g1.repositories.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CoachController {

    @Autowired
    CoachRepository coachRepository;

    @GetMapping(path = "/coaches")
    public String allCoaches(Model model,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "3") int size,
                             @RequestParam(name = "search", defaultValue = "") String searchName) {

        Page<Coach> pageCoaches = coachRepository.findByFullNameContains(searchName, PageRequest.of(page, size));

        int[] pages = new int[pageCoaches.getTotalPages()];
        for (int i = 0; i < pages.length; i++)
            pages[i] = i;

        model.addAttribute("pageCoaches", pageCoaches.getContent());
        model.addAttribute("tabPages", pages);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", searchName);
        return "coaches";
    }

    @GetMapping(path = "/createCoach")
    public String createCoach(Model model) {
        Coach coach = new Coach();
        model.addAttribute("coach", coach);
        return "formAddCoach";
    }

    @PostMapping(path = "/saveCoach")
    public String saveCoach(Model model, Coach coach,
                            @RequestParam(name = "currentPage", defaultValue = "0") int page,
                            @RequestParam(name = "size", defaultValue = "3") int size,
                            @RequestParam(name = "searchName", defaultValue = "") String search) {
        coachRepository.save(coach);
        return "redirect:/coaches?page=" + page + "&size=" + size + "&search=" + search;
    }

    @GetMapping(path = "/deleteCoach")
    public String deleteCoach(int page, int size, String search,
                              @RequestParam(name = "id") Integer id) {
        coachRepository.deleteById(id);
        return "redirect:/coaches?page=" + page + "&size=" + size + "&search=" + search;
    }

    @GetMapping(path = "/editCoach")
    public String editCoach(Model model, int page, int size, String search, Integer id) {
        Coach coach = coachRepository.findById(id).orElse(null);
        if (coach == null) throw new RuntimeException("Coach not found");
        model.addAttribute("coach", coach);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", search);
        return "formEditCoach";
    }
}
