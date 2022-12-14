package com.flaviosudre.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flaviosudre.os.domain.Cliente;
import com.flaviosudre.os.domain.Pessoa;
import com.flaviosudre.os.domain.Tecnico;
import com.flaviosudre.os.dtos.ClienteDTO;
import com.flaviosudre.os.dtos.TecnicoDTO;
import com.flaviosudre.os.repositories.PessoaRepository;
import com.flaviosudre.os.repositories.TecnicoRepository;
import com.flaviosudre.os.services.excptions.DataIntegratyViolationException;
import com.flaviosudre.os.services.excptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;

	/*
	 * Busca Tecnico peloId
	 */
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado." + id + " tipo : " + Tecnico.class.getName()));
	}	
	
	/*
	 *  Busca todos os tecnicos da base de dados
	 */
	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado.");
		}
		return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	/*
	 * Alteração de dados de Tecnico
	 */
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		Tecnico oldObj = findById(id);
		
		if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id ) {
			throw new DataIntegratyViolationException("CPF já cadastrado.");			
		}
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		return repository.save(oldObj);
	}

	/*
	 * Deleta um Tecnico pelo ID
	 */
	public void delete(Integer id) {
		
		Tecnico obj = findById(id);
		
		if(obj.getListaOS().size() > 0) {
			throw new DataIntegratyViolationException("Tecnico possui ordens de serviços, não pode ser deletado.");
		}		
		repository.deleteById(id);
	}
	
	/*
	 *     Busca pessoa pelo CPF
	 */
	
	private Pessoa findByCPF(TecnicoDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());

		if (obj != null) {
			return obj;
		}
		return null;
	}
}