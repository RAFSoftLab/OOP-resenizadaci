package rs.raf.oskar;

public class Kritika {
	private double ocena;
	private Kriticar kriticar;
	
	public Kritika(Kriticar kriticar, double ocena) {
		this.ocena = ocena;
		this.kriticar = kriticar;
	}
	
	@Override
	public String toString() {
		return "Kritika: " + kriticar + " " + ocena;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

	public Kriticar getKriticar() {
		return kriticar;
	}

	public void setKriticar(Kriticar kriticar) {
		this.kriticar = kriticar;
	}
}
