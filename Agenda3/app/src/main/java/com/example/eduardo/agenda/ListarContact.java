package com.example.eduardo.agenda;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListarContact extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lista;
    int posit   =-1;
    ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contact);

        lista               = (ListView)findViewById(R.id.listView);
        itemsAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new Agenda(getApplicationContext()).lista());
        lista.setAdapter(itemsAdapter);

        lista.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        posit   =position;
        CharSequence option[] = new CharSequence[] {"Editar", "Eliminar"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Opciones");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 Intent resultIntent = new Intent();

                /*Editar*/
                if(which==0){
                    resultIntent.putExtra("position",posit);
                    resultIntent.putExtra("operation","edit");
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }

                /*Eliminar*/
                if(which==1){
                    Agenda      agenda         =new Agenda(getApplicationContext());
                    agenda.delete(agenda.getid(posit));
                    Toast.makeText(getApplicationContext(), "Se ha Elimiado correctamente", Toast.LENGTH_SHORT).show();


                }
            }
        });
        builder.show();


    }


}
