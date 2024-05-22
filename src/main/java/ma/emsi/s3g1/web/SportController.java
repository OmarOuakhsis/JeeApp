package ma.emsi.s3g1.web;

import ma.emsi.s3g1.entities.Sport;
import ma.emsi.s3g1.repositories.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SportController {

    @Autowired
    SportRepository sportRepository;

    @GetMapping(path = "/sports")
    public String allSports(Model model,
                            @RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "size", defaultValue = "3") int size,
                            @RequestParam(name = "search", defaultValue = "") String searchName) {

        Page<Sport> pageSports = sportRepository.findByNameContains(searchName, PageRequest.of(page, size));

        int[] pages = new int[pageSports.getTotalPages()];
        for (int i = 0; i < pages.length; i++)
            pages[i] = i;

        model.addAttribute("pageSports", pageSports.getContent());
        model.addAttribute("tabPages", pages);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", searchName);
        return "sports";
    }

    @GetMapping(path = "/create-sport")
    public String createSport(Model model) {
        Sport sport = new Sport();
        model.addAttribute("sport", sport);
        return "formAddSport";
    }

    @PostMapping(path = "/save-sport")
    public String saveSport(Model model, Sport sport,
                            @RequestParam(name = "currentPage", defaultValue = "0") int page,
                            @RequestParam(name = "size", defaultValue = "3") int size,
                            @RequestParam(name = "searchName", defaultValue = "") String search) {
        sportRepository.save(sport);
        return "redirect:/sports?page=" + page + "&size=" + size + "&search=" + search;
    }

    @GetMapping(path = "/delete-sport")
    public String deleteSport(
            int page, int size, String search,
            @RequestParam(name = "id") Integer id) {
        sportRepository.deleteById(id);
        return "redirect:/sports?page=" + page + "&size=" + size + "&search=" + search;
    }

    @GetMapping(path = "/edit-sport")
    public String editSport(Model model, int page, int size, String search, Integer id) {
        Sport sport = sportRepository.findById(id).orElse(null);
        if (sport == null) throw new RuntimeException("Erreur");
        model.addAttribute("sport", sport);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", search);
        return "formEditSport";
    }
}
