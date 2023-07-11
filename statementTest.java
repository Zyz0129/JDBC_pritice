import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class statementTest {

    public static void main(String[] args) throws SQLException {
        //注册驱动
        DriverManager.registerDriver(new Driver());
        //建立连接
        Connection connection = DriverManager.getConnection
                ("JDBC:mysql://localhost:3306/student","root","123456");
        //创建statement发送sql语句的对象
        Statement statement=connection.createStatement();
        //执行sql并返回结果集resultSet
        String sql="select * from stu;";
        ResultSet resultSet = statement.executeQuery(sql);
        //对结果集进行解析
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String sex = resultSet.getString("sex");
            System.out.println(id+"--"+name+"--"+sex);
        }
        //资源释放
        connection.close();
        statement.close();
        resultSet.close();
    }
}
