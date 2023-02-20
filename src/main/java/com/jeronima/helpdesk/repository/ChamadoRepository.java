package com.jeronima.helpdesk.repository;

import com.jeronima.helpdesk.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado,Integer> {
}
