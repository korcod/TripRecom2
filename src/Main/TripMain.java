package Main;

import TripAccess.TripSQL;
import TripObject.*;
import Util.TripUtil;

import java.util.Scanner;

public class TripMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean run1 = true;
        int btn1 = 0;

        boolean run2 = true;
        int btn2 = 0;

        boolean run3 = true;
        int btn3 = 0;

        boolean run4 = true;
        int btn4 = 0;

        boolean run5 = true;
        int btn5 = 0;

        boolean run6 = true;
        int btn6 = 0;

        boolean run7 = true;
        int btn7 = 0;

        boolean run8 = true;
        int btn8 = 0;

        boolean run9 = true;
        int btn9 = 0;

        boolean run10 = true;
        int btn10 = 0;

        TripUtil util = new TripUtil();
        Hotel hotel = new Hotel();
        Landmark landmark = new Landmark();
        Region region = new Region();
        Reserve reserve = new Reserve();
        Restaur restaur = new Restaur();
        TripMember tripmember = new TripMember();
        TripSQL tripsql = new TripSQL();

        String tId = "";
        String tPw = "";

        tripsql.connect();

        while (run1) {
            // ★★★★★ 맨 처음 초기화면 ★★★★★
            System.out.println("┌===============================================┐");
            System.out.println("∥ [1] 여행지 추천\t[2] 랜덤 추천\t[0] 종료\t\t∥");
            System.out.println("└===============================================┘");
            System.out.println("↓↓↓ 선택 바랍니다. ↓↓↓");
            btn1 = sc.nextInt();

            switch (btn1) {
                case 1:
                    // ★★★★★ 여행지 추천(1번) 선택시 ★★★★★
                    run2 = true;
                    System.out.println("국내 여행지를 선택해 주세요!");
                    System.out.println("┌============================================┐");
                    System.out.println("∥ [1] 서울\t[2] 인천\t\t[3] 대전\t\t[4] 광주  ∥");
                    System.out.println("∥ [5] 대구\t[6] 울산\t\t[7] 부산\t\t[8] 강원  ∥");
                    System.out.println("∥ [9] 충북\t[10] 충남\t[11] 경기\t[12] 제주 ∥");
                    System.out.println("∥ [13] 전남\t[14] 전북\t[15] 경남\t[16] 경북 ∥");
                    System.out.println("└============================================┘");
                    System.out.println("↓↓↓ 선택 바랍니다. ↓↓↓");
                    region.setRegionNum(sc.nextInt());
                    // ●●●●● 번호를 누르면 그 데이터에 대한 값 가져옴 ●●●●●
                    tripsql.regionChoice(region);

                    if (region.getRegionNum() != 0 && region.getRegionName() != null) {        // ●●●●● 선택지가 0이 아니라면 ●●●●●
                        while (run2) {
                            run3 = true;
                            run4 = true;
                            run9 = true;
                            // ★★★★★ 지역 선택후 선택지 ★★★★★
                            System.out.println(region.getRegionName() + "의 어떤 것을 보시겠습니까?");
                            System.out.println("┌==================================================┐");
                            System.out.println("∥ [1] 지역명소\t[2] 맛집\t\t[3] 숙소\t\t[0] 뒤로가기∥");
                            System.out.println("└==================================================┘");
                            System.out.println("↓↓↓ 선택 바랍니다. ↓↓↓");
                            btn2 = sc.nextInt();
                            switch (btn2) {
                                // ★★★★★ 지역명소를 선택 시 선택지 ★★★★★
                                case 1:
                                    System.out.println(region.getRegionName() + "의 지역명소를 선택하셨습니다.");
                                    // ●●●●● 번호를 누르면 그 데이터에 대한 값 가져옴 ●●●●●
                                    landmark.setRegionNum(region.getRegionNum());
                                    tripsql.landmarkshow(landmark);

                                    while (run3) {
                                        // ★★★★★ 지역에대한 명소와 설명을 보여준 뒤 뜨는 선택지 ★★★★★
                                        System.out.println("┌=============================┐");
                                        System.out.println("∥ [1] 뒤로가기\t\t[0] 종료\t  ∥");
                                        System.out.println("└=============================┘");
                                        System.out.println("↓↓↓ 선택 바랍니다. ↓↓↓");
                                        btn3 = sc.nextInt();
                                        switch (btn3) {
                                            case 1:
                                                run3 = false;
                                                break;
                                            case 0:
                                                run1 = false;
                                                run2 = false;
                                                run3 = false;
                                                tripsql.conClose();
                                                break;
                                            default:
                                                System.out.println("잘못 입력하셨습니다.");
                                                break;
                                        }
                                    }

                                    break;

                                // ★★★★★ 맛집을 선택 시 선택지 ★★★★★
                                case 2:
                                    System.out.println(region.getRegionName() + "의 맛집을 선택하셨습니다.");
                                    restaur.setRegionNum(region.getRegionNum());        // 지역 번호 불러오기
                                    tripsql.restaurShow(restaur);                       // 맛집 테이블과 지역 테이블 조인으로 조회하는 메서드 작성

                                    while (run4) {
                                        // 맛집을 보여주고 예약하려면 로그인
                                        System.out.println("예약하려면 회원가입 및 로그인이 필요합니다.");
                                        System.out.println("┌==========================================┐");
                                        System.out.println("∥ [1] 회원가입\t[2] 로그인\t[0] 뒤로가기\t   ∥");
                                        System.out.println("└==========================================┘");
                                        System.out.println("↓↓↓ 선택 바랍니다. ↓↓↓");
                                        btn4 = sc.nextInt();


                                        switch (btn4) {
                                            // ★★★★★ 회원가입을 선택 시 선택지 ★★★★★
                                            case 1:
                                                // 미리 회원 고유번호를 만들어주기 그리고 DB에 대입
                                                tripmember.setMemberNum(tripsql.memberNumber());

                                                System.out.println("회원가입 진행하겠습니다.");
                                                System.out.println("아이디를 입력해주세요.");
                                                tripmember.setMemberId(sc.next());

                                                System.out.println("비밀번호를 입력해주세요.");
                                                tripmember.setMemberPw(sc.next());

                                                System.out.println("전화번호를 입력해주세요.");
                                                tripmember.setMemberPhone(sc.next());

                                                System.out.println("생년월일을 입력해주세요.");
                                                tripmember.setMemberBirth(sc.next());

                                                // 회원의 포인트는 0원으로 초기화
                                                tripmember.setMemberPoint(0);

                                                // 회원가입 sql 메서드 작성
                                                tripsql.tripMemberJoin(tripmember);
                                                break;
                                            // 로그인★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
                                            case 2:
                                                run5 = true;
                                                System.out.println("아이디를 입력해주세요");
                                                tId = sc.next();
                                                System.out.println("비밀번호를 입력해주세요");
                                                tPw = sc.next();

                                                tripmember = tripsql.memberLogin(tId, tPw); // 아이디랑 비밀번호가 맞다면
                                                if (tripmember.getMemberId() != null) {      // 로그인 성공 시 나오는 선택지
                                                    while (run5) {
                                                        System.out.println("┌=========================================================┐");
                                                        System.out.println("∥ [1] 회원 정보\t[2] 포인트 충전\t[3] 예약\t\t[0] 로그아웃\t  ∥");
                                                        System.out.println("└=========================================================┘");
                                                        System.out.println("↓↓↓ 선택 바랍니다. ↓↓↓");
                                                        btn5 = sc.nextInt();

                                                        switch (btn5) {
                                                            case 1:
                                                                run6 = true;
                                                                System.out.print("☆ 아이디: " + tripmember.getMemberId());
                                                                System.out.print("\t☆ 비밀번호: " + tripmember.getMemberPw());
                                                                System.out.println("\t☆ 전화번호: " + tripmember.getMemberPhone());
                                                                System.out.print("☆ 주민번호: " + tripmember.getMemberBirth());
                                                                System.out.println("\t☆ 포인트: " + tripmember.getMemberPoint());
                                                                reserve.setMemberNum(tripmember.getMemberNum());                        // 회원정보 조회 경우 고유번호를 가져와 조회 가능
                                                                System.out.println("☆ 예약 정보: " + tripsql.reserveinfo(reserve));     // 회원정보 조회 경우 예약 정보를 조인해서 조회하는 메서드 생성
                                                                while (run6) {
                                                                    run7 = true;
                                                                    System.out.println("┌===============================================┐");
                                                                    System.out.println("∥ [1] 회원 정보 수정\t[2] 회원 탈퇴\t[0] 뒤로가기\t∥");
                                                                    System.out.println("└===============================================┘");
                                                                    System.out.println("↓↓↓ 선택 바랍니다. ↓↓↓");
                                                                    btn6 = sc.nextInt();
                                                                    switch (btn6) {
                                                                        case 1:
                                                                            while (run7) {
                                                                                System.out.println("┌=======================================┐");
                                                                                System.out.println("∥ [1] 아이디 변경\t[2] 비밀번호 변경\t∥");
                                                                                System.out.println("∥ [3] 전화번호 변경\t[4] 주민번호 변경\t∥");
                                                                                System.out.println("└=======================================┘");
                                                                                System.out.println("↓↓↓ 선택 바랍니다. ↓↓↓");
                                                                                btn7 = sc.nextInt();
                                                                                sc.nextLine();

                                                                                switch (btn7) {
                                                                                    case 1:
                                                                                        System.out.println("변경하실 아이디를 입력해주세요.");
                                                                                        tripmember.setMemberId(sc.nextLine());

                                                                                        break;
                                                                                    case 2:
                                                                                        System.out.println("변경하실 비밀번호를 입력해주세요.");
                                                                                        tripmember.setMemberPw(sc.nextLine());
                                                                                        break;
                                                                                    case 3:
                                                                                        System.out.println("변경하실 전화번호를 입력해주세요.");
                                                                                        tripmember.setMemberPhone(sc.next());
                                                                                        break;
                                                                                    case 4:
                                                                                        System.out.println("변경하실 주민번호를 입력해주세요.");
                                                                                        tripmember.setMemberBirth(sc.next());
                                                                                        break;
                                                                                    default:
                                                                                        System.out.println("잘못 입력하셨습니다.");
                                                                                        break;
                                                                                }
                                                                                tripsql.memberUpdate(tripmember);           // 회원정보 수정 메서드 추가
                                                                                run6 = false;
                                                                                break;
                                                                            }
                                                                            break;
                                                                        case 2:
                                                                            System.out.println("정말로 탈퇴를 원하십니까? (Y/N)");
                                                                            String checkDelete = sc.next();
                                                                            switch (checkDelete) {
                                                                                case "y":
                                                                                case "Y":
                                                                                    tripsql.memberDelete(tripmember.getMemberNum());        // 회원 탈퇴 메서드 추가
                                                                                    run2 = false;
                                                                                    run4 = false;
                                                                                    run5 = false;
                                                                                    run6 = false;
                                                                                    tripmember = new TripMember();
                                                                                    break;
                                                                                case "n":
                                                                                case "N":
                                                                                    System.out.println("탈퇴 취소");

                                                                                    break;
                                                                                default:
                                                                                    System.out.println("잘못 입력하셨습니다.");
                                                                                    break;
                                                                            }

                                                                            break;
                                                                        case 0:
                                                                            run6 = false;
                                                                            break;
                                                                        default:
                                                                            System.out.println("잘못 입력하셨습니다.");
                                                                            break;
                                                                    }
                                                                }

                                                                break;
                                                            case 2:
                                                                System.out.println("비밀번호를 입력하세요");
                                                                String checkPW = sc.next();
                                                                if (tripmember.getMemberPw().equals(checkPW)) {         // 저장된 비밀번호를 불러와서 맞다면
                                                                    System.out.println("얼마를 충전하시겠습니까?");
                                                                    int plusPoint = sc.nextInt();
                                                                    tripsql.pointCharge(tripmember, plusPoint);         // 포인트 충전해 데이터베이스에 저장하는 메서드 생성
                                                                    System.out.println("현재 포인트: " + tripmember.getMemberPoint());
                                                                    break;
                                                                } else {
                                                                    System.out.println("비밀번호를 잘못 입력하셨습니다.");
                                                                }

                                                                break;
                                                            case 3:
                                                                if (tripsql.existReserve(tripmember.getMemberNum())) {       // 회원고유번호를 조회했을 때 이미 예약이 한 번 되어있는 상태라면 취소할 수 있도록 하는 메서드 생성
                                                                    System.out.println("당일 예약이기에 예약은 한번만 가능합니다.");
                                                                    System.out.println("취소 후 환불은 불가합니다!");
                                                                    System.out.println("취소하시겠습니까? (y/n)");
                                                                    String reserveCancel = sc.next();
                                                                    switch (reserveCancel) {
                                                                        case "y":
                                                                        case "Y":
                                                                            tripsql.cancelReserve(tripmember.getMemberNum());   // 회원고유번호를 조회해 예약정보를 지우는 메서드 생성
                                                                            break;
                                                                        case "n":
                                                                        case "N":
                                                                            break;
                                                                        default:
                                                                            System.out.println("잘못 입력하셨습니다.");
                                                                            break;

                                                                    }
                                                                    break;
                                                                }
                                                                //==========================================================================================  본격 예약
                                                                System.out.println(region.getRegionName() + "의 맛집을 선택하셨습니다.");
                                                                restaur.setRegionNum(region.getRegionNum());    // 지역 테이블의 지역번호로 맛집 테이블을 가져옴
                                                                tripsql.restaurShow(restaur);                   // 맛집 테이블의 정보 조회
                                                                tripsql.restaurNameShow(restaur);               // 맛집 테이블의 정보 가져와서 정렬
                                                                System.out.println("↓↓↓ 1~4번 중 선택 바랍니다.(숫자만 입력) ↓↓↓");  // 맛집 선택
                                                                int choiceRestaur = sc.nextInt();

                                                                int actualRestaurNum = tripsql.getRestaurNumByOrder(choiceRestaur, region.getRegionNum());      // 예약정보에서 메뉴를 조회하기위한 메서드 추가
                                                                restaur.setRestaurNum(actualRestaurNum);

                                                                switch (choiceRestaur) {
// ========================================================================================1번 맛집 선택========================================================================================
                                                                    case 1:
                                                                        System.out.println("1번을 선택하셨습니다");
                                                                        tripsql.restaurInfo1(restaur);      // 예약정보의  맛집테이블의 지역번호중에 위에서부터 첫번째 컬럼을 가져와 조회

                                                                        int choiceMenu1 = sc.nextInt();

                                                                        String selectMenu1 = null;
                                                                        String selectMenu2 = null;

                                                                        int selectMenuPrice = 0;

                                                                        String menuName1 = tripsql.restaurMenuName(restaur.getRestaurNum(), 1);     // 1번 메뉴 선택 시 메뉴이름 저장
                                                                        String menuName2 = tripsql.restaurMenuName(restaur.getRestaurNum(), 2);     // 2번 메뉴 선택 시 메뉴이름 저장
                                                                        int menuPrice1 = tripsql.restaurMenu1(restaur);                                // 1번 메뉴 선택 시 가격 저장
                                                                        int menuPrice2 = tripsql.restaurMenu2(restaur);                                // 2번 메뉴 선택 시 가격 저장

                                                                        switch (choiceMenu1) {
// ========================================================================================1번 맛집 1번 메뉴 선택========================================================================================
                                                                            case 1:
                                                                                int numOfTotalPeople = 0;           // 주문 인원

                                                                                while (true) {
                                                                                    System.out.println("인원 수 선택 (최대 10인분)");
                                                                                    numOfTotalPeople = sc.nextInt();
                                                                                    if (numOfTotalPeople > 1 && numOfTotalPeople < 11) {
                                                                                        break; // 인원 수 입력 하면 while문 탈출
                                                                                    }
                                                                                    System.out.println("잘못된 인원 선택입니다. 1인분부터 10인분까지만 가능합니다.");
                                                                                }
                                                                                int totalPrice = numOfTotalPeople * menuPrice1;     // 총 금액 계산

                                                                                TripUtil.reservationMenuProcess(        // TripUtil
                                                                                        sc,                 // Scanner 객체
                                                                                        reserve,            // Reserve 객체
                                                                                        restaur,            // 맛집 객체
                                                                                        tripmember,         // Tripmember 객체
                                                                                        tripsql,            // Tripsql 객체
                                                                                        menuName1,          // 선택된 1번 메뉴 이름
                                                                                        null,               // 2번 메뉴는 선택되지 않았으므로 null
                                                                                        totalPrice,         // 계산된 총 금액
                                                                                        numOfTotalPeople    // 선택된 인원수
                                                                                );
                                                                                break;
// ========================================================================================1번 맛집 2번 메뉴 선택========================================================================================
                                                                            case 2:
                                                                                int numOfTotalPeople2 = 0;           // 주문 인원

                                                                                while (true) {
                                                                                    System.out.println("인원 수 선택 (최대 10인분)");
                                                                                    numOfTotalPeople2 = sc.nextInt();
                                                                                    if (numOfTotalPeople2 > 1 && numOfTotalPeople2 < 11) {
                                                                                        break; // 인원 수 입력 하면 while문 탈출
                                                                                    }
                                                                                    System.out.println("잘못된 인원 선택입니다. 1인분부터 10인분까지만 가능합니다.");
                                                                                }
                                                                                int totalPrice2 = numOfTotalPeople2 * menuPrice2;     // 총 금액 계산

                                                                                TripUtil.reservationMenuProcess(        // TripUtil
                                                                                        sc,                 // Scanner 객체
                                                                                        reserve,            // Reserve 객체
                                                                                        restaur,            // 맛집 객체
                                                                                        tripmember,         // Tripmember 객체
                                                                                        tripsql,            // Tripsql 객체
                                                                                        null,               // null
                                                                                        menuName2,           // 2번 메뉴 선택시
                                                                                        totalPrice2,         // 계산된 총 금액
                                                                                        numOfTotalPeople2    // 선택된 인원수
                                                                                );
                                                                                break;
// ========================================================================================1번 맛집 1번, 2번 메뉴 선택========================================================================================
                                                                            case 3: // 1번, 2번 다중 선택 시!
                                                                                reserve.setReserveNum(tripsql.reserveNumber());     // 예약테이블의 예약 고유번호를 조회

                                                                                int numberOfMenu1 = 0;
                                                                                int numberOfMenu2 = 0;
                                                                                int totalPeople = 0; // 총 주문 인원수

                                                                                while (true) { // 올바른 수량을 입력받을 때까지 반복
                                                                                    System.out.println("1번 메뉴 [" + menuName1 + "] 몇 개 주문하시겠습니까? (최대 5개)");
                                                                                    numberOfMenu1 = sc.nextInt();

                                                                                    if (numberOfMenu1 < 1 || numberOfMenu1 > 5) {
                                                                                        System.out.println("잘못된 수량입니다. 1개부터 5개까지만 주문 가능합니다.");
                                                                                        continue; // 다시 입력하도록
                                                                                    }

                                                                                    System.out.println("2번 메뉴 [" + menuName2 + "] 몇 개 주문하시겠습니까? (최대 5개)");
                                                                                    numberOfMenu2 = sc.nextInt();

                                                                                    if (numberOfMenu2 < 1 || numberOfMenu2 > 5) {
                                                                                        System.out.println("잘못된 수량입니다. 1개부터 5개까지만 주문 가능합니다.");
                                                                                        continue;
                                                                                    }
                                                                                    totalPeople = numberOfMenu1 + numberOfMenu2;        // 인원 = 결국 총 메뉴의 개수
                                                                                    break;
                                                                                }
                                                                                reserve.setReservePeopleNum(totalPeople);   // 총 주문 인원수 설정

                                                                                reserve.setReserveMenu1(menuName1);
                                                                                reserve.setReserveMenu2(menuName2);

                                                                                int totalCombinedPrice = (menuPrice1 * numberOfMenu1) + (menuPrice2 * numberOfMenu2);   // 총 가격

                                                                                System.out.println("예약시간은 11시에서 22시까지 가능합니다.");
                                                                                System.out.println("몇 시에 예약 하시겠습니까?");
                                                                                reserve.setReserveTime(sc.nextInt());     // 예약 테이블 예약시간 불러오기
                                                                                if (10 < reserve.getReserveTime() && reserve.getReserveTime() < 23) {

                                                                                    tripsql.restaurCheck(restaur);          // 맛집테이블의 지역번호 불러와 조회
                                                                                    reserve.setMemberNum(tripmember.getMemberNum());    // 예약테이블에 회원번호 저장
                                                                                    reserve.setRestaurNum(restaur.getRestaurNum());     // 예약테이블에 맛집번호 저장

                                                                                    int amount = totalCombinedPrice;
                                                                                    reserve.setReservePrice(amount);        // 예약테이블에 주문 가격 저장

                                                                                    System.out.println("★★★예약은 당일 예약이므로 예약이 하나만 가능합니다.★★★");
                                                                                    System.out.println("예약시간은 " + reserve.getReserveTime() + "시 이며, 가격은 " + amount + "원입니다.");
                                                                                    System.out.println("결제는 포인트로 진행되며, 예약 상황은 회원 정보에서 확인이 가능합니다.");
                                                                                    System.out.println("결제 하시겠습니까? (y/n)");
                                                                                    int b = tripmember.getMemberPoint();    // 회원 포인트 불러오기
                                                                                    String pay1 = sc.next();
                                                                                    switch (pay1) {
                                                                                        case "y":
                                                                                        case "Y":
                                                                                            if (b < amount) {
                                                                                                System.out.println("포인트가 부족합니다. 현재 포인트: " + b + " 결제 금액: " + amount);
                                                                                                break;
                                                                                            }
                                                                                            tripsql.reserveCreate(reserve);     // 예약 테이블 값 넣어주는 메서드
                                                                                            tripmember = tripsql.remainAmount(tripmember, amount);
                                                                                            break;
                                                                                        case "n":
                                                                                        case "N":
                                                                                            System.out.println("결제를 취소합니다.");
                                                                                            break;
                                                                                        default:
                                                                                            System.out.println("잘못 입력하셨습니다.");
                                                                                            break;
                                                                                    }
                                                                                } else {
                                                                                    System.out.println("잘못된 시간 선택입니다.");
                                                                                }
                                                                                break;
                                                                            default:
                                                                                System.out.println("잘못 입력하셨습니다.");
                                                                                break;
                                                                        }
                                                                        break;
// ===================================================================================================2번 맛집 선택 시=================================================================================================================
                                                                    case 2:
                                                                        System.out.println("2번을 선택하셨습니다");
                                                                        tripsql.restaurInfo2(restaur);

                                                                        int choiceMenu2 = sc.nextInt();

                                                                        String selectMenu3 = null;
                                                                        String selectMenu4 = null;

                                                                        int selectMenuPrice2 = 0;

                                                                        String menuName3 = tripsql.restaurMenuName(restaur.getRestaurNum(), 1);
                                                                        String menuName4 = tripsql.restaurMenuName(restaur.getRestaurNum(), 2);
                                                                        int menuPrice3 = tripsql.restaurMenu3(restaur);
                                                                        int menuPrice4 = tripsql.restaurMenu4(restaur);

                                                                        switch (choiceMenu2) {
// ========================================================================================2번 맛집 1번 메뉴 선택========================================================================================
                                                                            case 1:
                                                                                int numOfTotalPeople = 0;           // 주문 인원

                                                                                while (true) {
                                                                                    System.out.println("인원 수 선택 (최대 10인분)");
                                                                                    numOfTotalPeople = sc.nextInt();
                                                                                    if (numOfTotalPeople > 1 && numOfTotalPeople < 11) {
                                                                                        break; // 인원 수 입력 하면 while문 탈출
                                                                                    }
                                                                                    System.out.println("잘못된 인원 선택입니다. 1인분부터 10인분까지만 가능합니다.");
                                                                                }
                                                                                int totalPrice = numOfTotalPeople * menuPrice3;     // 총 금액 계산

                                                                                TripUtil.reservationMenuProcess(        // TripUtil
                                                                                        sc,                 // Scanner 객체
                                                                                        reserve,            // Reserve 객체
                                                                                        restaur,            // 맛집 객체
                                                                                        tripmember,         // Tripmember 객체
                                                                                        tripsql,            // Tripsql 객체
                                                                                        menuName3,          // 선택된 1번 메뉴 이름
                                                                                        null,               // 2번 메뉴는 선택되지 않았으므로 null
                                                                                        totalPrice,         // 계산된 총 금액
                                                                                        numOfTotalPeople    // 선택된 인원수
                                                                                );
                                                                                break;
// ========================================================================================2번 맛집 2번 메뉴 선택========================================================================================
                                                                            case 2:
                                                                                int numOfTotalPeople2 = 0;           // 주문 인원

                                                                                while (true) {
                                                                                    System.out.println("인원 수 선택 (최대 10인분)");
                                                                                    numOfTotalPeople2 = sc.nextInt();
                                                                                    if (numOfTotalPeople2 > 1 && numOfTotalPeople2 < 11) {
                                                                                        break; // 인원 수 입력 하면 while문 탈출
                                                                                    }
                                                                                    System.out.println("잘못된 인원 선택입니다. 1인분부터 10인분까지만 가능합니다.");
                                                                                }
                                                                                int totalPrice2 = numOfTotalPeople2 * menuPrice4;     // 총 금액 계산

                                                                                TripUtil.reservationMenuProcess(        // TripUtil
                                                                                        sc,                 // Scanner 객체
                                                                                        reserve,            // Reserve 객체
                                                                                        restaur,            // 맛집 객체
                                                                                        tripmember,         // Tripmember 객체
                                                                                        tripsql,            // Tripsql 객체
                                                                                        null,          // 선택된 1번 메뉴 이름
                                                                                        menuName4,               // 2번 메뉴는 선택되지 않았으므로 null
                                                                                        totalPrice2,         // 계산된 총 금액
                                                                                        numOfTotalPeople2    // 선택된 인원수
                                                                                );
                                                                                break;
// ========================================================================================2번 맛집 1번, 2번 메뉴 선택========================================================================================
                                                                            case 3:
                                                                                reserve.setReserveNum(tripsql.reserveNumber());

                                                                                int numberOfMenu1 = 0;
                                                                                int numberOfMenu2 = 0;
                                                                                int totalPeople = 0; // 총 주문 인원수

                                                                                while (true) { // 올바른 수량을 입력받을 때까지 반복
                                                                                    System.out.println("1번 메뉴 [" + menuName3 + "] 몇 개 주문하시겠습니까? (최대 5개)");
                                                                                    numberOfMenu1 = sc.nextInt();

                                                                                    if (numberOfMenu1 < 1 || numberOfMenu1 > 5) {
                                                                                        System.out.println("잘못된 수량입니다. 1개부터 5개까지만 주문 가능합니다.");
                                                                                        continue; // 다시 입력하도록
                                                                                    }

                                                                                    System.out.println("2번 메뉴 [" + menuName4 + "] 몇 개 주문하시겠습니까? (최대 5개)");
                                                                                    numberOfMenu2 = sc.nextInt();

                                                                                    if (numberOfMenu2 < 1 || numberOfMenu2 > 5) {
                                                                                        System.out.println("잘못된 수량입니다. 1개부터 5개까지만 주문 가능합니다.");
                                                                                        continue;
                                                                                    }
                                                                                    totalPeople = numberOfMenu1 + numberOfMenu2;
                                                                                    break;
                                                                                }
                                                                                reserve.setReservePeopleNum(totalPeople);   // 총 주문 인원수 설정

                                                                                reserve.setReserveMenu1(menuName3);
                                                                                reserve.setReserveMenu2(menuName4);

                                                                                int totalCombinedPrice = (menuPrice3 * numberOfMenu1) + (menuPrice4 * numberOfMenu2);

                                                                                System.out.println("예약시간은 11시에서 22시까지 가능합니다.");
                                                                                System.out.println("몇 시에 예약 하시겠습니까?");
                                                                                reserve.setReserveTime(sc.nextInt());     // 예약 테이블 예약시간 불러오기
                                                                                if (10 < reserve.getReserveTime() && reserve.getReserveTime() < 23) {

                                                                                    tripsql.restaurCheck(restaur);
                                                                                    reserve.setMemberNum(tripmember.getMemberNum());
                                                                                    reserve.setRestaurNum(restaur.getRestaurNum());

                                                                                    int amount = totalCombinedPrice;
                                                                                    reserve.setReservePrice(amount);

                                                                                    System.out.println("★★★예약은 당일 예약이므로 예약이 하나만 가능합니다.★★★");
                                                                                    System.out.println("예약시간은 " + reserve.getReserveTime() + "시 이며, 가격은 " + amount + "원입니다.");
                                                                                    System.out.println("결제는 포인트로 진행되며, 예약 상황은 회원 정보에서 확인이 가능합니다.");
                                                                                    System.out.println("결제 하시겠습니까? (y/n)");
                                                                                    int b = tripmember.getMemberPoint();
                                                                                    String pay1 = sc.next();
                                                                                    switch (pay1) {
                                                                                        case "y":
                                                                                        case "Y":
                                                                                            if (b < amount) {
                                                                                                System.out.println("포인트가 부족합니다. 현재 포인트: " + b + " 결제 금액: " + amount);
                                                                                                break;
                                                                                            }
                                                                                            tripsql.reserveCreate(reserve);
                                                                                            tripmember = tripsql.remainAmount(tripmember, amount);
                                                                                            break;
                                                                                        case "n":
                                                                                        case "N":
                                                                                            System.out.println("결제를 취소합니다.");
                                                                                            break;
                                                                                        default:
                                                                                            System.out.println("잘못 입력하셨습니다.");
                                                                                            break;
                                                                                    }
                                                                                } else {
                                                                                    System.out.println("잘못된 시간 선택입니다.");
                                                                                }
                                                                                break;
                                                                            default:
                                                                                System.out.println("잘못 입력하셨습니다.");
                                                                                break;
                                                                        }
                                                                        break;
// ===================================================================================================3번 맛집 선택 시=================================================================================================================
                                                                    case 3:
                                                                        System.out.println("3번을 선택하셨습니다");
                                                                        tripsql.restaurInfo3(restaur);

                                                                        int choiceMenu3 = sc.nextInt();

                                                                        String selectMenu5 = null;
                                                                        String selectMenu6 = null;

                                                                        int selectMenuPrice3 = 0;

                                                                        String menuName5 = tripsql.restaurMenuName(restaur.getRestaurNum(), 1);
                                                                        String menuName6 = tripsql.restaurMenuName(restaur.getRestaurNum(), 2);
                                                                        int menuPrice5 = tripsql.restaurMenu5(restaur);
                                                                        int menuPrice6 = tripsql.restaurMenu6(restaur);

                                                                        switch (choiceMenu3) {
// ========================================================================================3번 맛집 1번 메뉴 선택========================================================================================
                                                                            case 1:
                                                                                int numOfTotalPeople = 0;           // 주문 인원

                                                                                while (true) {
                                                                                    System.out.println("인원 수 선택 (최대 10인분)");
                                                                                    numOfTotalPeople = sc.nextInt();
                                                                                    if (numOfTotalPeople > 1 && numOfTotalPeople < 11) {
                                                                                        break; // 인원 수 입력 하면 while문 탈출
                                                                                    }
                                                                                    System.out.println("잘못된 인원 선택입니다. 1인분부터 10인분까지만 가능합니다.");
                                                                                }
                                                                                int totalPrice = numOfTotalPeople * menuPrice5;     // 총 금액 계산

                                                                                TripUtil.reservationMenuProcess(        // TripUtil
                                                                                        sc,                 // Scanner 객체
                                                                                        reserve,            // Reserve 객체
                                                                                        restaur,            // 맛집 객체
                                                                                        tripmember,         // Tripmember 객체
                                                                                        tripsql,            // Tripsql 객체
                                                                                        menuName5,          // 선택된 1번 메뉴 이름
                                                                                        null,               // 2번 메뉴는 선택되지 않았으므로 null
                                                                                        totalPrice,         // 계산된 총 금액
                                                                                        numOfTotalPeople    // 선택된 인원수
                                                                                );
                                                                                break;
// ========================================================================================3번 맛집 2번 메뉴 선택========================================================================================
                                                                            case 2:
                                                                                int numOfTotalPeople2 = 0;           // 주문 인원

                                                                                while (true) {
                                                                                    System.out.println("인원 수 선택 (최대 10인분)");
                                                                                    numOfTotalPeople2 = sc.nextInt();
                                                                                    if (numOfTotalPeople2 > 1 && numOfTotalPeople2 < 11) {
                                                                                        break; // 인원 수 입력 하면 while문 탈출
                                                                                    }
                                                                                    System.out.println("잘못된 인원 선택입니다. 1인분부터 10인분까지만 가능합니다.");
                                                                                }
                                                                                int totalPrice2 = numOfTotalPeople2 * menuPrice6;     // 총 금액 계산

                                                                                TripUtil.reservationMenuProcess(        // TripUtil
                                                                                        sc,                 // Scanner 객체
                                                                                        reserve,            // Reserve 객체
                                                                                        restaur,            // 맛집 객체
                                                                                        tripmember,         // Tripmember 객체
                                                                                        tripsql,            // Tripsql 객체
                                                                                        null,          // 선택된 1번 메뉴 이름
                                                                                        menuName6,               // 2번 메뉴는 선택되지 않았으므로 null
                                                                                        totalPrice2,         // 계산된 총 금액
                                                                                        numOfTotalPeople2    // 선택된 인원수
                                                                                );
                                                                                break;
// ========================================================================================3번 맛집 1번, 2번 메뉴 선택========================================================================================
                                                                            case 3:
                                                                                reserve.setReserveNum(tripsql.reserveNumber());

                                                                                int numberOfMenu1 = 0;
                                                                                int numberOfMenu2 = 0;
                                                                                int totalPeople = 0; // 총 주문 인원수

                                                                                while (true) { // 올바른 수량을 입력받을 때까지 반복
                                                                                    System.out.println("1번 메뉴 [" + menuName5 + "] 몇 개 주문하시겠습니까? (최대 5개)");
                                                                                    numberOfMenu1 = sc.nextInt();

                                                                                    if (numberOfMenu1 < 1 || numberOfMenu1 > 5) {
                                                                                        System.out.println("잘못된 수량입니다. 1개부터 5개까지만 주문 가능합니다.");
                                                                                        continue; // 다시 입력하도록
                                                                                    }

                                                                                    System.out.println("2번 메뉴 [" + menuName6 + "] 몇 개 주문하시겠습니까? (최대 5개)");
                                                                                    numberOfMenu2 = sc.nextInt();

                                                                                    if (numberOfMenu2 < 1 || numberOfMenu2 > 5) {
                                                                                        System.out.println("잘못된 수량입니다. 1개부터 5개까지만 주문 가능합니다.");
                                                                                        continue;
                                                                                    }
                                                                                    totalPeople = numberOfMenu1 + numberOfMenu2;
                                                                                    break;
                                                                                }
                                                                                reserve.setReservePeopleNum(totalPeople);   // 총 주문 인원수 설정

                                                                                reserve.setReserveMenu1(menuName5);
                                                                                reserve.setReserveMenu2(menuName6);

                                                                                int totalCombinedPrice = (menuPrice5 * numberOfMenu1) + (menuPrice6 * numberOfMenu2);

                                                                                System.out.println("예약시간은 11시에서 22시까지 가능합니다.");
                                                                                System.out.println("몇 시에 예약 하시겠습니까?");
                                                                                reserve.setReserveTime(sc.nextInt());     // 예약 테이블 예약시간 불러오기
                                                                                if (10 < reserve.getReserveTime() && reserve.getReserveTime() < 23) {

                                                                                    tripsql.restaurCheck(restaur);
                                                                                    reserve.setMemberNum(tripmember.getMemberNum());
                                                                                    reserve.setRestaurNum(restaur.getRestaurNum());

                                                                                    int amount = totalCombinedPrice;
                                                                                    reserve.setReservePrice(amount);

                                                                                    System.out.println("★★★예약은 당일 예약이므로 예약이 하나만 가능합니다.★★★");
                                                                                    System.out.println("예약시간은 " + reserve.getReserveTime() + "시 이며, 가격은 " + amount + "원입니다.");
                                                                                    System.out.println("결제는 포인트로 진행되며, 예약 상황은 회원 정보에서 확인이 가능합니다.");
                                                                                    System.out.println("결제 하시겠습니까? (y/n)");
                                                                                    int b = tripmember.getMemberPoint();
                                                                                    String pay1 = sc.next();
                                                                                    switch (pay1) {
                                                                                        case "y":
                                                                                        case "Y":
                                                                                            if (b < amount) {
                                                                                                System.out.println("포인트가 부족합니다. 현재 포인트: " + b + " 결제 금액: " + amount);
                                                                                                break;
                                                                                            }
                                                                                            tripsql.reserveCreate(reserve);
                                                                                            tripmember = tripsql.remainAmount(tripmember, amount);
                                                                                            break;
                                                                                        case "n":
                                                                                        case "N":
                                                                                            System.out.println("결제를 취소합니다.");
                                                                                            break;
                                                                                        default:
                                                                                            System.out.println("잘못 입력하셨습니다.");
                                                                                            break;
                                                                                    }
                                                                                } else {
                                                                                    System.out.println("잘못된 시간 선택입니다.");
                                                                                }
                                                                                break;
                                                                            default:
                                                                                System.out.println("잘못 입력하셨습니다.");
                                                                                break;
                                                                        }
                                                                        break;
// ========================================================================================다른 여행지 선택========================================================================================
                                                                    case 4:
                                                                        System.out.println("┌============================================┐");
                                                                        System.out.println("∥ [1] 서울\t[2] 인천\t\t[3] 대전\t\t[4] 광주  ∥");
                                                                        System.out.println("∥ [5] 대구\t[6] 울산\t\t[7] 부산\t\t[8] 강원  ∥");
                                                                        System.out.println("∥ [9] 충북\t[10] 충남\t[11] 경기\t[12] 제주 ∥");
                                                                        System.out.println("∥ [13] 전남\t[14] 전북\t[15] 경남\t[16] 경북 ∥");
                                                                        System.out.println("└============================================┘");
                                                                        System.out.println("↓↓↓ 선택 바랍니다. ↓↓↓");
                                                                        region.setRegionNum(sc.nextInt());

                                                                        restaur.setRegionNum(region.getRegionNum());
                                                                        tripsql.restaurShow(restaur);
                                                                        break;
                                                                    default:
                                                                        System.out.println("잘못 입력하셨습니다.");
                                                                        break;
                                                                }


                                                                break;
                                                            case 0:
                                                                run5 = false;
                                                                break;
                                                            default:
                                                                System.out.println("잘못 입력하셨습니다.");
                                                                break;
                                                        }
                                                    }
                                                } else {
                                                    System.out.println("아이디 또는 비밀번호를 확인해주세요!(대소문자, 공백 확인)");
                                                }
                                                break;
                                            case 0:
                                                run4 = false;
                                                break;
                                            default:
                                                System.out.println("잘못 입력하셨습니다.");
                                                break;
                                        }
                                    }

                                    break;

                                case 3:
                                    System.out.println(region.getRegionName() + "의 숙소를 선택하셨습니다.");
                                    hotel.setRegionNum(region.getRegionNum());
                                    tripsql.hotelShow(hotel);

                                    while (run9) {
                                        // ★★★★★ 지역에대한 명소와 설명을 보여준 뒤 뜨는 선택지 ★★★★★
                                        System.out.println("┌=============================┐");
                                        System.out.println("∥ [1] 뒤로가기\t\t[0] 종료\t  ∥");
                                        System.out.println("└=============================┘");
                                        System.out.println("↓↓↓ 선택 바랍니다. ↓↓↓");
                                        btn9 = sc.nextInt();
                                        switch (btn9) {
                                            case 1:
                                                run9 = false;
                                                break;
                                            case 0:
                                                run1 = false;
                                                run2 = false;
                                                run9 = false;
                                                tripsql.conClose();
                                                break;
                                            default:
                                                System.out.println("잘못 입력하셨습니다.");
                                                break;
                                        }
                                    }

                                    break;

                                case 0:
                                    run2 = false;
                                    break;

                                default:
                                    System.out.println("잘못 입력하셨습니다.");
                                    break;
                            }


                        }
                    } else {
                        System.out.println("보기에 있는 숫자를 입력 바랍니다.");
                    }

                    break;
// ========================================================================================랜덤 여행지 선택========================================================================================
                case 2:
                    run10 = true;
                    while (run10) {
                        System.out.println("┌===============================┐");
                        System.out.println("∥ [1] 랜덤 돌리기\t[2] 뒤로가기\t∥");
                        System.out.println("└===============================┘");
                        System.out.println("↓↓↓ 선택 바랍니다. ↓↓↓");
                        btn10 = sc.nextInt();

                        switch (btn10) {
                            case 1:
                                // 랜덤 지역 가져오기
                                region = tripsql.getRandomRegion();
                                int regionNum = region.getRegionNum();

                                boolean runRandomDetail = true;
                                while (runRandomDetail) {
                                    System.out.println("┌=========================================┐");
                                    System.out.println("∥ [1] 지역명소 [2] 맛집 [3] 숙소 [4] 뒤로가기 ∥");
                                    System.out.println("└=========================================┘");
                                    System.out.println("↓↓↓ 선택 바랍니다. ↓↓↓");
                                    int choice = sc.nextInt();

                                    switch (choice) {
                                        case 1:
                                            landmark.setRegionNum(regionNum);
                                            tripsql.landmarkshow(landmark);
                                            break;
                                        case 2:
                                            restaur.setRegionNum(regionNum);
                                            tripsql.restaurShow(restaur);
                                            break;
                                        case 3:
                                            hotel.setRegionNum(regionNum);
                                            tripsql.hotelShow(hotel);
                                            break;
                                        case 4:
                                            runRandomDetail = false;
                                            break;
                                        default:
                                            System.out.println("잘못 입력하셨습니다");
                                            break;

                                    }
                                }
                                break;

                            case 2:
                                run10 = false;
                                break;

                            default:
                                System.out.println("잘못 입력하셨습니다.");
                                System.out.println("다시 입력바랍니다.");
                                break;

                        }
                    }

                    break;

                case 0:
                    run1 = false;
                    System.out.println("종료합니다.");
                    tripsql.conClose();
                    break;

                default:
                    System.out.println("잘못 입력하셨습니다.");
                    System.out.println("다시 입력바랍니다.");
                    break;
            }
        }
    }
}
