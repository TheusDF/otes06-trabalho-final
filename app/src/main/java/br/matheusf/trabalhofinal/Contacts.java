package br.matheusf.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.Observable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Contacts extends AppCompatActivity {
    ArrayList<String> listaUsuariosNaView = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        ListView lista = (ListView) findViewById(R.id.listView);

        // pegando a lista de usuarios daquele observable.
        listaUsuariosNaView.addAll(ListaUsuarios.getListaDeUsuarios().get());
        //jogando direto no adapter, mas mantendo a referencia no atributo pra usar na atualizacao do conteudo no onPropertyChanged
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaUsuariosNaView);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // Mostra toast com o nome do item
                //Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();

                // Muda para a tela de mensagens
                Intent intent = new Intent(getApplicationContext(), Message.class);
                startActivity(intent);
            }
        });
        //adicionei um listener da lista do modelo aqui...
        ListaUsuarios.getListaDeUsuarios().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        listaUsuariosNaView.clear();
                        listaUsuariosNaView.addAll(ListaUsuarios.getListaDeUsuarios().get());
                        adapter.notifyDataSetChanged();
                    }
                };
                //
                runOnUiThread(r);
            }
        });

    }
}