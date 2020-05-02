package com.example.sqliteclass1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

public class VisitorController {
    private BaseDatos db;
    Date fecha = new Date();
    public VisitorController(Context context) {
        this.db = new BaseDatos(context);
    }

    public long agregarVisitante(Visitor e){
        try{

            SQLiteDatabase database = db.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(DefDB.col_codigo,e.getIdentification());
            valores.put(DefDB.col_nombre,e.getName());
            valores.put(DefDB.col_visitorType,e.getVisitorType());
            valores.put(DefDB.col_apartmentId,e.getApartmentId());
            valores.put(DefDB.col_checkinDate,fecha.toString());
            valores.put(DefDB.col_identification,e.getIdentification());

            long id = database.insert(DefDB.tabla_est,null,valores);
            return id;
        }
        catch (Exception ex){
            System.out.println("Error al insertar");
            return 0;
        }
    }

    public boolean buscarVisitor(String cod){
        String[] args = new String[] {cod};
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor c = database.query(DefDB.tabla_est, null, "codigo=?", args, null, null, null);
        if (c.getCount()>0) {
            database.close();
            return true;
        }
        else{
            database.close();
            return false;}

    }

    public long actualizarVisitante(Visitor e){

        try{
            SQLiteDatabase database = db.getWritableDatabase();
            ContentValues valores = new ContentValues();
            String[] args = new String[] {String.valueOf(e.getIdentification())};
            valores.put(DefDB.col_nombre,e.getName());
            valores.put(DefDB.col_visitorType,e.getVisitorType());
            valores.put(DefDB.col_apartmentId,e.getApartmentId());
            valores.put(DefDB.col_checkinDate,fecha.toString());
            long id = database.update(DefDB.tabla_est, valores,"codigo=?",args);
            database.close();
            return id;
        }
        catch (Exception ex){
            System.out.println("Error al actualizar");
            return 0;
        }

    }
    public Cursor allVisitors() {
        try {
            SQLiteDatabase database = db.getWritableDatabase();
            Cursor cur = database.rawQuery("select codigo as _id,* from visita", null);
            return cur;
        } catch (Exception ex) {
            System.out.println("Error al consultar");
            return null;
        }
    }

    public Visitor findVisitor(long cod){
        String[] args = new String[] {String.valueOf(cod).trim()};
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor c = database.query(DefDB.tabla_est, null, "codigo=?", args, null, null, null);
        if (c.getCount()>0) {
            c.moveToFirst();
            Visitor visitor = new Visitor(
                    c.getString(c.getColumnIndexOrThrow(DefDB.col_nombre)),
                    Long.parseLong(c.getString(c.getColumnIndexOrThrow(DefDB.col_identification))),
                    Integer.parseInt(c.getString(c.getColumnIndexOrThrow(DefDB.col_apartmentId))),
                    c.getString(c.getColumnIndexOrThrow(DefDB.col_visitorType)),
                    new Date());
            c.close();
            database.close();
            return visitor;
        }
        else{
            database.close();
            return null;}

    }
    public boolean deleteVisitor(long cod){
        try{
            SQLiteDatabase database = db.getWritableDatabase();

            String[] args = new String[] {String.valueOf(cod)};

            long id = database.delete(DefDB.tabla_est,"codigo=?",args);
            database.close();
            return true;
        }
        catch (Exception ex){
            System.out.println("Error al eliminar");
            return false;
        }
    }
}
