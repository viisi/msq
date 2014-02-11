package br.gov.msq.pojo;


public class Aplicacao {
	
	private Integer id;
	private Apoiador apoiador;
	private Cnes cnes;
	private Questionario questionario;
	
	public Questionario getQuestionario() {
		return questionario;
	}
	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Apoiador getApoiador() {
		return apoiador;
	}
	public void setApoiador(Apoiador apoiador) {
		this.apoiador = apoiador;
	}
	public Cnes getCnes() {
		return cnes;
	}
	public void setCnes(Cnes cnes) {
		this.cnes = cnes;
	}
}