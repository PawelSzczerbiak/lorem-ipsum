package pl.szczerbiak.springLorem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.szczerbiak.springLorem.model.LoremGenerator;

import javax.validation.Valid;

@Controller
public class LoremController {

    // generate
    @GetMapping("/")
    public String generateText(LoremGenerator loremGenerator){
        return "generate";
    }

    // validate
    @PostMapping("/")
    public String checkProductInfo(@Valid LoremGenerator loremGenerator, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "generate";
        }
        redirectAttributes.addFlashAttribute("text", loremGenerator.generateText());
        return "redirect:/result"; // mapping onto /result
    }

    // show
    @GetMapping("/result")
    public String showResults(){
        return "show";
    }

}
