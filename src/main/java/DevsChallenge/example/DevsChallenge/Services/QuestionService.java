package DevsChallenge.example.DevsChallenge.Services;


import DevsChallenge.example.DevsChallenge.Models.Question;

import java.util.List;

public interface QuestionService {


    Question creer(Question question);

    List<Question> afficher();

    Question modifier(Long id, Question question);

    Object supprimer(Long id);
    Question solutionParId(Long id);
}
