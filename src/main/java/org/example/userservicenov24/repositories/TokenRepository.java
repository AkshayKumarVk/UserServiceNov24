package org.example.userservicenov24.repositories;

import org.example.userservicenov24.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
   Token save(Token token);
}
