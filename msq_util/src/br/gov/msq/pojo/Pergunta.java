package br.gov.msq.pojo;

import java.util.List;

public class Pergunta {
	
	private Integer id;
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
}