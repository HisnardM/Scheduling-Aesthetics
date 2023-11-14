package com.agendamento.crm.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agendamento.crm.model.Agendamento;
import com.agendamento.crm.model.AgendamentoRequest;
import com.agendamento.crm.model.AreasCorpo;
import com.agendamento.crm.model.Clientes;
import com.agendamento.crm.model.Funcionarios;
import com.agendamento.crm.model.Procedimentos;
import com.agendamento.crm.repository.AgendamentosRepository;
import com.agendamento.crm.repository.AreasCorpoRepository;
import com.agendamento.crm.repository.ClientesRepository;
import com.agendamento.crm.repository.FuncionariosRepository;
import com.agendamento.crm.repository.ProcedimentosRepository;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

	
	//Ligando todos os repositories a fim de validar as informações de cada registro
	@Autowired
	private AgendamentosRepository agendamentosRepository;
	
	@Autowired
	private FuncionariosRepository funcionariosRepository;
	
	@Autowired
	private AreasCorpoRepository areasCorpoRepository;
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	@Autowired
	private ProcedimentosRepository procedimentosRepository;
	
	@GetMapping("/todos")
	public List<Agendamento> listarTodosAgendamentos(){
		return agendamentosRepository.findAll();
	}
	
	@GetMapping("/{funcionario}")
	public List<Agendamento> listarAgendamentoPorProfissional(@PathVariable Funcionarios funcionario){
		return agendamentosRepository.findByFuncionario(funcionario);
	}
	
	@PostMapping
	public ResponseEntity<?> criarAgendamento(@RequestBody Agendamento agendamento) {
	    if (agendamento.getDataAgendamento() != null && agendamento.getHoraAgendamento() != null) {
	        // Obtém os nomes das entidades do request
	        String nomeCliente = agendamento.getNomeCliente();
	        String nomeFuncionario = agendamento.getNomeFuncionario();
	        String nomeProcedimento = agendamento.getNomeProcedimento();
	        String nomeAreaCorpo = agendamento.getNomeAreaCorpo();

	        // Valida os nomes das entidades e busca as entidades correspondentes do banco de dados
	        Clientes cliente = clientesRepository.findByNome(nomeCliente);
	        Funcionarios funcionario = funcionariosRepository.findByNome(nomeFuncionario);
	        Procedimentos procedimento = procedimentosRepository.findByNome(nomeProcedimento);
	        AreasCorpo areaCorpo = areasCorpoRepository.findByNome(nomeAreaCorpo);

	        // Verifica se as entidades foram encontradas
	        if (cliente == null || funcionario == null || procedimento == null || areaCorpo == null) {
	            return ResponseEntity.badRequest().body("Um ou mais campos não condizem com os registros.");
	        }

	        // Cria a instância de Agendamento com as entidades e datas/horas corretas
	        agendamento.setClientes(cliente);
	        agendamento.setFuncionarios(funcionario);
	        agendamento.setProcedimentos(procedimento);
	        agendamento.setAreasCorpo(areaCorpo);

	        // Salva no repositório
	        agendamentosRepository.save(agendamento);

	        return ResponseEntity.ok("Agendamento criado com sucesso.");
	    } else {
	        return ResponseEntity.badRequest().body("Data e hora de agendamento não podem ser nulas ou vazias.");
	    }
	}
	
    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizarAgendamento(@PathVariable Long id, @RequestBody Agendamento agendamento) {
        if (!agendamentosRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        agendamento.setId(id);
        Agendamento atualizado = agendamentosRepository.save(agendamento);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAgendamento(@PathVariable Long id) {
        if (!agendamentosRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        agendamentosRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
	
}