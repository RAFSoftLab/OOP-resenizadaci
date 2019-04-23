package rs.raf.muzika;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// Dogadjaj je uporediv sa drugim dogadjajima:
public abstract class Dogadjaj implements Comparable {

    private String naziv;
    private Date vremePocetka;

    public Dogadjaj(String naziv, Date vremePocetka) {
        this.naziv = naziv;
        this.vremePocetka = vremePocetka;
    }

    public String getNaziv() {
        return naziv;
    }

    public Date getVremePocetka() {
        return vremePocetka;
    }

    @Override
    public int compareTo(Object o) {
        /*
		if (this.getVremePocetka().before(o.getVremePocetka())) {
			return -1;
		} else if (this.getVremePocetka().after(o.getVremePocetka())) {
			return 1;
		} else {
			return 0;
		}
		*/

        try {
            // datumi su medjusobno uporedivi, tako da se moze iskoristiti
            // njihov prirodni redosled
            return getVremePocetka().compareTo(((Dogadjaj) o).getVremePocetka());
        }catch(ClassCastException e) {
            return 0;
        }
    }

    // pozvace se samo ako se radi o anonimnoj klasi
    @Override
    public String toString() {
        // za ispisivanje datuma moze se koristiti SimpleDateFormat
        // u konstruktoru se prosledjuje format datuma
        // oznake:
        // - d -> dan u mesecu (dd - dan sa dve cifre, pocinje nulom ako nema prvu cifru, isto vazi za sve ostalo)
        // - M -> mesec u godini
        // - y -> godina
        // - H -> sati u 24 sata (I -> sati u 12 sati, p -> AM ili PM)
        // - m -> minuti u satu
        // - s -> sekunde u minutu
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss");
        return sdf.format(vremePocetka) + " - " + naziv;
    }
}