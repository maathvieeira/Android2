package br.com.matheusvieira.exefilmes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTitulo;
    private Spinner spnGenero;
    private Spinner spnNota;
    private Button btnSalvar;
    private Button btnListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        spnGenero = (Spinner) findViewById(R.id.spnGenero);
        spnNota = (Spinner) findViewById(R.id.spnNota);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnListar = (Button) findViewById(R.id.btnListar);


        btnSalvar.setOnClickListener(this);
        btnListar.setOnClickListener(this);

        ArrayAdapter<String> adapterOne = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);

        adapterOne.add("Comédia");
        adapterOne.add("Terror");
        adapterOne.add("Ação");
        adapterOne.add("Drama");
        adapterOne.add("Fantasia");
        adapterOne.add("Aventura");
        adapterOne.add("Outro");

        adapterOne.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnGenero.setAdapter(adapterOne);


        ArrayAdapter<String> adapterTwo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);

        adapterTwo.add("1");
        adapterTwo.add("2");
        adapterTwo.add("3");
        adapterTwo.add("4");
        adapterTwo.add("5");
        adapterTwo.add("6");
        adapterTwo.add("7");
        adapterTwo.add("8");
        adapterTwo.add("9");
        adapterTwo.add("10");

        adapterTwo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnNota.setAdapter(adapterTwo);
    }


    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnSalvar){

            String tituloF = edtTitulo.getText().toString();
            String generoF = spnGenero.getSelectedItem().toString();
            String notaF = spnNota.getSelectedItem().toString();

            if(tituloF.trim().isEmpty()){
                Toast.makeText(this, "Preencha os campos!!", Toast.LENGTH_LONG).show();
                edtTitulo.requestFocus();
                return;
            }

            String filename = "filmes_notas.txt";
            FileOutputStream outputStream;


            try {

                outputStream = openFileOutput(filename, Context.MODE_APPEND);
                outputStream.write((tituloF + ";" + generoF + ";" + notaF).getBytes());
                outputStream.close();

                Toast.makeText(this, "Filme cadastrado com sucesso!!", Toast.LENGTH_LONG).show();
                edtTitulo.setText("");
                spnGenero.setSelection(0);
                spnNota.setSelection(0);

            } catch (Exception e) {

                Toast.makeText(this, "Erro..." + e.getMessage(), Toast.LENGTH_SHORT).show();

            }

        } else {
            Intent intent = new Intent(this, ListaFilmes.class);
            startActivity(intent);
        }

    }
}
