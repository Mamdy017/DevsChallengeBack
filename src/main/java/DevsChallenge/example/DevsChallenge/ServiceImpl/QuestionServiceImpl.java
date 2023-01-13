package DevsChallenge.example.DevsChallenge.ServiceImpl;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Question;
import DevsChallenge.example.DevsChallenge.Repositories.QuestionRepository;
import DevsChallenge.example.DevsChallenge.Services.QuestionService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@AllArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    @Override
    public Question creer(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> afficher() {
        return questionRepository.findAll();
    }

    @Override
    public Question modifier(Long id, Question question) {
        return questionRepository.findById(id).map( q ->{
            q.setQuestion(question.getQuestion());
            return questionRepository.save(q);
        }).orElseThrow(()-> new  RuntimeException("Question  non trouvée"));
    }

    @Override
    public Object supprimer(Long id) {
        this.questionRepository.deleteById(id);
        return Message.set( "Question supprimée avec succès", true);
    }

    @Override
    public Question solutionParId(Long id) {

        return this.questionRepository.findById(id).get();
    }
}