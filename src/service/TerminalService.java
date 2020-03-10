package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import vo.TerminalVO;
import dao.BusDao;
import dao.SeatDao;
import dao.TerminalDao;
import dao.TimeTableDao;

public class TerminalService {
	private static TerminalService instance;

	private TerminalService() {
	}

	public static TerminalService getInstance() {
		if (instance == null) {
			instance = new TerminalService();
		}
		return instance;
	}

	TerminalDao terminalDao = TerminalDao.getInstance();
	TimeTableDao timetableDao = TimeTableDao.getInstance();
	SeatDao seatDao = SeatDao.getInstance();
	BusDao busDao = BusDao.getInstance();

	public void terminalList() { //터미널 리스트보기
		ArrayList<TerminalVO> terminalList = terminalDao.selectTerminalList();

		System.out.println("------------------------------");
		System.out.println("터미널 아이디\t 터미널 이름");
		System.out.println("------------------------------");
		for (int i = terminalList.size() - 1; 0 <= i; i--) {
			TerminalVO terminal = terminalList.get(i);
			System.out.println(terminal.getTerminalID() + "\t"
					+ terminal.getTerminalName());
		}
		System.out.println("-------------------------------");
	}

	public void terminalRegist() { //터미널 등록

		Scanner s = new Scanner(System.in);

		String terminalid = null;
		TerminalVO terminalCheck = null;

		do {
			System.out.println("터미널아이디 : ");
			terminalid = s.nextLine();

			HashMap<String, String> param = new HashMap<>();
			param.put("terminalID", terminalid);
			terminalCheck = terminalDao.selectTerminal(param);
			if (terminalCheck != null) {
				System.out.println("이미 등록된 터미널입니다.");
			}
		} while (terminalCheck != null);

		System.out.println("터미널 이름 : ");
		String terminalName = s.nextLine();

		TerminalVO terminal = new TerminalVO();

		terminal.setTerminalID(terminalid);
		terminal.setTerminalName(terminalName);

		terminalDao.insertTerminal(terminal);
		System.out.println("터미널 등록 완료 했습니다.");

	}

	public void terminaldelete() { //터미널 삭제
		ArrayList<TerminalVO> terminalList = terminalDao.selectTerminalList();

		Scanner s = new Scanner(System.in);
		System.out.println("삭제할 인덱스번호를 입력해주세요.");
		for (int i = 0; i < terminalList.size(); i++) {
			TerminalVO terminal = terminalList.get(i);
			System.out.println(i + "." + terminal.getTerminalID());
		}
		int index = Integer.parseInt(s.nextLine());

		TerminalVO terminal = terminalList.get(index);
		terminalDao.deleteTerminal(terminal);

	}

}