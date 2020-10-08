package eduardo.enfermedades.modulos.diagnosticos;


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
import eduardo.enfermedades.dao.DiagnosticoDaoImpl;
import eduardo.enfermedades.helpers.listgeneric.AdapterList3;
import eduardo.enfermedades.helpers.listgeneric.DatosList;
import eduardo.enfermedades.modelos.Diagnostico;


/**
 * A simple {@link Fragment} subclass.
 */
public class Listadiagnosticos extends Fragment  implements AdapterView.OnItemClickListener, View.OnClickListener {

    View view;

    ListView lista;
    ArrayList<DatosList> arraydatos;
    AdapterList3 adapter ;
    Button btn_new;


    @Override
    public void onClick(View v) {
        int id  =v.getId();

        if(id==R.id.b_newdiagnostico){
            Fragment    fragment    =new Creardiagnostico();
            android.app.FragmentManager fragmentmanager =getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction =fragmentmanager.beginTransaction();
            fragmentTransaction.replace(R.id.container_layout,fragment);
            fragmentTransaction.commit();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        view    = inflater.inflate(R.layout.fragment_listadiagnosticos, container, false);

        lista              = (ListView)view.findViewById(R.id.listview_diagnosticos);
        arraydatos= new ArrayList<DatosList>();
        DatosList datos;

        ArrayList<Diagnostico> items=new DiagnosticoDaoImpl(getActivity().getApplicationContext()).findAll();

        for(int i=0;i<items.size();i++){
            Diagnostico diagnostico =items.get(i);
            datos   =new DatosList(getResources().getDrawable(R.drawable.ic_diagnostico),diagnostico.getId(),String.valueOf(diagnostico.getId_medico()),diagnostico.getDescripcion());
            arraydatos.add(datos);

        }

        adapter =new AdapterList3(getActivity(),arraydatos);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(this);

        btn_new =(Button)view.findViewById(R.id.b_newdiagnostico);
        btn_new.setOnClickListener(this);

        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
