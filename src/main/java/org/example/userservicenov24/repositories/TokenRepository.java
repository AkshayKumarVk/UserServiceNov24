package org.example.userservicenov24.repositories;

import org.example.userservicenov24.models.Token;
import org.example.userservicenov24.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
   Token save (Token token);

   @Query(value = "select count(t) from Token t where t.user.id = :#{# user.id}")
   int tokenCount (@Param("user") User user);

   Optional<Token> findByValueAndIsDeletedAndExpiryIsGreaterThan (String tokenValue, Boolean isDelete, Date expiry);

   @Query("select count(t) from Token t where t.user.id = :#{# user.id} and t.isDeleted = false ")
   int validTokenCount (@Param("user") User user);


   Token findTokenByValue(String value);

}
