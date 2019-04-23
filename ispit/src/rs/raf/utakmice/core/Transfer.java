package core;

import java.util.ArrayList;
import java.util.HashMap;
import model.Utakmica;

public class Transfer {
	
	private static Transfer instance;
	
	public HashMap<String, ArrayList<Utakmica>> utakmice;
	
	private Transfer() {
		utakmice = new HashMap<String, ArrayList<Utakmica>>();
	}
	
	public static Transfer getInstance() {
		if(instance == null) {
			instance = new Transfer();
		}
		
		return instance;
	}
}