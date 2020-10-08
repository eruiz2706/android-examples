package com.app.presupuestosuniajc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MenuPrincipal extends Activity implements View.OnClickListener{
    private ImageButton btn_empresa         = null;
    private ImageButton btn_producto        = null;
    private ImageButton btn_cedulas         = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        btn_empresa = (ImageButton) findViewById(R.id.btn_empresa);
        btn_empresa.setOnClickListener(this);
        btn_producto = (ImageButton) findViewById(R.id.btn_producto);
        btn_producto.setOnClickListener(this);
        btn_cedulas = (ImageButton) findViewById(R.id.btn_cedulas);
        btn_cedulas.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_empresa){
            Intent i = new Intent();
            i.setClass(getApplicationContext(),ListEmepresa.class);
            startActivityForResult(i,1);
        }
        if(v.getId() == R.id.btn_producto){
            Intent i = new Intent();
            i.setClass(getApplicationContext(),ListProducto.class);
            startActivityForResult(i,3);
        }
        if(v.getId() == R.id.btn_cedulas){
            Intent i = new Intent();
            i.setClass(getApplicationContext(),MenuCedulas.class);
            startActivityForResult(i,3);
        }
    }
}
