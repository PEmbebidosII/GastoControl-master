package com.gastoid.juanjo_android.gastocontrol;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Marianela on 29/05/2015.
 */
public class SQLAdminPResupuesto
{

    private DBAdminhelper dbadminhelper;
    private Context ourcontext;
    private SQLiteDatabase database;


    public SQLAdminPResupuesto(Context c) {
        ourcontext = c;
    }

    public SQLAdminPResupuesto abrirBaseDeDatos() throws SQLException {
        dbadminhelper = new DBAdminhelper(ourcontext);
        database = dbadminhelper.getWritableDatabase();
        return this;
    }


    public void cerrar() {
        dbadminhelper.close();
    }

    public void insertarDatos(String presupuesto) {
        ContentValues cv = new ContentValues();
        cv.put(DBAdminhelper.PRESUPUESTO, presupuesto);
        database.insert(DBAdminhelper.TABLE_MEMBER, null, cv);
    }

    public Cursor leerDatos() {
        String[] todasLasColumnas = new String[] {
                DBAdminhelper.ID,
                DBAdminhelper.PRESUPUESTO
        };
        Cursor c = database.rawQuery("select * from sueldo order by id desc", null);
        if (c != null) {
            return c;
        }
        return c;
    }

    public void eliminarTablaSueldo()
    {
        database.delete(DBAdminhelper.TABLE_MEMBER, null,null);
    }

    public int actualizarDatos(long Id, String newPresupuesto) {
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DBAdminhelper.PRESUPUESTO, newPresupuesto);
        int i = database.update(DBhelper.TABLE_MEMBER, cvActualizar,
                DBhelper.ID + " = " + Id, null);
        return i;
    }

    public void deleteData(long Id) {
        database.delete(DBhelper.TABLE_MEMBER, DBhelper.ID + "="
                + Id, null);
    }
}