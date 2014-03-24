/**
 * @author: marcotulio.nascimento@gmail.com
 * @since 31/01/2014
 */


var fileURI = "file:///storage/sdcard/msqV04a.db";

//Local
//var serverURI = "http://192.168.0.117:8080/msq_util/UploadFile";

//Producao
var serverURI = "http://189.28.128.18/msq_util/UploadFile";
										
var win = function (r) {
	
    console.log("Code = " + r.responseCode);
    console.log("Response = " + r.response);
    console.log("Sent = " + r.bytesSent);
    
    if(r.responseCode == 200) {
    	alert("Dados enviados com sucesso!");
    	
    	document.getElementById('progressBar').value = 100;
    	document.getElementById('labelProgressBar').innerHTML = "Status de envio: 100%";
    }
}

var fail = function (error) {
    alert("Ocorreu um erro ao transferir o arquivo, verfique a conexão de internet e tente novamente. COD - " + error.code);
    
    document.getElementById('progressBar').value = 0;
	document.getElementById('labelProgressBar').innerHTML = "Status de envio: 0%";
    
    console.log("upload error source " + error.source);
    console.log("upload error target " + error.target);
}

function uploadMSQFile() {
	var options = new FileUploadOptions();
	options.fileKey = "file";
	options.fileName = fileURI.substr(fileURI.lastIndexOf('/') + 1);
	options.mimeType = "text/plain";
	
	var params = {};
	params.apoiadorEnvio = "" + document.getElementById("apoiadorEnvio").value;
	options.params = params;
	
	NProgress.start();
	
	var ft = new FileTransfer();
	ft.onprogress = function(progressEvent) {
		
		var percent = 0;
		
		if(progressEvent.lengthComputable) {
			 percent = Math.floor(progressEvent.loaded / progressEvent.total * 100);
			 NProgress.set(progressEvent.loaded / progressEvent.total);
		} else {
		      ++percent
		      NProgress.inc();
		}
		
		document.getElementById('progressBar').value = percent;
		document.getElementById('labelProgressBar').innerHTML = "Aguarde... Status de envio: " + percent + "%";
		
	};
	ft.upload(fileURI, encodeURI("" + serverURI), win, fail, options);
	
	NProgress.done();
}