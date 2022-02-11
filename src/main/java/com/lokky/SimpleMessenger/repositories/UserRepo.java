package com.lokky.SimpleMessenger.repositories;

import com.lokky.SimpleMessenger.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long>
{
    Optional<User> findUserBySessionId(String sessionId);
}
