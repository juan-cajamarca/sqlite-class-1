package com.example.sqliteclass1;

import java.util.Date;

public class DefDB {

    public static final String nameDb = "visitasUAC.db";
    public static final String tabla_est = "visita";
    public static final String col_codigo = "codigo";
    public static final String col_nombre = "nombre";
    public static final String col_identification = "identification";
    public static final String col_apartmentId="apto";
    public static final String col_visitorType="tipo_visitante";
    public static final String col_checkinDate="fecha";

    public static final String create_tabla_est = "CREATE TABLE visita (" +
            "  codigo varchar(15) primary key," +
            "  nombre text," +
            "  identification int," +
            "  apto  text,"+
            "  tipo_visitante text,"+
            "  fecha text"+
            ");";

}
