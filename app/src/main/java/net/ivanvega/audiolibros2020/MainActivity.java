package net.ivanvega.audiolibros2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SelectorFragment selectorFragment
                = new SelectorFragment();

        if ( findViewById(R.id.contenedor_pequeno) != null        ){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenedor_pequeno,
                            selectorFragment).commit();
        }

    }
}