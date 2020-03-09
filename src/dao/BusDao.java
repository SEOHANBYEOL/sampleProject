package dao;

import java.util.ArrayList;
import java.util.HashMap;

import vo.BusVO;
import data.Database;

public class BusDao {

private static BusDao instance;
	
	private BusDao(){}
	
	public static BusDao getInstance(){
		if(instance == null){
			instance = new BusDao();
		}
		return instance;
	}
	
	Database database = Database.getInstance();
	
	
	public void insertBus(BusVO bus){
		database.tb_bus.add(bus);
	}

	
	public BusVO selectBus(HashMap<String, String> param) {
		BusVO rtnBus = null;
		for(int i=0; i<database.tb_bus.size(); i++){
			BusVO bus = database.tb_bus.get(i);
			boolean flag = true;
			for(String key : param.keySet()){
				String value = param.get(key);
				if(key.equals("busId")){
					if(!(bus.getBusId()).equals(value)) flag = false;
				}else if(key.equals("busGrade")){
					if(!bus.getBusGrade().equals(value)) flag = false;
				}
			}
			if(flag) rtnBus = bus;
		}
		return rtnBus;
	}
	
	
	public ArrayList<BusVO> selectBusList(){
		return database.tb_bus;
	}


	
}
