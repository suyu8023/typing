$(document).ready(function() {
    var url = window.location.search.substring(1);
    $.ajax({
        type: 'get',
        dataType : "json",
        async:false,
        url : "../Contestinfo?"+url,
        success:function(data){
            w(data[0].cid,data[0].contests_name,data[0].mid,data[0].mesname,data[0].begin_time,data[0].end_time,data[0].times);
        }
    });
});
function w(cid,contests_name,mid,mesname,begin_time,end_time,times) {
    $.ajax({
        type: 'get',
        dataType : "json",
        async:false,
        url : "../Message?name="+"&status=1" +"&page=0"+"&limit=0",
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
    // document.getElementById("uid").value=uid;
    document.getElementById("cid").value=cid;
    document.getElementById("contests_name").value=contests_name;
    // document.getElementById("mid").value=mid;
    document.getElementById("mesname").value=mid+"-"+mesname;
    document.getElementById("begin_time").value=begin_time;
    document.getElementById("end_time").value=end_time;
    document.getElementById("times").value=times;
    // console.log(document.getElementById("cid").value+document.getElementById("contests_name").value+document.getElementById("mesname").value+document.getElementById("begin_time").value)
    // console.log()
}