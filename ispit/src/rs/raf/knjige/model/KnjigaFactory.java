package rs.raf.knjige.model;

import java.util.Map;

// Ova klasa je final zato sto ne zelimo da
// neko nasledi ovu klasu vam paketa 'model'
// i na taj nacin uspe da instancira klasu 'Knjiga'
// sa ilegalnim poljima
public final class KnjigaFactory {

    public Knjiga getKnjiga(Map<String, String> args) throws NumberFormatException{
        Knjiga knjiga = new Knjiga();
        knjiga.setNaslov(args.get("naslov"));
        knjiga.setAutor(args.get("autor"));
        knjiga.setGodina(Integer.parseInt(args.get("godina")));
        knjiga.setIzdavac(new Izdavac(args.get("izdavac"), -1));
        knjiga.setCena(Float.parseFloat(args.get("cena")));
        knjiga.setStanje(Integer.parseInt(args.get("stanje")));

        return knjiga;
    }

    public void setIzdavac(Knjiga knjiga, Izdavac izdavac) {
        knjiga.setIzdavac(izdavac);
    }
}