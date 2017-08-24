package br.com.matheusvieira.exefilmes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.util.Scanner;

public class ListaFilmes extends AppCompatActivity {

    private ListView listFilmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);

        listFilmes = (ListView) findViewById(R.id.listFilmes);

        ArrayAdapter<String> adapterThree = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);

        String filename = "filmes_notas.txt";

        FileInputStream inputStream;

        try{

            inputStream = openFileInput(filename);
            Scanner entrada = new Scanner(inputStream);

            while(entrada.hasNextLine()){
                String linha = entrada.nextLine();
                String[] partes = linha.split(";");
                adapterThree.add(partes[0] + " (" + partes[2] +")\n " + partes[1]);
            }

            listFilmes.setAdapter(adapterThree);

        }catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
