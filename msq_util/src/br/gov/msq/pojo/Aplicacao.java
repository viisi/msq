package br.gov.msq.pojo;


public class Aplicacao {
	
	public Aplicacao() {
		
	}
	
	public Aplicacao(Apoiador apoiador, Questionario questionario, Pergunta pergunta, Cnes cnes, String valor) {
		this.apoiador = apoiador;
		this.questionario = questionario;
		this.pergunta = pergunta;
		this.cnes = cnes;
		this.valor = valor;
	}
	
	private Integer id;
	private Apoiador apoiador;
	private Cnes cnes;
	private Questionario questionario;
	private Pergunta pergunta;
	private String valor;
	
	@Override
	public String toString() {
		return "[codigoApoiador="+apoiador.getCodigo()+" codigoQuestionario="+questionario.getCodigo() + " codigoPergunta="+pergunta.getCodigo() +" valor="+valor;
	}
	
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
	public Pergunta getPergunta() {
		return pergunta;
	}
	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
}