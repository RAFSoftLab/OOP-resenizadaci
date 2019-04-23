package rs.raf.olimpijada;

import java.util.ArrayList;

public class Reprezentacija {
	private String naziv;
	private ArrayList<SportskaEkipa> sportskeEkipe;
	
	public Reprezentacija(String naziv) {
		this.naziv = naziv;
		sportskeEkipe = new ArrayList<>();
	}
	
	public void dodajEkipu(SportskaEkipa ekipa) {
		sportskeEkipe.add(ekipa);
	}
	
	@Override
	public String toString() {
		return naziv;
	}

	public String getNaziv() {
		return naziv;
	}

	public ArrayList<SportskaEkipa> getSportskeEkipe() {
		return sportskeEkipe;
	}
	
}
