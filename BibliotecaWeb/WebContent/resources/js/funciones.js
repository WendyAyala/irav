function fechaCompleta() {
	var meses = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo",
			"Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre",
			"Diciembre");
	var diasSemana = new Array("Domingo", "Lunes", "Martes", "Miercoles",
			"Jueves", "Viernes", "Sabado");
	var f = new Date();
	return diasSemana[f.getDay()] + ", " + f.getDate() + " de "
			+ meses[f.getMonth()] + " de " + f.getFullYear();
}

function validarPopUp(args, idPopUp) {
	if (!args.validationFailed) {
		PF(idPopUp).hide();
	}
}

function validarClientePopUp(args, idPopUp) {
	if (!args.validationClienteFailed) {
		PF(idPopUp).show();
	}
}

function validarFormOpenPopUp(args, idForm, idPopUp) {
	if (!args.validationFailed) {
		PF(idPopUp).show();
	}
}

// VALIDATIONCLIENTEFAILED Y SI DE AHI MANDO A DESPLEGAR EL POPUP...

function validarPopUpShowPopUp(args, idPopUp1, idPopUp2) {
	if (!args.validationFailed) {
		PF(idPopUp1).hide();
		PF(idPopUp2).show();
	}
}

function start() {
	PF('statusDialog').show();
	// alert('start');
}

function stop() {
	PF('statusDialog').hide();
	// alert('stop');
}

function formModal(modal, facesContext) {

	if (facesContext.validationFailed) {
		PF(modal).show();
	}

}

function clickFlow(item, e) {
	// prevents image opening...
	if ($(item).parent().hasClass('active')) {
		e.stopImmediatePropagation();
	}
}
