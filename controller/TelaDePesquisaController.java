// Pacote de controle
package controller;

// Importação de modelos necessários
import model.*;
// Importação de views necessárias
import view.*;

// Classe pública TelaDePesquisaController que estende TelaDePesquisaView
public class TelaDePesquisaController extends TelaDePesquisaView {

    // Método para notificar o usuário com uma mensagem
    public static void notificarUsuario(String textoNotificacao) {
        // Exibindo a notificação no label de notificações
        lblNotificacoes.setText(setHtmlFormat(textoNotificacao)); // Define o texto da notificação no label
    }

    // Método para preencher os campos de texto com informações do usuário
    public static void preencherCampos(String id, String nome, String email) {
        // Preenchendo o campo de texto de ID
        txtId.setText(id); 
        // Preenchendo o campo de texto de nome
        txtNome.setText(nome); 
        // Preenchendo o campo de texto de email
        txtEmail.setText(email); 
    }

    // Método para registrar a pesquisa realizada pelo usuário
    public static void registrarPesquisa() {
        // Registrando a pesquisa realizada pelo usuário no campo de texto de pesquisa
        txtUsuario = txtPesquisa.getText(); 
    }

    // Método para realizar a pesquisa
    public static void pesquisar() {
        // Obtendo o texto da pesquisa do campo de texto de pesquisa
        String textoPesquisa = txtPesquisa.getText().trim(); 
        // Verificando se a pesquisa é diferente da anterior
        if (textoPesquisa.equals(txtUsuario) == false) {
            // Limpando os campos de texto
            limparCampos(""); 
            // Chamando o método de pesquisa no modelo
            TelaDePesquisaModel.pesquisarModel(textoPesquisa); 
        }
    }

    // Método para exibir o primeiro registro da pesquisa
    public static void primeiroRegistro() {
        // Chamando o método de primeiro registro no modelo
        TelaDePesquisaModel.primeiroRegistroModel(txtPesquisa.getText()); 
    }

    // Método para exibir o registro anterior da pesquisa
    public static void registroAnterior() {
        // Chamando o método de registro anterior no modelo
        TelaDePesquisaModel.registroAnteriorModel(txtPesquisa.getText(), txtId.getText(), txtNome.getText(), txtEmail.getText()); 
    }

    // Método para exibir o próximo registro da pesquisa
    public static void proximoRegistro() {
        // Chamando o método de próximo registro no modelo
        TelaDePesquisaModel.proximoRegistroModel(txtPesquisa.getText(), txtId.getText(), txtNome.getText(), txtEmail.getText()); 
    }

    // Método para exibir o último registro da pesquisa
    public static void ultimoRegistro() {
        // Chamando o método de último registro no modelo
        TelaDePesquisaModel.ultimoRegistroModel(txtPesquisa.getText(), txtId.getText(), txtNome.getText(), txtEmail.getText()); 
    }

    // Método para limpar os campos de texto
    public static void limparCamposController(String txt) {
        // Limpando os campos de texto
        limparCampos(txt); 
    }

    // Método para desabilitar todos os botões
    public static void desabilitarTodos() {
        // Desabilitando o botão de primeiro registro
        btnPrimeiro.setEnabled(false); 
        // Desabilitando o botão de registro anterior
        btnAnterior.setEnabled(false); 
        // Desabilitando o botão de próximo registro
        btnProximo.setEnabled(false); 
        // Desabilitando o botão de último registro
        btnUltimo.setEnabled(false); 
    }

    // Método para habilitar os botões de voltar
    public static void habilitarVoltar() {
        // Desabilitando todos os botões
        desabilitarTodos(); 
        // Habilitando o botão de primeiro registro
        btnPrimeiro.setEnabled(true); 
        // Habilitando o botão de registro anterior
        btnAnterior.setEnabled(true); 
    }

    // Método para habilitar todos os botões
    public static void habilitarTodos() {
        // Habilitando o botão de primeiro registro
        btnPrimeiro.setEnabled(true); 
        // Habilitando o botão de registro anterior
        btnAnterior.setEnabled(true); 
        // Habilitando o botão de próximo registro
        btnProximo.setEnabled(true); 
        // Habilitando o botão de último registro
        btnUltimo.setEnabled(true); 
    }

    // Método para habilitar os botões de avançar
    public static void habilitarAvancar() {
        //Invoca o método desabilitar todos
        desabilitarTodos();
        //Habilitando o botão próximo 
        btnProximo.setEnabled(true);
        //Habilitando o botão último
        btnUltimo.setEnabled(true);
    }

    // Método para desabilitar o botão pesquisa
    public static void desabilitarPesquisar() {
        btnPesquisar.setEnabled(false);
    }
}