// Pacote onde a classe está localizada
package controller;
// Importação de classes do pacote model
import model.*;
// Importação de classes do pacote view
import view.*;
// Importação da classe Connection do pacote java.sql
import java.sql.*;

// Classe TelaDeAtualizacaoController que estende a classe TelaDeAtualizacaoView
public class TelaDeAtualizacaoController extends TelaDeAtualizacaoView {

    // Método estático para popular os IDs
    public static void popularIds() {
        // Chamada do método popularIdsModel da classe TelaDeAtualizacaoModel
        TelaDeAtualizacaoModel.popularIdsModel();
    }

    // Método estático para enviar os IDs
    public static void enviarIds(String[] idsView) {
        // Atribuição dos IDs recebidos ao array ids
        ids = idsView;
    }

    // Método estático para atualizar o ID
    public static void atualizarId() {
        try {
            // Inicialização de variáveis para armazenar os valores a serem atualizados
            String atualizarNome = "";
            String atualizarEmail = "";
            String atualizarSenha = "";

            // Verificação se o nome foi alterado
            if (txtNome.getText().trim().equals(nomeAtual) == false) {
                // Atualização do nome
                atualizarNome = "`nome` = '" + txtNome.getText() + "'";
            }

            // Verificação se o e-mail foi alterado
            if (txtEmail.getText().trim().equals(emailAtual) == false) {
                // Verificação se o nome foi alterado para adicionar vírgula
                if (atualizarNome.length() > 0) {
                    atualizarEmail = " , ";
                }
                // Atualização do e-mail
                atualizarEmail += "`email` = '" + txtEmail.getText() + "'";
            }

            // Verificação se a senha foi alterada
            if (String.valueOf(txtSenha.getPassword()).trim().equals(senhaAtual) == false) {
                // Verificação se o nome ou e-mail foi alterado para adicionar vírgula
                if (atualizarNome.length() > 0 || atualizarEmail.length() > 0) {
                    atualizarSenha = " , ";
                }
                // Atualização da senha
                atualizarSenha += "`senha` = '" + String.valueOf(txtSenha.getPassword()) + "'";
            }

            // Verificação se houve alterações
            if (atualizarNome.length() > 0 || atualizarEmail.length() > 0 || atualizarSenha.length() > 0) {
                // Conexão com o banco de dados
                Connection conexao = MySQLConnector.conectar();

                // Criação da query de atualização
                String strSqlAtualizarId = "update `db_senac`.`tbl_senac` set " + atualizarNome + atualizarEmail + atualizarSenha + " where `id` = " + cbxId.getSelectedItem().toString() + ";";

                // Criação do statement para executar a query
                Statement stmSqlAtualizarId = conexao.createStatement();

                // Adição da query ao batch
                stmSqlAtualizarId.addBatch(strSqlAtualizarId);

                // Execução do batch
                stmSqlAtualizarId.executeBatch();

                // Atualização dos valores atuais
                nomeAtual = txtNome.getText();
                emailAtual = txtEmail.getText();
                senhaAtual = String.valueOf(txtSenha.getPassword());

                // Fechamento do statement
                stmSqlAtualizarId.close();

                // Exibição da notificação de sucesso
                lblNotificacoes.setText("O id " + cbxId.getSelectedItem().toString() + " foi atualizado com sucesso!");
            } else {
                // Exibição da notificação de que não houve alterações
                lblNotificacoes.setText("Não foram encontradas alterações para atualizar o id " + cbxId.getSelectedItem().toString());
            }
        } catch (Exception e) {
            // Exibição da notificação de erro
            lblNotificacoes.setText(setHtmlFormat("Não foi possível atualizar o id! Por favor, verifique e tente novamente."));

            // Impressão do erro no console
            System.err.println("Erro: " + e);
        }
    }

    // Método estático para limpar os campos
    public static void limparCampos() {
        // Limpeza dos campos
        txtNome.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
        cbxId.setSelectedIndex(0);
    }

    // Método estático para atualizar os campos
    public static void atualizarCampos(String id) {
        try {
                // Verificação se o índice do combobox é maior que 0
    if (cbxId.getSelectedIndex() > 0) {
        // Conexão com o banco de dados
        Connection conexao = MySQLConnector.conectar();
        // Criação da query para selecionar os campos do id
        String strSqlAtualizarCampos = "select * from `db_senac`.`tbl_senac` where `id` = " + id + ";";
        // Criação do statement para executar a query
        Statement stmSqlAtualizarCampos = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // Execução da query e armazenamento do resultado em um ResultSet
        ResultSet rstSqlAtualizarCampos = stmSqlAtualizarCampos.executeQuery(strSqlAtualizarCampos);

        // Verificação se o ResultSet tem pelo menos uma linha
        if (rstSqlAtualizarCampos.next()) {
            // Atualização dos campos com os valores do ResultSet
            txtNome.setText(rstSqlAtualizarCampos.getString("nome"));
            nomeAtual = txtNome.getText();
            txtEmail.setText(rstSqlAtualizarCampos.getString("email"));
            emailAtual = txtEmail.getText();
            txtSenha.setText(rstSqlAtualizarCampos.getString("senha"));
            senhaAtual = String.valueOf(txtSenha.getPassword());

            // Exibição da notificação de sucesso
            lblNotificacoes.setText("Campos atualizados com sucesso!");
        } else {
            // Exibição da notificação de erro
            lblNotificacoes.setText("Ops! Não foi encontrado o id selecionado. Por favor, verifique e tente novamente.");
        }

        // Fechamento do statement
        stmSqlAtualizarCampos.close();
    } else {
        // Exibição da notificação de erro
        lblNotificacoes.setText("Selecione um id para continuar.");

        // Limpeza dos campos
        limparCampos();
    }
} catch (Exception e) {
    // Exibição da notificação de erro
    lblNotificacoes.setText(setHtmlFormat("Não foi possível encontrar os ids! Por favor, verifique e tente novamente."));
    // Impressão do erro no console
    System.err.println("Erro: " + e);
    }
}

// Método estático para notificar o usuário
    public static void notificarUsuario(String txt) {
        // Exibição da notificação com formatação HTML
        lblNotificacoes.setText(setHtmlFormat(txt));
    }

    public static void registrarAtualizacao() {
    nomeAtual = txtNome.getText();
    emailAtual = txtEmail.getText();
    senhaAtual = String.valueOf(txtSenha.getPassword());
    }    
}    