package sv.edu.utec.parcial4_electiva1_012023;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbH;
    Button insertar, actualizar, borrar, insertarC, actuC, delC;
    EditText etid, etnom, etape, etdir, etciu;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbH = new DatabaseHelper(this);

        etid = findViewById(R.id.edtUIDC);
        etnom = findViewById(R.id.edtUNombreC);
        etape = findViewById(R.id.edtUApellidoC);
        etdir = findViewById(R.id.edtUDireccionC);
        etciu = findViewById(R.id.edtUCiudadC);
        insertarC = findViewById(R.id.btnInsertarC);
        actuC = findViewById(R.id.btnInsertarC);
        delC = findViewById(R.id.btnInsertarC)

        insertar = findViewById(R.id.btnInsertar);
        actualizar = findViewById(R.id.btnActualizar);
        borrar = findViewById(R.id.btnBorrar);

        insertar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                form_insert();
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                form_update();
            }
        });

        borrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                form_delete();
            }
        });

        private void form_insert()
        {

            insertarC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nombre = etnom.getText().toString();
                    String apellido = etape.getText().toString();
                    String direccion = etdir.getText().toString();
                    String ciudad = etciu.getText().toString();

                    long resultado = dbH.insertar(nombre, apellido, direccion, ciudad);
                    if (resultado != -1) {
                        Toast.makeText(form_insert.this, "Datos insertados correctamente", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(form_insert.this, "Error al insertar los datos", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            Toast.makeText(this, "Abrir formulario de inserción", Toast.LENGTH_SHORT).show();
        }

        private void form_update()
        {
            actuC.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    String id = etid.getText().toString();
                    String nombre = etnom.getText().toString();
                    String apellido = etape.getText().toString();
                    String direccion = etdir.getText().toString();
                    String ciudad = etciu.getText().toString();

                    int resultado = dbH.actualizar(id, nombre, apellido, direccion, ciudad);
                    if (resultado > 0)
                    {
                        Toast.makeText(form_update.this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
                        finish();
                    } else
                    {
                        Toast.makeText(form_update.this, "Error al actualizar los datos", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(this, "Abrir formulario de actualización", Toast.LENGTH_SHORT).show();
        }

        private void form_delete()
        {
            delC.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    String id = etid.getText().toString();

                    int resultado = dbH.Eliminar(id);
                    if (resultado > 0)
                    {
                        Toast.makeText(form_delete.this, "Datos eliminados correctamente", Toast.LENGTH_SHORT).show();
                        finish(); // Cierra la actividad actual después de eliminar los datos
                    } else {
                        Toast.makeText(form_delete.this, "Error al eliminar los datos", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(this, "Abrir formulario de eliminación", Toast.LENGTH_SHORT).show();
        }
    }
}
