package com.flaviosudre.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.flaviosudre.os.domain.Cliente;
import com.flaviosudre.os.domain.OS;
import com.flaviosudre.os.domain.Tecnico;
import com.flaviosudre.os.domain.enuns.Prioridade;
import com.flaviosudre.os.domain.enuns.Status;
import com.flaviosudre.os.dtos.OsDTO;
import com.flaviosudre.os.repositories.OSRepository;
import com.flaviosudre.os.services.excptions.DataIntegratyViolationException;
import com.flaviosudre.os.services.excptions.ObjectNotFoundException;

@Service
public class OsService {

	@Autowired 
	private OSRepository osRepository;
	
	@Autowired
	private TecnicoService tecnicoService;

	@Autowired
	private ClienteService clienteService;
	
	public OS findById(@PathVariable Integer id) {
		Optional<OS> obj = osRepository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Os não encontrada." + id +
				 " tipo : " + OS.class.getName()));
	}
	
	public List<OS> findAll(){
		return osRepository.findAll();
	}
		
	
	public OS create(@Valid OsDTO obj){
		return fromDTO(obj);
	}
	
	public OS update(@Valid OsDTO obj) {
		findById(obj.getId());
		return fromDTO(obj);
	}
	
	public void delete(Integer id) {
		findById(id);
		osRepository.deleteById(id);
	}
	
	private  OS fromDTO(OsDTO obj) {
		OS newObj = new OS();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade().getCod()));
		newObj.setStatus(Status.toEnum(obj.getStatus().getCod()));
		
		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		Cliente cli = clienteService.findById(obj.getCliente());
		
		newObj.setTecnico(tec);
		newObj.setCliente(cli);
		
		if(newObj.getStatus().getCod().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		
		return osRepository.save(newObj);
	}	
}
