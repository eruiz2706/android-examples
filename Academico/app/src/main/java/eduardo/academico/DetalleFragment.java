package eduardo.academico;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleFragment extends Fragment {

    View            view;
    int             position    =-1;
    ListView lista;
    ArrayAdapter<String> itemsAdapter;

    public DetalleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_detalle,container,false);

        Bundle bundle1 = getArguments();
        if(bundle1 !=null) {
           position = bundle1.getInt("position");
            int id  =new ControlPrograma(getActivity().getApplicationContext()).getid(position);

            String[] datos =new ControlEstudiante(getActivity().getApplicationContext()).listProgram2(id);

            if(datos !=null){
                lista               = (ListView)view.findViewById(R.id.list_det);
                itemsAdapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,datos);
                lista.setAdapter(itemsAdapter);
            }

        }

        return view;
    }

}
