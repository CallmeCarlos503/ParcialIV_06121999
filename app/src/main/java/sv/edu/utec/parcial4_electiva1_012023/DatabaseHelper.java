package sv.edu.utec.parcial4_electiva1_012023;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String Nombredb = "vehiculos.db";
    private static final int Versiondb = 1;

    private static final String Nombre_Tabla = "Clientes";
    private static final String ID_Cliente = "id";
    private static final String sNombreCliente = "nombre";
    private static final String sApellidoCliente = "apellido";
    private static final String sDireccion = "direccion";
    private static final String sCiudad = "ciudad";

    public DatabaseHelper(Context context)
    {
        super(context, Nombredb, null, Versiondb);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + Nombre_Tabla + " (" +
                ID_Cliente + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                sNombreCliente + " TEXT, " +
                sApellidoCliente + " TEXT, " +
                sDireccion + " TEXT, " +
                sCiudad + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String dropTableQuery = "DROP TABLE IF EXISTS " + Nombre_Tabla;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }

    public long insertar (String nombre, String apellido, String direccion, String ciudad)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(sNombreCliente, nombre);
        values.put(sApellidoCliente, apellido);
        values.put(sDireccion, direccion);
        values.put(sCiudad, ciudad);

        return db.insert(Nombre_Tabla, null, values);
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + Nombre_Tabla, null);
    }

    public int actualizar (String id, String nombre, String apellido, String direccion, String ciudad)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(sNombreCliente, nombre);
        values.put(sApellidoCliente, apellido);
        values.put(sDireccion, direccion);
        values.put(sCiudad, ciudad);

        return db.update(Nombre_Tabla, values, ID_Cliente + " = ?", new String[]{id});
    }

    public int Eliminar (String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(Nombre_Tabla, ID_Cliente + " = ?", new String[]{id});
    }
}


