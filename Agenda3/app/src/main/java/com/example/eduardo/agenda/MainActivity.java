package com.example.eduardo.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Context;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner         spsexo;
    ArrayAdapter    adapter_sexo;
    String          []opcsexo   ={"-","Masculino","Femenino"};
    Button          b_guardar   =null;
    Button          b_listar    =null;
    String          operation   ="";
    int             position    =0;

    String          nombre;
    String          apellido;
    String          direccion;
    String          telefono;
    String          correo;
    String          sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spsexo          = (Spinner) findViewById(R.id.sel_sexo);
        adapter_sexo    =new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,opcsexo);
        spsexo.setAdapter(adapter_sexo);

        b_guardar   =(Button)findViewById(R.id.b_guardar);
        b_guardar.setOnClickListener(this);

        b_listar    =(Button)findViewById(R.id.b_listar);
        b_listar.setOnClickListener(this);


    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.crear:
                //startActivity(new Intent(this, About.class));
                return true;
            case R.id.listar:
                //startActivity(new Intent(this, Help.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
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


    public void onclickGuardar(){

        EditText    et_nombre      =(EditText) findViewById(R.id.nombre);
        EditText    et_apellido    =(EditText) findViewById(R.id.apellido);
        EditText    et_direccion   =(EditText) findViewById(R.id.direccion);
        EditText    et_telefono    =(EditText) findViewById(R.id.telefono);
        EditText    et_correo      =(EditText) findViewById(R.id.correo);
        Spinner     sp_sexo       =(Spinner) findViewById(R.id.sel_sexo);

        String      nombre         =et_nombre.getText().toString();
        String      apellido       =et_apellido.getText().toString();
        String      direccion      =et_direccion.getText().toString();
        String      telefono       =et_telefono.getText().toString();
        String      correo         =et_correo.getText().toString();
        String      sexo           =sp_sexo.getSelectedItem().toString();

        if(validate()){
            Agenda      agenda         =new Agenda(getApplicationContext());
            agenda.agregar(nombre,apellido,direccion,telefono,correo,sexo);

            //limpiar campos del formulario
            et_nombre.setText("");
            et_apellido.setText("");
            et_direccion.setText("");
            et_telefono.setText("");
            et_correo.setText("");
            sp_sexo.setSelection(0);
        }


    }

    public boolean validate(){
        boolean verif   =true;
        EditText    et_nombre      =(EditText) findViewById(R.id.nombre);
        EditText    et_apellido    =(EditText) findViewById(R.id.apellido);
        EditText    et_direccion   =(EditText) findViewById(R.id.direccion);
        EditText    et_telefono    =(EditText) findViewById(R.id.telefono);
        EditText    et_correo      =(EditText) findViewById(R.id.correo);
        Spinner     sp_sexo       =(Spinner) findViewById(R.id.sel_sexo);

        String      nombre         =et_nombre.getText().toString();
        String      apellido       =et_apellido.getText().toString();
        String      direccion      =et_direccion.getText().toString();
        String      telefono       =et_telefono.getText().toString();
        String      correo         =et_correo.getText().toString();
        String      sexo           =sp_sexo.getSelectedItem().toString();

        if(nombre.equals("")){
            Toast.makeText(getApplicationContext(), "Debe ingresar el nombre", Toast.LENGTH_SHORT).show();
            verif=false;
        }else if(apellido.equals("")){
            Toast.makeText(getApplicationContext(), "Debe ingresar el apellido", Toast.LENGTH_SHORT).show();
            verif=false;
        } else if(direccion.equals("")){
            Toast.makeText(getApplicationContext(), "Debe ingresar la direccion", Toast.LENGTH_SHORT).show();
            verif=false;

        } else if(telefono.equals("")){
            Toast.makeText(getApplicationContext(), "Debe ingresar la telefono", Toast.LENGTH_SHORT).show();
            verif=false;
        } else if(correo.equals("")){
            Toast.makeText(getApplicationContext(), "Debe ingresar el correo", Toast.LENGTH_SHORT).show();
            verif=false;
        } else if(sexo.equals("")){
            Toast.makeText(getApplicationContext(), "Debe seleccionar el sexo", Toast.LENGTH_SHORT).show();
            verif=false;

        }


        return verif;
    }

    public void onclickActualizar(){

        EditText    et_nombre      =(EditText) findViewById(R.id.nombre);
        EditText    et_apellido    =(EditText) findViewById(R.id.apellido);
        EditText    et_direccion   =(EditText) findViewById(R.id.direccion);
        EditText    et_telefono    =(EditText) findViewById(R.id.telefono);
        EditText    et_correo      =(EditText) findViewById(R.id.correo);
        Spinner     sp_sexo       =(Spinner) findViewById(R.id.sel_sexo);

        String      nombre         =et_nombre.getText().toString();
        String      apellido       =et_apellido.getText().toString();
        String      direccion      =et_direccion.getText().toString();
        String      telefono       =et_telefono.getText().toString();
        String      correo         =et_correo.getText().toString();
        String      sexo           =sp_sexo.getSelectedItem().toString();

        if(validate()){
            Agenda      agenda         =new Agenda(getApplicationContext());
            agenda.update(agenda.getid(position),1,nombre,apellido,direccion,telefono,correo,sexo);
            Toast.makeText(getApplicationContext(), "Se ha actualizo el contacto correctamente", Toast.LENGTH_SHORT).show();

        /*limpiar campos del formulario*/
            et_nombre.setText("");
            et_apellido.setText("");
            et_direccion.setText("");
            et_telefono.setText("");
            et_correo.setText("");
            sp_sexo.setSelection(posspsexo(sexo));

            b_guardar.setText("Guardar");
            position=-1;
            operation="";
        }


    }



    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.b_guardar){
            if(operation.equals("edit")){
                onclickActualizar();
            } else{
                onclickGuardar();
            }
        }
        if(v.getId()==R.id.b_listar){
            Intent i_listar = new Intent();
            i_listar.setClass(getApplicationContext(),ListarContact.class);
            startActivityForResult(i_listar,1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) { // Please, use a final int instead of hardcoded
            // int value
            if (resultCode == RESULT_OK) {
                position        =data.getExtras().getInt("position");
                operation       =data.getExtras().getString("operation");

                if(operation.equals("edit")){
                    EditText    et_nombre      =(EditText) findViewById(R.id.nombre);
                    EditText    et_apellido    =(EditText) findViewById(R.id.apellido);
                    EditText    et_direccion   =(EditText) findViewById(R.id.direccion);
                    EditText    et_telefono    =(EditText) findViewById(R.id.telefono);
                    EditText    et_correo      =(EditText) findViewById(R.id.correo);
                    Spinner     sp_sexo        =(Spinner) findViewById(R.id.sel_sexo);
                    b_guardar.setText("Actualizar");


                    Agenda      agenda         =new Agenda(getApplicationContext());
                    Contacto    contacto       =agenda.getContacto(agenda.getid(position));
                    nombre      =contacto.getNombre();
                    apellido    =contacto.getApellido();
                    direccion   =contacto.getDireccion();
                    telefono    =contacto.getTelefono();
                    correo      =contacto.getCorreo();
                    sexo        =contacto.getSexo();

                    et_nombre.setText(nombre);
                    et_apellido.setText(apellido);
                    et_direccion.setText(direccion);
                    et_telefono.setText(telefono);
                    et_correo.setText(correo);
                    sp_sexo.setSelection(posspsexo(sexo));
                }
            }

        }
    }
}
