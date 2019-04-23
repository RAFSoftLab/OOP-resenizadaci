package rs.raf.olimpijada;

/**
* @author Stefan Zivkovic - szivkovic17@raf.rs
*/

public class Olimpijada {

	public static void main(String[] args) {
		Kosarkas kosarkas1 = new Kosarkas(10, KosarkaskaPozicija.CENTAR);
		Kosarkas kosarkas2 = new Kosarkas(15, KosarkaskaPozicija.PLEJMEJKER);
		Kosarkas kosarkas3 = new Kosarkas(10, KosarkaskaPozicija.CENTAR);
		Kosarkas kosarkas4 = new Kosarkas(8, KosarkaskaPozicija.KRILO);
		Kosarkas kosarkas5 = new Kosarkas(28, KosarkaskaPozicija.BEK);
		
		KosarkaskaEkipa ekipa1 = new KosarkaskaEkipa("IspravnaJaca");
		KosarkaskaEkipa ekipa2 = new KosarkaskaEkipa("IspravnaSlabija");
		KosarkaskaEkipa ekipa3 = new KosarkaskaEkipa("Neispravna");
		
		ekipa1.dodajIgraca(kosarkas5);
		ekipa1.dodajIgraca(kosarkas3);
		ekipa2.dodajIgraca(kosarkas1);
		ekipa2.dodajIgraca(kosarkas2);
		ekipa3.dodajIgraca(kosarkas4);
		
		System.out.println(ekipa1 + "\n" + ekipa2);
		System.out.println("Pobedila je ekipa " + ekipa1.utakmica(ekipa2));
		System.out.println(ekipa1 + "\n" + ekipa3);
		System.out.println("Pobedila je ekipa " + ekipa1.utakmica(ekipa3));
		
		System.out.println("\nTreniranje: ");
		for(int i = 0; i < 25; i++) {
			System.out.println(kosarkas4 + "\n" + kosarkas5 + "\n" + kosarkas2);
			kosarkas4.trenira();
			kosarkas5.trenira();
			kosarkas2.trenira();
			System.out.println("\n");
		}
		
		Vaterpolista sapic = new Vaterpolista(1000, false);
		Vaterpolista pjetlovic = new Vaterpolista(50, true);
		Vaterpolista spanac1 = new Vaterpolista(100, false);
		Vaterpolista spanac2 = new Vaterpolista(150, true);
		
		VaterpoloEkipa srb = new VaterpoloEkipa("Srb");
		VaterpoloEkipa spa = new VaterpoloEkipa("Spa");
		
		srb.dodajIgraca(sapic);
		srb.dodajIgraca(pjetlovic);
		spa.dodajIgraca(spanac1);
		spa.dodajIgraca(spanac2);
		
		System.out.println("Vaterpolo mec: " + srb + " protiv " + spa);
		System.out.println("Pobednik je: " + srb.utakmica(spa));
		
		Reprezentacija srbija = new Reprezentacija("Srbija");
		Reprezentacija spanija = new Reprezentacija("Spanija");
		
		srbija.dodajEkipu(srb);
		srbija.dodajEkipu(ekipa2);
		spanija.dodajEkipu(spa);
		spanija.dodajEkipu(ekipa1);
		
		System.out.println("\n" + srbija + ":");
		for(SportskaEkipa s : srbija.getSportskeEkipe())
			System.out.println(s);
		System.out.println("\n" + spanija + ":" + "\n" + spa + "\n" + ekipa1);
		for(SportskaEkipa s : spanija.getSportskeEkipe())
			System.out.println(s);
	}

}
