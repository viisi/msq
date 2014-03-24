package br.gov.msq.pojo;

import java.util.List;

public class Pergunta {
	
	public Pergunta(String codigo) {
		this.codigo = codigo;
	}
	
	public Pergunta(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	private Integer id;
	private String codigo;
	private String descricao;
	private Dimensao dimensao;
	private List<Questionario> questionarios;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Dimensao getDimensao() {
		return dimensao;
	}
	public void setDimensao(Dimensao dimensao) {
		this.dimensao = dimensao;
	}
	public List<Questionario> getQuestionarios() {
		return questionarios;
	}
	public void setQuestionarios(List<Questionario> questionarios) {
		this.questionarios = questionarios;
	}
	public String getCodigo() {
		return codigo.replaceAll("'", "");
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}