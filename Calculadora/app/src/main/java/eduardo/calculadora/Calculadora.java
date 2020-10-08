package eduardo.calculadora;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Eduardo on 06/03/2017.
 */

public class Calculadora implements View.OnClickListener {

    Activity    contexto;
    EditText    et_result;
    Aritmetica  operar;

    String      pantalla;
    String      memorynum="";

    Button      b_cero;
    Button      b_uno;
    Button      b_dos;
    Button      b_tres;
    Button      b_cuatro;
    Button      b_cinco;
    Button      b_seis;
    Button      b_siete;
    Button      b_ocho;
    Button      b_nueve;

    Button      b_mc;
    Button      b_mmas;
    Button      b_mmenos;
    Button      b_mr;
    Button      b_ce;
    Button      b_masmenos;

    Button      b_suma;
    Button      b_resta;
    Button      b_multiplicacion;
    Button      b_division;
    Button      b_igual;
    Button      b_punto;

    String      resultado;

    public Calculadora(Activity contexto) {
        this.contexto   =contexto;
    }

    /*
    * crea las instancias de los elementos en pantalla y agrega sus respecivos escuchas
    * */
    public void initconfig(){
        et_result           =(EditText)contexto.findViewById(R.id.et_result);
        operar              =new Aritmetica();

        b_cero              =(Button)contexto.findViewById(R.id.b_cero);
        b_uno               =(Button)contexto.findViewById(R.id.b_uno);
        b_dos               =(Button)contexto.findViewById(R.id.b_dos);
        b_tres              =(Button)contexto.findViewById(R.id.b_tres);
        b_cuatro            =(Button)contexto.findViewById(R.id.b_cuatro);
        b_cinco             =(Button)contexto.findViewById(R.id.b_cinco);
        b_seis              =(Button)contexto.findViewById(R.id.b_seis);
        b_siete             =(Button)contexto.findViewById(R.id.b_siete);
        b_ocho              =(Button)contexto.findViewById(R.id.b_ocho);
        b_nueve             =(Button)contexto.findViewById(R.id.b_nueve);

        b_mc                =(Button)contexto.findViewById(R.id.b_mc);
        b_mmas              =(Button)contexto.findViewById(R.id.b_mmas);
        b_mmenos            =(Button)contexto.findViewById(R.id.b_mmenos);
        b_mr                =(Button)contexto.findViewById(R.id.b_mr);
        b_ce                =(Button)contexto.findViewById(R.id.b_ce);
        b_masmenos          =(Button)contexto.findViewById(R.id.b_masmenos);

        b_suma              =(Button)contexto.findViewById(R.id.b_suma);
        b_resta             =(Button)contexto.findViewById(R.id.b_resta);
        b_multiplicacion    =(Button)contexto.findViewById(R.id.b_multiplicacion);
        b_division          =(Button)contexto.findViewById(R.id.b_division);
        b_igual             =(Button)contexto.findViewById(R.id.b_igual);
        b_punto             =(Button)contexto.findViewById(R.id.b_punto);

        /*bloqueo campo de resultados*/
        et_result.setEnabled(false);

        /*agregar escuchas*/
        b_cero.setOnClickListener(this);
        b_uno.setOnClickListener(this);
        b_dos.setOnClickListener(this);
        b_tres.setOnClickListener(this);
        b_cuatro.setOnClickListener(this);
        b_cinco.setOnClickListener(this);
        b_seis.setOnClickListener(this);
        b_siete.setOnClickListener(this);
        b_ocho.setOnClickListener(this);
        b_nueve.setOnClickListener(this);

        b_mc.setOnClickListener(this);
        b_mmas.setOnClickListener(this);
        b_mmenos.setOnClickListener(this);
        b_mr.setOnClickListener(this);
        b_ce.setOnClickListener(this);
        b_masmenos.setOnClickListener(this);

        b_suma.setOnClickListener(this);
        b_resta.setOnClickListener(this);
        b_multiplicacion.setOnClickListener(this);
        b_division.setOnClickListener(this);
        b_igual.setOnClickListener(this);
        b_punto.setOnClickListener(this);
    }

    public void setpantalla(String texto){
        et_result.setText(texto);
    }

    public String getpantalla(){
        return et_result.getText().toString();
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.b_cero){
            validateaction("0",false);
        }
        if(v.getId()==R.id.b_uno){
            validateaction("1",false);
        }
        if(v.getId()==R.id.b_dos){
            validateaction("2",false);
        }
        if(v.getId()==R.id.b_tres){
            validateaction("3",false);
        }
        if(v.getId()==R.id.b_cuatro){
            validateaction("4",false);
        }
        if(v.getId()==R.id.b_cinco){
            validateaction("5",false);
        }
        if(v.getId()==R.id.b_seis){
            validateaction("6",false);
        }
        if(v.getId()==R.id.b_siete){
            validateaction("7",false);
        }
        if(v.getId()==R.id.b_ocho){
            validateaction("8",false);
        }
        if(v.getId()==R.id.b_nueve){
            validateaction("9",false);
        }
        if(v.getId()==R.id.b_punto){
            validateaction(".",true);
        }
        if(v.getId()==R.id.b_ce){
            clear();
        }
        if(v.getId()==R.id.b_suma){
            validateaction("+",true);
        }
        if(v.getId()==R.id.b_resta){
            validateaction("-",true);
        }
        if(v.getId()==R.id.b_multiplicacion){
            validateaction("*",true);
        }
        if(v.getId()==R.id.b_division){
            validateaction("/",true);
        }
        if(v.getId()==R.id.b_igual){
            executeOperation();
        }
        if(v.getId()==R.id.b_masmenos){
            masmenos();
        }
        if(v.getId()==R.id.b_mc){
            mc();
        }
        if(v.getId()==R.id.b_mmas){
            mmas();
        }
        if(v.getId()==R.id.b_mmenos){
            mmenos();
        }
        if(v.getId()==R.id.b_mr){
            mr();
        }
    }

    public void printmsj(String mensaje){
        Toast.makeText(contexto.getApplicationContext(),mensaje, Toast.LENGTH_SHORT).show();
    }

    /*
    *funcion controla los nuevos datos a ingresar por pantalla
    */
    public void validateaction(String dato,Boolean verif){
        pantalla    =getpantalla();

        if(verif==true){
            if(pantalla.indexOf(dato)==-1){
                setpantalla(pantalla+dato);
            }
        }else{
            setpantalla(pantalla+dato);
        }
    }

    public void executeOperation(){
        pantalla            =getpantalla();

      if(pantalla.indexOf("+") != -1){
            String[] numeros    = pantalla.split("\\+");
            resultado           =String.valueOf(operar.suma(numeros[0],numeros[1]));
            setpantalla(resultado);
        }
        if(pantalla.indexOf("-") != -1){
            String[] numeros    = pantalla.split("-");
            resultado           =String.valueOf(operar.resta(numeros[0],numeros[1]));
            setpantalla(resultado);
        }
        if(pantalla.indexOf("*") != -1){
            String[] numeros    = pantalla.split("\\*");
            resultado           =String.valueOf(operar.multiplicacion(numeros[0],numeros[1]));
            setpantalla(resultado);
        }
        if(pantalla.indexOf("/") != -1){
            String[] numeros    = pantalla.split("/");
            resultado           =String.valueOf(operar.division(numeros[0],numeros[1]));
            setpantalla(resultado);
        }

    }

    public void clear(){
        setpantalla("");
    }

    /*invierto el siguiente de la operacion realizada*/
    public void masmenos(){
        pantalla    =getpantalla();
        resultado   =String.valueOf(operar.invertir(pantalla));
        setpantalla(resultado);
    }

    public void mc(){
        memorynum="";
        printmsj("Memoria Borrada");
    }

    public void mmas(){
        pantalla            =getpantalla();

        if(memorynum==""){
            memorynum  =pantalla;
        }else{
            memorynum   =String.valueOf(operar.suma(memorynum,pantalla));
        }
        printmsj("Agregado en memoria");
    }

    public void mmenos(){
        pantalla    =getpantalla();

        if(memorynum !=""){
            memorynum           =String.valueOf(operar.resta(memorynum,pantalla));
        }
        printmsj("Subtraido de memoria");
    }

    public void mr(){
        pantalla    =getpantalla();
        setpantalla(pantalla+memorynum);
    }
}
