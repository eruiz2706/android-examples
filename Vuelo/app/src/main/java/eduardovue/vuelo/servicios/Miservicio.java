package eduardovue.vuelo.servicios;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import eduardovue.vuelo.Detallevuelo;
import eduardovue.vuelo.ImpVuelos;
import eduardovue.vuelo.R;
import eduardovue.vuelo.Vuelo;

/**
 * Created by Eduardo on 05/06/2017.
 */

public class Miservicio extends Service {
    MyTask myTask;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Toast.makeText(this, "Servicio creado!", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Alarma Activada", Toast.LENGTH_SHORT).show();
        myTask = new MyTask();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        myTask.execute();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this,"Servicio Terminado",Toast.LENGTH_LONG).show();
        myTask.cancel(true);
        //Toast.makeText(this,"Alarma Terminada",Toast.LENGTH_LONG).show();

    }
    private class MyTask extends AsyncTask<String, String, String> {

        private boolean continuar;
        String  fecha;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //dateFormat = new SimpleDateFormat("HH:mm:ss");
            continuar= true;
        }

        @Override
        protected String doInBackground(String... params) {
            while (continuar){
                try {
                    publishProgress();
                    Thread.sleep(20000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Calendar calendar=Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR,1);
            int dia =calendar.get(Calendar.DAY_OF_MONTH);
            int mes =calendar.get(Calendar.MONTH)+1;
            int anio=calendar.get(Calendar.YEAR);
            fecha   =anio+"-"+mes+"-"+dia;

            ImpVuelos impVuelos =new ImpVuelos(getApplicationContext());
            ArrayList<Vuelo> items=impVuelos.findAllPendientes();

            for(int i=0;i<items.size();i++){
                Vuelo vuelo =items.get(i);

                System.out.println("id=>"+vuelo.getId()+"origen=>"+vuelo.getOrigen()+" destino=>"+vuelo.getDestino()+" sistema=>"+fecha+" fecha=>"+vuelo.getFechaorigen());
                if(fecha.equals(vuelo.getFechaorigen())){
                    String mensaje="destino :"+vuelo.getDestino();

                    /*NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    NotificationCompat.Builder notificationManager = new NotificationCompat.Builder(
                            getBaseContext())
                            .setSmallIcon(android.R.drawable.ic_dialog_info)
                            .setContentTitle("VUELO IDA MAÑANA")
                            .setContentText(mensaje)
                            .setWhen(System.currentTimeMillis());
                    nManager.notify(vuelo.getId(), notificationManager.build());*/

                    Intent intent = new Intent(getApplicationContext(), Detallevuelo.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("notificationID",vuelo.getId());

                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent,PendingIntent.FLAG_ONE_SHOT);
                    NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

                    CharSequence ticker ="Click si ha sigo Notificado";
                    CharSequence contentTitle = "VUELO IDA MAÑANA";
                    CharSequence contentText = mensaje;
                    Notification noti = new NotificationCompat.Builder(getApplicationContext())
                            .setContentIntent(pendingIntent)
                            .setTicker(ticker)
                            .setContentTitle(contentTitle)
                            .setContentText(contentText)
                            .setSmallIcon(R.drawable.ic_flight)
                            .addAction(R.drawable.ic_flight, ticker, pendingIntent)
                            .setVibrate(new long[] {100, 250, 100, 500})
                            .build();
                    nm.notify(1, noti);

                    //.setVibrate(new long[] {100, 250, 100, 500})
                }

                if(fecha.equals(vuelo.getFechadestino())){
                    String mensaje="destino :"+vuelo.getDestino();

                    Intent intent = new Intent(getApplicationContext(), Detallevuelo.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("notificationID",vuelo.getId());

                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent,PendingIntent.FLAG_ONE_SHOT);
                    NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);


                    CharSequence ticker ="Click si ha sigo Notificado";
                    CharSequence contentTitle = "VUELO REGRESO MAÑANA";
                    CharSequence contentText = mensaje;
                    Notification noti = new NotificationCompat.Builder(getApplicationContext())
                            .setContentIntent(pendingIntent)
                            .setTicker(ticker)
                            .setContentTitle(contentTitle)
                            .setContentText(contentText)
                            .setSmallIcon(R.drawable.ic_flight)
                            .addAction(R.drawable.ic_flight, ticker, pendingIntent)
                            .setVibrate(new long[] {100, 250, 100, 500})
                            .build();
                    nm.notify(2, noti);
                }

            }


        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            continuar= false;
        }
    }
}
