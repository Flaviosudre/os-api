package com.flaviosudre.os.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cliente extends Pessoa implements Serializable{
	private static final Long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="cliente")
	private List<OS> ListaOS = new ArrayList<>();

	
	public Cliente() {
		super();
	}

	public Cliente(Integer id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);
	}

	
	public List<OS> getListaOS() {
		return ListaOS;
	}

	public void setListaOS(List<OS> listaOS) {
		ListaOS = listaOS;
	}	
}
