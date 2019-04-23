package rs.raf.olimpijada;

public class Kosarkas extends Igrac{
	private KosarkaskaPozicija pozicija;

	public Kosarkas(double kvalitetIgraca, KosarkaskaPozicija pozicija) {
		super(kvalitetIgraca);
		this.pozicija = pozicija;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + pozicija;
	}

	public KosarkaskaPozicija getPozicija() {
		return pozicija;
	}

	public void setPozicija(KosarkaskaPozicija pozicija) {
		this.pozicija = pozicija;
	}
}
