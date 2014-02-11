package br.gov.msq.parser;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.gov.msq.dao.ApoiadorDAO;
import br.gov.msq.pojo.Apoiador;

public class MSQPaser {
	
	private static final String PATH = "/home/tulio/git/ms/msq_util/WebContent/html/"; 
	
	private static final String FILE_ENCODING = "UTF-8";
	
	private static final String[] HTML_LIST = {
		"capaRespAssistFarm.html",
		"capaRespCentralAbastecimentoFarm.html",
		"capaRespFarmHospitalar.html",
		"capaRespPontoAtencao.html",
		"capaSecretarioSaude.html",
		"identificacao.html",
		"perfilRespAssistFarm.html",
		"perfilRespCentralAbastecimentoFarm.html",
		"perfilRespFarmHospitalar.html",
		"perfilRespPontoAtencao.html",
		"perfilSecretarioSaude.html",
	};
	
	public static void main(String[] args) {
		
		try {
			Set<String> colunas = new HashSet<String>();
			
			for(int i = 0; i < HTML_LIST.length; i++) {
				File entrada = new File(PATH + HTML_LIST[i]);
				Document doc = Jsoup.parse(entrada, FILE_ENCODING);
				Elements elements = doc.select("select, input, textarea");
				
				for(Element element : elements) {
					String coluna = !element.attr("id").equals("") ? element.attr("id") : element.attr("name");
					
					if(coluna.equals("apoiador")) {
						incluirApoiadores(element);
					}
					
					colunas.add(coluna);
				}
			}
			
			System.out.println("Size:"+ colunas.size() + " " + Arrays.toString(colunas.toArray()));
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void incluirApoiadores(Element element) throws Exception {
		ApoiadorDAO apoiadorDao = new ApoiadorDAO();
		for (Element e : element.children()) {
			if(!e.attr("value").equals("")) {
				apoiadorDao.incluirApoiadores(new Apoiador(Integer.valueOf(e.attr("value")), e.text()));
			}
		}
	}

}
