package org.example.userservicenov24.repositories;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.userservicenov24.models.Token;
import org.example.userservicenov24.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TokenRepository extends JpaRepository<Token, Long> {
   Token save (Token token);

   @Query(value = "select count(t) from Token t where t.user.id = :#{# user.id}")
   int tokenCount (@Param("user") User user);
}
