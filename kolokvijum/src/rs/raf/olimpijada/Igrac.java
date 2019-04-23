package rs.raf.olimpijada;

import java.util.Random;

public abstract class Igrac implements Trenira {
	private double kvalitetIgraca;
	private Povreda povreda;
	
	public Igrac(double kvalitetIgraca) {
		this.kvalitetIgraca = kvalitetIgraca;
		povreda = Povreda.NEMA;
	}
	
	@Override
	public void trenira() {
		if(povreda == Povreda.NEMA) {
			
			kvalitetIgraca++;
			Random r = new Random();
			int verovatnoca = r.nextInt(10);
			if(verovatnoca < 3) {
				
				verovatnoca = r.nextInt(2);
				if(verovatnoca == 0)
					
					povreda = Povreda.LAKA;
				else povreda = Povreda.TESKA;
			}
		}
	}
	
	@Override
	public String toString() {
		return kvalitetIgraca + " " + povreda;
	}

	public double getKvalitetIgraca() {
		return kvalitetIgraca;
	}

	public Povreda getPovreda() {
		return povreda;
	}
	
	
}
