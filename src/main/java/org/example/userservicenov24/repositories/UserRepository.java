package org.example.userservicenov24.repositories;

import org.example.userservicenov24.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findUserByEmail(String email);

   User save(User user);

}
