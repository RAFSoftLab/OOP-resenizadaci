package rs.raf.olimpijada;

import java.util.ArrayList;
import java.util.Random;

public abstract class SportskaEkipa {
	private String nazivEkipe;
	private int brojIgraca;
	private double kvalitetEkipe;
	private ArrayList<Igrac> ekipa;
	
	public SportskaEkipa(String naziv, int brojIgraca) {
		this.brojIgraca = brojIgraca;
		nazivEkipe = naziv;
		ekipa = new ArrayList<>();
	}
	
	public abstract void dodajIgraca(Igrac igrac);
	
	public void izracunajKvalitetEkipe() {
		int sum = 0;
		for(Igrac i : ekipa) {
			sum += (int) i.getKvalitetIgraca();
		}
		kvalitetEkipe = 1.0*sum/ekipa.size();
	}
	
	public double getKvalitetEkipe() {
		izracunajKvalitetEkipe();
		return this.kvalitetEkipe;
	}
	
	public int utakmica(SportskaEkipa drugaEkipa) {
		double tim1 = getKvalitetEkipe();
		double tim2 = drugaEkipa.getKvalitetEkipe();
		int zbir = (int) (tim1 + tim2);
		Random r = new Random();
		
		int ko = r.nextInt(zbir) + 1;
		if(ko <= tim1) 
			return 1;
		return 2;
	}

	public String getNazivEkipe() {
		return nazivEkipe;
	}

	public int getBrojIgraca() {
		return brojIgraca;
	}

	public ArrayList<Igrac> getEkipa() {
		return ekipa;
	}
	
	@Override
	public String toString() {
		return nazivEkipe + " " + brojIgraca + " " + getKvalitetEkipe();
	}
}
