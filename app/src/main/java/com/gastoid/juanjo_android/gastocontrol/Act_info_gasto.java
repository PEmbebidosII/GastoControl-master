package com.gastoid.juanjo_android.gastocontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Act_info_gasto extends Activity implements OnClickListener
{

    TextView tv_nombre, tv_desc, tv_costo;
    /*EditText et_costo;

    Button btnActualizar;*/

    long member_id;

    SQLControlador dbcon;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_gasto);

        dbcon = new SQLControlador(this);
        dbcon.abrirBaseDeDatos();

        tv_costo = (TextView) findViewById(R.id.tv_costo);
        tv_desc = (TextView) findViewById(R.id.tv_desc);
        tv_nombre = (TextView) findViewById(R.id.tv_nombre);
        /*btnActualizar = (Button) findViewById(R.id.btn_actualiza);*/

        Intent i = getIntent();
        String memberID = i.getStringExtra("miembroId");
        String memberName = i.getStringExtra("miembroNombre");
        String memberDesc = i.getStringExtra("desc");
        String memberCosto = i.getStringExtra("costo");

        member_id = Long.parseLong(memberID);

        tv_nombre.setText("Nombre: " + memberName);
        tv_desc.setText("Descripcion: " + memberDesc);
        tv_costo.setText(memberCosto);

        /*btnActualizar.setOnClickListener(this);*/

    } //onCreate

    @Override
    public void onClick(View v)
    {
        /*switch (v.getId())
        {
            case R.id.btn_actualiza:
                String newCosto = et_costo.getText().toString();
                dbcon.actualizarDatos(member_id, newCosto);
                this.returnHome();
                break;
        }*/
    } //onClick



    public void returnHome()
    {

        Intent home_intent = new Intent(getApplicationContext(),Act_Reporte.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
} //Main
