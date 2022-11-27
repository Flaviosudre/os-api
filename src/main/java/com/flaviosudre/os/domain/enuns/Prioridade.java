package com.flaviosudre.os.domain.enuns;

public enum Prioridade {

	BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(3, "ALTA");

	
	private Integer cod;
	private String descricao;

	
	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	// método construtor
	
	private Prioridade(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public static Prioridade toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(Prioridade  p : Prioridade.values()) {
			if(cod.equals(p.getCod())){
				return p;
			}
		}
		throw new IllegalArgumentException("Prioridade inválida " + cod);
		
	}
}
