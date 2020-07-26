package webquiz.engine.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import webquiz.engine.model.Quiz;
import webquiz.engine.model.QuizAnswer;
import webquiz.engine.service.QuizService;

import javax.validation.Valid;
import java.util.*;

@RestController
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("api/quizzes")
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz){
        return quizService.createQuiz(quiz);
    }

    @GetMapping("/api/quizzes/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable int id){
       return quizService.getQuizById(id);
    }

    @GetMapping("/api/quizzes")
    public List<Quiz> getAllQuizes(){
        return quizService.getAllQuizes();
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public ResponseEntity<?> solveQuiz(@PathVariable int id, @RequestBody QuizAnswer answer){
        return quizService.solveQuiz(id, answer);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HashMap<String, String> handleExceptions(MethodArgumentNotValidException ex){
        HashMap<String, String> response = new HashMap<>();
        response.put("message","You need to provide information");
        response.put("error", ex.getMessage());
        return response;
    }

}
