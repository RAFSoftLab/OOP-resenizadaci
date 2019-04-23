package rs.raf.olimpijada;

public class KosarkaskaEkipa extends SportskaEkipa {
	public static final int BROJ_KOSARKASA_EKIPA = 12;

	public KosarkaskaEkipa(String naziv) {
		super(naziv, BROJ_KOSARKASA_EKIPA);
	}
	
	@Override
	public void dodajIgraca(Igrac igrac) {
		if(getEkipa().size() < 12 && igrac instanceof Kosarkas) {
			getEkipa().add(igrac);
		}
	}
	
	@Override
	public int utakmica(SportskaEkipa drugaEkipa) {
		if(!(drugaEkipa instanceof KosarkaskaEkipa)) return 0;
		boolean centar1 = false;
		boolean centar2 = false;
		
		for(Igrac i : this.getEkipa()) {
			Kosarkas k = (Kosarkas) i;
			if(k.getPozicija() == KosarkaskaPozicija.CENTAR) {
				centar1 = true;
				break;
			}
		}
		
		for(Igrac i : drugaEkipa.getEkipa()) {
			Kosarkas k = (Kosarkas) i;
			if(k.getPozicija() == KosarkaskaPozicija.CENTAR) {
				centar2 = true;
				break;
			}
		}
		
		if(centar1 && centar2)
			return super.utakmica(drugaEkipa);
		else return 0;
	}
}
