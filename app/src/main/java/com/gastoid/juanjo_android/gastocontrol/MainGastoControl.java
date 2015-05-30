package com.gastoid.juanjo_android.gastocontrol;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainGastoControl extends ActionBarActivity {

    SQLAdminPResupuesto dbconecionSueldo;
    SQLControlador dbconeccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Aqui accesamos a las demás activities

        setContentView(R.layout.activity_main_gasto_control);

        dbconecionSueldo = new SQLAdminPResupuesto(this);
        dbconecionSueldo.abrirBaseDeDatos();

        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBaseDeDatos();

        findViewById(R.id.b_presupuesto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainGastoControl.this, Act_Presupuesto.class));
            }
        });
        findViewById(R.id.b_nuevoGasto);

        findViewById(R.id.b_reporte).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainGastoControl.this, Act_Reporte.class));
            }
        });
    }

    public void presupuestoGasta(View view)
    {
        Cursor existe = dbconecionSueldo.leerDatos();
        if(existe.moveToFirst())
        {
            Intent gasto = new Intent(MainGastoControl.this, Act_Gasto.class);
            startActivity(gasto);
        }else
        {
            Toast.makeText(this, "Debe ingresar un Presupuesto primero.",Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarRegistros(View view)
    {
        Cursor sueldo = dbconecionSueldo.leerDatos();
        Cursor gastos = dbconeccion.existeBD();

        if(sueldo.moveToFirst())
        {
            dbconecionSueldo.eliminarTablaSueldo();
            if(gastos.moveToFirst())
            {

                dbconeccion.eliminarTablaGastos();
            }
            Toast.makeText(this, "Se han eliminado los registros.",Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(this, "No existen datos para eliminar.",Toast.LENGTH_SHORT).show();
        }

    }

    public void ValidaIngreso(View view)
    {
        Cursor existe = dbconecionSueldo.leerDatos();
        if(existe.moveToFirst())
        {
            Intent gasto = new Intent(MainGastoControl.this, Act_Ingreso.class);
            startActivity(gasto);
        }else
        {
            Toast.makeText(this, "Debe ingresar un Presupuesto primero.",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_gasto_control, menu);
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
