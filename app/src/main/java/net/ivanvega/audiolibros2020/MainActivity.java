package net.ivanvega.audiolibros2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new GridLayoutManager(this,2);

        recyclerView.setLayoutManager(layoutManager);

        AdaptadorLibros adaptadorLibros =
                new AdaptadorLibros(this , Libro.ejemploLibros());

        recyclerView.setAdapter(adaptadorLibros);


    }
}