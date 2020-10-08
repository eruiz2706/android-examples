package eduardo.enfermedades;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import eduardo.enfermedades.db.DbHelper;
import eduardo.enfermedades.modulos.agenda.Listaagenda;
import eduardo.enfermedades.modulos.agenda.Listatratamientos;
import eduardo.enfermedades.modulos.diagnosticos.Listadiagnosticos;
import eduardo.enfermedades.modulos.enfermedades.Listaenfermedades;
import eduardo.enfermedades.modulos.medicamentos.Listamedicamentos;
import eduardo.enfermedades.modulos.medicos.Listamedicos;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Enfermedades Cronicas");

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment    fragment    =null;
        boolean     selected    =false;
        int id = item.getItemId();

        if (id == R.id.nav_agendacitas) {
            selected    =true;
            fragment    =new Listaagenda();

        } else if (id == R.id.nav_diagonosticos) {
            selected    =true;
            fragment    =new Listadiagnosticos();

        } else if (id == R.id.nav_tratamientos) {
            selected    =true;
            fragment    =new Listatratamientos();

        } else if (id == R.id.nav_alertacitas) {
            Toast.makeText(getApplicationContext(), "Pendiente por asignar fragment", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_alertamedicamento) {
            Toast.makeText(getApplicationContext(), "Pendiente por asignar fragment", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_medicos) {
            selected    =true;
            fragment    =new Listamedicos();

        } else if (id == R.id.nav_medicamentos) {
            selected    =true;
            fragment    =new Listamedicamentos();

        } else if (id == R.id.nav_enfermedades) {
            selected    =true;
            fragment    =new Listaenfermedades();
        }

        if(selected==true){
            android.app.FragmentManager fragmentmanager =getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction =fragmentmanager.beginTransaction();
            fragmentTransaction.replace(R.id.container_layout,fragment);
            fragmentTransaction.commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
