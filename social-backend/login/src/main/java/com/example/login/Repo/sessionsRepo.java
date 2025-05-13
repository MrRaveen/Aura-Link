package com.example.login.Repo;

import com.example.login.Entity.sessions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface sessionsRepo extends JpaRepository<sessions, Integer> {
}
