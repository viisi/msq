/**
 * @author: marcotulio.nascimento@gmail.com
 * @since 6/11/2013
 */

var FILENAME = 'msqV04a.db',

	$ = jQuery.noConflict(),
	
	failCB = function (msg) {
		return function () {
			alert('[FAIL] ' + msg);
		}
	},
	
	file = {
		writer: { available: false },
		reader: { available: false }
	},
	
	dbEntries = "";
 
document.addEventListener("deviceready", deviceready, false);

function deviceready() {
	var fail = failCB("requestFileSystem");
	console.log("Persistencia: deviceready, request filesystem...");
	window.requestFileSystem(LocalFileSystem.PERSISTENT, 0, gotFS, fail);
}
 
function gotFS(fs) {
	var fail = failCB('getFile');
	console.log("Persistencia: FileSystem name: " + fs.name);
	console.log("Persistencia: FileSystem root name: " + fs.root.name);
	fs.root.getFile(FILENAME, {create: true, exclusive: false}, gotFileEntry, fail);
}
 
function gotFileEntry(fileEntry) {
	var fail = failCB("createWriter");
	
	console.log("Persistencia: file entry ...");
	file.entry = fileEntry;
	
	console.log("Persistencia: criando writter ...");
	fileEntry.createWriter(gotFileWriter, fail);
	
	console.log("Persistencia: file entry ...");
	//readText();
}
 
function gotFileWriter(fileWriter) {
	console.log("Persistencia: definindo opcoes do writter...")
	file.writer.available = true;
	file.writer.object = fileWriter;
}
 
function salvarDados(dados) {
	
	console.log("Persistencia: salvando dados...  ");
 
	if(file.writer.available) {
		file.writer.available = false;
		file.writer.object.onwriteend = function (evt) {
			file.writer.available = true;
		}
		file.writer.object.seek(file.writer.object.length);
		file.writer.object.write(dados);
		
		console.log("Persistencia: dados salvo");
	}
 
	return false;
}

function preencherEstatistica(dados) {
	
}

function readText() {
	console.log("Persistencia: Lendo arquivo e dados...");
	
	if (file.entry) {
		file.entry.file(function (dbFile) {
			var reader = new FileReader();
			reader.onloadend = function (evt) {
				
				var dados = evt.target.result.split("_fimRegistro");
				
				if(dados != null) {
					var strHTML = ""; 
					for(var i = 0; i < dados.length-1; i++) {
						strHTML += "INICIO REGISTRO <br />" + dados[i] + "<br />FIM REGISTRO<br /><br />";
					}
					$('#savedText').html("Abrir a opção ES3E (navegador de diretorio) entrar na opção sdcard, enviar o arquivo /sdcard/msqV04a.db por e-mail para: marco.nascimento@saude.gov.br");
					//preencherQuestionario(dados[0]);
				}
				//preencherEstatistica();
			}
			reader.readAsText(dbFile);
		}, failCB("FileReader"));
		
	    file.entry.copyTo(
	    	new DirectoryEntry({ fullPath: "file:///sdcard/Download/"}),
	        "msq.txt",
	        function (newEntry) {
	            console.log("FileEntry copy to done. New Path: " + newEntry.fullPath);
	         },
	         function (error) {
	             console.log("FileEntry copy to fail. Error code: " + error.code);
	         }
	     )
		
	}
	
	return false;
}

function preencherQuestionario(dados) {
	
	var arrDados = dados.split("##"); 
	
	var id, name, type, value;
	
	try {
		for(var i = 0; i < arrDados.length; i++) {
			
			if(arrDados[i] != "" && arrDados[i].length > 1) {
				name = arrDados[i].split(',')[0].split('=')[1];
				id = arrDados[i].split(',')[1].split('=')[1];
				type = arrDados[i].split(',')[2].split('=')[1];
				value = arrDados[i].split(',')[3].split('=')[1];
				
				if(id != null && id.length > 0) {
					$("#" + id).val(value);
				}
				
				if(name != null && name.length > 0 && value != null && value.length > 0) {
					console.log("---------- Name=" + name + ", value=" + value + ", splitted="+value.split("sep_"));
					$("[name=" + name + "]").val( value.split("sep_") );
				}
			}
		}
	} catch (e) {
		alert("Erro: " + e);
	}
}


function salvar() {
	try {

		var dados = prepararDados();
		salvarDados(dados);
		
		document.getElementById("formQ").reset();
		
		alert("Questionário salvo com sucesso!");
		
	} catch (e) {
		alert("error:" + e);
	}
}

function prepararDados() {
	
	var map = obterValores();
	
	var output = "";
	for(x in map) {
		output += x + ",value=" +  map[x] + "##";
	}
	output += "_fimRegistro";
	
	return output;
}

function obterValores() {
	var map = new Object();
	var chave;
	
	var idComponente = ["perfilContainer", "identificacaoContainer"];
	
	for(var i = 0; i < idComponente.length; i++) {
		$("#" + idComponente[i]).find("input:radio:checked, input:checkbox:checked, option:selected, input:text")
	    .each(function() {
	    	
		    	var isTagSelect = $(this).is("option") && $(this).parent().is("select");
		    	
		    	if(isTagSelect && $(this).val() != '') {
		    		chave = 'name=' + $(this).parent().prop('name') + ', id=' + $(this).parent().prop('id') + ', type=' + $(this).parent().prop('tagName');
		    		map[chave] = $(this).val();
		    	} else {
		    		chave = 'name=' + $(this).prop('name') + ', id=' + $(this).prop('id') + ', type=' + $(this).prop('tagName');
		    		if(map[chave] != null && map[chave] != "") {
		    			map[chave] = map[chave] + "sep_" + $(this).val();
		    		} else {
		    			map[chave] = $(this).val();
		    		}
		    	}
	    	}
	    );
	}
	
	return map;
}