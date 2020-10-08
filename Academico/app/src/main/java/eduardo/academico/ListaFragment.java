package eduardo.academico;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaFragment extends Fragment implements AdapterView.OnItemClickListener {

    View view;
    ListView lista;
    ArrayAdapter<String> itemsAdapter;

    public ListaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_lista,container,false);

        lista               = (ListView)view.findViewById(R.id.list_program);

        String[] datos      =new ControlPrograma(getActivity().getApplicationContext()).listProgram2();

        if (datos != null) {
            itemsAdapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,datos);
            lista.setAdapter(itemsAdapter);
            lista.setOnItemClickListener(this);
        }

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        System.out.println(position);
        Bundle args = new Bundle();
        args.putInt("position",position+1);

        DetalleFragment      fragment    =new DetalleFragment();
        fragment.setArguments(args);


        android.app.FragmentManager fragmentmanager =getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction =fragmentmanager.beginTransaction();

        fragmentTransaction.replace(R.id.container_layout,fragment);
        fragmentTransaction.commit();

    }
}
