/**
 * Funcoes de manipulacao relativa aos questionarios qualisus-rede
 * 
 * @author: marcotulio.nascimento@gmail.com
 * @since 10/09/2013
 */

var $ = jQuery.noConflict();

//Temporario
//Temporario
//Temporario
var data = new Date();
var dataInicioQuestionario = "" + $.datepicker.formatDate("dd/mm/yy ", data) + " " + completeWithZero(data.getHours()) + ":" + completeWithZero(data.getMinutes()) + ":" + completeWithZero(data.getSeconds());

function completeWithZero(obj) {
	obj = "" + obj;
	return ("" + obj).length == 1 ? "0" + obj : "" +obj;
}
function teste() {
	alert($('#perfilResponsavel').val());
}
//FIM Temporario
//FIM Temporario
//FIM Temporario


jQuery(document).ready(function($) {
	$('#identificacaoContainer').load('./pages/identificacao.html', primeiraInicializacao());
	console.log('DOM ready!');
})

function primeiraInicializacao() {
	
}

function inicializar() {
	$('body').on('keypress', '.apenasNumero', function(e) {
		if(e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			alert('Digite apenas número!');
	        return false;
		}
	});
	
	readText();
	
	//clickRadio();
}

function radioClick(obj) {
	alert(obj.is('checked'));
	alert(obj);
}

function radioClick() {
	alert('not shown');
}

function hide(id) {
	var divOptions = $('#'+id);
	limparElementos(divOptions);
	divOptions.hide();
}

function limparElementos(obj) {
	obj.find('input:text, select, textarea')
    .each(function() {
        $(this).val('');
    });
	
	obj.find('input:radio, input:checkbox').each(function() {
		$(this).removeAttr('checked');
		$(this).removeAttr('selected');
	});
}

function show(id) {
	$('#'+id).show();
}

function hideElements(e) {
	var elementos = e.split(",");
	for (x in elementos) {
		$('#'+ elementos[x]).hide();
	}
}

function showElements(e) {
	var elementos = e.split(",");
	for (x in elementos) {
		$('#'+ elementos[x]).show();
	}
}

function recuperarValoresRadio() {
	
}

/**
 * Cria questionario de acordo com a opcao de perfil selecionada
 */
function createPerfilContainer(obj) {
	if(obj.value == 1) {
		$('#capaContainer').load('./pages/capaSecretarioSaude.html', inicializar);
		$('#perfilContainer').load('./pages/perfilSecretarioSaude.html', inicializar);
	}
	if(obj.value == 2) {
		$('#capaContainer').load('./pages/capaRespAssistFarm.html', inicializar);
		$('#perfilContainer').load('./pages/perfilRespAssistFarm.html', inicializar);
	}
	if(obj.value == 3) {
		$('#capaContainer').load('./pages/capaRespPontoAtencao.html', inicializar);
		$('#perfilContainer').load('./pages/perfilRespPontoAtencao.html', inicializar);
	}
	if(obj.value == 4) {
		$('#capaContainer').load('./pages/capaRespCentralAbastecimentoFarm.html', inicializar);
		$('#perfilContainer').load('./pages/perfilRespCentralAbastecimentoFarm.html', inicializar);
	}
	if(obj.value == 5) {
		$('#capaContainer').load('./pages/capaRespFarmHospitalar.html', inicializar);
		$('#perfilContainer').load('./pages/perfilRespFarmHospitalar.html', inicializar);
	}
}

function configurarModal(codigoPergunta) {
	//alert(codigoPergunta);
    $('#customModal').modal('show');
}

function fecharModal() {
    $('#customModal').modal('hide');
}

function salvarModal() {
	//todo salvar
    $('#customModal').modal('hide');
}

function clickRadio() {
	
	$("#capaContainer").find('input:radio').each(function() {
		//var onClick = $(this).attr('onclick');
		//if(onClick != null && onClick != "" && onClick != "undefined") {
		//	onClick();
		//}
		
		var isChecked = $(this).prop('checked');
		$(this).click(function() {
			isChecked = !isChecked;
			if(isChecked) {
				$(this).removeAttr('checked');
			}
		});
	});
	
	$("#perfilContainer").find('input:radio').each(function() {
		$(this).click(function() {
			if($(this).attr('checked') != 'undefined') {
				$(this).attr('checked', !($(this).attr('checked')));
			}
		});
	});
}

