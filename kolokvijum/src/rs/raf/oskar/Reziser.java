package rs.raf.oskar;

import java.util.Date;

public class Reziser extends ClanEkipe implements Comparable<Reziser>{
	private int reziranihFilmova;

	public Reziser(String ime, String prezime, Date datumRodjenja, Pol pol, int reziranihFilmova) {
		super(ime, prezime, datumRodjenja, pol);
		this.reziranihFilmova = reziranihFilmova;
	}
	
	public void odrziGovor() {
		System.out.println("Ja sam reziser " + this + " i dobio sam oskara. Sada drzim govor");
	}
	
	public void primiOskara() {
		odrziGovor();
	}
	
	@Override
	public int compareTo(Reziser o) {
		if(reziranihFilmova == o.reziranihFilmova)
			return 0;
		if(reziranihFilmova < o.reziranihFilmova)
			return -1;
		return 1;
	}
	
	@Override
	public String toString() {
		return "Reziser " + super.toString() + " " + reziranihFilmova;
	}

	public int getReziranihFilmova() {
		return reziranihFilmova;
	}

	public void setReziranihFilmova(int reziranihFilmova) {
		this.reziranihFilmova = reziranihFilmova;
	}
}
