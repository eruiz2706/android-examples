package eduardo.enfermedades.helpers.listgeneric;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import eduardo.enfermedades.R;

/**
 * Created by Eduardo on 21/05/2017.
 */

public class AdapterList2 extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<DatosList> items;

    public AdapterList2(Activity activity, ArrayList<DatosList> items) {
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

        TextView etiq1    =(TextView) view.findViewById(R.id.tv_etiq1_listageneric);
        etiq1.setText("Nombre");

        TextView dato1    =(TextView) view.findViewById(R.id.tv_dato1_listgeneric);
        dato1.setText(datos.getNombre());

        return view;
    }
}
