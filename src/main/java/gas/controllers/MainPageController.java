package gas.controllers;

import gas.services.GasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Main page controller. Uses Thymeleaf to initiate data on main page
 */
@Controller
public class MainPageController {

    private final GasService gasService;

    @Autowired
    public MainPageController(GasService gasService) {
        this.gasService = gasService;
    }

    /**
     * Starts index.html stored in templates folder. Uses Thymeleaf to initiate main statistics table
     * @param model
     * @return index.html page
     */
    @GetMapping("/main")
    public String mainPage(Model model){
        model.addAttribute("gasData", gasService.getAll());
        return "index";
    }
}
