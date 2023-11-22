package com.agendamento.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agendamento.crm.model.Procedimentos;

@Repository
public interface ProcedimentosRepository extends JpaRepository<Procedimentos, Long>{

	Procedimentos findById(long id);

	Procedimentos findByNome(String nomeProcedimento);

	List<Procedimentos> findNome();
}
