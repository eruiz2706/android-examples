package com.app.presupuestosuniajc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import database.DataMenuCedulas;


public class MenuCedulas extends Activity implements View.OnClickListener {

    private ListView lista = null;
    private ImageButton btn_atras = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_menu_cedulas);

        btn_atras = (ImageButton) findViewById(R.id.atras);
        btn_atras.setOnClickListener(this);
        btn_atras = (ImageButton) findViewById(R.id.atras);
        btn_atras.setOnClickListener(this);
        lista = (ListView) findViewById(R.id.lista);

        String[][] datos = new DataMenuCedulas(getApplicationContext()).getMenuCedulas();
        AdacterMenuCedulas adacterMenuCedulas = new AdacterMenuCedulas(datos, getApplicationContext());
        lista.setAdapter(adacterMenuCedulas);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.atras) {
            setResult(3);
            finish();
        }
    }

    public class AdacterMenuCedulas extends BaseAdapter {
        private String[][] data = null;
        private Context context = null;
        LayoutInflater layoutInflater;

        public AdacterMenuCedulas(String[][] data, Context context) {
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

            convertView = layoutInflater.inflate(R.layout.adacter_menu_cedulas, null);
            TextView txt = (TextView) convertView.findViewById(R.id.texto);
            txt.setText(data[position][0]);
            ImageView imb = (ImageView) convertView.findViewById(R.id.logoc);
            //imb.setImageResource(Integer.parseInt("R.drawable." + data[position][1]));
            return convertView;
        }
    }
}


