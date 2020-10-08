package eduardo.enfermedades.modulos.recordario;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import eduardo.enfermedades.R;
import eduardo.enfermedades.dao.AgendaDaoImpl;
import eduardo.enfermedades.helpers.listgeneric.AdapterList3;
import eduardo.enfermedades.helpers.listgeneric.AdapterList4;
import eduardo.enfermedades.helpers.listgeneric.DatosList;
import eduardo.enfermedades.modelos.Agenda;
import eduardo.enfermedades.modulos.agenda.Editagenda;
import eduardo.enfermedades.modulos.agenda.Listaagenda;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaAlertaCita extends Fragment implements AdapterView.OnItemClickListener {

    View view;
    ListView lista;
    ArrayList<DatosList> arraydatos;
    AdapterList4 adapter ;

    public ListaAlertaCita() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_lista_alerta_cita, container, false);

        lista              = (ListView)view.findViewById(R.id.listaview_alertacita);
        arraydatos= new ArrayList<DatosList>();
        DatosList datos;

        ArrayList<Agenda> items=new AgendaDaoImpl(getActivity().getApplicationContext()).findAllAlert();

        for(int i=0;i<items.size();i++){
            Agenda agenda =items.get(i);

            String estado="";

            if(agenda.getRecordatorio()==1){
                estado="Activado";
            }else{
                estado="Inactivado";
            }

            datos   =new DatosList(getResources().getDrawable(R.drawable.alarma),agenda.getId(),String.valueOf(agenda.getDescripcion()),agenda.getFecha()+" "+agenda.getHora(),estado,agenda.getFechar()+" "+agenda.getHorar());
            if(agenda.getRecordatorio()==0){
                datos   =new DatosList(getResources().getDrawable(R.drawable.alarmaoff),agenda.getId(),String.valueOf(agenda.getDescripcion()),agenda.getFecha()+" "+agenda.getHora(),estado,agenda.getFechar()+" "+agenda.getHorar());
            }
            arraydatos.add(datos);

        }

        adapter =new AdapterList4(getActivity(),arraydatos);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
        CharSequence option[] = new CharSequence[] {"Modificar"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Opciones");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent resultIntent = new Intent();

                /*Modificar*/
                if(which==0){

                    DatosList datos  = (DatosList) adapter.getItem(position);


                    Bundle args = new Bundle();
                    args.putInt("id",datos.getId());

                    Fragment    fragment    =new Editagenda();
                    fragment.setArguments(args);

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
