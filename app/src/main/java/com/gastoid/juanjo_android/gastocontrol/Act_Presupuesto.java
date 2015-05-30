package com.gastoid.juanjo_android.gastocontrol;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;


public class Act_Presupuesto extends Activity
{

    private EditText edita_presupuesto;
    SQLAdminPResupuesto dbconeccionSueldo;

    SQLControlador dbconeccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__presupuesto);

        edita_presupuesto = (EditText) findViewById(R.id.et_presupuesto);

        dbconeccionSueldo = new SQLAdminPResupuesto(this);
        dbconeccionSueldo.abrirBaseDeDatos();

        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBaseDeDatos();
    }

    public void Agregar(View v)
    {

        String presupuesto = edita_presupuesto.getText().toString();
        //float presupuesto = Float.parseFloat(presupuestoS);
        //bd.delete("Presupuesto", "id=" + id, null);

        dbconeccionSueldo.insertarDatos(presupuesto);

        Cursor existe = dbconeccion.leerDatos();
        if(existe.moveToFirst())
            dbconeccion.eliminarTablaGastos();

        dbconeccion.cerrar();
        dbconeccionSueldo.cerrar();

        Intent main = new Intent(Act_Presupuesto.this, MainGastoControl.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(main);
        Toast.makeText(this, "Se guardo el presupuesto",
                Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act__presupuesto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}//Main
