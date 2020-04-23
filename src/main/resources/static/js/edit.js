function editUserRepl(id) {
    fetch(url + "/" + id).then(function (response) {
        response.json().then(function (data) {
            var modal = document.forms["editUserForm"].elements;
            modal.idEdit.setAttribute("value", id);
            modal.name.setAttribute("value", data.name);
            modal.login.setAttribute("value", data.login);
            modal.password.setAttribute("value", data.password);
        })
    });
}

function editUserFetch() {
    event.preventDefault();
    var url = "http://localhost:3000/api/users";
    var newUserForm = document.forms["editUser"].elements;
    var id = newUserForm.idEdit.value;
    var name = newUserForm.name.value;
    var login = newUserForm.login.value;
    var password = newUserForm.password.value;
    var roleAdmin = newUserForm.roleAdmin.checked ? {id: 1, name: "ADMIN"} : null;
    var roleUser = newUserForm.roleUser.checked ? {id: 2, name: "USER"} : null;

    document.getElementById("close").click();
    var body = {
        id: id,
        name: name,
        login: login,
        password: password,
        roles: [roleAdmin, roleUser]
    };

    console.log("edit", JSON.stringify(body));

    fetch(url, {
        method: 'put',
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }, credentials: "include",
        body: JSON.stringify(body)
    })
        .then(json)
        .then(function (data) {
            console.log('Request succeeded with JSON response', data);
            var tr = '<tr id='+ data.id + '>' + oneTr(data) + '</tr>';
            document.getElementById(id).innerHTML = tr;
        })
        .catch(function (error) {
            console.log('Request failed', error);
        });
}

function json(response) {
    return response.json()
}