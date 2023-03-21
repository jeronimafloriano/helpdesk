package com.jeronima.helpdesk.repository;

import com.jeronima.helpdesk.domain.Cliente;
import com.jeronima.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
}
