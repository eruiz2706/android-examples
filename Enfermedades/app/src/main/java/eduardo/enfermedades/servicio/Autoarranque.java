package eduardo.enfermedades.servicio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Eduardo on 18/06/2017.
 */

public class Autoarranque extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent service = new Intent(context, Miservicio.class);
        context.startService(service);
    }
}
