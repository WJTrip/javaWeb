package dao;

import Utils.JdbcUtil;
import bean.Download;
import jdk.nashorn.internal.scripts.JD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DownloadDao {
    public Download get(String id) {
        Download download=null;
        Connection conn=new JdbcUtil().getConnection();
        try{
            String sql="select * from t_downloadlist where id=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,id);
            ResultSet rs=pst.executeQuery();

            if(rs.next()){
                download=new Download(rs.getString("id"),
                        rs.getString("name"),rs.getString("path"),
                        rs.getString("description"),rs.getString("size"),
                        rs.getString("start"),rs.getString("image"));
            }
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return download;
    }

    public ArrayList<Download> getAllDownloadList() {
        ArrayList<Download> downloads=new ArrayList<>();
        Connection conn= new JdbcUtil().getConnection();
        try{
            String sql="select * from t_downloadList";

            PreparedStatement pst=conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while (rs.next()){
                Download download=new Download(rs.getString("id"),
                        rs.getString("name"),rs.getString("path"),
                        rs.getString("description"),rs.getString("size"),
                        rs.getString("start"),rs.getString("image"));
                downloads.add(download);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return downloads;
    }
}
