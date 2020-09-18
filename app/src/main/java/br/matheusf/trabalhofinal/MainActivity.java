package br.matheusf.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botaoEnviar = findViewById(R.id.btConnectServer);
        final EditText edtUserId = findViewById(R.id.plainTextUsername);
        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread r = new Thread() {
                    @Override
                    public void run() {
                        Application.getInstance().enviarMensagemLogin(edtUserId.getText().toString());
                        Intent intent = new Intent(getApplicationContext(), Contacts.class);
                        startActivity(intent);
                    }
                };
                r.start();
            }
        });

        /*
        Button button = findViewById(R.id.btConnectServer);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Contacts.class);
                startActivity(intent);
            }
        });
         */
    }
}