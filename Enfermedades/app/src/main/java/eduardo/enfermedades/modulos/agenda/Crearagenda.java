package eduardo.enfermedades.modulos.agenda;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import eduardo.enfermedades.R;
import eduardo.enfermedades.dao.AgendaDaoImpl;
import eduardo.enfermedades.dao.MedicoDaoImpl;
import eduardo.enfermedades.modelos.Agenda;
import eduardo.enfermedades.modelos.Medico;

/**
 * A simple {@link Fragment} subclass.
 */
public class Crearagenda extends Fragment implements View.OnClickListener {

    View view;
    int dia;
    int mes;
    int anio;
    int r_dia;
    int r_mes;
    int r_anio;
    int fhora;
    int fminuto;
    int r_fhora;
    int r_fminuto;

    ImageButton b_guardar;
    ImageButton b_listaragenda;
    Button      b_fecha;
    Button      b_fechar;
    Button      b_hora;
    Button      b_horar;
    Spinner     medico;
    EditText    fecha;
    EditText    fechar;
    EditText    hora;
    EditText    horar;
    EditText    descripcion;
    ArrayAdapter adapter_medico;
    Switch      recordatorio;

    TextInputLayout til_descripnewagenda;
    TextInputLayout til_fechanewagenda;
    TextInputLayout til_horanewagenda;
    TextInputLayout til_fecharnewagenda;
    TextInputLayout til_horarnewagenda;

    int     g_medico;
    String  g_descripcion;
    String  g_fecha;
    int     g_recordatorio=0;
    String  g_fechar;
    String  g_hora;
    String  g_horar;

    public Crearagenda() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view    = inflater.inflate(R.layout.fragment_crearagenda, container, false);

        medico          =(Spinner)view.findViewById(R.id.medico_newagenda);
        adapter_medico   =new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,new MedicoDaoImpl(getActivity().getApplicationContext()).findAllArray());
        medico.setAdapter(adapter_medico);

        descripcion     =(EditText) view.findViewById(R.id.descripcion_newagenda);

        recordatorio    =(Switch)  view.findViewById(R.id.recordatorio_newagenda);

        fecha   =(EditText)view.findViewById(R.id.fecha_newagenda);
        fechar   =(EditText)view.findViewById(R.id.fechar_newagenda);

        hora   =(EditText)view.findViewById(R.id.hora_newagenda);
        horar  =(EditText)view.findViewById(R.id.horar_newagenda);

        b_fecha =(Button)view.findViewById(R.id.b_fecha_newagenda);
        b_fecha.setOnClickListener(this);

        b_hora =(Button)view.findViewById(R.id.b_hora_newagenda);
        b_hora.setOnClickListener(this);

        b_fechar =(Button)view.findViewById(R.id.b_fechar_newagenda);
        b_fechar.setOnClickListener(this);

        b_horar =(Button)view.findViewById(R.id.b_horar_newagenda);
        b_horar.setOnClickListener(this);

        b_guardar=(ImageButton)view.findViewById(R.id.b_guardaragenda);
        b_guardar.setOnClickListener(this);

        b_listaragenda=(ImageButton)view.findViewById(R.id.b_listaragenda);
        b_listaragenda.setOnClickListener(this);

        til_descripnewagenda=(TextInputLayout)view.findViewById(R.id.til_descripnewagenda);
        til_fechanewagenda  =(TextInputLayout)view.findViewById(R.id.til_fechanewagenda);
        til_horanewagenda   =(TextInputLayout)view.findViewById(R.id.til_horanewagenda);
        til_fecharnewagenda =(TextInputLayout)view.findViewById(R.id.til_fecharnewagenda);
        til_horarnewagenda  =(TextInputLayout)view.findViewById(R.id.til_horarnewagenda);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id  =v.getId();

        if(id==R.id.b_fecha_newagenda){
            final Calendar c=Calendar.getInstance();
            dia =c.get(Calendar.DAY_OF_MONTH);
            mes =c.get(Calendar.MONTH);
            anio=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog   =new DatePickerDialog(getActivity(),new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    fecha.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                }
            },anio,mes,dia);

            datePickerDialog.show();
        }

        if(id==R.id.b_fechar_newagenda){
            final Calendar c=Calendar.getInstance();
            r_dia =c.get(Calendar.DAY_OF_MONTH);
            r_mes =c.get(Calendar.MONTH);
            r_anio=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog2   =new DatePickerDialog(getActivity(),new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    fechar.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                }
            },r_anio,r_mes,r_dia);

            datePickerDialog2.show();
        }

        if(id==R.id.b_hora_newagenda){
            final Calendar c=Calendar.getInstance();
            fhora   =c.get(Calendar.HOUR_OF_DAY);
            fminuto =c.get(Calendar.MINUTE);

            TimePickerDialog tpd = new TimePickerDialog(getActivity(),
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            // Display Selected time in textbox

                            hora.setText(hourOfDay + ":" + minute);
                        }
                    }, fhora, fminuto, false);
            tpd.show();
        }

        if(id==R.id.b_horar_newagenda){
            final Calendar c=Calendar.getInstance();
            r_fhora   =c.get(Calendar.HOUR_OF_DAY);
            r_fminuto =c.get(Calendar.MINUTE);

            TimePickerDialog tpd2 = new TimePickerDialog(getActivity(),
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            // Display Selected time in textbox
                             horar.setText(hourOfDay + ":" + minute);
                        }
                    }, r_fhora, r_fminuto, false);
            tpd2.show();
        }

        if(id==R.id.b_guardaragenda){
            guardar();
        }

        if(id==R.id.b_listaragenda){
            Fragment    fragment    =new Listaagenda();
            android.app.FragmentManager fragmentmanager =getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction =fragmentmanager.beginTransaction();
            fragmentTransaction.replace(R.id.container_layout,fragment);
            fragmentTransaction.commit();
        }

    }

    public void guardar(){
        g_medico            =idselect(medico.getSelectedItem().toString());
        g_descripcion       =descripcion.getText().toString();
        g_fecha             =fecha.getText().toString();
        g_fechar            =fechar.getText().toString();
        g_hora              =hora.getText().toString();
        g_horar             =horar.getText().toString();

        Boolean sw_record   = recordatorio.isChecked();
        if(sw_record==true){
            g_recordatorio =1;
        }else{
            g_recordatorio =0;
        }

        if(validate()){
            AgendaDaoImpl agendaDao =new AgendaDaoImpl(getActivity().getApplicationContext());
            Agenda agenda    =new Agenda();
            agenda.setId_medico(g_medico);
            agenda.setDescripcion(g_descripcion);
            agenda.setFecha(g_fecha);
            agenda.setRecordatorio(g_recordatorio);
            agenda.setHora(g_hora);
            agenda.setFechar(g_fechar);
            agenda.setHorar(g_horar);

            if(!fechar.equals(""))agenda.setFechar(g_fechar);

            boolean dato    =agendaDao.save(agenda);
            if(dato==true){
                Toast.makeText(getActivity().getApplicationContext(), "Registro creado correctamento", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity().getApplicationContext(), "Inconsistencias al intentar crear", Toast.LENGTH_SHORT).show();
            }

            //limpiar campos del formulario
            descripcion.setText("");
            fecha.setText("");
            fechar.setText("");
            hora.setText("");
            horar.setText("");
            medico.setSelection(0);
            recordatorio.setChecked(false);
        }
    }


    public boolean validate(){
        boolean verif   =true;

        if(g_medico==0){
            Toast.makeText(getActivity().getApplicationContext(), "Debe seleccionar el medico", Toast.LENGTH_SHORT).show();
            verif=false;
        }else if(g_descripcion.equals("")){
            til_descripnewagenda.setError("campo obligatorio");
            //Toast.makeText(getActivity().getApplicationContext(), "Debe agregar la descripcion", Toast.LENGTH_SHORT).show();
            verif=false;
        }else if(g_fecha.equals("")){
            til_fechanewagenda.setError("campo obligatorio");
            //Toast.makeText(getActivity().getApplicationContext(), "Debe agregar la fecha", Toast.LENGTH_SHORT).show();
            verif=false;
        }else if(g_hora.equals("")){
            til_horanewagenda.setError("campo obligatorio");
            //Toast.makeText(getActivity().getApplicationContext(), "Debe agregar la hora", Toast.LENGTH_SHORT).show();
            verif=false;
        }else if(g_recordatorio==1 && g_fechar.equals("")){
            til_fecharnewagenda.setError("campo obligatorio");
            //Toast.makeText(getActivity().getApplicationContext(), "Debe agregar la fecha del recordatorio", Toast.LENGTH_SHORT).show();
            verif=false;
        }else if(g_recordatorio==1 && g_horar.equals("")){
            til_horarnewagenda.setError("campo obligatorio");
            //Toast.makeText(getActivity().getApplicationContext(), "Debe agregar la hora del recordatorio", Toast.LENGTH_SHORT).show();
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
