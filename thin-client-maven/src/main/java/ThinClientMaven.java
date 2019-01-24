import java.sql.*;

public class ThinClientMaven {

    public static void main(String[] args) throws Exception {

        Class.forName("org.apache.phoenix.queryserver.client.Driver");

        Connection conn = DriverManager.getConnection("jdbc:phoenix:thin:url=http://aio:8765;serialization=PROTOBUF");
        Statement stmt = conn.createStatement();

        stmt.execute("create table \"101_data\" (\"row_key\" varchar primary key, \"S\".\"m\" varchar)");
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
