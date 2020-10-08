package eduardovue.vuelo;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Detallevuelo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallevuelo);

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        System.out.println("id vuelo detalle=>"+getIntent().getExtras().getInt("notificationID"));

        int identificador=getIntent().getExtras().getInt("notificationID");
        nm.cancel(identificador);

        ContentValues values =new ContentValues();
        values.put("estado","notificado");

        ImpVuelos impVuelos = new ImpVuelos(this);
        Vuelo vuelo =impVuelos.findbyId(identificador);
        impVuelos.update(identificador,values);

        TextView v_origen   =(TextView)findViewById(R.id.v_origen);
        v_origen.setText(vuelo.getOrigen());

        TextView v_destino   =(TextView)findViewById(R.id.v_destino);
        v_destino.setText(vuelo.getDestino());

        TextView v_fechaida   =(TextView)findViewById(R.id.v_fechaida);
        v_fechaida.setText(vuelo.getFechaorigen());

        TextView v_fecharegreso   =(TextView)findViewById(R.id.v_fecharegreso);
        v_fecharegreso.setText(vuelo.getFechadestino());
        nm.cancel(1);

    }
}
