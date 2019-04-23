package rs.raf.oskar;

import java.util.Date;
import java.util.Random;

public class Glumac extends ClanEkipe{
	private boolean lepGlas;

	public Glumac(String ime, String prezime, Date datumRodjenja, Pol pol) {
		super(ime, prezime, datumRodjenja, pol);
		Random r = new Random();
		this.lepGlas = r.nextBoolean();
	}
	
	public void primiOskara() {
		Random r = new Random();
		
		if(r.nextInt(10) < 9)
			odrziGovor();
	}
	
	public void odrziGovor() {
		System.out.println(this + " Odrzavam Govor");
	}

	@Override
	public String toString() {
		return super.toString() + " " + lepGlas;
	}

	public boolean isLepGlas() {
		return lepGlas;
	}

	public void setLepGlas(boolean lepGlas) {
		this.lepGlas = lepGlas;
	}
}
	