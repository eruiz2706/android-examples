package eduardovue.vuelo.listas;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import eduardovue.vuelo.R;

/**
 * Created by Eduardo on 05/06/2017.
 */

public class AdapterList extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<DatosList> items;

    public AdapterList(Activity activity, ArrayList<DatosList> items) {
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
            view    =inf.inflate(R.layout.layout_listgeneric,null);
        }

        DatosList datos =items.get(position);

        ImageView foto  =(ImageView) view.findViewById(R.id.image_listgeneric);
        foto.setImageDrawable(datos.getFoto());

        TextView dato1    =(TextView) view.findViewById(R.id.valor1);
        dato1.setText(datos.getOrigen());

        TextView dato2    =(TextView) view.findViewById(R.id.valor2);
        dato2.setText(datos.getDestino());

        TextView dato3    =(TextView) view.findViewById(R.id.valor3);
        dato3.setText(datos.getFechaida());

        TextView dato4    =(TextView) view.findViewById(R.id.valor4);
        dato4.setText(datos.getFecharegreso());

        TextView dato5    =(TextView) view.findViewById(R.id.valor5);
        dato5.setText(datos.getEstado());

        return view;
    }
}
