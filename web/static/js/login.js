function login_judge(status) {
    var date = new Date();
    var now = (Array(2).join(0)+date.getFullYear()).slice(-4) + '-' + (Array(2).join(0)+(date.getMonth()+1)).slice(-2) + '-' + (Array(2).join(0)+date.getDate()).slice(-2)+
        ' ' + (Array(2).join(0)+date.getHours()).slice(-2) + ':' + (Array(2).join(0)+date.getMinutes()).slice(-2) + ':' + (Array(2).join(0)+date.getSeconds()).slice(-2);
    document.getElementById("upd_time").value = now;
    // console.log(document.getElementById("upd_time").value);
    var flag=1;
    var username = document.getElementById("username");
    var password = document.getElementById("password");
    if (status==1||status==3) {
        if (username.value.length <= 0) {
            document.getElementById("user1").innerHTML = "<font color='red'><i class='fa fa-times'></i>用户名不能为空</font>"
            document.getElementById("username").style.borderColor = "red";
            flag=0;
        }
        else if (username.value.length >13) {
            document.getElementById("user1").innerHTML = "<font color='red'><i class='fa fa-times'></i>用户名最长13位</font>"
            document.getElementById("username").style.borderColor = "red";
            flag=0;
        }
        else {
            var s=/^[a-zA-Z0-9]+$/;
            var ss= s.test(username.value);
            if (!ss){
                document.getElementById("user1").innerHTML = "<font color='red'><i class='fa fa-times'></i>用户名只能是数字和字母的组合</font>"
                document.getElementById("username").style.borderColor = "red";
                flag=0;
            }
            else{
                document.getElementById("user1").innerHTML = "";
                document.getElementById("username").style.borderColor = "green";
            }

        }
    }
    if (status==2||status==3){
        if (password.value.length <= 0) {
            document.getElementById("user2").innerHTML = "<font color='red'><i class='fa fa-times'></i>密码不能为空</font>"
            document.getElementById("password").style.borderColor = "red";
            flag=0;
        }
        else  if(password.value.length < 6){
            document.getElementById("user2").innerHTML = "<font color='red'><i class='fa fa-times'></i>密码至少六位</font>"
            document.getElementById("password").style.borderColor = "red";
            flag=0;
        }
        else if (password.value.length >18){
            document.getElementById("user2").innerHTML = "<font color='red'><i class='fa fa-times'></i>密码最长18位</font>"
            document.getElementById("password").style.borderColor = "red";
            flag=0;
        }
        else {
            var s=/^[a-zA-Z0-9]+$/;
            var ss= s.test(password.value);
            if (!ss){
                document.getElementById("user2").innerHTML = "<font color='red'><i class='fa fa-times'></i>请检查密码是否有非法字符</font>"
                document.getElementById("password").style.borderColor = "red";
                flag=0;
            }
            else{
                document.getElementById("user2").innerHTML = "";
                document.getElementById("password").style.borderColor = "green";
            }

        }
    }
    if(flag==0){
        return false;
    }
    else{
        if(status==3) {
            $.ajax({
                type: 'get',
                dataType: "text",
                async: false,
                url: "LoginServlet?username=" + username.value + "&password=" + password.value,
                success: function (data) {
                    // console.log(data);
                    var str = data.toString();
                    // console.log(str.length);
                    if (str.length == 6) {
                        // console.log("-=-=-=");
                        flag = 0;
                    }

                }
            });
            if (flag == 0) {
                document.getElementById("user2").innerHTML = "<font color='red'><i class='fa fa-times'></i>账号密码错误,请重新输入！</font>"
                document.getElementById("password").style.borderColor = "red";
                return false;
            }
            else {
                document.getElementById("user2").innerHTML = "";
                document.getElementById("password").style.borderColor = "green";
                return true;
            }
        }
    }
}