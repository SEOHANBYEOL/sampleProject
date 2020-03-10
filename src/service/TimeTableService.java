package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import vo.BusVO;
import vo.ReservationVO;
import vo.TimeTableVO;
import vo.UserVO;
import dao.ReservationDao;
import dao.TimeTableDao;
import data.Session;

public class TimeTableService {
	private static TimeTableService instance;

	private TimeTableService() {
	}

	public static TimeTableService getInstance() {
		if (instance == null) {
			instance = new TimeTableService();
		}
		return instance;
	}

	TimeTableDao timetabledao = TimeTableDao.getInstance();

	public void timetableList() { //타임테이블 조회
		ArrayList<TimeTableVO> timetableList = timetabledao
				.selectTimeTableList();

		System.out.println("------------------------------");
		System.out.println("index\t출발점\t도착지\t시간\t버스아이디");
		System.out.println("------------------------------");
		for (int i = timetableList.size() - 1; 0 <= i; i--) {
			TimeTableVO timetable = timetableList.get(i);
			System.out.println(timetable.getTimeTableId() + "\t"
					+ timetable.getStartPoint() + "\t"
					+ timetable.getEndPoint() + "\t" + timetable.getTime()
					+ "\t" + timetable.getBusId());
		}
		System.out.println("-------------------------------");
	}

	public void timetabledelete() { //타임테이블 삭제
		ArrayList<TimeTableVO> timetablelist = timetabledao
				.selectTimeTableList();

		Scanner s = new Scanner(System.in);
		System.out.println("삭제할 인덱스번호를 입력해주세요.");
		for (int i = 0; i < timetablelist.size(); i++) {
			TimeTableVO timetable = timetablelist.get(i);
			System.out.println(i + "." + timetable.getTimeTableId());
		}
		int index = Integer.parseInt(s.nextLine());

		TimeTableVO timetable = timetablelist.get(index);
		timetabledao.deleteTimetable(timetable);
 
	}

	public void timetableRegist() { //타임테이블 등록
		Scanner s = new Scanner(System.in);
		String timetableId = null;
		TimeTableVO timetableidCheck = null;
		do {
			System.out.println("시간표 index : ");
			timetableId = s.nextLine();

			HashMap<String, String> param = new HashMap<>();
			param.put("timeTableId", timetableId);
			timetableidCheck = timetabledao.selectTimeTable(param);
			if (timetableidCheck != null) {
				System.out.println("사용할 수 없는 타임테이블 아이디 입니다.");
			}
		} while (timetableidCheck != null);

		System.out.println("출발지 : ");
		String startPoint = s.nextLine();

		System.out.println("도착지: ");
		String endPoint = s.nextLine();

		System.out.println("버스아이디: ");
		String busId = s.nextLine();

		System.out.println("시간: ");
		String time = s.nextLine();

		TimeTableVO timetable = new TimeTableVO();

		timetable.setTimeTableId(timetableId);
		timetable.setStartPoint(startPoint);
		timetable.setEndPoint(endPoint);
		timetable.setTime(time);
		timetable.setBusId(busId);

		timetabledao.insertTimetable(timetable);

		System.out.println("시간표 등록 완료 되었습니다.");

	}

	

}
