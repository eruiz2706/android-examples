package eduardo.enfermedades.modulos.medicos;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import eduardo.enfermedades.dao.MedicoDaoImpl;
import eduardo.enfermedades.db.DbHelper;
import eduardo.enfermedades.helpers.listgeneric.AdapterList;
import eduardo.enfermedades.helpers.listgeneric.DatosList;
import eduardo.enfermedades.modelos.Medico;

/**
 * A simple {@link Fragment} subclass.
 */
public class Listamedicos extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    View view;

    ListView lista;
    int posit   =-1;
    ArrayList<DatosList> arraydatos;
    AdapterList adapter ;
    FloatingActionButton fab_newmedic;

    public Listamedicos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view    = inflater.inflate(R.layout.fragment_listamedicos, container, false);

        FloatingActionButton fab_newmedic = (FloatingActionButton) view.findViewById(R.id.fab_newmedic);
        fab_newmedic.setOnClickListener(this);


        lista              = (ListView)view.findViewById(R.id.listview_medicos);
        arraydatos= new ArrayList<DatosList>();
        DatosList datos;

        ArrayList<Medico> items=new MedicoDaoImpl(getActivity().getApplicationContext()).findAll();

        for(int i=0;i<items.size();i++){
            Medico medico =items.get(i);
            datos   =new DatosList(getResources().getDrawable(R.drawable.doctor),medico.getId(),medico.getNombre(),medico.getEspecialidad());
            arraydatos.add(datos);

        }

        adapter =new AdapterList(getActivity(),arraydatos);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {

        int id  =v.getId();

        if(id==R.id.fab_newmedic){
            Fragment    fragment    =new Crearmedico();
            android.app.FragmentManager fragmentmanager =getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction =fragmentmanager.beginTransaction();
            fragmentTransaction.replace(R.id.container_layout,fragment);
            fragmentTransaction.commit();
        }

    }
}
