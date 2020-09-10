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