package eduardo.enfermedades.interfaces;

import java.util.ArrayList;

import eduardo.enfermedades.modelos.Enfermedad;
import eduardo.enfermedades.modelos.Medico;

/**
 * Created by Eduardo on 21/05/2017.
 */

public interface EnfermedadDao {

    public boolean save(Enfermedad obj);
    public boolean update(Enfermedad obj);
    public boolean destroy(int id);
    public ArrayList<Enfermedad> findAll();
    public Enfermedad findbyId(int id);
}
