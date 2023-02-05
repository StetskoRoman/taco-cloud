package sia.tacocloud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") //обрабатывает запрос с корневым путем (/)
    public String home() {
        return "home"; //имя представления, в templates
    }
}
