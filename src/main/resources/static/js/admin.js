`use strict`

$(document).ready(function () {
    show_users()
})

function show_users() {
    $(`#table_data`).empty();
    $.ajax("/users", {
        dataType: "json",
        method: 'GET',
        success: function (data) {
            console.log(data);
            let users = JSON.parse(JSON.stringify(data));
            users.forEach(function (user) {
                let role = "";
                $.each(user.roles, function (index, value) {
                    role += value.name + " ";
                })
                $("#table_data").append(`<tr class= "vertical-align: middle" id="tr${user.id}">
                            <td>${user.id}</td>
                            <td id="firstName${user.id}">${user.firstName}</td>
                            <td id="lastName${user.id}">${user.lastName}</td>
                            <td id="age${user.id}">${user.age}</td>
                            <td id="login${user.id}">${user.login}</td>
                            <td id="roles${user.id}">${role}</td>
                            <td>
                                <button id="edit" class="btn btn-info btn-sm" data-toggle="modal"
                                        data-target="#editModal" onclick="doWhenOpeningEditModal('${user.id}')">Edit
                                </button>
                            </td>
                            <td>
                                <button id="delete" class="btn btn-danger btn-sm" data-toggle="modal"
                                        data-target="#deleteModal" onclick="doWhenOpeningDeleteModal('${user.id}')">Delete
                                </button>
                            </td>
                        </tr>`)
            })
        }
    })
}