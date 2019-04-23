package model;

public class Reprezentacija implements Comparable<Reprezentacija> {

	private String ime, grupa;
	private int pobede, porazi, nereseni, golovi, primljeni;
	
	public Reprezentacija(String ime) {
		this.ime = ime;
		this.grupa = "";
	}

	public String getGrupa() {
		return grupa;
	}
	
	public void setGrupa(String grupa) {
		this.grupa = grupa;
	}
	
	public String getIme() {
		return ime;
	}
	
	public int getPobede() {
		return pobede;
	}
	
	public void dodajPobede(int pobede) {
		this.pobede += pobede;
	}
	
	public int getPorazi() {
		return porazi;
	}
	
	public void dodajPorazi(int porazi) {
		this.porazi += porazi;
	}
	
	public int getNereseni() {
		return nereseni;
	}
	
	public void dodajNereseni(int nereseni) {
		this.nereseni += nereseni;
	}
	
	public int getGolovi() {
		return golovi;
	}
	
	public void dodajGolove(int golovi) {
		this.golovi += golovi;
	}
	
	public int getPrimljeni() {
		return primljeni;
	}
	
	public void dodajPrimljene(int primljeni) {
		this.primljeni += primljeni;
	}
	
	public int getPoeni() {
		return pobede * 3 + nereseni;
	}
	
	public String getSviGolovi() {
		return golovi + ":" + primljeni;
	}
	
	@Override
	public String toString() {
		return ime;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null || !(o instanceof Reprezentacija)) {
			return false;
		}
		
		Reprezentacija r = (Reprezentacija) o;
		
		return getIme().equals(r.getIme()) && getGrupa().equals(r.getGrupa());
	}

	@Override
	public int compareTo(Reprezentacija r) {
		return getGolovi() - r.getGolovi();
	}
}