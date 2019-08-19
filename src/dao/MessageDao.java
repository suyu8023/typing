package dao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageDao {

    public void addMessage(String name,String autor, String message, String diff, String rel_time, String upd_time, int uid) throws Exception {
        Connection conn = JdbcUtil.getConnection();
        String sql = "insert  into message(name,autor,message,diff,rel_time,upd_time,uid) values(?,?,?,?,?,?,?)";
        int rs = 0;
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, autor);
            stm.setString(3, message);
            stm.setString(4, diff);
            stm.setString(5, rel_time);
            stm.setString(6, upd_time);
            stm.setInt(7,uid);
            rs = stm.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //获取所有文章的JOSN数据
    public JSONArray get_ListInfo(int page, int limit) throws Exception {
        Connection conn = JdbcUtil.getConnection();
        int li = page*limit-limit;
        String sql;
        if (page == -1){
            sql = "select * from message";
        }
        else {
            if (page == 0) {
                sql = "select count(*) as ss from message";
            } else {
                sql = "select * from message limit " + li + "," + limit;
            }
        }

        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            JSONArray jsonarray = new JSONArray();
            JSONObject jsonobj = new JSONObject();
            if (page == 0){
//                System.out.println(stm);
                rs.next();
//                while(rs.next())
//                System.out.println(rs.getInt("ss"));
                jsonobj.put("count", rs.getInt("ss"));
                jsonarray.add(jsonobj);
            }else {
                while (rs.next()) {
                    jsonobj.put("mid", rs.getInt("mid"));
                    jsonobj.put("name", rs.getString("name"));
                    jsonobj.put("autor", rs.getString("autor"));
//                jsonobj.put("message", rs.getString("message"));
                    jsonobj.put("diff", rs.getString("diff"));
                    jsonobj.put("rel_time", rs.getString("rel_time"));
                    jsonobj.put("upd_time", rs.getString("upd_time"));
//                String str = (String)jsonobj.get("message");
//                str = str.replace("\r\n","'+'\\n'+'");
//                jsonobj.put("message", str);
                    jsonarray.add(jsonobj);
                }
            }
            rs.close();
            return jsonarray;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            conn.close();
        }
        return null;
    }

    //返回通过id查找的文章信息
    public JSONArray mes_info(int id) throws Exception {
        Connection conn = JdbcUtil.getConnection();
        String sql = "select * from message where mid='"+id+"'";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            JSONArray jsonarray = new JSONArray();
            JSONObject jsonobj = new JSONObject();
            while(rs.next()){
                jsonobj.put("mid", rs.getInt("mid"));
                jsonobj.put("name", rs.getString("name"));
                jsonobj.put("autor", rs.getString("autor"));
                jsonobj.put("message", rs.getString("message"));
                jsonobj.put("diff", rs.getString("diff"));
                jsonobj.put("rel_time", rs.getString("rel_time"));
                jsonobj.put("upd_time", rs.getString("upd_time"));
                String str = (String)jsonobj.get("message");
                str = str.replace("\r\n","\n");
                jsonobj.put("message", str);
                jsonarray.add(jsonobj);
            }
            rs.close();
            return jsonarray;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            conn.close();
        }
        return null;
    }
    //返回通过autor查找的文章信息
    public JSONArray autormes(String name) throws Exception {
        // TODO Auto-generated method stub
        Connection conn = JdbcUtil.getConnection();
        String sql = "select * from message where autor like '%"+name+"%'";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            JSONArray jsonarray = new JSONArray();
            JSONObject jsonobj = new JSONObject();
            while(rs.next()){
                jsonobj.put("mid", rs.getInt("mid"));
                jsonobj.put("name", rs.getString("name"));
                jsonobj.put("autor", rs.getString("autor"));
//                jsonobj.put("message", rs.getString("message"));
                jsonobj.put("diff", rs.getString("diff"));
                jsonobj.put("rel_time", rs.getString("rel_time"));
                jsonobj.put("upd_time", rs.getString("upd_time"));
//                String str = (String)jsonobj.get("message");
//                str = str.replace("\r\n","'+'\\n'+'");
//                jsonobj.put("message", str);
                jsonarray.add(jsonobj);
            }
            rs.close();
            return jsonarray;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            conn.close();
        }
        return null;
    }

    public JSONArray mesname(String name) throws Exception {
        // TODO Auto-generated method stub
        Connection conn = JdbcUtil.getConnection();
        String sql = "select * from message where name like '%"+name+"%'";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            JSONArray jsonarray = new JSONArray();
            JSONObject jsonobj = new JSONObject();
            while(rs.next()){
                jsonobj.put("mid", rs.getInt("mid"));
                jsonobj.put("name", rs.getString("name"));
                jsonobj.put("autor", rs.getString("autor"));
//                jsonobj.put("message", rs.getString("message"));
                jsonobj.put("diff", rs.getString("diff"));
                jsonobj.put("rel_time", rs.getString("rel_time"));
                jsonobj.put("upd_time", rs.getString("upd_time"));
//                String str = (String)jsonobj.get("message");
//                str = str.replace("\r\n","'+'\\n'+'");
//                jsonobj.put("message", str);
                jsonarray.add(jsonobj);
            }
            rs.close();
            return jsonarray;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            conn.close();
        }
        return null;
    }
    public void updateMesinfo(int mid, String name,String message, String diff, String upd_time) throws Exception {
        // TODO Auto-generated method stub
        Connection conn = JdbcUtil.getConnection();
        String sql = "update message set name=?,message=?,diff=?,upd_time=? where mid=?";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, message);
            stm.setString(3, diff);
            stm.setString(4, upd_time);
            stm.setInt(5, mid);
            stm.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void deleteMes(int mid) throws Exception {
        // TODO Auto-generated method stub
        Connection conn = JdbcUtil.getConnection();
        String sql = "delete from message where mid=?";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, mid);
            stm.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
