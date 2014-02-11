package br.gov.msq;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author marco.nascimento
 */
public class DadosQuestionario {

	//Arquivo gerado pelo app
	private static final String INPUT_FILE = "/home/tulio/AmbienteDesenvolvimento/ms/phonegap/msq_banco.txt";

	//saida em formato excel
	private static final String OUTPUT_XLS = "/home/tulio/AmbienteDesenvolvimento/ms/phonegap/poi-test.xls";
	
	
	
	public static void main(String[] args) throws Exception {
		
		FileOutputStream fileOut = new FileOutputStream(OUTPUT_XLS);
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet worksheet = workbook.createSheet("Dados - Questionario");
		
		Map<String, List<String>> mapQuestionario = new HashMap<String, List<String>>();
		
		String dados = recuperarDados();
		String[] registro = dados.split("_fimRegistro");
		
		List<DadosWrapper> wrapperList = new ArrayList<DadosWrapper>();
		
		for (int i = 0; i < registro.length; i++) {
			
			DadosWrapper dado = new DadosWrapper();
			dado.setNumeroLinha(++i);
			
			//System.out.println("numero linha: " + i);
			
			String[] pergunta = registro[i].split("##");
			for (int j = 0; j < pergunta.length; j++) {
				
				String[] atributosDOM = pergunta[j].split(",");
				
				Map<String, String> map = new HashMap<String, String>();
				for (String atributo : atributosDOM) {
					if(atributo.split("=").length == 2) {
						map.put(atributo.split("=")[0].trim(), atributo.split("=")[1]);
					}
				}
				String chave = map.get("id") != null && !map.get("id").equals("") ? map.get("id") : map.get("name");
				String valor = map.get("value");
				
				if(chave != null && !chave.equals("") && valor != null && !valor.equals("")) {
					if(mapQuestionario.get(chave) == null) {
						List<String> lst = new ArrayList<String>();
						lst.add(valor);
						mapQuestionario.put(chave, lst);
					} else {
						mapQuestionario.get(chave).add(valor);
					}
					
					dado.setChave(chave);
					dado.setValor(valor);
					dado.setNumeroColuna(j);
					wrapperList.add(dado);
				}
			}
		}
		
		//System.out.println("size:" + mapQuestionario.keySet().size() + " -- " +mapQuestionario.keySet());
		
		HSSFRow header = criarLinha(worksheet, 0);
		for (int i = 0; i < mapQuestionario.keySet().toArray().length; i++) {
			/**
			 * procurar fix, esta suportando apenas 256 colunas, atualmente temos 359 e outras colunas vao surgir 
			 */
			if(i < 256)
				header.createCell(i).setCellValue(mapQuestionario.keySet().toArray()[i].toString());
		}
		
		for (DadosWrapper dado : wrapperList) {
			HSSFRow linha = criarLinha(worksheet, dado.getNumeroLinha());
			linha.createCell(dado.getNumeroColuna()).setCellValue(dado.getValor());
		}
	    
		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
			
	}
	
	private static String recuperarDados() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
		
		//arquivo com apenas 1 linha
		String dados = reader.readLine();
		
		reader.close();
		return dados;
	}
	
	private static HSSFRow criarLinha(HSSFSheet worksheet, int numero) {
		HSSFRow linha = worksheet.createRow(numero);
		return linha;
	}
}