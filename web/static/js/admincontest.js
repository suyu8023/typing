var limit = 13;
var num=1;
$(document).ready(function() {
    // var url = window.location.search.substring(1);
    // var str="";
    // if (url==null){
    //     str="uid=";
    // }
    $.ajax({
        type: 'get',
        dataType : "json",
        async:false,
        url : "../home/Contest?"+"page=0" + "&limit="+limit,
        success:function(data){
            if (data.length==0){
                var totalCount = Number(data.length), showCount = 1, limit = 0;
                createTable(1, limit, totalCount);
                $('#page').pagination({
                    totalCount: totalCount,
                    showCount: showCount,
                    limit: limit,
                    data: data,
                    callback: function (curr, limit, totalCount) {
                        createTable(curr, limit, totalCount);
                    }
                });
            }
            else {
                var totalCount = Number(data.length), showCount = 1,limit = 13;
                createTable(1, limit, totalCount);
                $('#page').pagination({
                    totalCount: totalCount,
                    showCount: showCount,
                    limit: limit,
                    data: data,
                    callback: function (curr, limit, totalCount) {
                        createTable(curr, limit, totalCount);
                    }
                });
            }
        }
    });
    function createTable(currPage, limit, total) {
        var html = [], showNum = limit;
        // console.log("=-=="+currPage);
        if (total - (currPage * limit) < 0) showNum = total - ((currPage - 1) * limit);
        html.push(' <table class="table table-hover table-bordered" style="text-align: center" cellspacing="0" width="100%">');
        html.push(' <thead><tr><th style="font-size: 20px;text-align: center">ID</th><th style="font-size: 20px;text-align: center">比赛名称</th><th style="font-size: 20px;text-align: center">作者</th><th style="font-size: 20px;text-align: center">开始时间</th><th style="font-size: 20px;text-align: center">结束时间</th><th style="font-size: 20px;text-align: center">测试时间</th><th style="font-size: 20px;text-align: center">创建时间</th><th style="font-size: 20px;text-align: center">操作</th></thead><tbody>');
        $.ajax({
            type: 'get',
            dataType : "json",
            async:false,
            url : "../home/Contest?"+"&page="+currPage+"&limit="+limit,
            success:function(data){
                // console.log(url);
                if (data.length>0) {
                    for (var i = 0; i < showNum; i++) {

                        html.push('<tr><td>' + (limit * (currPage - 1) + i+1) + '</td>');
                        html.push('<td>' + data[i].contests_name + '</td>');
                        // html.push('<td>' + '<a href="Contest?cid='+data[i].cid+"&mid="+data[i].mid+'"'+'>'+data[i].contests_name+'</a>' + '</td>');
                        // html.push('<td>' + '<a href="Contest/Result?cid='+data[i].cid+'"'+'>'+"查看详情" +'</a>'+ '</td>');
                        html.push('<td>' + data[i].autor + '</td>');
                        html.push('<td>' + data[ i].begin_time + '</td>');
                        html.push('<td>' + data[ i].end_time + '</td>');
                        html.push('<td>' + data[i].times + '</td>');
                        html.push('<td>' + data[ i].create_time + '</td>');
                        html.push('<td>' + '<a href="Contestinfo1?cid='+data[i].cid+'"'+'><i class="fa fa-edit"></i></a>' + '&nbsp;&nbsp;&nbsp;&nbsp;'+'<a href="" data-toggle="modal" onclick="deletebook(' + data[i].cid + ')"><i class="fa fa-trash" style="color: red"></i></a>'+ '</td>');

                        html.push('</tr>');
                    }
                    num++;
                }
                html.push('</tbody></table>');
                var mainObj = $('#list');
                mainObj.empty();
                mainObj.html(html.join(''));
            }
        })
    }

});
