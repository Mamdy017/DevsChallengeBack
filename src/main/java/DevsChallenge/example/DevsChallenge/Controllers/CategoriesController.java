package DevsChallenge.example.DevsChallenge.Controllers;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Categories;
import DevsChallenge.example.DevsChallenge.Services.CategoriesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "devsCiwara", description = "")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/devs/auth/cate")
@RestController
@ToString
public class CategoriesController {

    @Autowired
    CategoriesService categoriesService;

    @ApiOperation(value = "Ajouter2 un pays")
    @PostMapping("/ajout")
    public Object creer (@RequestBody Categories categories){

        try {
            return categoriesService.creer(categories);
        } catch (Exception e) {
            return Message.ErreurReponse(" categories " + categories.getNom() + " existe déjà", HttpStatus.OK, null);
        }
    }

    @ApiOperation(value = "Afficher techonologies")
    @GetMapping("/afficher")
    public List<Categories> list(){
        return categoriesService.afficher();
    }

    @ApiOperation(value = "Modifier technologies")
    @PutMapping("/modifier/{Id}")
    public Categories Modifier(@PathVariable Long Id, Categories categories) {
        return categoriesService.modifier(Id, categories);
    }

    @ApiOperation(value = "Supprimer Technologies")
    @DeleteMapping("/Supprimer/{Id}")
    public String Supprimer(@PathVariable Long Id) {

        return categoriesService.supprimer(Id);
    }

}
