package rs.raf.predmeti;

import rs.raf.predmeti.model.Predmet;
import rs.raf.predmeti.model.Student;

import java.util.ArrayList;
import java.util.List;

public class Bundle {

    private static Bundle instance;

    public Student student;
    public List<Predmet> predmeti;

    private Bundle() {
        student = new Student();
        predmeti = new ArrayList<Predmet>();
    }

    public static Bundle getInstance() {
        if(instance == null) {
            instance = new Bundle();
        }

        return instance;
    }
}