package DevsChallenge.example.DevsChallenge.Controllers;

import DevsChallenge.example.DevsChallenge.Models.Appreciation;
import DevsChallenge.example.DevsChallenge.Services.ApprecitionService;
import io.swagger.annotations.Api;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "devsCiwara", description = "")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/devs/auth/appreciation")
@RestController
@ToString
public class ApprecitionController {

    @Autowired
    ApprecitionService appreciationService;
    @PostMapping("/incrementType1/{commentId}")
    public ResponseEntity<Appreciation> incrementType1(@PathVariable Long commentId) {
        Appreciation updatedAppreciation = appreciationService.incrementType1(commentId);
        return ResponseEntity.ok(updatedAppreciation);
    }

    @PutMapping("/incrementType2/{commentId}")
    public ResponseEntity<Appreciation> incrementType2(@PathVariable Long commentId) {
        Appreciation updatedAppreciation = appreciationService.incrementType2(commentId);
        return ResponseEntity.ok(updatedAppreciation);
    }

}
