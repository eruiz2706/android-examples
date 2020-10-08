package com.app.presupuestosuniajc;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import database.DataEmpresa;

public class ListEmepresa extends AppCompatActivity {
    ListView lista      = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_emepresa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(getApplicationContext(),ActEmpresa.class);
                startActivity(i);
                finish();
            }
        });
        fab.setImageResource(R.drawable.plus64);
        fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#303F9F")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lista = (ListView) findViewById(R.id.lista_empresa);

        String[][] datos = new DataEmpresa(getApplicationContext()).getEmpresas();
        AdacterListaEmpresa adacterListaEmpresa = new AdacterListaEmpresa(datos, getApplicationContext());
        lista.setAdapter(adacterListaEmpresa);

    }

    public class AdacterListaEmpresa extends BaseAdapter {
        private String[][] data = null;
        private Context context = null;
        LayoutInflater layoutInflater;

        public AdacterListaEmpresa(String[][] data, Context context) {
            this.data = data;
            this.context = context;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return this.data.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = layoutInflater.inflate(R.layout.adacter_lista_empresa, null);
            TextView txt_nit = (TextView) convertView.findViewById(R.id.txt_nit);
            txt_nit.setText(data[position][0]);
            TextView txt_nombre = (TextView) convertView.findViewById(R.id.txt_nombre);
            txt_nombre.setText(data[position][1]);
            TextView txt_sector = (TextView) convertView.findViewById(R.id.txt_sector);
            txt_sector.setText(data[position][2]);
            ImageView imb = (ImageView) convertView.findViewById(R.id.logoc);
            return convertView;
        }
    }

}
