package eduardo.enfermedades.modulos.agenda;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;

import java.util.Calendar;

import eduardo.enfermedades.R;
import eduardo.enfermedades.dao.AgendaDaoImpl;
import eduardo.enfermedades.modelos.Agenda;
import eduardo.enfermedades.modulos.recordario.ListaAlertaCita;

/**
 * A simple {@link Fragment} subclass.
 */
public class Editagenda extends Fragment implements View.OnClickListener {

    View view;
    Switch recordatorio_editagenda;
    EditText fechar_editagenda;
    Button b_fechar_editagenda;
    EditText horar_editagenda;
    Button b_horar_editagenda;
    ImageButton b_editaragenda;
    int id=0;

    public Editagenda() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_editagenda, container, false);

        recordatorio_editagenda =(Switch)view.findViewById(R.id.recordatorio_editagenda);;
        fechar_editagenda       =(EditText) view.findViewById(R.id.fechar_editagenda);;
        b_fechar_editagenda     =(Button) view.findViewById(R.id.b_fechar_editagenda);;
        horar_editagenda        =(EditText) view.findViewById(R.id.horar_editagenda);;
        b_horar_editagenda      =(Button) view.findViewById(R.id.b_horar_editagenda);;
        b_editaragenda          =(ImageButton) view.findViewById(R.id.b_editaragenda);;

        b_fechar_editagenda.setOnClickListener(this);
        b_horar_editagenda.setOnClickListener(this);
        b_editaragenda.setOnClickListener(this);



        Bundle bundle1 = getArguments();
        if(bundle1 !=null) {
            AgendaDaoImpl   agendaDao =new AgendaDaoImpl(getActivity().getApplicationContext());
            Agenda agenda   =agendaDao.findbyId(bundle1.getInt("id"));

            if(agenda.getRecordatorio()==1){
                recordatorio_editagenda.setChecked(true);
            }else{
                recordatorio_editagenda.setChecked(false);
            }

            id  =bundle1.getInt("id");
            fechar_editagenda.setText(agenda.getFechar());
            horar_editagenda.setText(agenda.getHorar());
        }

        return view;
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.b_fechar_editagenda){
            final Calendar c=Calendar.getInstance();
            int r_dia =c.get(Calendar.DAY_OF_MONTH);
            int r_mes =c.get(Calendar.MONTH);
            int r_anio=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog2   =new DatePickerDialog(getActivity(),new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    b_fechar_editagenda.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                }
            },r_anio,r_mes,r_dia);

            datePickerDialog2.show();
        }

        if(v.getId()==R.id.b_horar_editagenda){
            final Calendar c=Calendar.getInstance();
            int r_fhora   =c.get(Calendar.HOUR_OF_DAY);
            int r_fminuto =c.get(Calendar.MINUTE);

            TimePickerDialog tpd2 = new TimePickerDialog(getActivity(),
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            // Display Selected time in textbox
                            b_horar_editagenda.setText(hourOfDay + ":" + minute);
                        }
                    }, r_fhora, r_fminuto, false);
            tpd2.show();
        }

        if(v.getId()==R.id.b_editaragenda){
            Boolean sw_record   = recordatorio_editagenda.isChecked();
            int g_recordatorio=0;
            String g_fechar     =fechar_editagenda.getText().toString();
            String g_horar       =horar_editagenda.getText().toString();

            if(sw_record==true){
                g_recordatorio =1;
            }


            Agenda agenda =new Agenda();
            agenda.setId(id);
            agenda.setRecordatorio(g_recordatorio);
            agenda.setFechar(g_fechar);
            agenda.setHorar(g_horar);

            AgendaDaoImpl agendaDao =new AgendaDaoImpl(getActivity().getApplicationContext());
            agendaDao.update(agenda);


            Fragment    fragment    =new ListaAlertaCita();
            android.app.FragmentManager fragmentmanager =getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction =fragmentmanager.beginTransaction();
            fragmentTransaction.replace(R.id.container_layout,fragment);
            fragmentTransaction.commit();
        }
    }
}
