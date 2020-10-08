package eduardo.menu;


import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateFragment extends Fragment implements View.OnClickListener {

    View view;

    Spinner         spsexo;
    ArrayAdapter    adapter_sexo;
    String          []opcsexo   ={"-","Masculino","Femenino"};
    Button          b_guardar   =null;
    Button          b_listar    =null;
    Button          b_fechan    =null;
    String          operation   ="";
    int             position    =0;

    String          nombre;
    String          apellido;
    String          direccion;
    String          telefono;
    String          correo;
    String          sexo;
    String          fechan;

    private int dia,mes,anio;

    public CreateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_create,container,false);

        spsexo          = (Spinner) view.findViewById(R.id.sel_sexo);
        adapter_sexo    =new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,opcsexo);
        spsexo.setAdapter(adapter_sexo);

        b_guardar   =(Button)view.findViewById(R.id.b_guardar);
        b_guardar.setOnClickListener(this);

        b_fechan    =(Button)view.findViewById(R.id.b_fechan);
        b_fechan.setOnClickListener(this);

        Bundle bundle1 = getArguments();
        if(bundle1 !=null) {
            if (bundle1.getString("operation").equals("edit")) {
                operation = bundle1.getString("operation");
                position = bundle1.getInt("position");
                edit();
            }
        }

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.b_fechan){
            final Calendar c=Calendar.getInstance();
            dia =c.get(Calendar.DAY_OF_MONTH);
            mes =c.get(Calendar.MONTH);
            anio=c.get(Calendar.YEAR);
            final EditText    et_fechan      =(EditText) view.findViewById(R.id.fechan);

            if(operation.equals("edit")){
                String[] parts = et_fechan.getText().toString().split("-");
                anio = Integer.parseInt(parts[0]);
                mes = Integer.parseInt(parts[1]);
                dia = Integer.parseInt(parts[2]);
            }

            System.out.println("año=>"+anio+" mes=>"+mes+" dia=>"+dia);

            DatePickerDialog datePickerDialog   =new DatePickerDialog(getActivity(),new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                     et_fechan.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                }
            },anio,mes,dia);

            datePickerDialog.show();
        }

        if(v.getId()==R.id.b_guardar){
            if(operation.equals("edit")){
                onclickActualizar();
            } else{
                onclickGuardar();
            }
        }
    }

    public int posspsexo(String valor){
        for(int i=0;i<opcsexo.length;i++){
            if(opcsexo[i].equals(valor)){
                return i;
            }
        }

        return 0;
    }

    public void edit(){
        EditText    et_nombre      =(EditText) view.findViewById(R.id.nombre);
        EditText    et_apellido    =(EditText) view.findViewById(R.id.apellido);
        EditText    et_direccion   =(EditText) view.findViewById(R.id.direccion);
        EditText    et_telefono    =(EditText) view.findViewById(R.id.telefono);
        EditText    et_correo      =(EditText)view.findViewById(R.id.correo);
        Spinner     sp_sexo        =(Spinner) view.findViewById(R.id.sel_sexo);
        EditText    et_fechan      =(EditText) view.findViewById(R.id.fechan);

        b_guardar.setText("Actualizar");


        Agenda      agenda         =new Agenda(getActivity().getApplicationContext());
        Contacto    contacto       =agenda.getContacto(agenda.getid(position));
        nombre      =contacto.getNombre();
        apellido    =contacto.getApellido();
        direccion   =contacto.getDireccion();
        telefono    =contacto.getTelefono();
        correo      =contacto.getCorreo();
        sexo        =contacto.getSexo();
        fechan      =contacto.getFechan();

        et_nombre.setText(nombre);
        et_apellido.setText(apellido);
        et_direccion.setText(direccion);
        et_telefono.setText(telefono);
        et_correo.setText(correo);
        sp_sexo.setSelection(posspsexo(sexo));
        et_fechan.setText(fechan);
    }


    public void onclickGuardar(){

        EditText    et_nombre      =(EditText) view.findViewById(R.id.nombre);
        EditText    et_apellido    =(EditText) view.findViewById(R.id.apellido);
        EditText    et_direccion   =(EditText) view.findViewById(R.id.direccion);
        EditText    et_telefono    =(EditText) view.findViewById(R.id.telefono);
        EditText    et_correo      =(EditText) view.findViewById(R.id.correo);
        Spinner     sp_sexo       =(Spinner) view.findViewById(R.id.sel_sexo);
        EditText    et_fechan      =(EditText) view.findViewById(R.id.fechan);

        String      nombre         =et_nombre.getText().toString();
        String      apellido       =et_apellido.getText().toString();
        String      direccion      =et_direccion.getText().toString();
        String      telefono       =et_telefono.getText().toString();
        String      correo         =et_correo.getText().toString();
        String      sexo           =sp_sexo.getSelectedItem().toString();
        String      fechan         = et_fechan.getText().toString();

     if(validate()){
            Agenda      agenda         =new Agenda(getActivity().getApplicationContext());
            agenda.agregar(nombre,apellido,direccion,telefono,correo,sexo,fechan);

            //limpiar campos del formulario
            et_nombre.setText("");
            et_apellido.setText("");
            et_direccion.setText("");
            et_telefono.setText("");
            et_correo.setText("");
            et_fechan.setText("");
            sp_sexo.setSelection(0);
        }


    }

    public boolean validate(){
        boolean verif   =true;
        EditText    et_nombre      =(EditText) view.findViewById(R.id.nombre);
        EditText    et_apellido    =(EditText) view.findViewById(R.id.apellido);
        EditText    et_direccion   =(EditText) view.findViewById(R.id.direccion);
        EditText    et_telefono    =(EditText) view.findViewById(R.id.telefono);
        EditText    et_correo      =(EditText) view.findViewById(R.id.correo);
        Spinner     sp_sexo       =(Spinner) view.findViewById(R.id.sel_sexo);
        EditText    et_fechan      =(EditText) view.findViewById(R.id.fechan);

        String      nombre         =et_nombre.getText().toString();
        String      apellido       =et_apellido.getText().toString();
        String      direccion      =et_direccion.getText().toString();
        String      telefono       =et_telefono.getText().toString();
        String      correo         =et_correo.getText().toString();
        String      sexo           =sp_sexo.getSelectedItem().toString();
        String      fechan         =et_fechan.getText().toString();

        if(nombre.equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Debe ingresar el nombre", Toast.LENGTH_SHORT).show();
            verif=false;
        }else if(apellido.equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Debe ingresar el apellido", Toast.LENGTH_SHORT).show();
            verif=false;
        } else if(direccion.equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Debe ingresar la direccion", Toast.LENGTH_SHORT).show();
            verif=false;

        } else if(telefono.equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Debe ingresar la telefono", Toast.LENGTH_SHORT).show();
            verif=false;
        } else if(correo.equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Debe ingresar el correo", Toast.LENGTH_SHORT).show();
            verif=false;
        } else if(sexo.equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Debe seleccionar el sexo", Toast.LENGTH_SHORT).show();
            verif=false;

        }else if(fechan.equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Debe ingresar la fecha de nacimiento", Toast.LENGTH_SHORT).show();
            verif=false;
        }


        return verif;
    }

    public void onclickActualizar(){

        EditText    et_nombre      =(EditText) view.findViewById(R.id.nombre);
        EditText    et_apellido    =(EditText) view.findViewById(R.id.apellido);
        EditText    et_direccion   =(EditText) view.findViewById(R.id.direccion);
        EditText    et_telefono    =(EditText) view.findViewById(R.id.telefono);
        EditText    et_correo      =(EditText) view.findViewById(R.id.correo);
        Spinner     sp_sexo       =(Spinner) view.findViewById(R.id.sel_sexo);
        EditText    et_fechan      =(EditText) view.findViewById(R.id.fechan);

        String      nombre         =et_nombre.getText().toString();
        String      apellido       =et_apellido.getText().toString();
        String      direccion      =et_direccion.getText().toString();
        String      telefono       =et_telefono.getText().toString();
        String      correo         =et_correo.getText().toString();
        String      sexo           =sp_sexo.getSelectedItem().toString();
        String      fechan         =et_fechan.getText().toString();

        if(validate()){
            Agenda      agenda         =new Agenda(getActivity().getApplicationContext());
            agenda.update(agenda.getid(position),1,nombre,apellido,direccion,telefono,correo,sexo,fechan);
            Toast.makeText(getActivity().getApplicationContext(), "Se ha actualizo el contacto correctamente", Toast.LENGTH_SHORT).show();

            ListaFragment fragment    =new ListaFragment();
            android.app.FragmentManager fragmentmanager =getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction =fragmentmanager.beginTransaction();

            fragmentTransaction.replace(R.id.container_layout,fragment);
            fragmentTransaction.commit();
        }


    }


}
