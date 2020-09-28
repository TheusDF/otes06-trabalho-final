package br.matheusf.trabalhofinal;

import android.util.Log;

import java.util.List;

import br.matheusf.cliente.Comunicador;
import br.matheusf.cliente.Interpretador;
import br.matheusf.cliente.MensagemListener;


public class Application implements MensagemListener {

    private static final Application instancia = new Application();
    public static Application getInstance() {return instancia;}

    private Comunicador comunicador;

    private Application() {
        comunicador = new Comunicador();
        comunicador.addListener(Interpretador.getInstance());
        Interpretador.getInstance().addObservador(this);
    }

    /**
     *
     * @param userId
     */
    public void enviarMensagemLogin(String userId) {
        // { "login": { "user-id":"o.professor" } }
        String header = "{ \"login\": { \"user-id\":\"";
        String tail   = "\" } }";
        String mensagem = header + userId + tail;
        //Atualizando o id do Usuario.
        Usuario.getInstance().setUserId(userId);
        comunicador.enfileraMensagem(mensagem);
    }

    @Override
    public void onListaDeUsuariosChegando(List<String> usuarios) {
//        Log.d("LISTA", "onListaDeUsuariosChegando: " + usuarios.toString());
        ListaUsuarios.getListaDeUsuarios().set(usuarios);
    }

    @Override
    public void onMensagemChegando(String remetente, String texto) {

    }

    @Override
    public void onMensagemDeErroChegando(String motivo) {

    }
}