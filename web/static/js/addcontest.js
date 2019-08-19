$(document).ready(function() {
    $.ajax({
        type: 'get',
        dataType : "json",
        async:false,
        url : "../Message?name="+"&status=1" +"&page=0"+"&limit=0"+"&all=1",
        success:function(data){
            var html = [];
            for (var i=0;i<data.length;i++){
                html.push('<option value="'+data[i].mid+'-'+data[i].name+'"'+'>'+data[i].name+'</option>')
            }
            var mainObj = $('#mesname');
            mainObj.empty();
            mainObj.html(html.join(''));
        }
    });
    document.getElementById("cid").value=null;
    document.getElementById("contests_name").value=null;
    // document.getElementById("mid").value=null;
    // document.getElementById("mesname").value=null;
    document.getElementById("begin_time").value=null;
    document.getElementById("end_time").value=null;
    document.getElementById("times").value=null;
});