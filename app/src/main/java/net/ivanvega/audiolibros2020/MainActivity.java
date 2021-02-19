package net.ivanvega.audiolibros2020;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SelectorFragment selectorFragment
                = new SelectorFragment();

        if ( findViewById(R.id.contenedor_pequeno) != null    &&
                getSupportFragmentManager().findFragmentById(R.id.contenedor_pequeno) == null
        ){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenedor_pequeno,
                            selectorFragment).commit();
        }
        //AQUI REVISO SI LE MANDARON PARAMETROS A ESTA ACTIVIDAD
        //EN ESPECIAL REVISO SI LA ACTIVIDAD LA INVOCO LA NOTIFICACION LANZADA POR EL SERVICIO DE PRIMER PLANO
        //
        if(getIntent().getExtras()!=null){
            Log.d("MSPPN", getIntent().getExtras().getString("rep","no se encontro parametro"));
            Log.d("MSPPN", getIntent().getExtras().getString("id","no se encontro parametro"));
            int id = getIntent().getExtras().getInt("id",-1);
            mostrarDetalle(id, true);
        }


    }
    DetalleFragment detalleFragment;

    public void mostrarDetalle(int index, boolean comesFromNotification){
        if(!comesFromNotification) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if(fragmentManager.findFragmentById(R.id.detalle_fragment) != null){
                DetalleFragment fragment_detalle = (DetalleFragment) fragmentManager.findFragmentById(R.id.detalle_fragment);
                fragment_detalle.ponInfoLibro(index);
            } else {
                detalleFragment = new DetalleFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(DetalleFragment.ARG_ID_LIBRO, index);
                detalleFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.contenedor_pequeno, detalleFragment).addToBackStack(null).commit();
            }
        }else{
            FragmentManager fragmentManager = getSupportFragmentManager();
            detalleFragment = new DetalleFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(DetalleFragment.ARG_ID_LIBRO, index);
            bundle.putBoolean("Service", true);
            detalleFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.contenedor_pequeno, detalleFragment).addToBackStack(null).commit();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detalleFragment.stopService();
    }
}