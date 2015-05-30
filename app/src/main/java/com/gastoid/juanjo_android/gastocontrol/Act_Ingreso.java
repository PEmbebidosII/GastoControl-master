package com.gastoid.juanjo_android.gastocontrol;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Act_Ingreso extends ActionBarActivity {

    EditText et_ingreso;

    SQLAdminPResupuesto dbconecionSueldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__ingreso);

        dbconecionSueldo = new SQLAdminPResupuesto(this);
        dbconecionSueldo.abrirBaseDeDatos();

        et_ingreso = (EditText) findViewById(R.id.et_ingreso);


    }

    public void AgregarIngreso(View view)
    {
        Cursor fila = dbconecionSueldo.leerDatos();
        String ingreso = et_ingreso.getText().toString();
        float presupuesto;

        fila.moveToFirst();
        String presupuestoS = fila.getString(1);
        presupuesto = Float.parseFloat(presupuestoS);
        float ingresoF = Float.parseFloat(ingreso);

        presupuesto = presupuesto + ingresoF;

        dbconecionSueldo.insertarDatos(Float.toString(presupuesto));
        dbconecionSueldo.cerrar();

        Intent main = new Intent(Act_Ingreso.this, MainGastoControl.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(main);
        Toast.makeText(this, "Se actualizo el presupuesto",
                Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act__ingreso, menu);
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
}
