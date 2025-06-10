package TripAccess;

import TripObject.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TripSQL {

    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
    public TripSQL() {
        connect();
    }

    public void connect(){
        con = DBC.DBConnect();
    }

    public void conClose(){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public Region regionChoice(Region region) {
        PreparedStatement pstmt = null; // pstmt 초기화
        ResultSet rs = null;

        try {
            String sql = "SELECT REGIONNAME FROM REGION WHERE REGIONNUM = ?";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1,region.getRegionNum());
            rs = pstmt.executeQuery();

            if (rs.next()){
                String bringRegionName = rs.getString("REGIONNAME");      // 위에 REGIONNAME값 가져와 bringRegionName에 저장
                region.setRegionName(bringRegionName);                               // setRegionName에 가져온 값 넣오줌

                System.out.println("선택하신 지역은 " + region.getRegionName() + "입니다.");
                return region;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return region;
    }

    public Landmark landmarkshow(Landmark landmark) {
        PreparedStatement pstmt = null; // pstmt 초기화
        ResultSet rs = null;
        Region region = new Region();

        try {
            String sql = "SELECT R.REGIONNAME, L.LANDMARKNAME, L.LANDMARKPRICE FROM REGION R JOIN LANDMARK L ON R.REGIONNUM = L.REGIONNUM WHERE R.REGIONNUM = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, landmark.getRegionNum());
            rs = pstmt.executeQuery();

            boolean regionNameonce = false;     // 지역이름은 한번만 출력되게

            while (rs.next()){
                String bringregionName = rs.getString("REGIONNAME");
                String bringLandmarkName = rs.getString("LANDMARKNAME");
                int bringLandmarkPrice = rs.getInt("LANDMARKPRICE");


                //  ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ 정렬하기 (가독성 좋게)
                if (!regionNameonce){
                    System.out.println("--- " + bringregionName + "의 지역명소와 가격 ---");
                    regionNameonce = true;
                }
                String priceInfo;
                if (bringLandmarkPrice != 0){
                    priceInfo = String.format("가격: %d원", bringLandmarkPrice);
                } else {
                    priceInfo = "입장료 무료";
                }
                String content = String.format("명소: %-30s %s", bringLandmarkName, priceInfo);
                String contentTrim = content.stripTrailing();
                int totalLine = 53;
                int space = totalLine - 2 - content.length() -1;
                if (space < 0){
                    space = 0;
                }
                System.out.println("■ " + content + " ".repeat(space) + "■");
//                System.out.printf("■ 명소: %-30s, %s ■%n", bringLandmarkName, priceInfo);
//                System.out.print("■ 명소: " + bringLandmarkName + ",\t\t가격: " + bringLandmarkPrice);
//                if (bringLandmarkPrice != 0) {
//                    System.out.println("원 ■");
//                    } else {
//                    System.out.println("입장료 무료 ■");
//                    }
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return landmark;
    }

    public void tripMemberJoin(TripMember tripmember) {


        try {
            String sql = "INSERT INTO TRIPMEMBER VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, tripmember.getMemberNum());
            pstmt.setString(2, tripmember.getMemberId());
            pstmt.setString(3, tripmember.getMemberPw());
            pstmt.setString(4, tripmember.getMemberPhone());
            pstmt.setString(5, tripmember.getMemberBirth());
            pstmt.setInt(6, tripmember.getMemberPoint());

            int result = pstmt.executeUpdate();

            if (result > 0){
                System.out.println("회원가입 성공");
            } else {
                System.out.println("회원가입 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public int memberNumber() {
        int memberNum = 0;

        try {
            String sql = "SELECT MAX(MEMBERNUM) FROM TRIPMEMBER";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()){
                memberNum = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        memberNum ++;


        return memberNum;
    }

//    public int memberNumber(Connection con) throws SQLException {
//        String sql = "SELECT MAX(MEMBERNUM) FROM TRIPMEMBER";
//        try (PreparedStatement pstmt = con.prepareStatement(sql);
//             ResultSet rs = pstmt.executeQuery()) {
//            if (rs.next()) {
//                int maxNum = rs.getInt(1);
//                return maxNum + 1;  // 현재 최대값 + 1 반환
//            } else {
//                return 1;  // 테이블이 비어있으면 1부터 시작
//            }
//        }
//    }

    public TripMember memberLogin(String tId, String tPw) {
        TripMember tripMember = new TripMember();

        try {
            String sql = "SELECT * FROM TRIPMEMBER WHERE MEMBERID = ? AND MEMBERPW = ?";
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1,tId);
            pstmt.setString(2,tPw);

            rs = pstmt.executeQuery();

            if (rs.next()){
                tripMember.setMemberNum(rs.getInt(1));
                tripMember.setMemberId(rs.getString(2));
                tripMember.setMemberPw(rs.getString(3));
                tripMember.setMemberPhone(rs.getString(4));
                tripMember.setMemberBirth(rs.getString(5));
                tripMember.setMemberPoint(rs.getInt(6));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tripMember;
    }

    public Restaur restaurShow(Restaur restaur) {
        PreparedStatement pstmt = null; // pstmt 초기화
        ResultSet rs = null;
        Region region = new Region();

        try {
            String sql = "SELECT R.REGIONNAME, A.RESTAURNAME, A.RESTAURMENU1, A.RESTAURMENU2, A.RESTAURPRICE1, A.RESTAURPRICE2 FROM REGION R JOIN RESTAURANT A ON R.REGIONNUM = A.REGIONNUM WHERE R.REGIONNUM = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, restaur.getRegionNum());
            rs = pstmt.executeQuery();

            boolean regionNameonce = false;     // 지역이름은 한번만 출력되게

            while (rs.next()){
                String bringregionName = rs.getString("REGIONNAME");
                String bringRestaurName = rs.getString("RESTAURNAME");
                String bringRestaurMenu1 = rs.getString("RESTAURMENU1");
                int bringRestaurPrice1 = rs.getInt("RESTAURPRICE1");
                String bringRestaurMenu2 = rs.getString("RESTAURMENU2");
                int bringRestaurPrice2 = rs.getInt("RESTAURPRICE2");

//  ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ 정렬하기 (가독성 좋게)
                if (!regionNameonce){
                    System.out.println("--- " + bringregionName + "의 맛집이름, 메뉴 그리고 가격 ---");
                    regionNameonce = true;
                }
                System.out.println("맛집 이름: " + bringRestaurName);
                System.out.print("■ 첫 번째 메뉴: " + bringRestaurMenu1 + "\t\t\t");
                System.out.print("첫 번째 메뉴 가격: " + bringRestaurPrice1 + "원" + "\t■\t\t");
                System.out.print("■ 두 번째 메뉴: " + bringRestaurMenu2 + "\t\t\t");
                System.out.println("두 번째 메뉴 가격:" + bringRestaurPrice2 + "원" + "\t■");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return restaur;
    }

    public Hotel hotelShow(Hotel hotel) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Region region = new Region();


        try {
            String sql = "SELECT R.REGIONNAME, H.HOTELNAME, H.HOTELPRICE FROM REGION R JOIN HOTEL H ON R.REGIONNUM = H.REGIONNUM WHERE R.REGIONNUM = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, hotel.getRegionNum());
            rs = pstmt.executeQuery();

            boolean regionNameonce = false;

            while (rs.next()){
                String bringRegionName = rs.getString("REGIONNAME");
                String bringHotelName = rs.getString("HOTELNAME");
                int bringHotelPrice = rs.getInt("HOTELPRICE");

                //  ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ 정렬하기 (가독성 좋게)
                if (!regionNameonce){
                    System.out.println("--- " + bringRegionName + "의 숙소와 가격");
                    regionNameonce = true;
                }
                System.out.println("■ 숙소 이름: " + bringHotelName + " ■");
                System.out.println("■ 숙소 가격: " + bringHotelPrice + "원 ■");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return hotel;
    }

    public void memberUpdate(TripMember tripmember) {

        try {
            String sql = "UPDATE TRIPMEMBER SET MEMBERID = ?, MEMBERPW = ?, MEMBERPHONE = ?, MEMBERBIRTH = ? WHERE MEMBERNUM = ? AND MEMBERPOINT = ?";
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, tripmember.getMemberId());
            pstmt.setString(2, tripmember.getMemberPw());
            pstmt.setString(3, tripmember.getMemberPhone());
            pstmt.setString(4, tripmember.getMemberBirth());
            pstmt.setInt(5, tripmember.getMemberNum());
            pstmt.setInt(6, tripmember.getMemberPoint());

            int result = pstmt.executeUpdate();

            if (result > 0){
                System.out.println("수정 완료");
            } else {
                System.out.println("수정 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void memberDelete(int memberNum) {

        try {
            String sql = "DELETE FROM TRIPMEMBER WHERE MEMBERNUM = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, memberNum);
            int result = pstmt.executeUpdate();
            if (result > 0 ){
                System.out.println("탈퇴 성공");
            } else {
                System.out.println("탈퇴 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TripMember pointCharge(TripMember tripmember, int plusPoint) {

        try {
            String sql = "UPDATE TRIPMEMBER SET  MEMBERPOINT = MEMBERPOINT + ? WHERE MEMBERNUM = ?";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1,plusPoint);
            pstmt.setInt(2, tripmember.getMemberNum());
            int result = pstmt.executeUpdate();

            if (result > 0){
                System.out.println("충전 완료");
                tripmember.setMemberPoint(tripmember.getMemberPoint() + plusPoint);
            } else {
                System.out.println("충전 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tripmember;
    }

    public void restaurNameShow(Restaur restaur) {

        PreparedStatement pstmt = null; // pstmt 초기화
        ResultSet rs = null;
        Region region = new Region();

        try {
            String sql = "SELECT R.REGIONNAME, A.RESTAURNAME FROM REGION R JOIN RESTAURANT A ON R.REGIONNUM = A.REGIONNUM WHERE R.REGIONNUM = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, restaur.getRegionNum());
            rs = pstmt.executeQuery();
            System.out.println(" ");
            for (int i=1; i<4; i++){
                if (rs.next()) {
                    String bringRestaurName = rs.getString("RESTAURNAME");
                    System.out.print("(" + i + ")번");
                    System.out.println("맛집 이름: " + bringRestaurName);
                }
            }
            System.out.println("(4)번 다른 맛집 찾기");
//            while (rs.next()){
//                String bringRestaurName = rs.getString("RESTAURNAME");
//
//                System.out.println("맛집 이름: " + bringRestaurName);
//            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void restaurInfo1(Restaur restaur) {

        PreparedStatement pstmt = null; // pstmt 초기화
        ResultSet rs = null;

        try {
//            String sql = "SELECT RESTAURNAME, RESTAURMENU1, RESTAURPRICE1, RESTAURMENU2, RESTAURPRICE2 FROM RESTAURANT WHERE REGIONNUM = ?";
            String sql = "SELECT RESTAURNAME, RESTAURMENU1, RESTAURPRICE1, RESTAURMENU2, RESTAURPRICE2 " +
                    "FROM ( " +
                    "    SELECT " +
                    "        RESTAURNAME, RESTAURMENU1, RESTAURPRICE1, RESTAURMENU2, RESTAURPRICE2, " +
                    "        ROW_NUMBER() OVER (ORDER BY RESTAURNUM ASC, RESTAURNAME ASC) as rn " + // ORDER BY 기준 중요!
                    "    FROM RESTAURANT " +
                    "    WHERE REGIONNUM = ? " +
                    ") " +
                    "WHERE rn = 1";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1,restaur.getRegionNum());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String bringRestaurName = rs.getString("RESTAURNAME");
                String bringRestaurMenu1 = rs.getString("RESTAURMENU1");
                int bringRestaurPrice1 = rs.getInt("RESTAURPRICE1");
                String bringRestaurMenu2 = rs.getString("RESTAURMENU2");
                int bringRestaurPrice2 = rs.getInt("RESTAURPRICE2");

                System.out.println(bringRestaurName);
                System.out.println("※ 1번 메뉴: " + bringRestaurMenu1 + "\t" + bringRestaurPrice1 + "원\t\t ※ 2번 메뉴: " + bringRestaurMenu2 + "\t" + bringRestaurPrice2 + "원\t\t  ※ 3번 메뉴: 둘 다 선택하기");
                System.out.println("숫자 1,2,3 중 하나를 선택해주세요.");
            } else {
                System.out.println("그런 건 없습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void restaurInfo2(Restaur restaur) {

        PreparedStatement pstmt = null; // pstmt 초기화
        ResultSet rs = null;

        try {
//            String sql = "SELECT RESTAURNAME, RESTAURMENU1, RESTAURPRICE1, RESTAURMENU2, RESTAURPRICE2 FROM RESTAURANT WHERE REGIONNUM = ?";
            String sql = "SELECT RESTAURNAME, RESTAURMENU1, RESTAURPRICE1, RESTAURMENU2, RESTAURPRICE2 " +
                    "FROM ( " +
                    "    SELECT " +
                    "        RESTAURNAME, RESTAURMENU1, RESTAURPRICE1, RESTAURMENU2, RESTAURPRICE2, " +
                    "        ROW_NUMBER() OVER (ORDER BY RESTAURNUM ASC, RESTAURNAME ASC) as rn " + // ORDER BY 기준 중요!
                    "    FROM RESTAURANT " +
                    "    WHERE REGIONNUM = ? " +
                    ") " +
                    "WHERE rn = 2";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1,restaur.getRegionNum());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String bringRestaurName = rs.getString("RESTAURNAME");
                String bringRestaurMenu1 = rs.getString("RESTAURMENU1");
                int bringRestaurPrice1 = rs.getInt("RESTAURPRICE1");
                String bringRestaurMenu2 = rs.getString("RESTAURMENU2");
                int bringRestaurPrice2 = rs.getInt("RESTAURPRICE2");

                System.out.println(bringRestaurName);
                System.out.println("※ 1번 메뉴: " + bringRestaurMenu1 + "\t" + bringRestaurPrice1 + "원\t\t ※ 2번 메뉴: " + bringRestaurMenu2 + "\t" + bringRestaurPrice2 + "원\t\t  ※ 3번 메뉴: 둘 다 선택하기");
                System.out.println("숫자 1,2,3 중 하나를 선택해주세요.");
            } else {
                System.out.println("그런 건 없습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void restaurInfo3(Restaur restaur) {

        PreparedStatement pstmt = null; // pstmt 초기화
        ResultSet rs = null;

        try {
            // SELECT RESTAURMENU1, RESTAURPRICE1 FROM (SELECT RESTAURMENU1, RESTAURPRICE1, ROW_NUMBER() OVER (ORDER BY RESTAURNUM ASC, RESTAURNAME ASC) as rn FROM RESTAURANT WHERE REGIONNUM =?) WHERE rn = 1
//            String sql = "SELECT RESTAURNAME, RESTAURMENU1, RESTAURPRICE1, RESTAURMENU2, RESTAURPRICE2 FROM RESTAURANT WHERE REGIONNUM = ?";
            String sql = "SELECT RESTAURNAME, RESTAURMENU1, RESTAURPRICE1, RESTAURMENU2, RESTAURPRICE2 " +
                    "FROM ( " +
                    "    SELECT " +
                    "        RESTAURNAME, RESTAURMENU1, RESTAURPRICE1, RESTAURMENU2, RESTAURPRICE2, " +
                    "        ROW_NUMBER() OVER (ORDER BY RESTAURNUM ASC, RESTAURNAME ASC) as rn " + // ORDER BY 기준 중요!
                    "    FROM RESTAURANT " +
                    "    WHERE REGIONNUM = ? " +
                    ") " +
                    "WHERE rn = 3";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1,restaur.getRegionNum());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String bringRestaurName = rs.getString("RESTAURNAME");
                String bringRestaurMenu1 = rs.getString("RESTAURMENU1");
                int bringRestaurPrice1 = rs.getInt("RESTAURPRICE1");
                String bringRestaurMenu2 = rs.getString("RESTAURMENU2");
                int bringRestaurPrice2 = rs.getInt("RESTAURPRICE2");

                System.out.println(bringRestaurName);
                System.out.println("※ 1번 메뉴: " + bringRestaurMenu1 + "\t" + bringRestaurPrice1 + "원\t\t ※ 2번 메뉴: " + bringRestaurMenu2 + "\t" + bringRestaurPrice2 + "원\t\t  ※ 3번 메뉴: 둘 다 선택하기");
                System.out.println("숫자 1,2,3 중 하나를 선택해주세요.");
            } else {
                System.out.println("그런 건 없습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int restaurMenu1(Restaur restaur) {

        PreparedStatement pstmt = null; // pstmt 초기화
        ResultSet rs = null;
        int price1 = 0;

        try {
            String sql = "SELECT  RESTAURPRICE1 FROM ( SELECT RESTAURPRICE1, ROW_NUMBER() OVER (ORDER BY RESTAURNUM ASC, RESTAURNAME ASC) as rn FROM RESTAURANT WHERE REGIONNUM = ? ) WHERE rn = 1";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1,restaur.getRegionNum());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                price1 = rs.getInt("RESTAURPRICE1");

            } else {

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return price1;
    }

    public TripMember remainAmount(TripMember tripmember, int reduceMoney) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        try {
            String sql = "UPDATE TRIPMEMBER SET MEMBERPOINT = MEMBERPOINT - ? WHERE MEMBERNUM = ? ";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, reduceMoney);
            pstmt.setInt(2,tripmember.getMemberNum());
            int reducePoint = pstmt.executeUpdate();
            if (reducePoint > 0){
                tripmember.setMemberPoint(tripmember.getMemberPoint() - reduceMoney);
                System.out.println("결제 완료되었습니다. 현재 남은 금액: " + tripmember.getMemberPoint());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tripmember;
    }

    public String reserveinfo(Reserve reserve) {
        String info = "없음";
        // 예약정보는 예약 시간O, 인원O, 장소, 메뉴?             구매가격,
        try {
            String sql = "SELECT TO_CHAR(RE.RESERVETIME, 'YYYY/MM/DD-HH24')" +
                    " AS restime, RE.RESERVEPEOPLENUM, RE.RESERVEMENU1, RE.RESERVEMENU2, RE.RESERVEPRICE, RS.RESTAURNAME, TR.MEMBERNUM FROM RESERVE RE JOIN RESTAURANT RS ON RE.RESTAURNUM = RS.RESTAURNUM JOIN TRIPMEMBER TR ON RE.MEMBERNUM = TR.MEMBERNUM WHERE RE.MEMBERNUM = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, reserve.getMemberNum());
            rs = pstmt.executeQuery();

            if (rs.next()){
                String bringReserveTime = rs.getString("restime");
                int bringReservePeopleNum = rs.getInt("RESERVEPEOPLENUM");
                String bringReserveMenu1 = rs.getString("RESERVEMENU1");
                String bringReserveMenu2 = rs.getString("RESERVEMENU2");
                int bringReservePrice = rs.getInt("RESERVEPRICE");
                String bringRestaurName = rs.getString("RESTAURNAME");

                String menuInfo ="";
                if (bringReserveMenu1 == null){
                    menuInfo = bringReserveMenu2;
                } else if (bringReserveMenu2 == null) {
                    menuInfo = bringReserveMenu1;
                } else {
                    menuInfo = bringReserveMenu1 + ", " + bringReserveMenu2;
                }
                info = "◈◈◈ 예약 시간: " + bringReserveTime + "시" + "\t◈◈◈ 예약 인원: " + bringReservePeopleNum + "명" + "\t◈◈◈ 예약 장소: " + bringRestaurName + "◈◈◈ 예약 메뉴: " + menuInfo + "\t◈◈◈ 예약 가격: " + bringReservePrice;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return info;
    }

    public int reserveNumber() {

        int reserveNum = 0;

        try {
            String sql = "SELECT MAX(MEMBERNUM) FROM TRIPMEMBER";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()){
                reserveNum = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        reserveNum ++;

        return reserveNum;
    }

    public void reserveCreate(Reserve reserve) {

        try {
            String sql = "INSERT INTO RESERVE (RESERVENUM, MEMBERNUM, RESTAURNUM, RESERVETIME, RESERVEPEOPLENUM, RESERVEMENU1, RESERVEMENU2, RESERVEPRICE) VALUES (?, ?, ?, TO_DATE(TO_CHAR(SYSDATE, 'YYYYMMDD') || ?, 'YYYYMMDDHH24'), ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, reserve.getReserveNum());
            pstmt.setInt(2, reserve.getMemberNum());
            pstmt.setInt(3, reserve.getRestaurNum());
            pstmt.setInt(4, reserve.getReserveTime());
            pstmt.setInt(5, reserve.getReservePeopleNum());
            pstmt.setString(6, reserve.getReserveMenu1());
            pstmt.setString(7, reserve.getReserveMenu2());
            pstmt.setInt(8, reserve.getReservePrice());

            int result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void restaurCheck(Restaur restaur) {
        try {
            String sql = "SELECT RESTAURNUM FROM RESTAURANT WHERE REGIONNUM = ? ";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,restaur.getRegionNum());
            rs = pstmt.executeQuery();
            if (rs.next()){
                int restNum = rs.getInt("RESTAURNUM");
                restaur.setRestaurNum(restNum);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean
    existReserve(int memberNum) {
        boolean exist = false;

        try {
            String sql = "SELECT COUNT(*) FROM RESERVE WHERE MEMBERNUM = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, memberNum);
            rs = pstmt.executeQuery();

            if (rs.next()){
                if (rs.getInt(1) > 0){
                    exist = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exist;
    }

    public int restaurMenu2(Restaur restaur) {

        PreparedStatement pstmt = null; // pstmt 초기화
        ResultSet rs = null;
        int price2 = 0;

        try {
            String sql = "SELECT  RESTAURPRICE2 FROM ( SELECT RESTAURPRICE2, ROW_NUMBER() OVER (ORDER BY RESTAURNUM ASC, RESTAURNAME ASC) as rn FROM RESTAURANT WHERE REGIONNUM = ? ) WHERE rn = 1";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1,restaur.getRegionNum());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                price2 = rs.getInt("RESTAURPRICE2");

            } else {

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return price2;
    }

    public int restaurMenu3(Restaur restaur) {

        PreparedStatement pstmt = null; // pstmt 초기화
        ResultSet rs = null;
        int price3 = 0;

        try {
            String sql = "SELECT  RESTAURPRICE1 FROM ( SELECT RESTAURPRICE1, ROW_NUMBER() OVER (ORDER BY RESTAURNUM ASC, RESTAURNAME ASC) as rn FROM RESTAURANT WHERE REGIONNUM = ? ) WHERE rn = 2";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1,restaur.getRegionNum());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                price3 = rs.getInt("RESTAURPRICE1");

            } else {

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return price3;
    }

    public int restaurMenu4(Restaur restaur) {

        PreparedStatement pstmt = null; // pstmt 초기화
        ResultSet rs = null;
        int price4 = 0;

        try {
            String sql = "SELECT  RESTAURPRICE2 FROM ( SELECT RESTAURPRICE2, ROW_NUMBER() OVER (ORDER BY RESTAURNUM ASC, RESTAURNAME ASC) as rn FROM RESTAURANT WHERE REGIONNUM = ? ) WHERE rn = 2";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1,restaur.getRegionNum());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                price4 = rs.getInt("RESTAURPRICE2");

            } else {

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return price4;
    }

    public int restaurMenu5(Restaur restaur) {

        PreparedStatement pstmt = null; // pstmt 초기화
        ResultSet rs = null;
        int price5 = 0;

        try {
            String sql = "SELECT  RESTAURPRICE1 FROM ( SELECT RESTAURPRICE1, ROW_NUMBER() OVER (ORDER BY RESTAURNUM ASC, RESTAURNAME ASC) as rn FROM RESTAURANT WHERE REGIONNUM = ? ) WHERE rn = 3";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1,restaur.getRegionNum());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                price5 = rs.getInt("RESTAURPRICE1");

            } else {

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return price5;
    }

    public int restaurMenu6(Restaur restaur) {

        PreparedStatement pstmt = null; // pstmt 초기화
        ResultSet rs = null;
        int price6 = 0;

        try {
            String sql = "SELECT  RESTAURPRICE2 FROM ( SELECT RESTAURPRICE2, ROW_NUMBER() OVER (ORDER BY RESTAURNUM ASC, RESTAURNAME ASC) as rn FROM RESTAURANT WHERE REGIONNUM = ? ) WHERE rn = 3";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1,restaur.getRegionNum());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                price6 = rs.getInt("RESTAURPRICE2");

            } else {

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return price6;
    }

    public void cancelReserve(int memberNum) {
        try {
            String sql = "DELETE FROM RESERVE WHERE MEMBERNUM = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, memberNum);
            int result = pstmt.executeUpdate();
            if (result > 0 ){
                System.out.println("예약 취소 성공");
            } else {
                System.out.println("예약 취소 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String restaurMenuName(int restaurNum, int i) {
        String menuName1 = null;

        try {
            String sql;
            if (i == 1){
                sql = "SELECT RESTAURMENU1 FROM RESTAURANT WHERE RESTAURNUM = ?";
            } else if (i == 2){
                sql = "SELECT RESTAURMENU2 FROM RESTAURANT WHERE RESTAURNUM = ?";
            } else {
                return null;
            }
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,restaurNum);
            rs = pstmt.executeQuery();

            if (rs.next()){
                menuName1 = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return menuName1;
    }

    public int getRestaurNumByOrder(int choiceRestaur, int regionNum) {
        int restaurantNum = 0;
        try {
            String sql = "SELECT RESTAURNUM FROM (SELECT RESTAURNUM, ROW_NUMBER() OVER (ORDER BY RESTAURNUM ASC) as rn FROM RESTAURANT WHERE REGIONNUM = ?) WHERE rn = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,regionNum);
            pstmt.setInt(2,choiceRestaur);
            rs = pstmt.executeQuery();
            if (rs.next()){
                restaurantNum = rs.getInt("RESTAURNUM");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return restaurantNum;

    }

    public Region getRandomRegion() {
        Region region = new Region();
        String sql = "SELECT REGIONNUM, REGIONNAME FROM REGION ORDER BY DBMS_RANDOM.VALUE FETCH FIRST 1 ROWS ONLY";

        try (
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {
            if (rs.next()) {
                region.setRegionNum(rs.getInt("REGIONNUM"));
                region.setRegionName(rs.getString("REGIONNAME"));
                System.out.println("🎲 랜덤 선택된 지역: " + region.getRegionName());
            }
        } catch (SQLException e) {
            throw new RuntimeException("랜덤 지역 조회 오류: " + e.getMessage());
        }

        return region;
    }

}
