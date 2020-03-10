package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import vo.BusVO;
import vo.TimeTableVO;
import vo.UserVO;
import dao.BusDao;

public class BusService {
private static BusService instance;
	
	private BusService(){}
	
	public static BusService getInstance(){
		if(instance == null){
			instance = new BusService();
		}
		return instance;
	}
	
	BusDao busDao = BusDao.getInstance();

	public void BusRegist(){ //버스 등록
		Scanner s = new Scanner(System.in);
		String busid = null;
		BusVO busidCheck = null;
		do{			
			System.out.println("버스아이디 : ");
			busid = s.nextLine();
			
			HashMap<String, String> param = new HashMap<>();
			param.put("busId", busid);
			busidCheck = busDao.selectBus(param);
			if(busidCheck != null){
				System.out.println("사용할 수 없는 버스 아이디 입니다.");
			}
		}while(busidCheck != null);
	
		System.out.println("버스 등급 : ");
		String busgrade = s.nextLine();

		BusVO bus = new BusVO();
		
		bus.setBusId(busid);
		bus.setBusGrade(busgrade);
		busDao.insertBus(bus);
		System.out.println("버스 등록완료 되었습니다.");	
	}

	
	
	public void Busdelete(){ // 버스삭제
		ArrayList<BusVO> busList = busDao.selectBusList();
		
		Scanner s = new Scanner(System.in);
		System.out.println("삭제할 인덱스번호를 입력해주세요.");
		for(int i=0; i<busList.size(); i++){
			BusVO bus = busList.get(i);
			System.out.println(i + "."+bus.getBusId());
		}
		int index = Integer.parseInt(s.nextLine());
		
		BusVO bus = busList.get(index);
		busDao.deleteBus(bus);
		
		
	}
	
	
	
	public void Buslist() { //버스 목록 보기
			ArrayList<BusVO> busList = busDao.selectBusList();
			
			System.out.println("------------------------------");
			System.out.println("버스아이디\t버스등급");
			System.out.println("------------------------------");
			for(int i= busList.size() -1; 0<= i; i--){
				BusVO bus = busList.get(i);
				System.out.println(i+1+"\t"+bus.getBusId()+"\t"+bus.getBusGrade());
			}
			System.out.println("-------------------------------");
		}
	
	}
