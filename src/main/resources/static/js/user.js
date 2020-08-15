window.onload = function () {
    `use strict`
    let str = "";
    fetch("/user_data").then(
        res => {
            res.json().then(
                data => {
                    let role = "";
                    $.each(data.roles, function (index, value) {
                        role += value.name + " ";
                    })
                    str = `<strong>${data.login}</strong> with roles: ${role}`;
                    document.getElementById("head").innerHTML = str;
                })
        })

    $('document').ready(function () {
        $.ajax('/user_data', {
            method: 'GET',
            success: function (user) {
                $('#id').text(user.id);
                $('#firstName').text(user.firstName);
                $('#lastName').text(user.lastName);
                $('#age').text(user.age);
                $('#login').text(user.login)
                user.roles.forEach(function (role) {
                    $('#roleTitle').append('<strong>' + role.simpleName + ' </strong>');
                    $('#role').append('<span>' + role.simpleName + ' </span>');
                    if (role.simpleName === 'ADMIN') {
                        $('#nav').prepend('<li class="nav-item"><a class="nav-link border rounded" href="/admin">Admin</a></li>');
                    }
                })
            }
        })
    })
}