package pl.szczerbiak.springLorem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.szczerbiak.springLorem.model.LoremGenerator;

@Controller
public class LoremController {

    @GetMapping("/")
    public String generateText(){
        return "generate";
    }

    @GetMapping("/result")
    public String showResults(@RequestParam int n, @RequestParam String type,  ModelMap modelMap){
        modelMap.addAttribute("text", LoremGenerator.generateText(n, type));
        return "result";
    }

}
