package eduardo.enfermedades.modulos.medicamentos;


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
import eduardo.enfermedades.dao.MedicamentoDaoImpl;
import eduardo.enfermedades.dao.MedicoDaoImpl;
import eduardo.enfermedades.helpers.listgeneric.AdapterList;
import eduardo.enfermedades.helpers.listgeneric.AdapterList2;
import eduardo.enfermedades.helpers.listgeneric.DatosList;
import eduardo.enfermedades.modelos.Medicamento;
import eduardo.enfermedades.modelos.Medico;
import eduardo.enfermedades.modulos.medicos.Crearmedico;

/**
 * A simple {@link Fragment} subclass.
 */
public class Listamedicamentos extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    View view;

    ListView lista;
    int posit   =-1;
    ArrayList<DatosList> arraydatos;
    AdapterList2 adapter ;
    Button btn_new;

    public Listamedicamentos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view    = inflater.inflate(R.layout.fragment_listamedicamentos, container, false);

        lista              = (ListView)view.findViewById(R.id.listview_medicament);
        arraydatos= new ArrayList<DatosList>();
        DatosList datos;

        ArrayList<Medicamento> items=new MedicamentoDaoImpl(getActivity().getApplicationContext()).findAll();

        for(int i=0;i<items.size();i++){
            Medicamento medicamento =items.get(i);
            datos   =new DatosList(getResources().getDrawable(R.drawable.medicamento32),medicamento.getId(),medicamento.getNombre(),"");
            arraydatos.add(datos);

        }

        adapter =new AdapterList2(getActivity(),arraydatos);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(this);

        btn_new =(Button)view.findViewById(R.id.b_newmedicament);
        btn_new.setOnClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        int id  =v.getId();

        if(id==R.id.b_newmedicament){
            Fragment    fragment    =new Crearmedicamento();
            android.app.FragmentManager fragmentmanager =getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction =fragmentmanager.beginTransaction();
            fragmentTransaction.replace(R.id.container_layout,fragment);
            fragmentTransaction.commit();
        }
    }
}
