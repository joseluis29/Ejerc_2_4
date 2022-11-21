package com.example.ejerc_2_4;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejerc_2_4.OBJ.Adaptador;
import com.example.ejerc_2_4.OBJ.Signatures;
import com.example.ejerc_2_4.SQLiteConexion.SQLiteConexion;
import com.example.ejerc_2_4.Transacciones.Transacciones;


import java.util.ArrayList;
import java.util.List;

public class ActivityListaFirma extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adaptador reviewadapter;
    ArrayList<Signatures> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firma);

        recyclerView = findViewById(R.id.viewRe);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reviewadapter = new Adaptador(ObtenerFirma());
        recyclerView.setAdapter(reviewadapter);
    }

    private List<Signatures> ObtenerFirma() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDataBase,null,1);
        SQLiteDatabase db= conexion.getReadableDatabase();
        Signatures firma;
        lista= new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.tablaFirma, null);

        while(cursor.moveToNext())
        {
            firma = new Signatures();
            firma.setId(cursor.getInt(0));
            firma.setDescripcion(cursor.getString(2));
            firma.setFirma(cursor.getBlob(1));
            lista.add(firma);
        }
        cursor.close();
        return lista;
    }
}