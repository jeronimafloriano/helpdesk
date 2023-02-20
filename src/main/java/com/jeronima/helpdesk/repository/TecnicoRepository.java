package com.jeronima.helpdesk.repository;

import com.jeronima.helpdesk.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico,Integer> {
}
