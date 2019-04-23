package rs.raf.oskar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class FilmskaAkademija {
	public static final double MIN_REJTING = 0;
	private ArrayList<Film> filmNom;
	private ArrayList<Glumac> muskaUlogaNom;
	private ArrayList<Glumac> zenskaUlogaNom;
	private ArrayList<Reziser> reziserNom;
	
	public FilmskaAkademija() {
		this.filmNom = new ArrayList<>();
		this.muskaUlogaNom = new ArrayList<>();
		this.zenskaUlogaNom = new ArrayList<>();
		this.reziserNom = new ArrayList<>();
	}
	
	public boolean nominuj(Nominovan nominovan) {
		if(nominovan instanceof Glumac) {
			if(((Glumac) nominovan).getPol() == Pol.MUSKI && !muskaUlogaNom.contains((Glumac)nominovan))
				return muskaUlogaNom.add((Glumac)nominovan);
			else if(!zenskaUlogaNom.contains((Glumac)nominovan))
				return zenskaUlogaNom.add((Glumac)nominovan);
		}
		else if(nominovan instanceof Reziser && !reziserNom.contains((Reziser)nominovan)) {
			return reziserNom.add((Reziser)nominovan);
		}
		else if(nominovan instanceof Film) {
			if(!filmNom.contains((Film)nominovan) && ((Film) nominovan).getRejting() >= MIN_REJTING && ((Film) nominovan).getTrajanje() > 40){
				boolean imaMuski = false, imaZenski = false;
				for(ClanEkipe clan : ((Film) nominovan).getClanoviEkipe())
					if(clan.getPol() == Pol.MUSKI)
						imaMuski = true;
					else if(clan.getPol() == Pol.ZENSKI)
						imaZenski = true;
				if(imaMuski && imaZenski) {
					if(((Film) nominovan).getZanr() == Zanr.DOKUMENTARNI || ((Film) nominovan).getZanr() == Zanr.BIOGRAFSKI) {
						if(((Film) nominovan).getTrajanje() < 150)
							return filmNom.add((Film)nominovan);
						else return false;
					}
					return filmNom.add((Film)nominovan);
				}
			}
		}
		
		return false;
	}
	
	private String getNominacijeIzvestaj() {
		String s = new String("IZVESTAJ O NOMINACIJAMA\n");
		s = s + "\nNominacije za muske uloge:\n";
		for(Glumac g : muskaUlogaNom)
			s = s + g + "\n";
		s = s + "\nNominacije za zenske uloge:\n";
		for(Glumac g : zenskaUlogaNom)
			s = s + g + "\n";
		
		reziserNom.sort(Collections.reverseOrder());
		filmNom.sort(Collections.reverseOrder());
		
		s = s + "\nNominacije za najboljeg rezisera:\n";
		for(Reziser r : reziserNom)
			s = s + r + "\n";
		s = s + "\nNominacije za najbolji film:\n";
		for(Film f : filmNom)
			s = s + f + "\n";
		return s;
	}
	
	public void objaviNominacije() {
		File f = new File("nominacije.txt");
		try {
			PrintWriter pw = new PrintWriter(f);
			pw.print(getNominacijeIzvestaj());
			pw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ispisiStatistikuFilmova() {
		int niz[] = new int[12];
		for(Film f : filmNom) {
			niz[f.getZanr().ordinal()]++;
		}
		
		for(Zanr z : Zanr.values()) {
			System.out.println(z + " - " + niz[z.ordinal()] + " nominovanih filmova");
		}
	}
	
	public void odrziDodelu() {
		if(!muskaUlogaNom.isEmpty() && !zenskaUlogaNom.isEmpty() && !reziserNom.isEmpty() && !filmNom.isEmpty()) {
			Random r = new Random();
			System.out.println("\n*********************************************");
			System.out.println("\nNajbolja muska uloga:");
			muskaUlogaNom.get(r.nextInt(muskaUlogaNom.size())).primiOskara();
			System.out.println("\nNajbolja zenska uloga:");
			zenskaUlogaNom.get(r.nextInt(zenskaUlogaNom.size())).primiOskara();
			System.out.println("\nNajbolji reziser:");
			reziserNom.get(r.nextInt(reziserNom.size())).primiOskara();
			System.out.println("\nNajbolji film:");
			filmNom.get(r.nextInt(filmNom.size())).primiOskara();
		}
	}
}
