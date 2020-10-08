package eduardovue.vuelo;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import eduardovue.vuelo.listas.AdapterList;
import eduardovue.vuelo.listas.DatosList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListVuelo extends Fragment {

    private     View view;
    private ListView lista;
    ArrayList<DatosList> arraydatos;
    AdapterList adapter ;

    public FragmentListVuelo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_list_vuelo, container, false);

        lista              = (ListView)view.findViewById(R.id.listview_vuelos);
        arraydatos= new ArrayList<DatosList>();
        DatosList datos;

        ArrayList<Vuelo> items=new ImpVuelos(getActivity().getApplicationContext()).findAll();

        for(int i=0;i<items.size();i++){
            Vuelo vuelo =items.get(i);
            datos   =new DatosList(getResources().getDrawable(R.drawable.ic_flight),vuelo.getId(),vuelo.getOrigen(),vuelo.getDestino(),vuelo.fechaorigen,vuelo.fechadestino,vuelo.getEstado());
            arraydatos.add(datos);

        }

        adapter =new AdapterList(getActivity(),arraydatos);
        lista.setAdapter(adapter);

        return view;
    }

}
