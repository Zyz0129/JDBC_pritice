import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class preparedStatementTest2 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //建立连接
        Connection connection = DriverManager.getConnection
                ("JDBC:mysql:///student?user=root&password=123456");
        //创建sql语句
        String sql="select * from stu;";
        //对sql语句进行预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //返回运行结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        //初始化list并获取结果集的列信息
        List<Map> list=new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while(resultSet.next()){
            //初始化map集合
            Map map=new HashMap();
            for (int i = 1; i <= columnCount; i++) {
                map.put(metaData.getColumnLabel(i),resultSet.getObject(i));
            }
            list.add(map);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        //资源释放
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
