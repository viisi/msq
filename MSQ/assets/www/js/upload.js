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
    }
}

var fail = function (error) {
    alert("Ocorreu um erro ao transferir o arquivo, verfique a conexão de internet e tente novamente. COD - " + error.code);
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
	
	var ft = new FileTransfer();
	ft.onprogress = function(progressEvent) {
		if (progressEvent.lengthComputable) {
			var perc = Math.floor(progressEvent.loaded / progressEvent.total * 100);
			//$('#progress').html("" + perc + "% enviado...");
		} else {
			//$('#progress').html("Arquivo enviado!...");
		}
	};
	ft.upload(fileURI, encodeURI("" + serverURI), win, fail, options);
}