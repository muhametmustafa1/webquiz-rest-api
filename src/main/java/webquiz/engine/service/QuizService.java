package webquiz.engine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import webquiz.engine.model.Quiz;
import webquiz.engine.model.QuizAnswer;
import webquiz.engine.repository.QuizRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizRepository quizRepository;

    public Quiz createQuiz(Quiz quiz){
        return quizRepository.save(quiz);
    }

    public ResponseEntity<Quiz> getQuizById(@PathVariable int id){
        Optional<Quiz> quizOptional = quizRepository.findById(id);

        if(quizOptional.isPresent()) {
            return new ResponseEntity<Quiz>(quizOptional.get(), HttpStatus.OK);
        }
        else{
            return  new ResponseEntity<Quiz>(new Quiz(),HttpStatus.NOT_FOUND);
        }
    }

    public List<Quiz> getAllQuizes(){
        return quizRepository.findAll();
    }

    public ResponseEntity<?> solveQuiz( int id, QuizAnswer answer){
        Optional<Quiz> quizToBeSolved = quizRepository.findById(id);

        if(quizToBeSolved.isPresent()) {

            if (answer.getAnswer().equals(quizToBeSolved.get().getAnswer())){
                return new ResponseEntity<engine.model.QuizResponse>(new engine.model.QuizResponse(true, "Congratulations, you're right!"), HttpStatus.OK);
            } else {
                return new ResponseEntity<engine.model.QuizResponse>(new engine.model.QuizResponse(false, "Wrong answer! Please, try again."), HttpStatus.OK);
            }
        }
        else{
            return new ResponseEntity<String>("Not found!", HttpStatus.NOT_FOUND);
        }
    }

}
