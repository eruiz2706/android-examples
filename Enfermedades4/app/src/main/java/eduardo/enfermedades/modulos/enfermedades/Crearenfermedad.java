package eduardo.enfermedades.modulos.enfermedades;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import eduardo.enfermedades.R;
import eduardo.enfermedades.dao.EnfermedadDaoImpl;
import eduardo.enfermedades.dao.MedicoDaoImpl;
import eduardo.enfermedades.modelos.Enfermedad;
import eduardo.enfermedades.modelos.Medico;

/**
 * A simple {@link Fragment} subclass.
 */
public class Crearenfermedad extends Fragment implements View.OnClickListener {

    View view;
    EditText nombre;
    Button btn_guardar;
    String g_nombre="";


    public Crearenfermedad() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view    = inflater.inflate(R.layout.fragment_crearenfermedad, container, false);

        nombre          =(EditText)view.findViewById(R.id.nombre_newenfermedad);

        btn_guardar     =(Button)view.findViewById(R.id.b_guardarenfermedad);
        btn_guardar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id  =v.getId();

        if(id==R.id.b_guardarenfermedad){
            guardar();
        }
    }

    public void guardar(){
        g_nombre         =nombre.getText().toString();

        if(validate()){
            EnfermedadDaoImpl enfermedadDao =new EnfermedadDaoImpl(getActivity().getApplicationContext());
            Enfermedad enfermedad    =new Enfermedad();
            enfermedad.setNombre(g_nombre);

            boolean dato    =enfermedadDao.save(enfermedad);
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
            Toast.makeText(getActivity().getApplicationContext(), "Debe ingresar el nombre", Toast.LENGTH_SHORT).show();
            verif=false;
        }

        return verif;
    }
}
