package com.app.presupuestosuniajc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import database.DataEmpresa;
import modelo.Empresa;

public class ActEmpresa extends Activity implements View.OnClickListener{
    ImageButton btn_listar          = null;
    ImageButton btn_guardar         = null;
    EditText txt_nit                = null;
    EditText txt_nombre             = null;
    EditText txt_razons             = null;
    EditText txt_sector             = null;
    EditText txt_numempleados       = null;
    EditText txt_descripcion        = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa);
        btn_listar      =(ImageButton)findViewById(R.id.btn_listar);
        btn_listar.setOnClickListener(this);
        btn_guardar     =(ImageButton)findViewById(R.id.btn_guardar);
        btn_guardar.setOnClickListener(this);
        txt_nit         =(EditText) findViewById(R.id.txt_nit);
        txt_nombre      =(EditText) findViewById(R.id.txt_nombre);
        txt_razons      =(EditText) findViewById(R.id.txt_razons);
        txt_sector      =(EditText) findViewById(R.id.txt_sector);
        txt_numempleados=(EditText) findViewById(R.id.txt_numempleados);
        txt_descripcion =(EditText) findViewById(R.id.txt_descripcion);
    }
    private void mostrarMensaje(String mensaje){
        Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_SHORT).show();
    }
    private void guardarEmpresa() {
        if(setEmpresa()){
            mostrarMensaje("Se creo la empreda de manera exitosa !!!");
            txt_nit.setText("");
            txt_nit.requestFocus();
            txt_nombre.setText("");
            txt_razons.setText("");
            txt_sector.setText("");
            txt_numempleados.setText("");
            txt_descripcion.setText("");
        }else{
            //mostrarMensaje("Problemas al crear empresa");
        }
    }
    private boolean setEmpresa(){
        boolean creacion = false;
        if(txt_nit.getText().toString().equals("")){
            mostrarMensaje("EL campo nit es obligatorio");
        }else if(txt_nombre.getText().toString().equals("")){
            mostrarMensaje("EL campo nombre empresa es obligatorio");
        }else if(txt_razons.getText().toString().equals("")){
            mostrarMensaje("EL campo razon social es obligatorio");
        }else if(txt_sector.getText().toString().equals("")){
            mostrarMensaje("EL campo sector es obligatorio");
        }else if(txt_numempleados.getText().toString().equals("")){
            mostrarMensaje("EL campo numero de empleados es obligatorio");
        }else {
            Empresa e = new Empresa(0,txt_nit.getText().toString(),txt_nombre.getText().toString(),txt_razons.getText().toString(),
                                      txt_sector.getText().toString(),Integer.parseInt(txt_numempleados.getText().toString()),
                                      txt_descripcion.getText().toString());
            creacion = new DataEmpresa(getApplicationContext()).crearEmpresa(e);
        }
        return creacion;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_guardar){
            guardarEmpresa();
        }

        if(v.getId()==R.id.btn_listar){
            Intent i = new Intent();
            i.setClass(getApplicationContext(),ListEmepresa.class);
            startActivity(i);
            finish();
        }
    }


}
