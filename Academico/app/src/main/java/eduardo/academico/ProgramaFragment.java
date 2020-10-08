package eduardo.academico;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProgramaFragment extends Fragment implements View.OnClickListener {

    View    view;
    String  codigo;
    String  nombre;
    String  duracion;
    Button  g_guardarp;


    public ProgramaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_programa,container,false);

        g_guardarp   =(Button)view.findViewById(R.id.b_guardarp);
        g_guardarp.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.b_guardarp){
            createProgram();
        }
    }

    public void createProgram(){
        EditText et_codigo      =(EditText) view.findViewById(R.id.et_codigop);
        EditText et_nombre      =(EditText) view.findViewById(R.id.et_nombrep);
        EditText et_duracion    =(EditText) view.findViewById(R.id.et_duraciop);

        codigo   =et_codigo.getText().toString();
        nombre   =et_nombre.getText().toString();
        duracion =et_duracion.getText().toString();

        if(validate()){

            ControlPrograma programa    =new ControlPrograma(getActivity().getApplicationContext());
            programa.agregar(codigo,nombre,duracion);
            programa.save();

            /* se limpian los datos */
            et_codigo.setText("");
            et_nombre.setText("");
            et_duracion.setText("");
        }
    }

    public boolean validate(){
        boolean verif   =true;
        ControlPrograma programa    =new ControlPrograma(getActivity().getApplicationContext());

        if(codigo.equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Debe ingresar el codigo", Toast.LENGTH_SHORT).show();
            verif=false;
        }else if(programa.getexist(codigo)==true){
            Toast.makeText(getActivity().getApplicationContext(), "El codigo ya se encuentra registrado", Toast.LENGTH_SHORT).show();
            verif=false;
        }else if(nombre.equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Debe ingresar el nombre", Toast.LENGTH_SHORT).show();
            verif=false;
        } else if(duracion.equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Debe ingresar la duracion", Toast.LENGTH_SHORT).show();
            verif=false;
        } else if(duracion.equals("0")){
            Toast.makeText(getActivity().getApplicationContext(), "La duracion debe ser mayor a cero", Toast.LENGTH_SHORT).show();
            verif=false;
        }

        return verif;
    }
}
