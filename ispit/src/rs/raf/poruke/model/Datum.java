package rs.raf.poruke.model;

import rs.raf.poruke.core.Config;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Datum {

    private Date date;

    public Datum(Date date) {
        this.date = date;
    }

    public Datum(String date) throws ParseException {
        this.date = new SimpleDateFormat(Config.datumFormat).parse(date);
    }

    @Override
    public String toString() {
        return new SimpleDateFormat(Config.datumFormat).format(date);
    }
}