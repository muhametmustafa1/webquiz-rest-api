package webquiz.engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webquiz.engine.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
