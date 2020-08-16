window.onload = function () {
    `use strict`
     $('document').ready(function () {
        $.ajax('/user_data', {
            method: 'GET',
            success: function (user) {
                $('#id').text(user.id);
                $('#firstName').text(user.fistName);
                $('#lastName').text(user.lastName);
                $('#age').text(user.age);
                $('#login').text(user.login);
                $('#nameTitle').text(user.login)
                user.roles.forEach(function (role) {
                    $('#roleTitle').append('<span>' + role.name + ' </span>');
                    $('#role').append('<span>' + role.name + ' </span>');
                    if (role.name === 'ADMIN') {
                        $('#nav').prepend('<li class="nav-item"><a class="nav-link border rounded" href="/admin">Admin</a></li>');
                    }
                })
            }
        })
    })
}