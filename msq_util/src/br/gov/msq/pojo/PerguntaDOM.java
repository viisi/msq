package br.gov.msq.pojo;


public class PerguntaDOM {
	
	private String name;
	private String id;
	private String type;
	private String value;
	private String codigoApoiador;
	private String codigoQuestionario;
	private String numeroCNES;

	//name, id, type, value
	public PerguntaDOM(String[] atributo) throws Exception {
		
		for (int i = 0; i < atributo.length; i++) {
			if(atributo[i].split("=").length == 2) {
				String chave = atributo[i].split("=")[0].trim();
				String valor = atributo[i].split("=")[1];
				
				if(chave.equals("id")) {
					this.id = valor;
				}
				if(chave.equals("﻿name")) {
					this.name = valor;
				}
				if(chave.equals("value")) {
					this.value = valor;
				}
			}
		}
	}
	
	public String getCodigoPergunta() throws Exception {
		String codigo = null;
		if(id != null && !id.toString().equals("")) {
			codigo = id;
		} else if(name != null && !name.toString().equals("")) {
			codigo = name;
		}
		return codigo;
	}
	
	@Override
	public String toString() {
		return "[Name=" + name + " id="+id + " value="+value+" codigoApoiador=" + codigoApoiador + " codigoQuestionario="+codigoQuestionario +" numeroCNES="+numeroCNES+"]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getNumeroCNES() {
		if(numeroCNES == null || numeroCNES.trim().equals("")) {
			numeroCNES = "0";
		}
		return numeroCNES;
	}

	public void setNumeroCNES(String numeroCNES) {
		this.numeroCNES = numeroCNES;
	}

	public void setCodigoApoiador(String codigoApoiador) {
		this.codigoApoiador = codigoApoiador;
	}

	public void setCodigoQuestionario(String codigoQuestionario) {
		this.codigoQuestionario = codigoQuestionario;
	}

	public String getCodigoApoiador() {
		return codigoApoiador;
	}

	public String getCodigoQuestionario() {
		return codigoQuestionario;
	}
}
