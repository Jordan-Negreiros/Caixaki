/* Facilita a obtenção de valores dos componentes bem como trabalhar com ajax */
let arrayIdsElementsPage = [];

function carregarIdElementosPagina() {
    for (let form = 0; form <= document.forms.length; form++) {
        let formAtual = document.forms[form];
        if (formAtual !== undefined) {
            for (let i = 0; i < document.forms[form].elements.length; i++) {
                if (document.forms[form].elements[i].id !== '') {
                    arrayIdsElementsPage[i] = document.forms[form].elements[i].id;
                }
            }
        }
    }
}

/**
 * Retorna o valor do id do componente dentro do documento html passando como
 * parametro a descrição do id declarada em jsf
 */
function getValorElementPorId(id) {
    carregarIdElementosPagina();
    for (let i = 0; i < arrayIdsElementsPage.length; i++) {
        let valor = "" + arrayIdsElementsPage[i];
        if (valor.indexOf(id) > -1) {
            return valor;
        }
    }
    return undefined;
}

// invalida a sessão do spring security
function logout(contextPath) {
    let post = 'invalidate_session';
    $.ajax(
        {
            type: "POST",
            url: post
        }).always(function (resposta) {
        document.location = contextPath + '/j_spring_security_logout';
    });

}

/* Traduz o calendario do primefaces para português  */
function localeData_pt_br() {
    PrimeFaces.locales['pt'] = {
        closeText : 'Fechar',
        prevText : 'Anterior',
        nextText : 'Próximo',
        currentText : 'Começo',
        monthNames : [ 'Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio',
            'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro',
            'Dezembro' ],
        monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul',
            'Ago', 'Set', 'Out', 'Nov', 'Dez' ],
        dayNames : [ 'Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta',
            'Sexta', 'Sábado' ],
        dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb' ],
        dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S' ],
        weekHeader : 'Semana',
        firstDay : 0,
        isRTL : false,
        showMonthAfterYear : false,
        yearSuffix : '',
        timeOnlyTitle : 'São Horas',
        timeText : 'Tempo',
        hourText : 'Hora',
        minuteText : 'Minuto',
        secondText : 'Segundo',
        ampm : false,
        month : 'Mês',
        week : 'Semana',
        day : 'Dia',
        allDayText : 'Todo o Dia'
    };

}

function invalidateSession(context, pagina) {
    document.location = (context + pagina + '.jsf');
}

function validateSenhaLogin() {
    let j_username = document.getElementById("j_username").value;
    let j_password = document.getElementById("j_password").value;

    if (j_username === null || j_username.trim() === '') {
        alert('Informe o Login');
        $('#j_username').focus();

        return false;
    }

    if (j_password === null || j_password.trim() === '') {
        alert('Informe o Senha');
        $('#j_password').focus();

        return false;
    }

    return true;
}

function initTamplete() {
    $(document).ready(function() {
        $('#menuPop').hide();
        $('#barraMenu').hide();
        $('#barraMenu').css("left", "-200px");
        $('#iniciarMenu').click(function() {
            if ($('#barraMenu').is(':visible')) {
                ocultarMenu();
            } else {
                $('#barraMenu').show();
                $('#barraMenu').animate({"left":"0px"}, "slow");
            }
        });
    });
}

/* Oculta menu lateral */
function ocultarMenu() {
    $('#barraMenu').animate({"left":"-200px"}, "slow", function() {
        $('#barraMenu').hide();
    });
}

/* Menu aberto ao clicar na seta ao lado do botão de sair */
function abrirMenuPop() {
    $("#menuPop").show('slow').mouseleave(function () {
        fecharMenuPop();
    });
}

function fecharMenuPop() {
    if ($("#menuPop").is(":visible")) {
        $("#menuPop").hide("slow")
    }
}

function redirecionarPagina(context, pagina) {
    pagina = pagina + '.jsf';
    document.location = context + pagina;
}