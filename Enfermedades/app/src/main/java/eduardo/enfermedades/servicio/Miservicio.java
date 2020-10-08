package eduardo.enfermedades.servicio;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import eduardo.enfermedades.R;
import eduardo.enfermedades.dao.AgendaDaoImpl;
import eduardo.enfermedades.helpers.listgeneric.DatosList;
import eduardo.enfermedades.modelos.Agenda;
import eduardo.enfermedades.modulos.recordario.NotificacionCita;

/**
 * Created by Eduardo on 18/06/2017.
 */

public class Miservicio extends Service {
    MyTask myTask;

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
        String  fechasistema;


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
            int dia =calendar.get(Calendar.DAY_OF_MONTH);
            int mes =calendar.get(Calendar.MONTH)+1;
            int anio=calendar.get(Calendar.YEAR);
            int hora=calendar.get(Calendar.HOUR_OF_DAY);
            int minuto=calendar.get(Calendar.MINUTE);
            fechasistema   =anio+"-"+mes+"-"+dia;
            String  horasistema=hora+""+minuto;

            AgendaDaoImpl agendaDao =new AgendaDaoImpl(getApplicationContext());
            ArrayList<Agenda> items=new AgendaDaoImpl(getApplicationContext()).findAllAlert2();

            for(int i=0;i<items.size();i++){
                Agenda agenda =items.get(i);

                if(agenda.getFechar().equals(fechasistema)){

                    String horar    =agenda.getHorar().replace(":", "");

                    if(Integer.parseInt(horasistema)>=Integer.parseInt(horar)){

                        Intent intent = new Intent(getApplicationContext(), NotificacionCita.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("notificationID",String.valueOf(agenda.getId()));
                        System.out.println("vuelo=>"+agenda.getId());

                        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),agenda.getId(), intent,PendingIntent.FLAG_ONE_SHOT);
                        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

                        String mensaje  ="Fecha y hora :"+agenda.getFecha()+" "+agenda.getHora()+"Descripcion :"+agenda.getDescripcion();

                        CharSequence contentTitle = "ALERTA CITA MEDICA";
                        Notification noti = new NotificationCompat.Builder(getApplicationContext())
                                .setContentTitle(contentTitle)
                                .setSmallIcon(R.drawable.ic_alerta)
                                .setStyle(
                                        new NotificationCompat.BigTextStyle()
                                                .bigText("fecha y hora :" +agenda.getFecha()+" "+agenda.getHora()+"                        "+
                                                        " descripcion : " +agenda.getDescripcion()))
                                .addAction(R.drawable.ic_alarm_off,
                                        "DESACTIVAR", pendingIntent)
                                .setVibrate(new long[] {100, 250, 100, 500})
                                .build();
                        nm.notify(agenda.getId(), noti);
                    }
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
