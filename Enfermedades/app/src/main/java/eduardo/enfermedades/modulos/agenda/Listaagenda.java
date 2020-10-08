package eduardo.enfermedades.modulos.agenda;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
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
public class Listaagenda extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    View view;

    ListView lista;
    int posit   =-1;
    ArrayList<DatosList> arraydatos;
    AdapterList3 adapter ;
    FloatingActionButton fab_newagenda;

    public Listaagenda() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view    = inflater.inflate(R.layout.fragment_listaagenda, container, false);

        lista              = (ListView)view.findViewById(R.id.listaview_agenda);
        arraydatos= new ArrayList<DatosList>();
        DatosList datos;

        ArrayList<Agenda> items=new AgendaDaoImpl(getActivity().getApplicationContext()).findAll();

        for(int i=0;i<items.size();i++){
            Agenda agenda =items.get(i);
            datos   =new DatosList(getResources().getDrawable(R.drawable.agenda),agenda.getId(),String.valueOf(agenda.getNombre_medico()),agenda.getDescripcion(),agenda.getFecha()+" "+agenda.getHora());
            arraydatos.add(datos);

        }

        adapter =new AdapterList3(getActivity(),arraydatos);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(this);

        fab_newagenda =(FloatingActionButton)view.findViewById(R.id.fab_newagenda);
        fab_newagenda.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id  =v.getId();

        if(id==R.id.fab_newagenda){
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
