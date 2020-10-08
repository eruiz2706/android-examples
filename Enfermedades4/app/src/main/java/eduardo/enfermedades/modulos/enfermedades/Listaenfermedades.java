package eduardo.enfermedades.modulos.enfermedades;


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
import eduardo.enfermedades.dao.EnfermedadDaoImpl;
import eduardo.enfermedades.dao.MedicoDaoImpl;
import eduardo.enfermedades.helpers.listgeneric.AdapterList;
import eduardo.enfermedades.helpers.listgeneric.AdapterList2;
import eduardo.enfermedades.helpers.listgeneric.DatosList;
import eduardo.enfermedades.modelos.Enfermedad;
import eduardo.enfermedades.modelos.Medico;
import eduardo.enfermedades.modulos.medicos.Crearmedico;

/**
 * A simple {@link Fragment} subclass.
 */
public class Listaenfermedades extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    View view;

    ListView lista;
    int posit   =-1;
    ArrayList<DatosList> arraydatos;
    AdapterList2 adapter ;
    Button btn_new;

    public Listaenfermedades() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view   = inflater.inflate(R.layout.fragment_listaenfermedades, container, false);

        lista              = (ListView)view.findViewById(R.id.listaview_enfermedades);
        arraydatos= new ArrayList<DatosList>();
        DatosList datos;

        ArrayList<Enfermedad> items=new EnfermedadDaoImpl(getActivity().getApplicationContext()).findAll();

        for(int i=0;i<items.size();i++){
            Enfermedad enfermedad =items.get(i);
            datos   =new DatosList(getResources().getDrawable(R.drawable.virus32),enfermedad.getId(),enfermedad.getNombre(),"");
            arraydatos.add(datos);

        }

        adapter =new AdapterList2(getActivity(),arraydatos);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(this);

        btn_new =(Button)view.findViewById(R.id.b_newenfermedades);
        btn_new.setOnClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        int id  =v.getId();

        if(id==R.id.b_newenfermedades){
            Fragment    fragment    =new Crearenfermedad();
            android.app.FragmentManager fragmentmanager =getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction =fragmentmanager.beginTransaction();
            fragmentTransaction.replace(R.id.container_layout,fragment);
            fragmentTransaction.commit();
        }
    }
}
