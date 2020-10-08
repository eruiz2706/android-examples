package eduardo.venta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    GestionVenta    gestion;
    EditText        et_cedula;
    EditText        et_nombres;
    EditText        et_telefono;
    EditText        et_direccion;
    EditText        et_valorbr;
    EditText        et_bono;
    Button          b_agregar;
    Button          b_listar;
    String          cedula;
    String          nombres;
    String          telefono;
    String          direccion;
    double          valorbr;
    double          bono;
    String[]        lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*crea las instancias*/
        iniconfig();

        /*agrega los escuchas a los botones*/
        escuchas();

    }

    public void printmsj(String mensaje){
        makeText(getApplicationContext(),mensaje, LENGTH_SHORT).show();
    }

    public void iniconfig(){
        et_cedula       =(EditText) findViewById(R.id.et_cedula);
        et_nombres      =(EditText) findViewById(R.id.et_nombres);
        et_telefono     =(EditText) findViewById(R.id.et_telefono);
        et_direccion    =(EditText) findViewById(R.id.et_direccion);
        et_valorbr      =(EditText) findViewById(R.id.et_valorbr);
        et_bono         =(EditText) findViewById(R.id.et_bono);
        b_agregar       =(Button) findViewById(R.id.b_agregar);
        b_listar        =(Button) findViewById(R.id.b_listar);
        gestion         =new GestionVenta();
    }

    public void escuchas(){
        b_agregar.setOnClickListener(this);
        b_listar.setOnClickListener(this);
    }

    public boolean validate(){
        boolean validate=true;

        cedula      =et_cedula.getText().toString();
        nombres     =et_nombres.getText().toString();
        telefono    =et_telefono.getText().toString();
        direccion   =et_direccion.getText().toString();
        valorbr     =Double.parseDouble(et_valorbr.getText().toString());
        bono        =Double.parseDouble(et_bono.getText().toString());

        if(cedula.equals("")){
            printmsj("Debe agregar la cedula");
            validate=false;
        }else if(nombres.equals("")){
            printmsj("Debe agregar el nombre");
            validate=false;
        }else if(telefono.equals("")){
            printmsj("Debe agregar el telefono");
            validate=false;
        } else if(direccion.equals("")){
            printmsj("Debe agregar la direccion");
            validate=false;
        }else if(valorbr<=0){
            printmsj("El valor bruto debe ser mayor a cero");
            validate=false;
        }

        return validate;
    }

    public void addVenta(){

        if(validate()==true){
            cedula      =et_cedula.getText().toString();
            nombres     =et_nombres.getText().toString();
            telefono    =et_telefono.getText().toString();
            direccion   =et_direccion.getText().toString();
            valorbr     =Double.parseDouble(et_valorbr.getText().toString());
            bono        =Double.parseDouble(et_bono.getText().toString());

            gestion.agregarVta(cedula,nombres,telefono,direccion,valorbr,bono);
            printmsj("Se agrego la venta correctamente");
            clear();
        }
    }

    public void clear(){
        et_cedula.setText("");
        et_nombres.setText("");
        et_telefono.setText("");
        et_direccion.setText("");
        et_valorbr.setText("0");
        et_bono.setText("0");
    }

    public void listar(){
        Intent i_listar = new Intent();
        i_listar.setClass(getApplicationContext(),ListaVenta.class);
        startActivityForResult(i_listar,10);
    }


    @Override
    public void onClick(View v) {

        if(R.id.b_agregar==v.getId()){
            addVenta();
        }
        if(R.id.b_listar==v.getId()){
            listar();
        }
    }
}
