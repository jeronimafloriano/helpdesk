package com.jeronima.helpdesk.repository;

import com.jeronima.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa,Integer> {
}
