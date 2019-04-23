package rs.raf.oskar;

import java.util.ArrayList;

public class Film implements Nominovan, Comparable<Film>{
	private String naziv;
	private int godina;
	private int trajanje;
	private double rejting;
	private Zanr zanr;
	private ArrayList<Kritika> kritike;
	private ArrayList<ClanEkipe> clanoviEkipe;
	
	public Film(String naziv, int godina, int trajanje, Reziser reziser, Zanr zanr) {
		this.naziv = naziv;
		this.godina = godina;
		if(trajanje <= 0)
			this.trajanje = 90;
		else
			this.trajanje = trajanje;
		this.zanr = zanr;
		clanoviEkipe = new ArrayList<>();
		clanoviEkipe.add(reziser);
		kritike = new ArrayList<>();
	}
	
	public boolean dodajGlumca(Glumac glumac) {
		if(!clanoviEkipe.contains(glumac) && zanr != Zanr.ANIMIRANI && zanr != Zanr.MJUZIKL)
			return clanoviEkipe.add(glumac);
		if(!clanoviEkipe.contains(glumac) && glumac.isLepGlas())
			return clanoviEkipe.add(glumac);
		return false;
	}
	
	public boolean dodajRezisera(Reziser reziser) {
		if(!clanoviEkipe.contains(reziser)) {
			reziser.setReziranihFilmova(reziser.getReziranihFilmova() + 1);
			return clanoviEkipe.add(reziser);
		}
		return false;
	}
	
	public Reziser getPredstavnik(){
		Reziser maxR = null;
		for(ClanEkipe c : clanoviEkipe) {
			if(c instanceof Reziser) {
				if(maxR == null)
					maxR = (Reziser)c;
				else if(c.getDatumRodjenja().compareTo(maxR.getDatumRodjenja()) > 0)
					maxR = (Reziser)c;
			}
		}
		
		return maxR;
	}
	
	public boolean dodajKritiku(Kritika kritika) {
		for(Kritika k : kritike) {
			if(k.getKriticar().equals(kritika.getKriticar()))
				return false;
		}
		return kritike.add(kritika);
	}
	
	@Override
	public void primiOskara() {
		getPredstavnik().primiOskara();
	}

	private void izracunajRejting() {
		double sum = 0, tezina = 0;
		for(Kritika k : kritike) {
			sum += k.getOcena()*k.getKriticar().getReputacija();
			tezina += k.getKriticar().getReputacija();
		}
		rejting = 1.0*sum/tezina;
	}
	
	@Override
	public int compareTo(Film o) {
		if(this.getRejting() < o.getRejting())
			return -1;
		if(this.getRejting() > o.getRejting())
			return 1;
		if(this.getPredstavnik().getReziranihFilmova() > o.getPredstavnik().getReziranihFilmova())
			return 1;
		if(this.getPredstavnik().getReziranihFilmova() < o.getPredstavnik().getReziranihFilmova())
			return -1;
		return 0;
	}
	

	
	@Override
	public String toString() {
		return "Film: " + naziv + ", godina=" + godina + ", trajanje=" + trajanje + ", rejting=" + rejting
				+ ", zanr=" + zanr;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getGodina() {
		return godina;
	}

	public void setGodina(int godina) {
		this.godina = godina;
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	public double getRejting() {
		izracunajRejting();
		return rejting;
	}

	public void setRejting(double rejting) {
		this.rejting = rejting;
	}

	public Zanr getZanr() {
		return zanr;
	}

	public void setZanr(Zanr zanr) {
		this.zanr = zanr;
	}

	public ArrayList<Kritika> getKritike() {
		return kritike;
	}

	public void setKritike(ArrayList<Kritika> kritike) {
		this.kritike = kritike;
	}

	public ArrayList<ClanEkipe> getClanoviEkipe() {
		return clanoviEkipe;
	}

	public void setClanoviEkipe(ArrayList<ClanEkipe> clanoviEkipe) {
		this.clanoviEkipe = clanoviEkipe;
	}
	
	
}
