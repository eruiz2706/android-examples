package eduardo.enfermedades.interfaces;

import java.util.ArrayList;

import eduardo.enfermedades.modelos.Agenda;
import eduardo.enfermedades.modelos.Medico;

/**
 * Created by Eduardo on 21/05/2017.
 */

public interface AgendaDao {
    public boolean save(Agenda obj);
    public boolean update(Agenda obj);
    public boolean destroy(int id);
    public ArrayList<Agenda> findAll();
    public Agenda findbyId(int id);

}
