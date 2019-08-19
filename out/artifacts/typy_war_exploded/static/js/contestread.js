$(document).ready(function() {
    var url = window.location.search.substring(1);
    $.ajax({
        type: 'get',
        dataType : "json",
        async:false,
        url : "../Contestinfo?"+url,
        success:function(data){
            document.getElementById("cid").value = data[0].cid;
            document.getElementById("qtime").value = data[0].times;
            var leftTime = data[0].times*60;
            var m, s;
            if (leftTime >= 0) {
                m = Math.floor(leftTime / 60);
                s = Math.floor(leftTime % 60);
            }
            document.getElementById("times").innerHTML = (Array(2).join(0) + m).slice(-2) + ":" + (Array(2).join(0) + s).slice(-2);
            document.getElementById("times2").value = (Array(2).join(0) + m).slice(-2) + ":" + (Array(2).join(0) + s).slice(-2);
            $.ajax({
                type: 'get',
                dataType : "json",
                async:false,
                url : "../Message?mid="+data[0].mid+"&status=2",
                success:function(data1){
                    w(data1[0].mid,data1[0].name,data1[0].diff,data1[0].message);
                    document.getElementById("mes_name").innerHTML=data1[0].name;
                    document.getElementById("mes_name1").value=data1[0].name;
                    document.getElementById("mid").value=data1[0].mid;
                    
                    // console.log(document.getElementById("mes_name1").value);
                    // console.log(document.getElementById("mid").value);
                    // console.log(data1[0].cid);
                }
            });
        }
    });

});
function w(mid,name,diff,message) {
    // var srt = new Array();
    var data = new Array();
    // srt = message.split("\n");
    // for (var i=0;i<srt.length;i++){
    message = message.replace(/\n/g," ");
    message = message.replace(/\s+/g, ' ');
    // console.log(message);
    var str = new Array();
    str = message.split(" ");
    var str1 = " ";
    for (var k = 0; k < str.length; k++) {
        if (k % 15 == 0 && k != 0) {
            data.push(str1);
            // str[k] = str[k].replace(/ /g,"");
            str1 = " "+str[k];
        }
        else {
            if (k==0){
                // str[k] = str[k].replace(/ /g,"");
                str1=str1+str[k];
            }
            else {
                // str[k] = str[k].replace(/ /g,"");
                str1 += " " + str[k];
            }
        }
    }
    data.push(str1);
    // }
    var html = [];
    for (var i=0;i<data.length;i++){
        html.push('<div class="read" id=qaq'+i+'>');
        html.push('<div class="write" id=show'+i+'>'+ data[i] + '</div>');
        html.push('<div hidden class="write" id=xread'+i+'>'+ data[i] + '</div>');
        if(i==0) {
            html.push('<input onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false" class="write" id=read' + i + ' oninput="CheckInput(this.id)">');
        }
        else{
            html.push('<input onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false" class="write" id=read' + i + ' disabled="disabled" oninput="CheckInput(this.id)">');
        }
        html.push('</div>')
    }
    var mainObj = $('#list');
    mainObj.empty();
    mainObj.html(html.join(''));
}
