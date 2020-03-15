function checkPass()
{
    var pass1 = document.getElementById('password1');
    var pass2 = document.getElementById('password2');
    var message = document.getElementById('error-nwl');
    var goodColor = "#66cc66";
    var badColor = "#ff6666";

    if(pass1.value.length > 5)
    {
        pass1.style.backgroundColor = goodColor;
        message.style.color = goodColor;
        message.innerHTML = "Количество символов достаточно"
    }
    else
    {
        pass1.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = " Пароль должен содержать 6 и более символов"
        return;
    }

    if(pass1.value === pass2.value)
    {
        pass2.style.backgroundColor = goodColor;
        message.style.color = goodColor;
        message.innerHTML = "Всё верно!"
    }
    else
    {
        pass2.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = "Пароли не совпадают"
    }
}

function checkPhone() {
    var number = document.getElementById("number");
    var badColor = "#ff6666";
    var message = document.getElementById('error-number');
    if (number.value.length > 12)
    {
        message.style.color = badColor;
        message.innerText = "Номер телефона должен содержать менее 12 цифр";
    }
    else
    {
        message.innerText = "";
    }

}