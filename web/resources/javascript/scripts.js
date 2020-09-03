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