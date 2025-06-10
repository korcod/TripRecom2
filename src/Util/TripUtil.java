package Util;

import java.util.Scanner;
import TripObject.Reserve;
import TripObject.Restaur;
import TripObject.TripMember;
import TripAccess.TripSQL;

public class TripUtil {

    public static void reservationMenuProcess(
            Scanner sc,
            Reserve reserve,
            Restaur restaur,
            TripMember tripmember,
            TripSQL tripsql,
            String selectedMenu1Name,
            String selectedMenu2Name,
            int totalPrice,
            int numOfTotalPeople
    ) {

        // 예약 번호 설정
        reserve.setReserveNum(tripsql.reserveNumber());
        reserve.setReservePeopleNum(numOfTotalPeople);

        // 선택된 메뉴 이름을 Reserve 객체에 설정
        if (selectedMenu1Name != null && selectedMenu2Name == null) {
            reserve.setReserveMenu1(selectedMenu1Name);
            reserve.setReserveMenu2(null);
        } else if (selectedMenu1Name == null && selectedMenu2Name != null) {
            reserve.setReserveMenu1(null);
            reserve.setReserveMenu2(selectedMenu2Name);
        } else if (selectedMenu1Name != null && selectedMenu2Name != null) {
            reserve.setReserveMenu1(selectedMenu1Name);
            reserve.setReserveMenu2(selectedMenu2Name);
        }

        System.out.println("예약시간은 11시에서 22시까지 가능합니다.");
        System.out.println("몇 시에 예약 하시겠습니까?");
        int reservationHour = sc.nextInt();
        reserve.setReserveTime(reservationHour);

        if (10 < reservationHour && reservationHour < 23) {
            tripsql.restaurCheck(restaur);
            reserve.setMemberNum(tripmember.getMemberNum());
            reserve.setRestaurNum(restaur.getRestaurNum());

            int finalAmount = totalPrice;
            reserve.setReservePrice(finalAmount);

            System.out.println("★★★예약은 당일 예약이므로 예약이 하나만 가능합니다.★★★");
            System.out.println("예약시간은 " + reserve.getReserveTime() + "시 이며, 가격은 " + finalAmount + "원입니다.");
            System.out.println("결제는 포인트로 진행되며, 예약 상황은 회원 정보에서 확인이 가능합니다.");
            System.out.println("결제 하시겠습니까? (y/n)");

            int currentMemberPoint = tripmember.getMemberPoint();
            String payChoice = sc.next();

            switch (payChoice) {
                case "y":
                case "Y":
                    if (currentMemberPoint < finalAmount) {
                        System.out.println("포인트가 부족합니다. 현재 포인트: " + currentMemberPoint + " 결제 금액: " + finalAmount);
                        break;
                    }
                    tripsql.reserveCreate(reserve);
                    tripmember = tripsql.remainAmount(tripmember, finalAmount);
                    System.out.println("결제가 완료되었습니다. 예약이 성공적으로 처리되었습니다!");
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
    }
}

