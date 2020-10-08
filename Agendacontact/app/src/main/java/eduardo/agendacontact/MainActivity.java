package eduardo.agendacontact;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.getbase.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    FloatingActionButton fab_cear;
    FloatingActionButton fab_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab_cear = (FloatingActionButton) findViewById(R.id.fab_cear);
        fab_cear.setOnClickListener(this);

        fab_list = (FloatingActionButton) findViewById(R.id.fab_list);
        fab_list.setOnClickListener(this);

        openListcontact();
    }

    @Override
    public void onClick(View v) {

        if(R.id.fab_cear==v.getId()){
            openNewcontact();
        }
        if(R.id.fab_list==v.getId()){
            openListcontact();
        }
    }

    public void openListcontact(){

        RelativeLayout rl =(RelativeLayout) findViewById(R.id.container_layout);
        rl.removeAllViews();

        ListaFragment fragment    =new ListaFragment();
        android.app.FragmentManager fragmentmanager =getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction =fragmentmanager.beginTransaction();

        fragmentTransaction.add(R.id.container_layout,fragment);
        fragmentTransaction.commit();
    }

    public void openNewcontact(){

        RelativeLayout rl =(RelativeLayout) findViewById(R.id.container_layout);
        rl.removeAllViews();

        CreateFragment      fragment    =new CreateFragment();
        android.app.FragmentManager fragmentmanager =getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction =fragmentmanager.beginTransaction();

        fragmentTransaction.add(R.id.container_layout,fragment);
        fragmentTransaction.commit();
    }
}
