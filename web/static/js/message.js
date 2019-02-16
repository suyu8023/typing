$(document).ready(function() {
    document.getElementById('btn').click();

});
var limit =13;
function find() {
    var name=document.getElementById("autorname").value;
    var str="";
    if (name.length>0){
        str=name;
    }
    $.ajax({
        type: 'get',
        dataType : "json",
        async:false,
        url : "../Message?name="+str+"&status=1" +"&page=0"+"&limit="+limit,
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
                var totalCount = Number(data.length), showCount = 1;
                limit = 13;
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
        if (total - (currPage * limit) < 0) showNum = total - ((currPage - 1) * limit);
        html.push(' <table class="table table-hover table-bordered" cellspacing="0" width="100%">');
        html.push(' <thead><tr><th style="font-size: 20px">ID</th><th style="font-size: 20px">标题</th><th style="font-size: 20px">作者</th><th style="font-size: 20px">发布时间</th><th style="font-size: 20px">更新时间</th><th style="font-size: 20px">操作</th></tr></thead><tbody>');
        $.ajax({
            type: 'get',
            dataType : "json",
            async:false,
            url : "../Message?name="+str+"&status=1" +"&page=1"+"&limit="+limit,
            success:function(data){
                if (data.length>0) {
                    for (var i = 0; i < showNum; i++) {
                        // html.push('<tr><td>' + data[limit * (currPage - 1) + i].mid + '</td>');
                        // html.push('<td>' + data[limit * (currPage - 1) + i].name + '</td>');
                        // html.push('<td>' + data[limit * (currPage - 1) + i].autor + '</td>');
                        // html.push('<td>' + data[limit * (currPage - 1) + i].rel_time + '</td>');
                        // html.push('<td>' + data[limit * (currPage - 1) + i].upd_time + '</td>');
                        // html.push('<td>' + '<a href="Mesinfo?mid='+data[limit * (currPage - 1) + i].mid+'"'+'><i class="fa fa-edit"></i></a>' + '&nbsp;&nbsp;&nbsp;&nbsp;'+'<a href="" data-toggle="modal" onclick="deletebook(' + data[limit * (currPage - 1) + i].mid + ')"><i class="fa fa-trash" style="color: red"></i></a>'+ '</td>');
                        html.push('<tr><td>' + (limit * (currPage - 1) + i+1) + '</td>');
                        html.push('<td>' + data[i].name + '</td>');
                        html.push('<td>' + data[ i].autor + '</td>');
                        html.push('<td>' + data[ i].rel_time + '</td>');
                        html.push('<td>' + data[i].upd_time + '</td>');
                        html.push('<td>' + '<a href="Mesinfo?mid='+data[i].mid+'"'+'><i class="fa fa-edit"></i></a>' + '&nbsp;&nbsp;&nbsp;&nbsp;'+'<a href="" data-toggle="modal" onclick="deletebook(' + data[i].mid + ')"><i class="fa fa-trash" style="color: red"></i></a>'+ '</td>');

                        html.push('</tr>');
                    }
                }
                html.push('</tbody></table>');
                var mainObj = $('#list');
                mainObj.empty();
                mainObj.html(html.join(''));
            }
        })
    }
}

function deletebook(mid) {
    con = confirm("是否删除?");
    if (con == true) {
        location.href = "/admin/DeleteMesinfo?mid=" + mid;
    }
}
