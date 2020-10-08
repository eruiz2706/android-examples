package eduardo.enfermedades.modulos.medicamentos;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import eduardo.enfermedades.R;
import eduardo.enfermedades.dao.MedicamentoDaoImpl;
import eduardo.enfermedades.dao.MedicoDaoImpl;
import eduardo.enfermedades.modelos.Medicamento;
import eduardo.enfermedades.modelos.Medico;
import eduardo.enfermedades.modulos.medicos.Listamedicos;

/**
 * A simple {@link Fragment} subclass.
 */
public class Crearmedicamento extends Fragment implements View.OnClickListener {

    View view;
    EditText nombre;
    ImageButton btn_guardar;
    ImageButton b_listarmedicament;
    String g_nombre="";
    TextInputLayout til_nombremedicament;

    public Crearmedicamento() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view    = inflater.inflate(R.layout.fragment_crearmedicamento, container, false);

        nombre          =(EditText)view.findViewById(R.id.nombre_newmedicament);
        til_nombremedicament=(TextInputLayout)view.findViewById(R.id.til_nombremedicament);

        btn_guardar     =(ImageButton)view.findViewById(R.id.b_guardarmedicament);
        btn_guardar.setOnClickListener(this);

        b_listarmedicament=(ImageButton)view.findViewById(R.id.b_listarmedicament);
        b_listarmedicament.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id  =v.getId();

        if(id==R.id.b_guardarmedicament){
            guardar();
        }

        if(id==R.id.b_listarmedicament){
            Fragment    fragment    =new Listamedicamentos();
            android.app.FragmentManager fragmentmanager =getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction =fragmentmanager.beginTransaction();
            fragmentTransaction.replace(R.id.container_layout,fragment);
            fragmentTransaction.commit();
        }
    }

    public void guardar(){
        g_nombre         =nombre.getText().toString();

        if(validate()){
            MedicamentoDaoImpl medicamentoDao =new MedicamentoDaoImpl(getActivity().getApplicationContext());
            Medicamento medicamento    =new Medicamento();
            medicamento.setNombre(g_nombre);

            boolean dato    =medicamentoDao.save(medicamento);
            if(dato==true){
                Toast.makeText(getActivity().getApplicationContext(), "Registro creado correctamento", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity().getApplicationContext(), "Inconsistencias al intentar crear", Toast.LENGTH_SHORT).show();
            }

            //limpiar campos del formulario
            nombre.setText("");
        }

    }

    public boolean validate(){
        boolean verif   =true;

        if(g_nombre.equals("")){
            til_nombremedicament.setError("campo obligatorio");
            //Toast.makeText(getActivity().getApplicationContext(), "Debe ingresar el nombre", Toast.LENGTH_SHORT).show();
            verif=false;
        }

        return verif;
    }
}
