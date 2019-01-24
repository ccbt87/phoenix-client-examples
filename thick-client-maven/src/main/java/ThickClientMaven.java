import java.sql.*;

public class ThickClientMaven {

    public static void main(String[] args) throws Exception {

        Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
        Connection conn = DriverManager.getConnection("jdbc:phoenix:aio");
        Statement stmt = conn.createStatement();

        stmt.execute("create view \"101_data\" (\"row_key\" varchar primary key, \"S\".\"m\" varchar)");
        conn.commit();

        PreparedStatement statement = conn.prepareStatement("select * from \"101_data\"");
        ResultSet rset = statement.executeQuery();
        while (rset.next()) {
            System.out.println(rset.getString("m"));
        }
        statement.close();
        conn.close();
    }
}
