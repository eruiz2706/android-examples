package eduardo.enfermedades.modulos.agenda;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import eduardo.enfermedades.R;
import eduardo.enfermedades.dao.AgendaDaoImpl;
import eduardo.enfermedades.dao.EnfermedadDaoImpl;
import eduardo.enfermedades.helpers.listgeneric.AdapterList2;
import eduardo.enfermedades.helpers.listgeneric.AdapterList3;
import eduardo.enfermedades.helpers.listgeneric.DatosList;
import eduardo.enfermedades.modelos.Agenda;
import eduardo.enfermedades.modelos.Enfermedad;
import eduardo.enfermedades.modulos.medicos.Crearmedico;

/**
 * A simple {@link Fragment} subclass.
 */
public class Listatratamientos extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    View view;

    ListView lista;
    int posit   =-1;
    ArrayList<DatosList> arraydatos;
    AdapterList3 adapter ;
    Button btn_new;





    @Override
    public void onClick(View v) {
        int id  =v.getId();

        if(id==R.id.b_newagenda){
            Fragment    fragment    =new Crearagenda();
            android.app.FragmentManager fragmentmanager =getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction =fragmentmanager.beginTransaction();
            fragmentTransaction.replace(R.id.container_layout,fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
