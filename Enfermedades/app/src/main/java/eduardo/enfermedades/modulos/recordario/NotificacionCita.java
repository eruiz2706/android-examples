package eduardo.enfermedades.modulos.recordario;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;

import eduardo.enfermedades.MainActivity;
import eduardo.enfermedades.R;
import eduardo.enfermedades.dao.AgendaDaoImpl;
import eduardo.enfermedades.interfaces.AgendaDao;
import eduardo.enfermedades.modelos.Agenda;

public class NotificacionCita extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacion_cita);

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        System.out.println("id vuelo detalle=>"+getIntent().getExtras().getString("notificationID"));
        String identificador=getIntent().getExtras().getString("notificationID");

        Agenda agenda = new Agenda();
        agenda.setId(Integer.parseInt(identificador));
        agenda.setRecordatorio(0);

        AgendaDaoImpl agendaDao=new AgendaDaoImpl(this);
        agendaDao.update(agenda);

        nm.cancel(Integer.parseInt(identificador));

        super.finish();
    }
}
