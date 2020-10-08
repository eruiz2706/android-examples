package eduardo.enfermedades.modulos.medicos;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import eduardo.enfermedades.R;
import eduardo.enfermedades.dao.MedicoDaoImpl;
import eduardo.enfermedades.modelos.Medico;

/**
 * A simple {@link Fragment} subclass.
 */
public class Crearmedico extends Fragment implements View.OnClickListener {

    View view;
    EditText nombre;
    EditText especialidad;
    Button  btn_guardar;

    String g_nombre="";
    String g_especialidad="";

    public Crearmedico() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_crearmedico, container, false);

        nombre          =(EditText)view.findViewById(R.id.nombre_newmedic);
        especialidad    =(EditText)view.findViewById(R.id.especialidad_newmedic);

        btn_guardar     =(Button)view.findViewById(R.id.b_guardarmedic);
        btn_guardar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        int id  =v.getId();

        if(id==R.id.b_guardarmedic){
            guardar();
        }

    }


    public void guardar(){
        g_nombre         =nombre.getText().toString();
        g_especialidad   =especialidad.getText().toString();

        if(validate()){
            MedicoDaoImpl medicoDao =new MedicoDaoImpl(getActivity().getApplicationContext());
            Medico        medico    =new Medico();
            medico.setNombre(g_nombre);
            medico.setEspecialidad(g_especialidad);

            boolean dato    =medicoDao.save(medico);
            if(dato==true){
                Toast.makeText(getActivity().getApplicationContext(), "Registro creado correctamento", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity().getApplicationContext(), "Inconsistencias al intentar crear", Toast.LENGTH_SHORT).show();
            }

            //limpiar campos del formulario
            nombre.setText("");
            especialidad.setText("");
        }

    }

    public boolean validate(){
        boolean verif   =true;

        if(g_nombre.equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Debe ingresar el nombre", Toast.LENGTH_SHORT).show();
            verif=false;
        }

        return verif;
    }
}
