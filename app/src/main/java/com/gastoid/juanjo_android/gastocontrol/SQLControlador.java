package com.gastoid.juanjo_android.gastocontrol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Marianela on 29/05/2015.
 */
public class SQLControlador
{

    private DBhelper dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;


    public SQLControlador(Context c) {
        ourcontext = c;
    }

    public SQLControlador abrirBaseDeDatos() throws SQLException {
        dbhelper = new DBhelper(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void cerrar() {
        dbhelper.close();
    }

    public void insertarDatos(String name,String costo,String desc) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.NOMBRE, name);
        cv.put(DBhelper.COSTO,costo);
        cv.put(DBhelper.DESCRIPCION,desc);
        database.insert(DBhelper.TABLE_MEMBER, null, cv);
    }

    public Cursor leerDatos() {
        String[] todasLasColumnas = new String[] {
                DBhelper.ID,
                DBhelper.NOMBRE,
                DBhelper.COSTO,
                DBhelper.DESCRIPCION
        };
        Cursor c = database.query(DBhelper.TABLE_MEMBER, todasLasColumnas, null,
                null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor existeBD()
    {
        Cursor c = database.rawQuery("select * from miembros", null);
        if (c != null) {
            return c;
        }
        return c;
    }

    public void eliminarTablaGastos()
    {
        database.delete(DBhelper.TABLE_MEMBER, null,null);
    }

    public int actualizarDatos(long Id, String newCosto) {
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DBhelper.COSTO, newCosto);
        int i = database.update(DBhelper.TABLE_MEMBER, cvActualizar,
                DBhelper.ID + " = " + Id, null);
        return i;
    }

    public void deleteData(long Id) {
        database.delete(DBhelper.TABLE_MEMBER, DBhelper.ID + "="
                + Id, null);
    }
}
