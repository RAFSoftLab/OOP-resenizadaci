package model;

public class Utakmica {

	// Moze da se spoji sa Reprezentacija ali ajde cas ovako
	private String tim1, tim2, grupa;
	private int golovi1, golovi2;
	
	public Utakmica(String tim1, String tim2, String grupa, int golovi1, int golovi2) {
		this.tim1 = tim1;
		this.tim2 = tim2;
		this.grupa = grupa;
		this.golovi1 = golovi1;
		this.golovi2 = golovi2;
	}
	
	public String getTim1() {
		return tim1;
	}
	
	public String getTim2() {
		return tim2;
	}
	
	public String getGrupa() {
		return grupa;
	}
	
	public int getGolovi1() {
		return golovi1;
	}
	
	public int getGolovi2() {
		return golovi2;
	}

	public int getBrojGolova() {
		return golovi1 + golovi2;
	}
	
	@Override
	public String toString() {
		return tim1 + "-" + tim2 + "," + golovi1 + ":" + golovi2;
	}
}