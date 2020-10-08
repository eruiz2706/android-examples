package eduardo.enfermedades.interfaces;

import java.util.ArrayList;

import eduardo.enfermedades.modelos.Medico;

/**
 * Created by Eduardo on 21/05/2017.
 */

public interface MedicoDao {

    public boolean save(Medico obj);
    public boolean update(Medico obj);
    public boolean destroy(int id);
    public ArrayList<Medico> findAll();
    public Medico findbyId(int id);
}
