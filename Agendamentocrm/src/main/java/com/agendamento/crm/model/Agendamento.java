package com.agendamento.crm.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Agendamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private LocalDate dataAgendamento;
    
    private LocalTime horaAgendamento;
    
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionarios funcionario;
    
    @ManyToOne
    private AreasCorpo areaCorpo;
    
    @ManyToMany
    @JoinTable(name = "agendamento_procedimentos",
        joinColumns = @JoinColumn(name = "agendamento_id"),
        inverseJoinColumns = @JoinColumn(name = "procedimentos_id")
    )
    private Set<Procedimentos> procedimentos;
    
    @ManyToOne
    @JoinColumn(name = "clientes_id")
    private Clientes clientes;

    //Getters e Setters para todos os campos
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Funcionarios getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionarios funcionario) {
		this.funcionario = funcionario;
	}

	public AreasCorpo getAreaCorpo() {
		return areaCorpo;
	}

	public void setAreaCorpo(AreasCorpo areaCorpo) {
		this.areaCorpo = areaCorpo;
	}

	public Set<Procedimentos> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(Set<Procedimentos> procedimentos) {
		this.procedimentos = procedimentos;
	}

	public Clientes getClientes() {
		return clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDate dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public LocalTime getHoraAgendamento() {
		return horaAgendamento;
	}

	public void setHoraAgendamento(LocalTime horaAgendamento) {
		this.horaAgendamento = horaAgendamento;
	}

	public void setFuncionarios(Funcionarios funcionarios) {
		// TODO Auto-generated method stub
		
	}

	public void setProcedimentos(Procedimentos procedimentos2) {
		// TODO Auto-generated method stub
		
	}

	public void setAreasCorpo(AreasCorpo areasCorpo) {
		// TODO Auto-generated method stub
		
	}

	public String getNomeCliente() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNomeFuncionario() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNomeProcedimento() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNomeAreaCorpo() {
		// TODO Auto-generated method stub
		return null;
	}
    
}