package DevsChallenge.example.DevsChallenge.Controllers;


import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.critere;
import DevsChallenge.example.DevsChallenge.Repositories.baremeRepository;
import DevsChallenge.example.DevsChallenge.Services.critereService;
import io.swagger.annotations.Api;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "devsCiwara", description = "azertyuio")
@RequestMapping("/devs/auth/critere")
@RestController
@ToString
public class CritereController {

    @Autowired
  critereService critereService1;

    @Autowired
baremeRepository baremeRepository1;
    @PostMapping("/ajout") public Object creer (@RequestParam("baremeids") Long[] baremeids, @RequestBody critere critere1){    try {
        for (Long baremeid : baremeids) {
            critere1.getBareme().add(baremeRepository1.findById(baremeid).get());
        }
        return critereService1.creer(critere1);
    } catch (Exception e) {
        return Message.set(" ce critère  existe déjà",  true);
    }
    }





}