$(document).ready(function() {
    var url = window.location.search.substring(1);
    $.ajax({
        type: 'get',
        dataType : "json",
        async:false,
        url : "../Message?"+url+"&status=2",
        success:function(data){
            w(data[0].mid,data[0].name,data[0].diff,data[0].message);
        }
    });
});
function w(mid,name,diff,message) {
    document.getElementById("mid").value=mid;
    document.getElementById("name").value=name;
    document.getElementById("level").value=diff;
    document.getElementById("message").innerHTML=message;
}