package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {
    //数据库的四大参数
    private static String DRIVER="";
    private static String URL="";
    private static String USER="";
    private static String PASSWORD="";
    static {
        Properties properties=new Properties();
        try {

            //使用类加载器加载资源，path不以‘/’开头时默认是从此类所在的包下取资源
            //以‘/’开头则是从ClassPath（src）根下获取
            InputStream in=JdbcUtil.class.getResourceAsStream("/jdbc.properties");
            properties.load(in);
            in.close();
            DRIVER=properties.getProperty("DRIVER");
            URL=properties.getProperty("URL");
            USER=properties.getProperty("USER");
            PASSWORD=properties.getProperty("PASSWORD");
            Class.forName(DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  void close(Connection conn) {
        if(conn!=null){
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
