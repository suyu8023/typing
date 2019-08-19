$(document).ready(function() {
    var url = window.location.search.substring(1);
    $.ajax({
        type: 'get',
        dataType : "json",
        async:false,
        url : "../AdminUser?"+url+"&status=2",
        success:function(data){
            w(data[0].uid,data[0].username,data[0].nickname,data[0].email,data[0].status);
        }
    });
});
function w(uid,username,nickname,email,status) {
    document.getElementById("uid").value=null;
    document.getElementById("username").value=null;
    document.getElementById("nickname").value=null;
    document.getElementById("email").value=null;
    document.getElementById("Permission").value=null;
    document.getElementById("uid").value=uid;
    document.getElementById("username").value=username;
    document.getElementById("nickname").value=nickname;
    document.getElementById("email").value=email;
    document.getElementById("Permission").value=status;
    document.getElementById("password").value=null;
    // console.log()
}