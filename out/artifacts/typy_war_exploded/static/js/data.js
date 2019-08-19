var data1;
var data2=[];
var limit = 13;
var num=1;
$(document).ready(function() {
    var url = window.location.search.substring(1);
    var str1 = url.split('=');
    var url1="";
    var str="";
    if (url==null){
        url1="../home/UserContestRank?";
        str="csid=";
    }
    else if (str1[0]=="csid"){
        url1="../home/UserContestRank?";
        str=url;
    }
    else if (str1[0]=="sid"){
        url1="../home/UserStatusRank?";
        str=url;
    }
    // $.getJSON()
    $.ajax({
        type: 'get',
        dataType : "json",
        async:false,
        url : url1+str,
        success:function(data){
            data1=data[0].instan;
            for (var i=0;i<data1.length;i++){
                var da={};
                da.y=data1[i].y;
                da.x=data1[i].x;
                data2.push(da);
            }
        }
    });
    Highcharts.chart('container', {

        title: {
            text: '速度'
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            type: 'category',
        },
        yAxis: {
            title: {
                text: '速度'
            }
        },
        tooltip: {
            headerFormat: '时间: {point.x:.1f}S<br>',
            pointFormat: '速度：{point.y}KPM',
        },
        series: [{
            data: data2
        }]

    });


});
// console.log(data2);
// noinspection JSAnnotator
