package eduardo.enfermedades.modulos.diagnosticos;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import eduardo.enfermedades.R;
import eduardo.enfermedades.dao.AgendaDaoImpl;
import eduardo.enfermedades.dao.DiagnosticoDaoImpl;
import eduardo.enfermedades.dao.EnfermedadDaoImpl;
import eduardo.enfermedades.dao.MedicoDaoImpl;
import eduardo.enfermedades.interfaces.DiagnosticoDao;
import eduardo.enfermedades.modelos.Agenda;
import eduardo.enfermedades.modelos.Diagnostico;
import eduardo.enfermedades.modelos.Medico;

/**
 * A simple {@link Fragment} subclass.
 */
public class Creardiagnostico extends Fragment implements View.OnClickListener {

    View view;


    Button      b_guardar;

    Spinner     medico;
    Spinner     enfermedad;
    EditText    observaciones;
    EditText    descripcion;
    ArrayAdapter adapter_medico;
    ArrayAdapter adapter_enfermedad;


    int     g_medico;
    int     g_enfermedad;
    String  g_descripcion;
    String  g_observaciones;

    public Creardiagnostico() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view    = inflater.inflate(R.layout.fragment_creardiagnostico, container, false);

        medico          = (Spinner)view.findViewById(R.id.medico_newdiagnostico);
        adapter_medico   =new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,new MedicoDaoImpl(getActivity().getApplicationContext()).findAllArray());
        medico.setAdapter(adapter_medico);

        enfermedad          = (Spinner)view.findViewById(R.id.enfermedad_newdiagnostico);
        adapter_enfermedad   =new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,new EnfermedadDaoImpl(getActivity().getApplicationContext()).findAllArray());
        enfermedad.setAdapter(adapter_enfermedad);

        observaciones    =(EditText) view.findViewById(R.id.Observaciones_newdiagnostico);

        descripcion     =(EditText) view.findViewById(R.id.Descripcion_newdiagnostico);

        b_guardar=(Button)view.findViewById(R.id.b_guardardiagnostico);
        b_guardar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id  =v.getId();








        if(id==R.id.b_guardardiagnostico){
            guardar();
        }

    }

    public void guardar(){
        g_medico            =idselect(medico.getSelectedItem().toString());
        g_enfermedad        =idselect(enfermedad.getSelectedItem().toString());
        g_descripcion       =descripcion.getText().toString();
        g_observaciones      =observaciones.getText().toString();

        if(validate()){
            DiagnosticoDaoImpl diagnosticoDao =new DiagnosticoDaoImpl(getActivity().getApplicationContext());
            Diagnostico diagnostico   =new Diagnostico();
            diagnostico.setId_medico(g_medico);
            diagnostico.setId_enfermedad(g_enfermedad);
            diagnostico.setObservaciones(g_observaciones);
            diagnostico.setDescripcion(g_descripcion);


            boolean dato    = diagnosticoDao.save(diagnostico);
            if(dato==true){
                Toast.makeText(getActivity().getApplicationContext(), "Registro creado correctamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity().getApplicationContext(), "Inconsistencias al intentar crear", Toast.LENGTH_SHORT).show();
            }

            //limpiar campos del formulario
            medico.setSelection(0);
            enfermedad.setSelection(1);
            observaciones.setText("");
            descripcion.setText("");

        }
    }


    public boolean validate(){
        boolean verif   =true;

        if(g_medico==0) {
            Toast.makeText(getActivity().getApplicationContext(), "Debe seleccionar el medico", Toast.LENGTH_SHORT).show();
            verif = false;
        }else if(g_enfermedad==0){
                Toast.makeText(getActivity().getApplicationContext(), "Debe seleccionar la enfermedad", Toast.LENGTH_SHORT).show();
                verif=false;
        } else if(g_observaciones.equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Debe agregar las observaciones", Toast.LENGTH_SHORT).show();
            verif=false;
        }
        else if(g_descripcion.equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Debe agregar la descripcion", Toast.LENGTH_SHORT).show();
            verif=false;
        }

        return verif;
    }

    public int idselect(String cadena){

        if(cadena.equals("-") || cadena.equals("")){
            return 0;
        }else{
            String[] parts =cadena.split("-");
            String part1 = parts[0];
            part1        =part1.trim();

            if(part1.equals("")){
                return 0;
            }else{
                return Integer.parseInt(part1);
            }
        }
    }
}
