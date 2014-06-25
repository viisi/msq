package br.gov.msq;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.gov.msq.dao.AplicacaoDAO;
import br.gov.msq.dao.CnesDAO;
import br.gov.msq.dao.PerguntaDAO;
import br.gov.msq.pojo.Aplicacao;
import br.gov.msq.pojo.Apoiador;
import br.gov.msq.pojo.Cnes;
import br.gov.msq.pojo.Pergunta;
import br.gov.msq.pojo.PerguntaDOM;
import br.gov.msq.pojo.Questionario;

/**
 * @author marco.nascimento
 */
public class DadosQuestionario {
	
	private static final String[] INPUT_FILES = {"/home/tulio/AmbienteDesenvolvimento/ms/phonegap/msq_banco.txt"};
	
	private PerguntaDAO perguntaDAO = new PerguntaDAO();
	private CnesDAO cnesDAO = new CnesDAO();
	private AplicacaoDAO aplicacaoDAO = new AplicacaoDAO();
	
	public static void main(String[] args) throws Exception {
		new DadosQuestionario().incluirDados();
	}
	
	public void incluirDados() throws Exception {
		List<Aplicacao> aplicacoes = new ArrayList<Aplicacao>();
		
		File folder = new File("/Users/rafaeldemorais/git/msq/msq_util/msq/uploaded");
		File[] listOfFiles = folder.listFiles();
		 
		for (int i = 0; i < listOfFiles.length; i++) {
			String dados = recuperarDados(listOfFiles[i].getAbsolutePath());
			String[] registro = dados.split("_fimRegistro");
			aplicacoes.addAll(criarAplicacoes(criarPerguntasDOM(registro)));
		}
		
		incluirCnes(aplicacoes);
		
		incluirAplicacoes(aplicacoes);
	}
	
	private void incluirCnes(List<Aplicacao> aplicacoes) throws SQLException {
		Set<String> cnes = new HashSet<String>();
		
		cnesDAO.incluirCnes(new Cnes("0"));
		
		for (Aplicacao a : aplicacoes) {
			if(a.getCnes() != null && a.getCnes().getCodigoCnes() != null && !a.getCnes().getCodigoCnes().equals("")) {
				cnes.add(a.getCnes().getCodigoCnes());
			}
		}
		for (String numeroCnes : cnes) {
			cnesDAO.incluirCnes(new Cnes(numeroCnes.trim()));
		}
	}
	
	private void incluirAplicacoes(List<Aplicacao> aplicacoes) throws Exception {
		List<Aplicacao> listValidas = new ArrayList<Aplicacao>(); 
		List<Aplicacao> listInvalidas = new ArrayList<Aplicacao>();
		
		for (Aplicacao a : aplicacoes) {
			
			if(validarAplicacao(a)) {
				listValidas.add(a);
			} else {
				listInvalidas.add(a);
			}
			
			if(perguntaDAO.perguntaExistente(a.getPergunta().getCodigo())) {
				aplicacaoDAO.incluirAplicacoes(a);
			}
		}
		
		System.out.println("Qtde Validos" + listValidas.size());
		System.out.println("Qtde Invalidos" + listInvalidas.size());
		
		for(Aplicacao a : listInvalidas) {
			//System.out.println("Apoiador " + a.getApoiador().getCodigo() + " Pergunta " + a.getPergunta().getCodigo() + " Cnes " + a.getCnes().getCodigoCnes() + " valor " + a.getValor());
		}
	}
	
	private boolean validarAplicacao(Aplicacao a) throws Exception {
		if(a.getApoiador() != null && a.getApoiador().getCodigo() != null 
				&& a.getQuestionario() != null && a.getQuestionario().getCodigo() != null
				&& a.getPergunta() != null && a.getPergunta().getCodigo() != null && !a.getPergunta().getCodigo().equals("")
				&& a.getValor() != null && !a.getValor().equals("")) {
			
				boolean perguntaExiste = perguntaDAO.perguntaExistente(a.getPergunta().getCodigo());
				if(!perguntaExiste) {
					return false;
				}
			
			return true;
		}
		return false;
	}
	
	private List<PerguntaDOM> criarPerguntasDOM(String[] registro) throws Exception {
		List<PerguntaDOM> perguntasDOM = new ArrayList<PerguntaDOM>();
		
		for (int i = 0; i < registro.length; i++) {
			String[] perguntas = registro[i].split("##");
			
			String codigoApoiador = null;
			String codigoQuestionario = null;
			String numeroCNES = null;
			
			for (int k = 0; k < perguntas.length; k++) {
				
				if(perguntas[k].split(",")[1].trim().equals("id=apoiador")) {
					String[] chaveValor = perguntas[k].split(",")[3].trim().split("=");
					if(chaveValor.length == 2 && chaveValor[0].equals("value")) {
						codigoApoiador = chaveValor[1];
					}
				}
				if(perguntas[k].split(",")[1].trim().equals("id=perfilResponsavel")) {
					String[] chaveValor = perguntas[k].split(",")[3].trim().split("=");
					if(chaveValor.length == 2 && chaveValor[0].equals("value")) {
						codigoQuestionario = chaveValor[1];
					}
				}
				if(perguntas[k].split(",")[1].trim().equals("id=nrCNES") && perguntas[k].split(",")[3].split("=").length == 2) {
					String[] chaveValor = perguntas[k].split(",")[3].trim().split("=");
					if(chaveValor.length == 2 && chaveValor[0].equals("value")) {
						numeroCNES = chaveValor[1].trim();
					}
				}
			}
			
			for (int j = 0; j < perguntas.length; j++) {
				
				boolean isPerguntaCnes = perguntas[j].split(",")[1].trim().equals("id=nrCNES");
				boolean isPerguntaApoiador = perguntas[j].split(",")[1].trim().equals("id=apoiador");
				boolean isPerguntaQuestionario = perguntas[j].split(",")[1].trim().equals("id=perfilResponsavel");
				
				if(!isPerguntaCnes && !isPerguntaApoiador && !isPerguntaQuestionario) {
					PerguntaDOM dom = new PerguntaDOM(perguntas[j].split(","));
					
					dom.setCodigoApoiador(codigoApoiador);
					dom.setCodigoQuestionario(codigoQuestionario);
					dom.setNumeroCNES(numeroCNES);
					
					if(dom.getCodigoPergunta() != null && codigoQuestionario != null && codigoApoiador != null) {
						perguntasDOM.add(dom);
					}
				}
			}
		}
		
		return perguntasDOM;
	}
	
	private List<Aplicacao> criarAplicacoes(List<PerguntaDOM> perguntasDOM) throws Exception {
		List<Aplicacao> aplicacoes = new ArrayList<Aplicacao>(0);
		
		for (PerguntaDOM perguntaDOM : perguntasDOM) {
				
			Apoiador apoiador = new Apoiador(perguntaDOM.getCodigoApoiador());
			Questionario questionario = new Questionario(perguntaDOM.getCodigoQuestionario());
			Pergunta pergunta = new Pergunta(perguntaDOM.getCodigoPergunta());
			Cnes cnes = new Cnes(perguntaDOM.getNumeroCNES());
			
			String value = "";
			if(perguntaDOM.getValue() != null && !perguntaDOM.getValue().equals("")) {
				value = perguntaDOM.getValue().replaceAll("'", "");
			}
			
			//varios valores para uma mesma pergunta
			if(value.contains("sep_")) {
				String[] values = value.split("sep_");
				for (int i = 0; i < values.length; i++) {
					Aplicacao aplicacaoMultiValue = new Aplicacao();
					aplicacaoMultiValue.setApoiador(apoiador);
					aplicacaoMultiValue.setQuestionario(questionario);
					aplicacaoMultiValue.setPergunta(pergunta);
					aplicacaoMultiValue.setCnes(cnes);
					aplicacaoMultiValue.setValor(values[i]);
					aplicacoes.add(aplicacaoMultiValue);
				}
			} else {
				Aplicacao aplicacao = new Aplicacao();
				aplicacao.setApoiador(apoiador);
				aplicacao.setQuestionario(questionario);
				aplicacao.setPergunta(pergunta);
				aplicacao.setCnes(cnes);
				aplicacao.setValor(value);
				aplicacoes.add(aplicacao);
			}
		}
		
		return aplicacoes;
	}
	
	private String recuperarDados(String INPUT_FILE) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
		
		System.out.println(INPUT_FILE);
		
		//arquivo com apenas 1 linha
		String dados = reader.readLine();
		
		reader.close();
		return dados;
	}
}