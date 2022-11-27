package com.flaviosudre.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.flaviosudre.os.domain.Cliente;
import com.flaviosudre.os.domain.OS;
import com.flaviosudre.os.domain.Tecnico;
import com.flaviosudre.os.domain.enuns.Prioridade;
import com.flaviosudre.os.domain.enuns.Status;
import com.flaviosudre.os.repositories.ClienteRepository;
import com.flaviosudre.os.repositories.OSRepository;
import com.flaviosudre.os.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OSRepository osRepository;


	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Flavio Sudre", "01302711792", "21982916625");
		Cliente c1 = new Cliente(null, "Gabriela", "62353728090", "2120511878");
		OS os1 = new OS(null, Prioridade.ALTA, Status.ANDAMENTO, "Serviço com urgencia", t1, c1);

		t1.getListaOS().add(os1);
		
		c1.getListaOS().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
	}

}
