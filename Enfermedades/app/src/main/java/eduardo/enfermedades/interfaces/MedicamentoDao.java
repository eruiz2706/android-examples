package eduardo.enfermedades.interfaces;

import java.util.ArrayList;

import eduardo.enfermedades.modelos.Medicamento;

/**
 * Created by Eduardo on 21/05/2017.
 */

public interface MedicamentoDao {

    public boolean save(Medicamento obj);
    public boolean update(Medicamento obj);
    public boolean destroy(int id);
    public ArrayList<Medicamento> findAll();
    public Medicamento findbyId(int id);
}
