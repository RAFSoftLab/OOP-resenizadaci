package rs.raf.biblioteka.model;

import rs.raf.biblioteka.core.Config;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Datum {

    private Date date;

    public Datum(Date date) {
        this.date = date;
    }

    public void dodajGodine(int delta) throws Exception {
        int godina = Integer.parseInt(new SimpleDateFormat("yyyy").format(date)) + delta;
        int mesec = Integer.parseInt(new SimpleDateFormat("MM").format(date));
        int dan = Integer.parseInt(new SimpleDateFormat("dd").format(date));

        date = new SimpleDateFormat("dd-MM-yyyy").parse(dan + "-" + mesec + "-" + godina);
    }

    @Override
    public String toString() {
        return new SimpleDateFormat(Config.datumFormat).format(date);
    }
}