package com.lokky.SimpleMessenger.repositories;

import com.lokky.SimpleMessenger.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long>
{

}
