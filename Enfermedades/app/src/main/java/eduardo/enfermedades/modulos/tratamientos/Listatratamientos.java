package eduardo.enfermedades.modulos.tratamientos;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eduardo.enfermedades.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Listatratamientos extends Fragment {

    View view;

    public Listatratamientos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view    = inflater.inflate(R.layout.fragment_listatratamientos, container, false);

        return view;
    }

}
