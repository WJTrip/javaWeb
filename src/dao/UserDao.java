package dao;

import Utils.JdbcUtil;
import bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public User get(String userName) throws SQLException {
        User user=null;
        Connection connection= JdbcUtil.getConnection();
        String sql="select * from t_user where userName=?";
        PreparedStatement pst=connection.prepareStatement(sql);
        pst.setString(1,userName);
        ResultSet rs=pst.executeQuery();
        if (rs.next()){
            user=new User(rs.getString("userName"),
                    rs.getString("passWord"),
                    rs.getString("chrName"),
                    rs.getString("role"));
        }
        connection.close();
        return user;
    }
}
