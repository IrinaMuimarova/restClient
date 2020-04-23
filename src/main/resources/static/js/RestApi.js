var url = "http://localhost:3000/api/users";
fetch(url, {credentials: "include"})
    .then(
        function (response) {
            if (response.status !== 200) {
                console.log('Looks like there was a problem. Status Code: ' +
                    response.status);
                return;
            }
            // Examine the text in the response
            response.json().then(function (data) {
                for (let i = 0; i < data.length; i++) {
                    document.getElementById("users").innerHTML += '<tr id='+ data[i].id + '>' + oneTr(data[i]) + '</tr>';
                }
            });
        }
    )
    .catch(function (err) {
        console.log('Fetch Error :-S', err);
    });


function oneTr(u) {
    return '<td id="id">' + u['id'] + '</td> ' +
        '<td>' + u['name'] + '</td> ' +
        '<td>' + u['login'] + '</td> ' +
        '<td>' + getRoleName(u.roles) +
        '</td> ' + '<td class="d-flex justify-content-around"> ' +
        '<a class="btn btn-primary eBtn" data-toggle="modal" data-target="#editUser" onclick="editUserRepl(' + u.id + ')">Edit</a> ' +
        '<a class="btn btn-danger dBtn" onclick="deleteUser(' + u.id + ')" ' +
        ' >Delete</a></td>'
}

function getRoleName(u) {
    var s = "";
    for (let i = 0; i < u.length; i++) {
        s += u[i].name + ", "
    }
    s = s.substr(0, s.length - 2);
    return s;

}

function deleteUser(id) {
    fetch(url + "/" + id, {method: 'delete', credentials: "include"})
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                }
                document.getElementById(id).remove();
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

