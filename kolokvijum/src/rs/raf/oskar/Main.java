package rs.raf.oskar;

import java.util.Date;

/**
* @author Stefan Zivkovic - szivkovic17@raf.rs
*/

public class Main {

	public static void main(String[] args) {
		Glumac bred = new Glumac("Bred", "Pitt", new Date(65, 4, 25), Pol.MUSKI);
		Glumac leo = new Glumac("Leonardo", "DiKaprio", new Date(65, 2, 25), Pol.MUSKI);
		Glumac morgan = new Glumac("Morgan", "Freeman", new Date(51, 7, 11), Pol.MUSKI);
		Glumac jenifer = new Glumac("Jennifer", "Lawrence", new Date(88, 4, 25), Pol.ZENSKI);
		Glumac merilin = new Glumac("Marilyn", "Monroe", new Date(30, 11, 5), Pol.ZENSKI);
		Glumac marlon = new Glumac("Marlon", "Brando", new Date(36, 5, 31), Pol.MUSKI);
		Glumac selma = new Glumac("Salma", "Hayek", new Date(68, 5, 31), Pol.ZENSKI);
		
		Reziser kopola = new Reziser("Frencis", "Kopola", new Date(42, 9, 1), Pol.MUSKI, 5);
		Reziser tarantino = new Reziser("Quentin", "Tarantino", new Date(65, 9, 9), Pol.MUSKI, 8);
		Reziser leone = new Reziser("Sergio", "Leone", new Date(38, 7, 14), Pol.MUSKI, 15);
		
		Film hollywood = new Film("Once upon a time in Hollywood", 2020, 180, tarantino, Zanr.AKCIJA);
		Film old = new Film("Film klasa", 1965, 120, leone, Zanr.DRAMA);
		Film nov = new Film("UH!", 2018, 150, kopola, Zanr.TRILER);
		
		Kriticar drkadzija = new Kriticar("Jordan", "Avakumovic", 400);
		Kriticar fini = new Kriticar("Milan", "Guzovrt", 14);
		
		hollywood.dodajGlumca(bred);
		hollywood.dodajGlumca(leo);
		hollywood.dodajGlumca(selma);
		old.dodajGlumca(marlon);
		old.dodajGlumca(merilin);
		nov.dodajGlumca(jenifer);
		nov.dodajGlumca(morgan);
		
		drkadzija.oceniFilm(hollywood);
		drkadzija.oceniFilm(old);
		drkadzija.oceniFilm(nov);
		fini.oceniFilm(nov);
		fini.oceniFilm(old);
		fini.oceniFilm(hollywood);
		
		FilmskaAkademija oscar = new FilmskaAkademija();
		oscar.nominuj(nov);
		oscar.nominuj(old);
		oscar.nominuj(hollywood);
		oscar.nominuj(leone);
		oscar.nominuj(tarantino);
		oscar.nominuj(kopola);
		oscar.nominuj(marlon);
		oscar.nominuj(merilin);
		oscar.nominuj(jenifer);
		oscar.nominuj(morgan);
		oscar.nominuj(leo);
		oscar.nominuj(selma);
		oscar.nominuj(bred);
		
		oscar.ispisiStatistikuFilmova();
		oscar.objaviNominacije();
		oscar.odrziDodelu();
		
		
	}

}
