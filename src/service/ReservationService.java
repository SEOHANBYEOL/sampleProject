package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

import dao.BusDao;
import dao.ReservationDao;
import dao.SeatDao;
import dao.TerminalDao;
import dao.TimeTableDao;
import data.Session;
import vo.BusVO;
import vo.ReservationVO;
import vo.SeatVO;
import vo.TerminalVO;
import vo.TimeTableVO;
import vo.UserVO;




public class ReservationService {

   
private static ReservationService  instance;
   
   private ReservationService (){}
   
   public static ReservationService getInstance(){
      if(instance == null){
         instance = new ReservationService();
      }
      return instance;
   }

   TerminalDao terminalDao = TerminalDao.getInstance();
   TimeTableDao timetableDao = TimeTableDao.getInstance();
   SeatDao seatDao = SeatDao.getInstance();
   BusDao busDao = BusDao.getInstance();
   ReservationDao reservationDao = ReservationDao.getInstance();
   
   

   
   
   public void terminalSelect() { //예매하기
      
      //Date type을  String으로 형변환
      DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date nowDate = new Date();
      String today = sdFormat.format(nowDate);


      String busGrade = "";
      Scanner s = new Scanner(System.in);
      List<TerminalVO> terminallist = terminalDao.selectTerminalList();
      List<BusVO> buslist = busDao.selectBusList();

      System.out.println("-------출발터미널 선택-------");
      for (int i = 0; i < terminallist.size(); i++) {
         System.out.println(i + terminallist.get(i).getTerminalName());
      }//출발터미널 목록보이기
      int startterminal = Integer.parseInt(s.nextLine());
      String startpoint = terminallist.get(startterminal).getTerminalName();

      System.out.println("-------도착터미널 선택-------");
      for (int i = 0; i < terminallist.size(); i++) {
         if (!startpoint.equals(terminallist.get(i).getTerminalName())) {
            System.out.println(i + terminallist.get(i).getTerminalName());
         }
      }//도착터미널 목록보이기
      int endterminal = Integer.parseInt(s.nextLine());
      String endpoint = terminallist.get(endterminal).getTerminalName();

      List<TimeTableVO> timetablelist = timetableDao.selectTimeTableList();
      System.out.println("번호\t출발지\t도착지\t시간\t등급\t남은좌석수");
      
      for (int i = 0; i < timetablelist.size(); i++) {
         if (timetablelist.get(i).getStartPoint().equals(startpoint)
               && timetablelist.get(i).getEndPoint().equals(endpoint)) {
            TimeTableVO timetable = timetablelist.get(i);
            for (int j = 0; j < buslist.size(); j++) {
               if (timetable.getBusId().equals(buslist.get(j).getBusId())) {
                  busGrade = buslist.get(j).getBusGrade();
               }
            }
            
            //남은 좌석수 출력
            int seats = seatNumbers(timetablelist.get(i).getTimeTableId());
            
            System.out.println(i + "\t" + timetable.getStartPoint() + "\t"
                  + timetable.getEndPoint() + "\t" + timetable.getTime()
                  + "\t" + busGrade +"\t" + seats);
         }
      }//시작터미널 과 도착터미널의 값이 일치하는 timetablelist 출력
      
      System.out.print("예매시간을 선택해주세요>");
      String reservationNum = s.nextLine();
      for (int i = 0; i < timetablelist.size(); i++) {
         TimeTableVO timeTable = timetablelist.get(i);
         if (reservationNum.equals(timeTable.getTimeTableId())) {//timetablelist중 선택한 값이 같은 행을 출력
            System.out.println();
            System.out.println("번호\t출발지\t도착지\t시간\t등급");
            for (int j = 0; j < buslist.size(); j++) { //busGrade 받기 (timetablelist의BusID==buslist의 BusID)
               if (timeTable.getBusId().equals(buslist.get(j).getBusId())) {
                  busGrade = buslist.get(j).getBusGrade();
               }
            }
            
            
            System.out.println(timeTable.getTimeTableId() + "\t"
                  + timeTable.getStartPoint() + "\t"
                  + timeTable.getEndPoint() + "\t" + timeTable.getTime()
                  + "\t" + busGrade +"\t" );//예약확인을 위한 출력
            
            System.out.print("예약하시겠습니까?(yes:y  no:n)"); 
            String result = s.nextLine();
            int reservationId = 0;
            if (result.equals("y")) { //y일때
               selectSeat(timeTable.getBusId());//seat예약 화면 출력
               String seatNumber = s.nextLine();
               boolean exist = false; //중복된 좌석 유무
               
               //중복된 좌석 유무 체크를 위한 코드
               ArrayList<ReservationVO> reservationList = reservationDao.selectReservationList();//2020_03_09수정 byHB.S (selectReservationList메소드명변경 오타라서..)
               for(int a=0; a<reservationList.size(); a++){
                  //2020_03_09수정 byHB.S (중복된값을 제대로 거르지못해서 수정함)
                  if(reservationList.get(a).getSeatNumber().equals(seatNumber) && reservationList.get(a).getTimeTableId().equals(reservationNum)){
                     exist = true;
                     System.out.println("이미 예매된 좌석입니다.");
                     break;
                  }
               }
               
               if(exist==false){//중복되지 않았을때 reservationDao와 seatDao에 저장
                  ReservationVO reservation = new ReservationVO();
                  reservation.setReservationId(Integer.toString(reservationId++));
                  reservation.setTimeTableId(timeTable.getTimeTableId());
                  reservation.setUserId(Session.loginUser.getId());
                  reservation.setSeatNumber(seatNumber);
                  reservation.setDate(today);
                  reservationDao.insertReservation(reservation);
                  
                  String seatIndex = timeTable.getBusId()+"_"+seatNumber;
                  
                  SeatVO seat = new SeatVO();
                  seat.setReservationId(reservation.getReservationId());
                  seat.setSeatNumber(seatNumber);
                  seat.setSeatIndex(seatIndex);
                  seatDao.insertSeat(seat);
               }
               

            }else{//n일때
               break;
            }
                  
         }
      }
   }
   
   
   
   public void ReservationListByUser() { //user별 예약 목록 보기 (user용)
      ArrayList<ReservationVO> reservationList = reservationDao.selectReservationList();
      ArrayList<TimeTableVO> timetableList = timetableDao.selectTimeTableList();
      ArrayList<BusVO> busList = busDao.selectBusList();
      
      System.out.println("-----------------------------------------------------------------------");
      System.out.println("예약번호\t좌석번호\t유저아이디\t날짜\t\t출발지\t도착지\t시간\t버스등급");
      
      for(int i= reservationList.size() -1; 0<= i; i--){
         ReservationVO reservation = reservationList.get(i);
         if(Session.loginUser.getId().equals(reservation.getUserId())){
            System.out.print(i+1+"\t"+reservation.getSeatNumber()+"\t"+reservation.getUserId()+"\t"+reservation.getDate());
            for(int j=0; j<timetableList.size(); j++){
               if(reservation.getTimeTableId().equals(timetableList.get(j).getTimeTableId())){
                  for(int k=0; k<busList.size(); k++){
                     if(timetableList.get(j).getBusId().equals(busList.get(k).getBusId()))
                        System.out.println("\t"+timetableList.get(j).getStartPoint()+"\t"+timetableList.get(j).getEndPoint()+"\t"+timetableList.get(j).getTime()+"\t"+busList.get(k).getBusGrade());
                  }
                  
               }
               
            }
         }
         
      }
      System.out.println("-----------------------------------------------------------------------");
   }
   
   
   public void ReservationList() { //전체 예약 목록 보기 (관리자용)
      ArrayList<ReservationVO> reservationList = reservationDao.selectReservationList();
      ArrayList<TimeTableVO> timetableList = timetableDao.selectTimeTableList();
      ArrayList<BusVO> busList = busDao.selectBusList();
      System.out.println("-----------------------------------------------------------------------");
      System.out.println("번호\t좌석번호\t유저아이디\t날짜\t\t출발지\t도착지\t시간\t버스등급");
      for(int i= reservationList.size() -1; 0<= i; i--){
         ReservationVO reservation = reservationList.get(i);
         System.out.print(i+1+"\t"+reservation.getSeatNumber()+"\t"+reservation.getUserId()+"\t"+reservation.getDate());
         for(int j=0; j<timetableList.size(); j++){
            if(reservation.getTimeTableId().equals(timetableList.get(j).getTimeTableId())){
               for(int k=0; k<busList.size(); k++){
                  if(timetableList.get(j).getBusId().equals(busList.get(k).getBusId()))
                     System.out.println("\t"+timetableList.get(j).getStartPoint()+"\t"+timetableList.get(j).getEndPoint()+"\t"+timetableList.get(j).getTime()+"\t"+busList.get(k).getBusGrade());
               }
               
            }
            
         }
      }
      System.out.println("-----------------------------------------------------------------------");
   }
   
   
   
   public void selectSeat(String busId){ //버스 좌석 출력
      
      ArrayList<SeatVO> seatList = seatDao.selectSeatTableList();
      ArrayList<BusVO> busList = busDao.selectBusList();
      
      String busGrade = null;
      String[] reservatedSeatNumber = new String[seatList.size()]; //예약된 좌석넘버
      String[] reservatedBusId = new String[seatList.size()]; //예약된 버스아이디
      
      
      for(int i=0; i<busList.size(); i++){ //버스아이디를 통해 버스등급 받아오기
         if(busList.get(i).getBusId().equals(busId)){
            busGrade = busList.get(i).getBusGrade();
         }
      }
      
      
      for(int i=0; i<seatList.size(); i++){ //예약된 좌석넘버와 버스아이디 배열에 각각저장
         seatList.get(i).getSeatIndex();
         int idx = seatList.get(i).getSeatIndex().indexOf("_");
         
         reservatedSeatNumber[i] = seatList.get(i).getSeatIndex().substring(idx+1);
         reservatedBusId[i] = seatList.get(i).getSeatIndex().substring(0,idx);
      }
      
      boolean check = false;
      if(reservatedBusId.length==0){ //사용자가 처음 예약할때
         seatView(busGrade);
         check = true;
      }
      
      if(reservatedBusId.length!=0){ //사용자가 처음 예약하는게아니고, 해당버스아이디가 중복되지않을경우
         int count = 0;
         for(int i=0; i<reservatedBusId.length; i++){
            if(!reservatedBusId[i].equals(busId)){
               count++;
            }
         }
         if(count == reservatedBusId.length){
            seatView(busGrade);
            check = true;
         }
      }
      
      
      
         //(예약된 버스아이디가 있는 경우) 
            if(!check){ //처음예약하는게 아니라면
               int countBySeatNumber = 0;
               
               for(int i=0; i<reservatedBusId.length; i++){ //입력받은 해당 버스 아이디의 갯수(해당 버스의 총예약된 좌석갯수)를 countBySeatNumber변수에 담는다.
                  if(reservatedBusId[i].equals(busId)){
                     countBySeatNumber++;
                  }
               } 
               
               String [] reservatedSeatNumberByBusId = new String[countBySeatNumber]; //좌석갯수만큼 reservatedSeatNumberByBusId라는 배열을 만들어준다.
               
               int j = 0;
               for(int i=0; i<reservatedBusId.length; i++){ 
                  if(reservatedBusId[i].equals(busId)){
                     reservatedSeatNumberByBusId[j] = reservatedSeatNumber[i]; //예약된 좌석넘버들을 reseratedSeatNumberByBusId배열에 넣어준다.
                     j++;            
                  }
               }
               
                Arrays.sort(reservatedSeatNumberByBusId); // 해당 버스의 예약된 좌석넘버를 담은 배열을 정렬해준다.(오름차순)안써도될거같다..
             
               
               
               if (busGrade.equals("일반")) {   
                  for (int i = 1; i <= 45; i++) {
                     if(Arrays.asList(reservatedSeatNumberByBusId).contains(Integer.toString(i))){ //i의 값이 reservatedSeatNumberByBusId배열에 존재한다면
                        if (i >= 41) {
                           System.out.print(i + " ■" + "\t");
                           
                        } else if (i % 2 == 0 && i % 4 != 0) {
                           System.out.print(i + " ■" + "\t\t");
                           
                        } else if (i % 4 == 0) {
                           System.out.println(i + " ■");
                           
                        } else {
                           System.out.print(i + " ■" + "\t");
                           
                        }
                     }else if (i >= 41) {
                        System.out.print(i + " □" + "\t");
                     } else if (i % 2 == 0 && i % 4 != 0) {
                        System.out.print(i + " □" + "\t\t");
                     } else if (i % 4 == 0) {
                        System.out.println(i + " □");
                     } else {
                        System.out.print(i + " □" + "\t");
                     }

                  }
                  System.out.println();
               }else if(busGrade.equals("우등")){
                  for (int i = 1; i <= 28; i++) {
                     if(Arrays.asList(reservatedSeatNumberByBusId).contains(Integer.toString(i))){
                        if (i % 3 != 0 || i>=27) {
                           System.out.print(i+" ■" + "\t");
                        }else{
                           System.out.print("\t" + i + " ■");
                           System.out.println();
                        }
                     }
                     else if (i % 3 != 0 || i>=27) {
                        System.out.print(i+" □" + "\t");
                     }else{
                        System.out.print("\t" + i + " □");
                        System.out.println();
                     }
                  }
                  System.out.println();
                  
               }else if(busGrade.equals("프리미엄")){
                  for (int i = 1; i <= 21; i++) {
                     if (Arrays.asList(reservatedSeatNumberByBusId).contains(Integer.toString(i))){
                        if (i % 3 != 0) {
                           System.out.print(i+" ■" + "\t");
                        }else{
                           System.out.print("\t" + i + " ■");
                           System.out.println();
                        }
                     }
                     else if(i % 3 != 0) {
                        System.out.print(i+" □" + "\t");
                     }else{
                        System.out.print("\t" + i + " □");
                        System.out.println();
                     }
                  }
               }
               
               System.out.println("좌석을 선택해주세요?~");
            }

      
      
   }
   
   
   
   
   public void seatView(String busGrade){ //빈좌석 화면 출력 함수
      if (busGrade.equals("일반")) {
         for (int i = 1; i <= 45; i++) {
            if (i >= 41) {
               System.out.print(i + " □" + "\t");
            } else if (i % 2 == 0 && i % 4 != 0) {
               System.out.print(i + " □" + "\t\t");
            } else if (i % 4 == 0) {
               System.out.println(i + " □");
            } else {
               System.out.print(i + " □" + "\t");
            }

         }
         System.out.println();
      }else if(busGrade.equals("우등")){
         for (int i = 1; i <= 28; i++) {
            if (i % 3 != 0 || i>=27) {
               System.out.print(i+" □" + "\t");
            }else{
               System.out.print("\t" + i + " □");
               System.out.println();
            }
         }
         System.out.println();
      }else if(busGrade.equals("프리미엄")){
         for (int i = 1; i <= 21; i++) {
            if (i % 3 != 0) {
               System.out.print(i+" □" + "\t");
            }else{
               System.out.print("\t" + i + " □");
               System.out.println();
            }
         }
      }
      
      System.out.println("좌석을 선택해주세요?~");
   }
   
   
   public int seatNumbers(String timetableId){ //남은좌석수 구하는 함수
      int availableSeatCount = 0;
      ArrayList<ReservationVO> reservationList = reservationDao.selectReservationList();
      ArrayList<TimeTableVO> timetableList = timetableDao.selectTimeTableList();
      ArrayList<BusVO> busList = busDao.selectBusList();
      
      for(int i=0; i<reservationList.size(); i++){
         if(reservationList.get(i).getTimeTableId().equals(timetableId)){
            availableSeatCount++; // 예약된 좌석수
         }
      }
      
      String busGrade = null;
      
            for(int j=0; j<timetableList.size(); j++){
                  for(int k=0; k<busList.size(); k++){
                     if(timetableList.get(j).getTimeTableId().equals(timetableId)){
                        if(timetableList.get(j).getBusId().equals(busList.get(k).getBusId())){
                           busGrade = busList.get(k).getBusGrade();
                        }   
                     }
               }
            }
         
      
      if(busGrade.equals("일반")){
         availableSeatCount=45-availableSeatCount;
      }else if(busGrade.equals("우등")){
         availableSeatCount=28-availableSeatCount;
      }else{
         availableSeatCount=21-availableSeatCount;
      }
      return availableSeatCount;
   }
   
   
   
   public void Reservationdelete(){
		ArrayList<ReservationVO> reservationList = reservationDao.selectReservationList();
		ArrayList<SeatVO> seatList = seatDao.selectSeatTableList();
		ArrayList<TimeTableVO> timetableList = timetableDao.selectTimeTableList();
		
			System.out.println("인덱스 \t 예약날짜 \t\t 좌석번호  \t버스아이디 \t출발지 \t도착지 \t시간");
			Scanner s = new Scanner(System.in);
			
			
			for(int i=0; i<reservationList.size(); i++){
				ReservationVO reservation = reservationList.get(i);
				System.out.print(i+"\t"+reservation.getDate()+"\t"+reservation.getSeatNumber()+"\t");
				for(int j=0; j<timetableList.size(); j++){
					if(reservationList.get(i).getTimeTableId().equals(timetableList.get(j).getTimeTableId())){
						System.out.println(timetableList.get(j).getBusId()+"\t"+timetableList.get(j).getStartPoint()+"\t"+timetableList.get(j).getEndPoint()+"\t"+timetableList.get(j).getTime());
					}
				}
			}
			System.out.println("삭제할 인덱스번호를 입력해주세요.");

		
		int index = Integer.parseInt(s.nextLine());
		
		ReservationVO reservation = reservationList.get(index);
		reservationDao.deleteReservation(reservation);
		
		SeatVO seat = seatList.get(index);
		seatDao.deleteSeat(seat);
		
		
	}
   
   
   
   
}
      
      
         
      
      
      
   
