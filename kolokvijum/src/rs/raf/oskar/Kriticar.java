package rs.raf.oskar;

import java.util.Random;

public class Kriticar {
	private String ime;
	private String prezime;
	private int reputacija;
	
	public Kriticar(String ime, String prezime, int reputacija) {
		this.ime = ime;
		this.prezime = prezime;
		this.reputacija = Math.min(reputacija, 100);
	}
	
	public void oceniFilm(Film film) {
		Random r = new Random();
		film.dodajKritiku(new Kritika(this, r.nextInt(10) + 1));
	}
	
	public void pohvali(Kriticar drugi) {
		drugi.reputacija += this.reputacija*0.1;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public int getReputacija() {
		return reputacija;
	}

	public void setReputacija(int reputacija) {
		this.reputacija = reputacija;
	}
	
	@Override
	public String toString() {
		return "Kriticar: " + ime + " " + prezime + " reputacija: " + reputacija;
	}
}
