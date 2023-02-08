package DevsChallenge.example.DevsChallenge.Controllers;


import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Technologies;
import DevsChallenge.example.DevsChallenge.Models.critere;
import DevsChallenge.example.DevsChallenge.Repositories.baremeRepository;
import DevsChallenge.example.DevsChallenge.Services.critereService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/ajout") public Object creer(@Param("baremeids") Long[] baremeids, @Param("criteres") String criteres){    try {
        critere Critere = new critere(criteres);
        for (Long baremeid : baremeids) {
            Critere.getBareme().add(baremeRepository1.findById(baremeid).get());
        }
        return critereService1.creer(Critere);
    } catch (Exception e) {
        return Message.set(" ce critère  existe déjà",  true);
    }
    }

    @ApiOperation(value = "Afficher critere")
    @GetMapping("/afficher")
    public List<critere> list(){
        return critereService1.afficher();
    }




}