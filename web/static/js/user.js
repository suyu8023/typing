$(document).ready(function () {
    $.ajax({
        type: 'get',
        dataType: "text",
        async: false,
        url: "../LoginServlet?name=",
        success: function (data) {
            // console.log(data);
            var str = data.toString();
            str = str.split('-');
            // console.log(str[0] + "-=" + str[1] + "-=" + str[2] + "-=" + str[3] + "-=" + str[4]);
            document.getElementById("user").innerHTML = str[1];
            document.getElementById("user11").innerHTML = str[1];
            if (document.getElementById("uid") && document.getElementById("username") && document.getElementById("nickname") && document.getElementById("email") && document.getElementById("Permission") && document.getElementById("uid1") && document.getElementById("username1") && document.getElementById("nickname1") && document.getElementById("email1") && document.getElementById("Permission1")) {
                document.getElementById("uid").value = str[0];
                document.getElementById("username").value = str[1];
                document.getElementById("nickname").value = str[2];
                document.getElementById("email").value = str[3];
                document.getElementById("Permission").value = str[4];
                document.getElementById("uid1").value = str[0];
                document.getElementById("username1").value = str[1];
                document.getElementById("nickname1").value = str[2];
                document.getElementById("email1").value = str[3];
                document.getElementById("Permission1").value = str[4];
            }
        }
    });
});

function qaq() {
    $.ajax({
        type: 'get',
        dataType: "text",
        async: false,
        url: "../LoginServlet?name=",
        success: function (data) {
            // console.log(data);
            var str = data.toString();
            str = str.split('-');
            console.log(str[0] + "-=" + str[1] + "-=" + str[2] + "-=" + str[3] + "-=" + str[4]);
            document.getElementById("user").innerHTML = str[1];
            document.getElementById("user11").innerHTML = str[1];
            if (document.getElementById("uid") && document.getElementById("username") && document.getElementById("nickname") && document.getElementById("email") && document.getElementById("Permission") && document.getElementById("uid1") && document.getElementById("username1") && document.getElementById("nickname1") && document.getElementById("email1") && document.getElementById("Permission1")) {
                document.getElementById("uid").style.borderColor = "";
                document.getElementById("username").style.borderColor = "";
                document.getElementById("nickname").style.borderColor = "";
                document.getElementById("email").style.borderColor = "";
                document.getElementById("Permission").style.borderColor = "";
                document.getElementById("uid1").style.borderColor = "";
                document.getElementById("username1").style.borderColor = "";
                document.getElementById("nickname1").style.borderColor = "";
                document.getElementById("email1").style.borderColor = "";
                document.getElementById("Permission1").style.borderColor = "";
                document.getElementById("password").style.borderColor = "";
                document.getElementById("password1").style.borderColor = "";
                document.getElementById("user1").style.borderColor = "";
                document.getElementById("user1").innerHTML = "";
                document.getElementById("user2").style.borderColor = "";
                document.getElementById("user2").innerHTML = "";
                document.getElementById("user3").style.borderColor = "";
                document.getElementById("user3").innerHTML = "";
                document.getElementById("user4").style.borderColor = "";
                document.getElementById("user4").innerHTML = "";
                document.getElementById("user5").style.borderColor = "";
                document.getElementById("user5").innerHTML = "";
                document.getElementById("uid").value = str[0];
                document.getElementById("username").value = str[1];
                document.getElementById("nickname").value = str[2];
                document.getElementById("email").value = str[3];
                document.getElementById("Permission").value = str[4];
                document.getElementById("uid1").value = str[0];
                document.getElementById("username1").value = str[1];
                document.getElementById("nickname1").value = str[2];
                document.getElementById("email1").value = str[3];
                document.getElementById("Permission1").value = str[4];
            }
        }
    });

}