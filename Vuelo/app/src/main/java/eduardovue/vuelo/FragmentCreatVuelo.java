package eduardovue.vuelo;


import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCreatVuelo extends Fragment implements View.OnClickListener, View.OnFocusChangeListener {

    View        view;
    EditText    et_origen;
    EditText    et_destino;
    EditText    et_fechaida;
    EditText    et_fechadestino;
    EditText    et_cantidad;
    EditText    et_totalapagar;
    Button      b_guardarvuelo;

    TextInputLayout    til_origen;
    TextInputLayout    til_destino;
    TextInputLayout    til_fechaida;
    TextInputLayout    til_fechadestino;
    TextInputLayout    til_cantidad;
    TextInputLayout    til_totalapagar;


    final Calendar calendar=Calendar.getInstance();


    public FragmentCreatVuelo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view    = inflater.inflate(R.layout.fragment_fragment_creat_vuelo, container, false);

        et_origen           =(EditText)view.findViewById(R.id.et_origen);
        et_destino          =(EditText)view.findViewById(R.id.et_destino);
        et_fechaida         =(EditText)view.findViewById(R.id.et_fechaida);
        et_fechadestino     =(EditText)view.findViewById(R.id.et_fechadestino);
        et_cantidad         =(EditText)view.findViewById(R.id.et_cantidad);
        et_totalapagar      =(EditText)view.findViewById(R.id.et_totalapagar);
        b_guardarvuelo      =(Button) view.findViewById(R.id.b_guardarvuelo);

        til_origen          =(TextInputLayout)view.findViewById(R.id.til_origen);
        til_destino         =(TextInputLayout)view.findViewById(R.id.til_destino);
        til_fechaida        =(TextInputLayout)view.findViewById(R.id.til_fechaida);
        til_fechadestino    =(TextInputLayout)view.findViewById(R.id.til_fechadestino);
        til_cantidad        =(TextInputLayout)view.findViewById(R.id.til_cantidad);
        til_totalapagar     =(TextInputLayout)view.findViewById(R.id.til_totalapagar);

        b_guardarvuelo.setOnClickListener(this);
        et_fechaida.setOnClickListener(this);
        et_fechadestino.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id  =v.getId();

        if(id==R.id.b_guardarvuelo){
            guardar();
        }
        if(id==R.id.et_fechaida){
           int dia =calendar.get(Calendar.DAY_OF_MONTH);
           int mes =calendar.get(Calendar.MONTH)+1;
           int anio=calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog   =new DatePickerDialog(getActivity(),new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    et_fechaida.setText(year+"-"+month+"-"+dayOfMonth);
                }
            },anio,mes,dia);

            datePickerDialog.show();
        }
        if(id==R.id.et_fechadestino){
            int dia =calendar.get(Calendar.DAY_OF_MONTH);
            int mes =calendar.get(Calendar.MONTH)+1;
            int anio=calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog   =new DatePickerDialog(getActivity(),new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    et_fechadestino.setText(year+"-"+month+"-"+dayOfMonth);
                }
            },anio,mes,dia);

            datePickerDialog.show();
        }
    }


    private void guardar(){
        ImpVuelos impVuelos =new ImpVuelos(getActivity().getApplicationContext());

        String origen       =et_origen.getText().toString();
        String destino      =et_destino.getText().toString();
        String fechaida     =et_fechaida.getText().toString();
        String fechadestino =et_fechadestino.getText().toString();

        int cantidad        =0;
        if(!et_cantidad.getText().toString().equals("")){
            cantidad        =Integer.parseInt(et_cantidad.getText().toString());
        }

        double totalapagar  =0;
        if(!et_totalapagar.getText().toString().equals("")){
            totalapagar  =Double.parseDouble(et_totalapagar.getText().toString());
        }

        System.out.println("origen=>"+origen+" destino=>"+destino+" fechaida=>"+fechaida+" fechadestino=>"+fechadestino+" cantidad=>"+cantidad+" total=>"+totalapagar);


        if(validate()){
            ContentValues contentValues=new ContentValues();
            contentValues.put("origen",origen);
            contentValues.put("destino",destino);
            contentValues.put("fechaida",fechaida);
            contentValues.put("fechadestino",fechadestino);
            contentValues.put("cantidad",cantidad);
            contentValues.put("totalapagar",totalapagar);
            contentValues.put("estado","pendiente");
            boolean verificacion    = impVuelos.register(contentValues);
            if(verificacion==true){
                Toast.makeText(getActivity().getApplicationContext(), "El vuelo se registro correctamente", Toast.LENGTH_SHORT).show();

                et_origen.setText("");
                et_destino.setText("");
                et_fechaida.setText("");
                et_fechadestino.setText("");
                et_cantidad.setText("");
                et_totalapagar.setText("");
            }else{
                Toast.makeText(getActivity().getApplicationContext(), "Inconsistencias al intentar crear el vuelo", Toast.LENGTH_SHORT).show();
            }
        }


    }

    public boolean validate(){
        boolean verif   =true;

        til_origen.setError(null);
        til_destino.setError(null);
        til_fechaida.setError(null);
        til_fechadestino.setError(null);
        til_cantidad.setError(null);
        til_totalapagar.setError(null);

        if(et_origen.getText().toString().equals("")){
            til_origen.setError("campo obligatorio");
            verif=false;
        }else  if(et_destino.getText().toString().equals("")){
            til_destino.setError("campo obligatorio");
            verif=false;
        }else if(et_fechaida.getText().toString().equals("")){
            til_fechaida.setError("campo obligatorio");
            verif=false;
        }else if(et_fechadestino.getText().toString().equals("")){
            til_fechadestino.setError("campo obligatorio");
            verif=false;
        }else if(et_cantidad.getText().toString().equals("")){
            til_cantidad.setError("campo obligatorio");
            verif=false;
        }else if(et_totalapagar.getText().toString().equals("")){
            til_totalapagar.setError("campo obligatorio");
            verif=false;
        }

        return verif;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id  =v.getId();

        /*if(id==R.id.et_fechaida){
            int dia =calendar.get(Calendar.DAY_OF_MONTH);
            int mes =calendar.get(Calendar.MONTH)+1;
            int anio=calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog   =new DatePickerDialog(getActivity(),new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    et_fechaida.setText(year+"-"+month+"-"+dayOfMonth);
                }
            },anio,mes,dia);

            datePickerDialog.show();
        }
        if(id==R.id.et_fechadestino){
            int dia =calendar.get(Calendar.DAY_OF_MONTH);
            int mes =calendar.get(Calendar.MONTH)+1;
            int anio=calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog   =new DatePickerDialog(getActivity(),new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    et_fechadestino.setText(year+"-"+month+"-"+dayOfMonth);
                }
            },anio,mes,dia);

            datePickerDialog.show();
        }*/
    }
}
