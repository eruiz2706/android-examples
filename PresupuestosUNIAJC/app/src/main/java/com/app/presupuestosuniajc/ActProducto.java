package com.app.presupuestosuniajc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import control.CrearImagen;
import database.DataEmpresa;
import database.DataProducto;
import modelo.Producto;

public class ActProducto extends AppCompatActivity implements View.OnClickListener{
    static final int REQUEST_TAKE_PHOTO     = 1;
//    private String mCurrentPhotoPath        = null;
    private ImageButton btn_listar          = null;
    private ImageButton btn_guardar         = null;
    private ImageView vista_foto            = null;
    private FloatingActionButton btn_foto   = null;
    private Spinner spn_empresa             = null;
    EditText txt_descripcion                = null;
    EditText txt_nombre                     = null;
    String nameImg                          ="";
    EditText txt_precio                     = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        vista_foto =(ImageView)findViewById(R.id.vista_foto);
        btn_foto = (FloatingActionButton)findViewById(R.id.btn_foto);
        btn_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 nameImg = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                startActivityForResult(new CrearImagen(getApplicationContext(),nameImg).dispatchTakePictureIntent(), REQUEST_TAKE_PHOTO);
            }
        });

        btn_listar      =(ImageButton)findViewById(R.id.btn_listar);
        btn_listar.setOnClickListener(this);
        btn_guardar     =(ImageButton)findViewById(R.id.btn_guardar);
        btn_guardar.setOnClickListener(this);

        txt_nombre      =(EditText) findViewById(R.id.txt_nombre);
        txt_descripcion =(EditText) findViewById(R.id.txt_descripcion);
        txt_precio      =(EditText) findViewById(R.id.txt_precio);
        spn_empresa     =(Spinner)findViewById(R.id.spn_empresa);


        DataEmpresa em =new DataEmpresa(getApplicationContext());

           String[] e = new String[]{" ","1","2"};
        if(e == null){
            mostrarMensaje("Debe crear primero una empresa");
            Intent i = new Intent();
            i.setClass(getApplicationContext(),ListEmepresa.class);
            startActivity(i);
            finish();
        }else {
            String[] data_l = new String[e.length];
            for (int i = 0; i < e.length; i++) {
                data_l[i] = e[i];
            }

            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,data_l);
            spn_empresa.setAdapter(adapter);
        }

    }
    private void mostrarMensaje(String mensaje){
        Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_SHORT).show();
    }
    private void guardarProducto() {
        if(setProducto()){
            mostrarMensaje("Se creo el producto de manera exitosa !!!");
            txt_nombre.setText("");
            txt_descripcion.setText("");
            txt_precio.setText("0");
            spn_empresa.setSelection(0);
        }else{
            //mostrarMensaje("Problemas al crear empresa");
        }
    }
    private boolean setProducto(){
        boolean creacion = false;
        if(txt_nombre.getText().toString().equals("")){
            mostrarMensaje("EL campo nombre producto es obligatorio");
        }else if(txt_precio.getText().toString().trim().equals("")){
            mostrarMensaje("EL campo precio es obligatorio");
        }else if(spn_empresa.getSelectedItem().toString().trim().equals("")){
            mostrarMensaje("EL campo empresa es obligatorio");
        }else {//int codigo, String nombre, String descripcion, int empresa, String foto
            Producto e = new Producto(0,txt_nombre.getText().toString(),txt_descripcion.getText().toString(),
                    spn_empresa.getSelectedItemPosition(),txt_descripcion.getText().toString(),Float.parseFloat(txt_precio.getText().toString()));
            creacion = new DataProducto(getApplicationContext()).crearProducto(e);
        }
        return creacion;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO) {
            Bundle extras = data.getExtras();

            File file = (File) extras.get("data");
            System.out.print(file.getPath());

            Bitmap imageBitmap = (Bitmap) extras.get("data");
            vista_foto.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_guardar){
            guardarProducto();
        }

        if(v.getId()==R.id.btn_listar){
            Intent i = new Intent();
            i.setClass(getApplicationContext(),ListProducto.class);
            startActivity(i);
            finish();
        }
    }
}
