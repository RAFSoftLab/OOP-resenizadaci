package rs.raf.oskar;

import java.util.Date;

public abstract class ClanEkipe implements Nominovan{
	private String ime;
	private String prezime;
	private Date datumRodjenja;
	private Pol pol;
	
	public ClanEkipe(String ime, String prezime, Date datumRodjenja, Pol pol) {
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
		this.pol = pol;
	}
	
	public abstract void odrziGovor();
	
	@Override
	public String toString() {
		return ime + " " + prezime + " " + datumRodjenja;
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

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public Pol getPol() {
		return pol;
	}

	public void setPol(Pol pol) {
		this.pol = pol;
	}
}
