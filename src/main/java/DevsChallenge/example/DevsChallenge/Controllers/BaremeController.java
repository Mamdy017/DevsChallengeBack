package DevsChallenge.example.DevsChallenge.Controllers;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Categories;
import DevsChallenge.example.DevsChallenge.Models.bareme;
import DevsChallenge.example.DevsChallenge.Services.baremeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "devsCiwara", description = "azertyuio")
@RequestMapping("/devs/auth/bareme")
@RestController
@ToString
public class BaremeController {

    @Autowired
    baremeService  baremeService1;

    @ApiOperation(value = "Ajouter2 un categories")
    @PostMapping("/ajout")
    public Object creer (@RequestBody bareme bareme1){

        try {
            return baremeService1.creer(bareme1);
        } catch (Exception e) {
            return Message.set(" categories " + bareme1.getBareme() + " existe déjà",  true);
        }
    }

    @ApiOperation(value = "Afficher bareme")
    @GetMapping("/afficher")
    public List<bareme> list(){
        return baremeService1.afficher();
    }

}
