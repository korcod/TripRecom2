/**
 * 작성일: 2025.05.29
 * 작성자: 정창현
 * 설명: TripAccess DataBase Connect
 * 버전: 1.0
 */


package TripAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// import java.sql.* 자바의 sql에 있는 모든 걸 임포트 할 수 있는 마법의 단어
public class DBC {


    public static Connection DBConnect(){

        Connection con = null;
//        String user = "JCH";        // ID
        String user = "tripuser";        // ID
//        String password = "7777";    // PW
        String password = "trip123";    // PW
//        String ur1 = "jdbc:oracle:thin:@localhost:1521:XE";
        String ur1 = "jdbc:oracle:thin:@192.168.0.12:1521/XEPDB1";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(ur1,user,password);
//            System.out.println("DB접속 성공!");
        } catch (ClassNotFoundException e) {
            System.out.println("DB접속 실패 : ojdbc 드라이버 로딩 실패");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("DB접속 실패 : 접속 정보 오류");
            throw new RuntimeException(e);
        }


        return con;
    }

}
