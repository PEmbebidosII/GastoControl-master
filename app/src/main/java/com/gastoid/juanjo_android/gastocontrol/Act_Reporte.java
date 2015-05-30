package com.gastoid.juanjo_android.gastocontrol;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Act_Reporte extends ActionBarActivity
{

    ListView lista;
    SQLControlador dbconeccion;
    SQLAdminPResupuesto dbconecionSueldo;
    TextView tv_miemID, tv_miemNombre, tv_costo, tv_desc,tv_reporteIngreso;
    private Cursor fila;
    int id;

    static double Estimate=10;
    static double Contador=0;
    static String[] arreglo=new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_reporte);

        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBaseDeDatos();
        dbconecionSueldo = new SQLAdminPResupuesto(this);
        dbconecionSueldo.abrirBaseDeDatos();

        lista = (ListView) findViewById(R.id.listView);
        tv_reporteIngreso = (TextView) findViewById(R.id.tv_reporteIngreso);


        fila = dbconecionSueldo.leerDatos();

        if(fila.moveToFirst())
        {
            tv_reporteIngreso.setText("Presupuesto actual: " + fila.getString(1));
        }else
        {
            tv_reporteIngreso.setText("No se ha ingresado un presupuesto");
        }


        Cursor cursor = dbconeccion.leerDatos();

        String [] from = new String[]
                {
                        DBhelper.ID,
                        DBhelper.NOMBRE,
                        DBhelper.COSTO,
                        DBhelper.DESCRIPCION
                };
        int[] to = new int[]
                {
                        R.id.gastos_id,
                        R.id.miembro_nombre,
                        R.id.costo,
                        R.id.desc
                };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(Act_Reporte.this, R.layout.formato_fila, cursor,  from, to);

        adapter.notifyDataSetChanged();
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                tv_miemID = (TextView) view.findViewById(R.id.gastos_id);
                tv_miemNombre = (TextView) view.findViewById(R.id.miembro_nombre);
                tv_costo = (TextView) view.findViewById(R.id.costo);
                tv_desc = (TextView) view.findViewById(R.id.desc);

                String aux_miembroId = tv_miemID.getText().toString();
                String aux_miembroNombre = tv_miemNombre.getText().toString();
                String aux_desc = tv_desc.getText().toString();
                String aux_costo = tv_costo.getText().toString();

                Intent modify_intent;
                modify_intent = new Intent(getApplicationContext(),Act_info_gasto.class);
                modify_intent.putExtra("miembroId", aux_miembroId);
                modify_intent.putExtra("miembroNombre", aux_miembroNombre);
                modify_intent.putExtra("desc", aux_desc);
                modify_intent.putExtra("costo", aux_costo);
                startActivity(modify_intent);
            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_reporte, menu);
        return true;
    }

    public void Act_info_gasto()
    {
        Intent intent = new Intent(this, Act_info_gasto.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            Act_info_gasto();
        }

        return super.onOptionsItemSelected(item);
    }
}
