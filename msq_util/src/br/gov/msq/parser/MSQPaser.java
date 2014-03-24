package br.gov.msq.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.gov.msq.dao.ApoiadorDAO;
import br.gov.msq.dao.PerguntaDAO;
import br.gov.msq.dao.QuestionarioDAO;
import br.gov.msq.pojo.Apoiador;
import br.gov.msq.pojo.Pergunta;
import br.gov.msq.pojo.Questionario;

public class MSQPaser {
	
	private static final String PATH = "/home/tulio/git/ms/msq_util/WebContent/html/"; 
	
	private static final String FILE_ENCODING = "UTF-8";
	
	private static final String[] HTML_LIST = {
		/*"capaRespAssistFarm.html",
		"capaRespCentralAbastecimentoFarm.html",
		"capaRespFarmHospitalar.html",
		"capaRespPontoAtencao.html",
		"capaSecretarioSaude.html",*/
		"perfilRespAssistFarm.html",
		"perfilRespCentralAbastecimentoFarm.html",
		"perfilRespFarmHospitalar.html",
		"perfilRespPontoAtencao.html",
		"perfilSecretarioSaude.html",
	};
	
	private static Map<String, String> map = new HashMap<String, String>();;
	
	public static void main(String[] args) {
		
		try {
			for(int i = 0; i < HTML_LIST.length; i++) {
				File entrada = new File(PATH + HTML_LIST[i]);
				Document doc = Jsoup.parse(entrada, FILE_ENCODING);
				
				Elements elementsCodigo = doc.select("select, input, textarea");
				Elements elementsDescricao = doc.select("label");
				
				//preencherCodigos(elementsCodigo);
				//preencherDescricao(elementsDescricao);
				
				opcoes(elementsCodigo);
			}
			
			//incluirQuestionarios();
			//incluirApoiadores();
			//incluirPerguntas();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void opcoes(Elements elements) {
		
		try {
			String content = "pergunta, valor, texto;\n";
			
			for(Element element : elements) {
				
				String codigo = !element.attr("id").equals("") ? element.attr("id") : element.attr("name");
				
				content += "" + codigo + "," + element.val() + "," + element.nextSibling()+ "\r";
				
			}
			System.out.println(content);
			
			File file = new File("/home/tulio/AmbienteDesenvolvimento/ms/phonegap/bancoDeDados/musu.csv");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void preencherCodigos(Elements elements) {
		for(Element element : elements) {
			String codigo = !element.attr("id").equals("") ? element.attr("id") : element.attr("name");
			if(codigo == null || codigo.equals("")) {
				System.out.println("Verificar elemento:" + element.text());
			} else {
				map.put(codigo, "");
			}
		}
	}
	
	private static void preencherDescricao(Elements elements) {
		for(Element element : elements) {
			if(element.tagName().equals("label")) {
				String descricao = element.text();
				String codigo = element.attr("for");
				map.replace(codigo, descricao);
			}
		}
	}
	
	private static void incluirPerguntas() throws Exception {
		PerguntaDAO perguntaDAO = new PerguntaDAO();
		for(Map.Entry<String, String> entry : map.entrySet()) {
			perguntaDAO.incluirPerguntas((new Pergunta(entry.getKey().replaceAll("'", ""), entry.getValue().replaceAll("'", ""))));
		}
	}
	
	private static void incluirApoiadores() throws Exception {
		File entrada = new File(PATH + "identificacao.html");
		Document doc = Jsoup.parse(entrada, FILE_ENCODING);
		Elements elements = doc.select("select");
		
		ApoiadorDAO apoiadorDAO = new ApoiadorDAO();
		for(Element element : elements) {
			String idElemento = !element.attr("id").equals("") ? element.attr("id") : element.attr("name");
			if(idElemento.equals("apoiador")) {
				for (Element e : element.children()) {
					if(!e.attr("value").equals("")) {
						apoiadorDAO.incluirApoiadores(new Apoiador(e.attr("value"), e.text()));
					}
				}
			}
		}
	}
	
	private static void incluirQuestionarios() throws Exception {
		File entrada = new File(PATH + "identificacao.html");
		Document doc = Jsoup.parse(entrada, FILE_ENCODING);
		Elements elements = doc.select("select");
		
		QuestionarioDAO questionarioDAO = new QuestionarioDAO();
		for(Element element : elements) {
			String idElemento = !element.attr("id").equals("") ? element.attr("id") : element.attr("name");
			if(idElemento.equals("perfilResponsavel")) {
				for (Element e : element.children()) {
					if(!e.attr("value").equals("")) {
						questionarioDAO.incluirQuestionario(new Questionario(e.attr("value"), e.text()));
					}
				}
			}
		}
	}
	
}