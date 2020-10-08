package com.example.joseluis.selectorfecha;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Jose Luis on 24/04/2017.
 */
public class Registro extends Fragment implements View.OnClickListener {
    View myview;
    private DatePicker selectorFecha;


    private TextView vistaFecha;

    private Calendar calendario;
    private int dia,mes,anio;
    MainActivity activity;
    EditText fecha_nacimiento;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.registro,container,false);
        vistaFecha = (TextView) myview.findViewById(R.id.vistafecha);
        calendario = (Calendar.getInstance());

        anio = calendario.get(Calendar.YEAR);
        mes= calendario.get(Calendar.MONTH);
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        //Capturo el boton y le asigno el oyente de los eventos
        Button boton =(Button) myview.findViewById(R.id.button);
        boton.setOnClickListener(this);


        mostrarFecha(anio,mes+1,dia);

        return  myview;
    }





    public void mostrarFecha(int anio,int mes, int dia){
         vistaFecha.setText(dia+"/"+mes+"/"+anio);
    }

    public  void setActivity(MainActivity main){
        activity = main;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(myview.getContext(),"llamando calendario", Toast.LENGTH_LONG).show();
        activity.mostrarDialogoFecha(dia,mes,anio);
    }
}
