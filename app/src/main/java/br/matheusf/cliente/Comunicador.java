package br.matheusf.cliente;

import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.matheusf.trabalhofinal.R;

/**
 *
 * @author old_adam
 */
public class Comunicador {
    private Socket socket;
    //
    private final ArrayList<ComunicadorListener> listaDeObservadores = new ArrayList<>(1);

    public void addListener(ComunicadorListener observador) {
        listaDeObservadores.add(observador);
    }

    public void removeListener(ComunicadorListener observador) {
        listaDeObservadores.remove(observador);
    }

    public void falaComOServidor(String mensagem) {
        try{
            //abrindo o socket com o servidor.
            socket = new Socket("192.168.15.3", 1408);
            //
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.println(mensagem);
            //

            String feedback = new String();
            System.out.println("antes de ler.");
            //Reading back
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner reader = new Scanner(br);
                feedback = br.readLine();
                //
                reader.close();
                socket.close();
                socket = null;
                //Chamar um observador/listener
                for(ComunicadorListener observador : listaDeObservadores) {
                    observador.onMenssagemChegandoDoServidor(feedback);
                }

            } catch (IOException ex) {
                Logger.getLogger(Comunicador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch(IOException e) {
            System.out.println("Erro ao enviar msg: " + e.getMessage());
        }
    }

}