import java.sql.*;

public class preparedStatementTest1 {
    /*
        测试增删改查
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        testInsert();
//        testDelete();
//        testUpdate();
        testSelect();
    }
    public static void testInsert() throws ClassNotFoundException, SQLException {
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //建立连接
        Connection connection = DriverManager.getConnection("JDBC:mysql:///student?user=root&password=123456");
        //创建sql语句
        String sql="insert into stu (id,name,sex) values (?,?,?);";
        //对sql语句进行预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //对占位符进行赋值
        preparedStatement.setObject(1,1);
        preparedStatement.setObject(2,"周禹泽");
        preparedStatement.setObject(3,"男");
        //返回变动的行数
        int i = preparedStatement.executeUpdate();
        //对结果集进行解析
        System.out.println("成功插入"+i+"条数据");
        //资源释放
        preparedStatement.close();
        connection.close();
    }
    public static void testDelete() throws ClassNotFoundException, SQLException {
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //建立连接
        Connection connection = DriverManager.getConnection("JDBC:mysql://127.0.0.1:3306/student", "root", "123456");
        //创建sql语句
        String sql="delete from stu where name=?;";
        //对sql语句进行预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //对占位符进行赋值
        preparedStatement.setObject(1,"周禹泽");
        //返回变动的行数
        int i = preparedStatement.executeUpdate();
        //对结果集进行解析
        System.out.println("成功删除"+i+"条数据");
        //资源释放
        preparedStatement.close();
        connection.close();
    }
    public static void testUpdate() throws ClassNotFoundException, SQLException {
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //建立连接
        Connection connection = DriverManager.getConnection("JDBC:mysql:///student?user=root&password=123456");
        //创建sql语句
        String sql="update stu set name=? where name=?;";
        //对sql语句进行预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //对占位符进行赋值
        preparedStatement.setObject(1,"周禹泽");
        preparedStatement.setObject(2,"吕子乔");
        //返回变动的行数
        int i = preparedStatement.executeUpdate();
        //对结果集进行解析
        System.out.println("成功修改"+i+"条数据");
        //资源释放
        preparedStatement.close();
        connection.close();
    }
    public static void testSelect() throws ClassNotFoundException, SQLException {
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //建立连接
        Connection connection = DriverManager.getConnection("JDBC:mysql:///student", "root", "123456");
        //创建sql语句
        String sql="select * from stu where sex=?;";
        //对sql语句进行预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //对占位符进行赋值
        preparedStatement.setObject(1,"男");
        //返回运行结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        //对结果集进行解析
        while(resultSet.next()){
            Object id = resultSet.getObject(1);
            Object name = resultSet.getObject(2);
            Object sex = resultSet.getObject(3);
            System.out.println(id+" "+name+" "+sex);
        }
        //资源释放
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
