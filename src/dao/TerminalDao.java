package dao;

import java.util.ArrayList;
import java.util.HashMap;

import vo.TerminalVO;
import data.Database;

public class TerminalDao {
	
private static TerminalDao instance;
	
	private TerminalDao(){}
	
	public static TerminalDao getInstance(){
		if(instance == null){
			instance = new TerminalDao();
		}
		return instance;
	}
	
	Database database = Database.getInstance();
	
	
	public void insertTerminal(TerminalVO terminal){
		database.tb_terminal.add(terminal);
	}
	
	public TerminalVO selectTerminal(HashMap<String, String> param) {
		TerminalVO rtnTerminal = null;
		for(int i=0; i<database.tb_terminal.size(); i++){
			TerminalVO terminal = database.tb_terminal.get(i);
			boolean flag = true;
			for(String key : param.keySet()){
				String value = param.get(key);
				if(key.equals("terminalID")){
					if(!(terminal.getTerminalID()).equals(value)) flag = false;
				}else if(key.equals("terminalName")){
					if(!terminal.getTerminalName().equals(value)) flag = false;
				}
			}
			if(flag) rtnTerminal = terminal;
		}
		return rtnTerminal;
	}

	public ArrayList<TerminalVO> selectTerminalList(){
		return database.tb_terminal;
	}

}
