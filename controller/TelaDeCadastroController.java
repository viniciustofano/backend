// Pacote de controle
package controller;

// Importação de classes necessárias para modelo e visualização
import model.*;
import view.*;

// Classe pública TelaDeCadastroController que estende TelaDeCadastroView
public class TelaDeCadastroController extends TelaDeCadastroView {

    // Array de strings para armazenar as respostas para o usuário
    public static String[] retornoUsuario = {
        "Email já cadastrado! Favor digitar outro email e tentar novamente.", // resposta 0
        "Não foi possível realizar o seu cadastro, por uma falha no servidor! Por favor, tente novamente mais tarde.", // resposta 1
        "Cadastro realizado com sucesso" // resposta 2
    };

    public static String cadastrarController(String nome, String email, String senha) {
        // Chamando o método de cadastro no modelo e retornando a mensagem de resposta
        return retornoUsuario[TelaDeCadastroModel.cadastrarModel(nome, email, senha)];
    }
}