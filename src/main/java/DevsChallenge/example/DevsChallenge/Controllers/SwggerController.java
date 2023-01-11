package DevsChallenge.example.DevsChallenge.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class SwggerController {

    @RequestMapping(method = RequestMethod.GET)
    public String swaggerUI(){

        return "redirect:/swagger-ui.html";
    }
}
