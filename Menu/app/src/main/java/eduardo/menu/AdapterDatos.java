package eduardo.menu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Eduardo on 10/04/2017.
 */

public class AdapterDatos extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Datos> items;

    public AdapterDatos(Activity activity, ArrayList<Datos> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view   =convertView;
        if(convertView==null){
            LayoutInflater inf =(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view    =inf.inflate(R.layout.itemslista,null);
        }

        Datos datos =items.get(position);

        ImageView foto  =(ImageView) view.findViewById(R.id.imageView);
        foto.setImageDrawable(datos.getFoto());

        TextView nombre    =(TextView) view.findViewById(R.id.txv_nombre);
        nombre.setText("Nombre :"+datos.getNombre());

        TextView documento    =(TextView) view.findViewById(R.id.txv_documento);
        documento.setText("Documento :"+datos.getDocumento());

        TextView fechan    =(TextView) view.findViewById(R.id.txv_fechan);
        fechan.setText("Fecha Nac :"+datos.getFechan());

        return view;
    }
}
