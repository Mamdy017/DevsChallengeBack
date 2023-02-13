package DevsChallenge.example.DevsChallenge.Services;


import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Question;
import DevsChallenge.example.DevsChallenge.Models.Solution;

import java.util.List;

public interface QuestionService {


    Message creer(Question question);

    List<Question> afficher();

    Question modifier(Long id, Question question);

    Object supprimer(Long id);
    List<Question> findByChallengeId(Long challengeId);

    Question solutionParId(Long id);
}
