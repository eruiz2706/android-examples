package eduardo.academico;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class EstudianteFragment extends Fragment implements View.OnClickListener {

    View            view;
    String          codigo;
    String          nombre;
    int             programa;
    int             progacad;
    Button          b_guardare;
    Spinner         spprograma;
    ArrayAdapter    adapter_programa;

    public EstudianteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_estudiante,container,false);

        spprograma          = (Spinner) view.findViewById(R.id.sel_programa);
        adapter_programa    =new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,new ControlPrograma(getActivity().getApplicationContext()).listProgram());
        spprograma.setAdapter(adapter_programa);

        b_guardare   =(Button)view.findViewById(R.id.b_guardare);
        b_guardare.setOnClickListener(this);

        return view;

    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.b_guardare){
            createEstudiante();
        }
    }

    public void createEstudiante(){
        EditText et_codigo     =(EditText) view.findViewById(R.id.et_codigoe);
        EditText et_nombre     =(EditText) view.findViewById(R.id.et_nombree);
        Spinner  sp_program    =(Spinner) view.findViewById(R.id.sel_programa);

        codigo     =et_codigo.getText().toString();
        nombre     =et_nombre.getText().toString();
        programa   =(int)sp_program.getSelectedItemId();

        if(validate()){
            System.out.println("progra=>"+programa);
            progacad    =new ControlPrograma(getActivity().getApplicationContext()).getid(programa);
            System.out.println("id=>"+progacad);

            ControlEstudiante estudiante    =new ControlEstudiante(getActivity().getApplicationContext());
            estudiante.agregar(codigo,nombre,progacad);
            estudiante.save();

            /* se limpian los datos */
            et_codigo.setText("");
            et_nombre.setText("");
            sp_program.setSelection(0);
        }

    }

    public boolean validate(){
        boolean verif   =true;
        ControlEstudiante estudiante    =new ControlEstudiante(getActivity().getApplicationContext());

        if(codigo.equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Debe ingresar el codigo", Toast.LENGTH_SHORT).show();
            verif=false;
        }else if(estudiante.getexist(codigo)==true){
            Toast.makeText(getActivity().getApplicationContext(), "El codigo ya se encuentra registrado", Toast.LENGTH_SHORT).show();
            verif=false;
        }else if(nombre.equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Debe ingresar el nombre", Toast.LENGTH_SHORT).show();
            verif=false;
        }else if(programa==0){
            Toast.makeText(getActivity().getApplicationContext(), "Debe seleccionar el programa", Toast.LENGTH_SHORT).show();
            verif=false;
        }

        return verif;
    }
}
