package DevsChallenge.example.DevsChallenge.Services;

import DevsChallenge.example.DevsChallenge.Models.Technologies;
import DevsChallenge.example.DevsChallenge.Repositories.TechnoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@AllArgsConstructor
public class TechnoServiceImpl implements TechnoService{

    private final TechnoRepository technoRepository;
    @Override
    public Technologies creer(Technologies technologies) {
        return technoRepository.save(technologies);
    }

    @Override
    public List<Technologies> afficher() {
        return technoRepository.findAll();
    }

    @Override
    public Technologies modifier(Long id, Technologies technologies) {
        return this.technoRepository.findById(id).map(
                tec->{
                    tec.setNom(technologies.getNom());
                    tec.setDescription(technologies.getDescription());
                    return technoRepository.save(tec);
                }).orElseThrow(()-> new  RuntimeException("Technologies non trouvé"));
    }

    @Override
    public String supprimer(Long id) {
        this.technoRepository.deleteById(id);
        return "Technologies supprimer avec succes";
    }
}
