package rs.raf.olimpijada;

public class VaterpoloEkipa extends SportskaEkipa {
	public static final int BROJ_VATERPOLISTA_EKIPA = 13;

	public VaterpoloEkipa(String naziv) {
		super(naziv, BROJ_VATERPOLISTA_EKIPA);
	}
	
	@Override
	public void dodajIgraca(Igrac igrac) {
		if(getEkipa().size() < BROJ_VATERPOLISTA_EKIPA && igrac instanceof Vaterpolista) {
			getEkipa().add(igrac);
		}
	}
	
	@Override
	public int utakmica(SportskaEkipa drugaEkipa) {
		if(!(drugaEkipa instanceof VaterpoloEkipa)) return 0;
		boolean golman1 = false;
		boolean golman2 = false;
		
		for(Igrac i : this.getEkipa()) {
			Vaterpolista v = (Vaterpolista) i;
			if(v.isGolman()) {
				golman1 = true;
				break;
			}
		}
		
		for(Igrac i : drugaEkipa.getEkipa()) {
			Vaterpolista v = (Vaterpolista) i;
			if(v.isGolman()) {
				golman2 = true;
				break;
			}
		}
		
		if(golman1 && golman2)
			return super.utakmica(drugaEkipa);
		else return 0;
	}
	
}
