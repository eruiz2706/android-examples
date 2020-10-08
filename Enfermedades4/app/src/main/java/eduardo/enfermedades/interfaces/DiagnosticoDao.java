package eduardo.enfermedades.interfaces;

import java.util.ArrayList;

import eduardo.enfermedades.modelos.Agenda;
import eduardo.enfermedades.modelos.Diagnostico;

/**
 * Created by Eduardo on 21/05/2017.
 */

public interface DiagnosticoDao {
    public boolean save(Diagnostico obj);
    public boolean update(Diagnostico obj);
    public boolean destroy(int id);
    public ArrayList<Diagnostico> findAll();
    public Diagnostico findbyId(int id);

}
