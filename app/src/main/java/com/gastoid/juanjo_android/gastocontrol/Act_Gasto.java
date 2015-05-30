package com.gastoid.juanjo_android.gastocontrol;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class Act_Gasto extends Activity implements OnClickListener
{

    SQLAdminPResupuesto dbconecionSueldo;

    EditText et_nombre,et_desc, et_gasto;
    Button btn_guardar;

    SQLControlador dbconeccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__gasto);

        dbconecionSueldo = new SQLAdminPResupuesto(this);
        dbconecionSueldo.abrirBaseDeDatos();

        et_nombre = (EditText) findViewById(R.id.et_gasto);
        et_desc = (EditText) findViewById(R.id.et_descrip);
        et_gasto = (EditText) findViewById(R.id.et_montogasto);
        btn_guardar = (Button) findViewById(R.id.b_guardaGasto);

        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBaseDeDatos();
        btn_guardar.setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.b_guardaGasto:
                float presupuesto, costoF;
                String name = et_nombre.getText().toString();
                String costo = et_gasto.getText().toString();
                String desc = et_desc.getText().toString();
                dbconeccion.insertarDatos(name,costo,desc);
                Cursor fila = dbconecionSueldo.leerDatos();

                fila.moveToFirst();
                String presupuestoS = fila.getString(1);
                presupuesto = Float.parseFloat(presupuestoS);
                costoF = Float.parseFloat(costo);

                presupuesto = presupuesto - costoF;

                dbconecionSueldo.insertarDatos(Float.toString(presupuesto));
                dbconecionSueldo.cerrar();
                dbconeccion.cerrar();
                Intent main = new Intent(Act_Gasto.this, MainGastoControl.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;

            default:
                break;
        }

    }
}
