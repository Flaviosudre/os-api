
package com.flaviosudre.os.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tecnico extends Pessoa implements Serializable{
	private static final Long seralVersionUID = 1L;
	

	@OneToMany(mappedBy= "tecnico")	
	private List<OS> ListaOS = new ArrayList<>();

	public Tecnico() {
		super();		
	}

	public Tecnico(Integer id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);
	}

	@JsonIgnore
	public List<OS> getListaOS() {
		return ListaOS;
	}

	public void setListaOS(List<OS> listaOS) {
		ListaOS = listaOS;
	}

	
	
}
