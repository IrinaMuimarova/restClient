function addUser() {
    var url = "http://localhost:3000/api/users";
    var newUserForm = document.forms["newUser"].elements;
    var name = newUserForm.name.value;
    var login = newUserForm.login.value;
    var password = newUserForm.password.value;
    var roleAdmin = newUserForm.roleAdmin.checked ? {id: 1} : null;
    var roleUser = newUserForm.roleUser.checked ? {id: 2} : null;
    console.log(roleUser);

    var body = {
        name: name,
        login: login,
        password: password,
        roles: [roleAdmin, roleUser]
    };

    console.log(JSON.stringify(body));

    fetch(url, {
        method: 'post',
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }, credentials: "include",
        body: JSON.stringify(body)
    })
        .then(json)
        .then(function (data) {
            console.log('Request succeeded with JSON response', data);
        })
        .catch(function (error) {
            console.log('Request failed', error);
        });
}

function json(response) {
    return response.json()
}