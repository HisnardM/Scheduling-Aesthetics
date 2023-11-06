package com.agendamento.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agendamento.crm.model.AreasCorpo;
import com.agendamento.crm.model.Clientes;

@Repository
public interface AreasCorpoRepository extends JpaRepository<AreasCorpo, Long> {
	AreasCorpo findByNome(String nomeAreaCorpo);
}
