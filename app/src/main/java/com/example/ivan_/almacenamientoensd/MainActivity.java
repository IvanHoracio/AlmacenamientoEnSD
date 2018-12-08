package com.example.ivan_.almacenamientoensd;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText edtGrabar, edtRecuperar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtGrabar = (EditText) findViewById(R.id.editText);
        edtRecuperar = (EditText) findViewById(R.id.edtContenido);
    }
    public void grabar(View v) {
        String nomarchivo = edtGrabar.getText().toString();
        String contenido=edtRecuperar.getText().toString();
        try
        {
            File tarjeta = Environment.getExternalStorageDirectory();
            File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
            OutputStreamWriter osw =new OutputStreamWriter(new FileOutputStream(file));
            osw.write(contenido);
            osw.flush();
            osw.close();
            Toast.makeText(this,"Los datos fueron grabados correctamente",Toast.LENGTH_SHORT).show();
            edtGrabar.setText("");
            edtRecuperar.setText("");
        }
        catch (IOException ioe)
        {
        }
    }

    public void recuperar(View v) {
        String nomarchivo = edtGrabar.getText().toString();
        File tarjeta = Environment.getExternalStorageDirectory();
        File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
        try {
            FileInputStream fIn = new FileInputStream(file);
            InputStreamReader archivo=new InputStreamReader(fIn);
            BufferedReader br=new BufferedReader(archivo);
            String linea=br.readLine();
            String todo="";
            while (linea!=null)
            {
                todo=todo+linea+"\n";
                linea=br.readLine();
            }
            br.close();
            archivo.close();
            edtRecuperar.setText(todo);

        } catch (IOException e)
        {
        }
    }


}
