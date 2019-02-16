function jud1(status) {
    var username = document.getElementById("username");
    var nickname = document.getElementById("nickname");
    var email = document.getElementById("email");
    var flag = 1;
    if (status == 1 || status == 4) {
        if (username.value.length <= 0) {
            document.getElementById("user1").innerHTML = "<font color='red'><i class='fa fa-times'></i>用户名不能为空</font>";
            document.getElementById("username").style.borderColor = "red";
            flag = 0;
        }
        else if (username.value.length >13) {
            document.getElementById("user1").innerHTML = "<font color='red'><i class='fa fa-times'></i>用户名最长13位</font>"
            document.getElementById("username").style.borderColor = "red";
            flag=0;
        }
        else {
            var s = /^[a-zA-Z0-9]+$/;
            var ss = s.test(username.value);
            if (!ss) {
                document.getElementById("user1").innerHTML = "<font color='red'><i class='fa fa-times'></i>用户名只能是数字和字母的组合</font>";
                document.getElementById("username").style.borderColor = "red";
                flag = 0;
            }
            else {
                document.getElementById("user1").innerHTML = "";
                document.getElementById("username").style.borderColor = "green";
            }
        }
    }
    if (status == 2 || status == 4) {
        if (nickname.value.length <= 0) {
            document.getElementById("user2").innerHTML = "<font color='red'><i class='fa fa-times'></i>昵称不能为空</font>";
            document.getElementById("nickname").style.borderColor = "red";
            flag = 0;
        }
        else {
            if (nickname.value.length > 15) {
                document.getElementById("user2").innerHTML = "<font color='red'><i class='fa fa-times'></i>昵称长度不得超过15!</font>";
                document.getElementById("nickname").style.borderColor = "red";
                flag = 0;
            }
            else {
                document.getElementById("user2").innerHTML = "";
                document.getElementById("nickname").style.borderColor = "green";
            }
        }
    }
    if (status == 3 || status == 4) {
        var s = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        var ss = s.test(email.value);
        if (!ss) {
            document.getElementById("user3").innerHTML = "<font color='red'><i class='fa fa-times'></i>邮箱输入有误!</font>";
            document.getElementById("email").style.borderColor = "red";
            flag = 0;
        }
        else {
            document.getElementById("user3").innerHTML = "";
            document.getElementById("email").style.borderColor = "green";
        }
    }
    if (flag==0){
        return false;
    }
}
function jud2(status) {
    var password = document.getElementById("password");
    var password1 = document.getElementById("password1");
    var flag = 1;
    if (status==4||status==6){
        if (password.value.length <= 0) {
            document.getElementById("user4").innerHTML = "<font color='red'><i class='fa fa-times'></i>密码不能为空</font>"
            document.getElementById("password").style.borderColor = "red";
            flag=0;
        }
        else  if(password.value.length < 6){
            document.getElementById("user4").innerHTML = "<font color='red'><i class='fa fa-times'></i>密码至少六位</font>"
            document.getElementById("password").style.borderColor = "red";
            flag=0;
        }
        else if (password.value.length >18){
            document.getElementById("user4").innerHTML = "<font color='red'><i class='fa fa-times'></i>密码最长18位</font>"
            document.getElementById("password").style.borderColor = "red";
            flag=0;
        }
        else {
            var s=/^[a-zA-Z0-9]+$/;
            var ss= s.test(password.value);
            if (!ss){
                document.getElementById("user4").innerHTML = "<font color='red'><i class='fa fa-times'></i>请检查密码是否有非法字符</font>"
                document.getElementById("password").style.borderColor = "red";
                flag=0;
            }
            else{
                document.getElementById("user4").innerHTML = "";
                document.getElementById("password").style.borderColor = "green";
            }

        }
    }
    if (status==5||status==6){
        if ((password.value>password1.value||password.value<password1.value)||password1.value.length<=0){
            document.getElementById("user5").innerHTML = "<font color='red'><i class='fa fa-times'></i>两次输入密码不一样!</font>";
            document.getElementById("password1").style.borderColor = "red";
            flag=0;
        }
        else {
            document.getElementById("user5").innerHTML = "";
            document.getElementById("password1").style.borderColor = "green";
        }
    }
    if (flag==0){
        return false;
    }
}