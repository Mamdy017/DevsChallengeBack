package DevsChallenge.example.DevsChallenge.Controllers;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Categories;
import DevsChallenge.example.DevsChallenge.Services.CategoriesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "devsCiwara", description = "azertyuio")
@RequestMapping("/devs/auth/cate")
@RestController
@ToString
public class CategoriesController {

    @Autowired
    CategoriesService categoriesService;

    @PutMapping("/{id}")
    public Message updateCategory(@PathVariable Long id, @RequestBody Categories categories) {
     return categoriesService.modifier(id,categories);
    }
    @ApiOperation(value = "Ajouter2 un categories")
    @PostMapping("/ajout")
    public Object creer (@Param("cate") String cate){
        Categories categories= new Categories(cate);
        try {
            return categoriesService.creer(categories);
        } catch (Exception e) {
            return Message.set(" categories " + categories.getNom() + " existe déjà",  true);
        }
    }

    @ApiOperation(value = "Afficher techonologies")
    @GetMapping("/afficher")
    public List<Categories> list(){
        return categoriesService.afficher();
    }




    @ApiOperation(value = "Supprimer Technologies")
    @DeleteMapping("/Supprimer/{Id}")
    public String Supprimer(@PathVariable Long Id) {

        return categoriesService.supprimer(Id);
    }

}
