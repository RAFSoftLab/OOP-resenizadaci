package rs.raf.olimpijada;

public class Vaterpolista extends Igrac {
	private boolean golman;

	public Vaterpolista(double kvalitetIgraca, boolean golman) {
		super(kvalitetIgraca);
		this.golman = golman;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + golman;
	}

	public boolean isGolman() {
		return golman;
	}

	public void setGolman(boolean golman) {
		this.golman = golman;
	}
}
