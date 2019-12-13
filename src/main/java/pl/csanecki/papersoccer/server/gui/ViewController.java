package pl.csanecki.papersoccer.server.gui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/game")
    public String renderGamePage() {
        return "game";
    }
}
