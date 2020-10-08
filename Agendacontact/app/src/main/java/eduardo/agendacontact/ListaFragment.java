package eduardo.agendacontact;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaFragment extends Fragment implements AdapterView.OnItemClickListener {

    View view;

    ListView lista;
    int posit   =-1;
    ArrayList<Datos> arraydatos;
    AdapterDatos adapter ;

    public ListaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_lista,container,false);


        /*lista              = (ListView)view.findViewById(R.id.listView);
        itemsAdapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, new Agenda(getActivity().getApplicationContext()).lista());
        lista.setAdapter(itemsAdapter);*/

        lista              = (ListView)view.findViewById(R.id.listView);
        arraydatos= new ArrayList<Datos>();
        Datos datos;

        ArrayList<Contacto> items=new Agenda(getActivity().getApplicationContext()).lista();

        for(int i=0;i<items.size();i++){
            Contacto contacto =items.get(i);
            datos   =new Datos(getResources().getDrawable(R.drawable.foto),contacto.getNombre(),contacto.getTelefono(),contacto.getId());
            arraydatos.add(datos);

        }


        adapter =new AdapterDatos(getActivity(),arraydatos);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(this);

        return view;
        //return inflater.inflate(R.layout.fragment_lista, container, false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        posit   =position;
        CharSequence option[] = new CharSequence[] {"Editar", "Eliminar"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Opciones");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent resultIntent = new Intent();

                /*Editar*/
                if(which==0){

                    Bundle args = new Bundle();
                    args.putString("operation", "edit");
                    args.putInt("position",posit);

                    CreateFragment      fragment    =new CreateFragment();
                    fragment.setArguments(args);


                    android.app.FragmentManager fragmentmanager =getFragmentManager();
                    android.app.FragmentTransaction fragmentTransaction =fragmentmanager.beginTransaction();

                    fragmentTransaction.replace(R.id.container_layout,fragment);
                    fragmentTransaction.commit();

                }

                /*Eliminar*/
                if(which==1){
                    Agenda      agenda         =new Agenda(getActivity().getApplicationContext());
                    agenda.delete(agenda.getid(posit));
                    Toast.makeText(getActivity().getApplicationContext(), "Se ha Elimiado correctamente", Toast.LENGTH_SHORT).show();

                    ListaFragment fragment    =new ListaFragment();
                    android.app.FragmentManager fragmentmanager =getFragmentManager();
                    android.app.FragmentTransaction fragmentTransaction =fragmentmanager.beginTransaction();

                    fragmentTransaction.replace(R.id.container_layout,fragment);
                    fragmentTransaction.commit();
                }
            }
        });
        builder.show();
    }
}
