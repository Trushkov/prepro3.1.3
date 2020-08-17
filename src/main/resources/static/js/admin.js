`use strict`

$('document').ready(function () {
    $.ajax('/user_data', {
        method: 'GET',
        success: function (user) {
            $('#nameTitle').text(user.login)
            user.roles.forEach(function (role) {
                $('#roleTitle').append('<span>' + role.name + ' </span>');
                $('#role').append('<span>' + role.name + ' </span>');
            })
        }
    })
    show_users();
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

//добавление юзера
$('#add_user').on('click', function (event) {
    event.preventDefault();
    let user = {
        id: $("#id_input").val(),
        firstName: $("#firstName_input").val(),
        lastName: $("#lastName_input").val(),
        age: $("#age_input").val(),
        login: $("#login_input").val(),
        password: $("#password_input").val(),
        roles: $("#roles_input").val()
    };

    $.ajax(`/users/add`, {
        data: JSON.stringify(user),
        dataType: 'json',
        contentType: 'application/JSON; charset=utf-8',
        method: 'POST',
        success: function () {
            $('#tab1').addClass('active');
            $('#tab-1').addClass('active');
            $('#tab2').removeClass('active');
            $('#tab-2').removeClass('active');
            show_users();
        }
    })
})

//заполняем форму удаления данными юзера
function doWhenOpeningDeleteModal(id) {
    let firstName = $('#firstName' + id).text();
    let lastName = $('#lastName' + id).text();
    let age = $('#age' + id).text();
    let login = $('#login' + id).text();
    let roles = $('#roles' + id).text().trim().split(" ");
    $('#id_delete').val(id);
    $('#firstName_delete').val(firstName);
    $('#lastName_delete').val(lastName);
    $('#age_delete').val(age);
    $('#login_delete').val(login);
    $('#roles_delete').empty();
    $.each(roles, function (key, value) {
        $('#roles_delete').append('<option value="' + key + '">' + value + '</option>');
    });
}

//обработка нажатия кнопки удаления юзера
$('#deleteButton').on('click', function deleteUser(event) {
    let id = $('#deleteModal #id_delete').val();
    console.log(id)
    $.ajax('/users/' + id, {
        method: 'DELETE',
        success: function () {
            $("#table_data").find('#tr' + id).remove();
            show_users();
        }
    })
})

//заполняем форму редактирония данными юзера
function doWhenOpeningEditModal(id) {
    let firstName = $('#firstName' + id).text();
    let lastName = $('#lastName' + id).text();
    let age = $('#age' + id).text();
    let login = $('#login' + id).text();
    let roles = $('#roles' + id).text().trim().split(" ");
    console.log(firstName + lastName + age + login + roles)
    $('#id_edit').val(id);
    $('#firstName_edit').val(firstName);
    $('#lastName_edit').val(lastName);
    $('#age_edit').val(age);
    $('#login_edit').val(login);
}

//обработка нажатия кнопки редактирования
$('#editButton').on('click', function (event) {

    let user = {
        id: $("#id_edit").val(),
        firstName: $("#firstName_edit").val(),
        lastName: $("#lastName_edit").val(),
        age: $("#age_edit").val(),
        login: $("#login_edit").val(),
        password: $("#password_edit").val(),
        roles: $("#roles_edit").val()
    };
    $.ajax('/users/edit', {
        data: JSON.stringify(user),
        dataType: 'json',
        contentType: 'application/JSON; charset=utf-8',
        method: 'PUT',
        success: function () {
            show_users();
        }
    })
})

