// Pacote onde a classe está localizada
package model;

// Importação de classes do pacote java.sql
import java.sql.*;

// Importação de classes do pacote controller
import controller.*;

// Classe TelaDePesquisaModel
public class TelaDePesquisaModel {

    // Método estático para pesquisar registros
    public static void pesquisarModel(String textoPesquisa) {
        try {
            // Conexão com o banco de dados
            Connection conexao = MySQLConnector.conectar();
            // Criação da query para pesquisar registros
            String strSqlPesquisa = "select * from `db_senac`.`tbl_senac` where `nome` like '%" + textoPesquisa + "%' or `email` like '%" + textoPesquisa + "%' order by `id` asc;";
            // Criação do statement para executar a query
            Statement stmSqlPesquisa = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Execução da query e armazenamento do resultado em um ResultSet
            ResultSet rstSqlPesquisa = stmSqlPesquisa.executeQuery(strSqlPesquisa);
            // Verificação se o ResultSet tem pelo menos uma linha
            if (rstSqlPesquisa.next()) {
                // Movimento para o último registro do ResultSet
                rstSqlPesquisa.last();
                // Obtenção do número de linhas do ResultSet
                int rowNumbers = rstSqlPesquisa.getRow();
                // Movimento para o primeiro registro do ResultSet
                rstSqlPesquisa.first();

                // Notificação de sucesso ao usuário
                TelaDePesquisaController.notificarUsuario("Legal! Foi(Foram) encontrado(s) " + rowNumbers + " resultado(s).");

                // Preenchimento dos campos com os valores do primeiro registro
                TelaDePesquisaController.preencherCampos(rstSqlPesquisa.getString("id"), rstSqlPesquisa.getString("nome"), rstSqlPesquisa.getString("email"));
                // Registro da pesquisa
                TelaDePesquisaController.registrarPesquisa();

                // Desabilitação do botão de pesquisar
                TelaDePesquisaController.desabilitarPesquisar();
                // Verificação se há mais de um registro
                if (rowNumbers > 1) {
                    // Habilitação do botão de avançar
                    TelaDePesquisaController.habilitarAvancar();
                }

                // Fechamento do statement
                stmSqlPesquisa.close();
            } else {
                // Registro da pesquisa
                TelaDePesquisaController.registrarPesquisa();
                // Desabilitação do botão de pesquisar
                TelaDePesquisaController.desabilitarPesquisar();
                // Notificação de erro ao usuário
                TelaDePesquisaController.notificarUsuario("Poxa vida! Não foram encontrados resultados para: \"" + textoPesquisa + "\".");
                // Fechamento do statement
                stmSqlPesquisa.close();
            }
        } catch (Exception e) {
            // Impressão do erro no console
            System.err.println("Erro: " + e);
            // Notificação de erro ao usuário
            TelaDePesquisaController.notificarUsuario("Não foi possível prosseguir com a pesquisa! Por favor, verifique e tente novamente.");
        }
    }

    // Método estático para obter o primeiro registro
    public static void primeiroRegistroModel(String textoPesquisa) {
        try {
            // Limpeza dos campos
            TelaDePesquisaController.limparCamposController("Você está no primeiro registro.");
            // Conexão com o banco de dados
            Connection conexao = MySQLConnector.conectar();
            // Criação da query para obter o primeiro registro
            String strSqlPesquisa = "select * from `db_senac`.`tbl_senac` where `nome` like '%" + textoPesquisa + "%' or `email` like '%" + textoPesquisa + "%' order by `id` asc;";
            // Criação do statement para executar a query
            Statement stmSqlPesquisa = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Execução da query e armazenamento do resultado em um ResultSet
            ResultSet rstSqlPesquisa = stmSqlPesquisa.executeQuery(strSqlPesquisa);
            // Verificação se o ResultSet tem pelo menos uma linha
            if (rstSqlPesquisa.next()) {
                // Preenchimento dos campos com os valores do primeiro registro
                TelaDePesquisaController.preencherCampos(rstSqlPesquisa.getString("id"), rstSqlPesquisa.getString("nome"), rstSqlPesquisa.getString("email"));

              // Habilitação do botão de avançar
TelaDePesquisaController.habilitarAvancar();
} else {
// Notificação de erro ao usuário
TelaDePesquisaController.notificarUsuario("Poxa vida! Não foram encontrados resultados para: \"" + textoPesquisa + "\".");
}
// Registro da pesquisa
TelaDePesquisaController.registrarPesquisa();

// Desabilitação do botão de pesquisar
TelaDePesquisaController.desabilitarPesquisar();

// Fechamento do statement
stmSqlPesquisa.close();
} catch (Exception e) {
// Notificação de erro ao usuário
TelaDePesquisaController.notificarUsuario("Não foi possível prosseguir com a pesquisa! Por favor, verifique e tente novamente.");

// Impressão do erro no console
System.err.println("Erro: " + e);
}
}

// Método estático para obter o registro anterior
public static void registroAnteriorModel(String textoPesquisa, String idAtual, String nomeAtual, String emailAtual) {
try {
// Limpeza dos campos
TelaDePesquisaController.limparCamposController("Registro anterior posicionado com sucesso.");

// Conexão com o banco de dados
Connection conexao = MySQLConnector.conectar();

// Criação da query para obter o registro anterior
String strSqlProximoRegistro = "select * from `db_senac`.`tbl_senac` where (`nome` like '%" + textoPesquisa + "%' or `email` like '%" + textoPesquisa + "%') and `id` < " + idAtual + " order by `id` desc;";

// Criação do statement para executar a query
Statement stmSqlProximoRegistro = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

// Execução da query e armazenamento do resultado em um ResultSet
ResultSet rstSqlProximoRegistro = stmSqlProximoRegistro.executeQuery(strSqlProximoRegistro);

// Verificação se o ResultSet tem pelo menos uma linha
if (rstSqlProximoRegistro.next()) {
// Preenchimento dos campos com os valores do registro anterior
TelaDePesquisaController.preencherCampos(rstSqlProximoRegistro.getString("id"), rstSqlProximoRegistro.getString("nome"), rstSqlProximoRegistro.getString("email"));

// Habilitação dos botões de avançar e voltar
TelaDePesquisaController.habilitarTodos();
} else {
// Preenchimento dos campos com os valores atuais
TelaDePesquisaController.preencherCampos(idAtual, nomeAtual, emailAtual);

// Habilitação do botão de avançar
TelaDePesquisaController.habilitarAvancar();

// Notificação de erro ao usuário
TelaDePesquisaController.notificarUsuario("Você chegou ao primeiro registro.");
}
// Fechamento do statement
stmSqlProximoRegistro.close();
    } catch (Exception e) {
    // Notificação de erro ao usuário
    TelaDePesquisaController.notificarUsuario("Não foi possível encontrar o próximo registro! Por favor, verifique e tente novamente.");
// Impressão do erro no console
    System.err.println("Erro: " + e);
        }
    }

    // Método estático para obter o próximo registro
    public static void proximoRegistroModel(String textoPesquisa, String idAtual, String nomeAtual, String emailAtual) {
    try {
    // Limpeza dos campos
    TelaDePesquisaController.limparCamposController("Próximo registro posicionado com sucesso.");
// Conexão com o banco de dados
    Connection conexao = MySQLConnector.conectar();
// Criação da query para obter o próximo registro
    String strSqlProximoRegistro = "select * from `db_senac`.`tbl_senac` where (`nome` like '%" + textoPesquisa + "%' or `email` like '%" + textoPesquisa + "%') and `id` > " + idAtual + " order by `id` asc;";

    // Criação do statement para executar a query
    Statement stmSqlProximoRegistro = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

    // Execução da query e armazenamento do resultado em um ResultSet
    ResultSet rstSqlProximoRegistro = stmSqlProximoRegistro.executeQuery(strSqlProximoRegistro);

    // Verificação se o ResultSet tem pelo menos uma linha
    if (rstSqlProximoRegistro.next()) {
    // Preenchimento dos campos com os valores do próximo registro
    TelaDePesquisaController.preencherCampos(rstSqlProximoRegistro.getString("id"), rstSqlProximoRegistro.getString("nome"), rstSqlProximoRegistro.getString("email"));
    // Habilitação dos botões de avançar e voltar
    TelaDePesquisaController.habilitarTodos();
    } else {
    // Preenchimento dos campos com os valores atuais
    TelaDePesquisaController.preencherCampos(idAtual, nomeAtual, emailAtual);

    // Habilitação do botão de voltar
    TelaDePesquisaController.habilitarVoltar();

    // Notificação de erro ao usuário
    TelaDePesquisaController.notificarUsuario("Você chegou ao último registro.");
    }
    // Fechamento do statement
    stmSqlProximoRegistro.close();
    } catch (Exception e) {
    // Notificação de erro ao usuário
    TelaDePesquisaController.notificarUsuario("Não foi possível encontrar o próximo registro! Por favor, verifique e tente novamente.");

    // Impressão do erro no console
    System.err.println("Erro: " + e);
    }
    }

    // Método estático para obter o último registro
    public static void ultimoRegistroModel(String textoPesquisa, String idAtual, String nomeAtual, String emailAtual) {
    try {
    // Limpeza dos campos
    TelaDePesquisaController.limparCamposController("");

    // Conexão com o banco de dados
    Connection conexao = MySQLConnector.conectar();

    // Criação da query para obter o último registro
    String strSqlProximoRegistro = "select * from `db_senac`.`tbl_senac` where `nome` like '%" + textoPesquisa + "%' or `email` like '%" + textoPesquisa + "%' order by `id` desc;";

    // Criação do statement para executar a query
    Statement stmSqlProximoRegistro = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

    // Execução da query e armazenamento do resultado em um ResultSet
    ResultSet rstSqlProximoRegistro = stmSqlProximoRegistro.executeQuery(strSqlProximoRegistro);

        // Verificação se o ResultSet tem pelo menos uma linha
        if (rstSqlProximoRegistro.next()) {
            // Preenchimento dos campos com os valores do último registro
            TelaDePesquisaController.preencherCampos(rstSqlProximoRegistro.getString("id"), rstSqlProximoRegistro.getString("nome"), rstSqlProximoRegistro.getString("email"));

            // Habilitação do botão de voltar
            TelaDePesquisaController.habilitarVoltar();

            // Notificação de erro ao usuário
            TelaDePesquisaController.notificarUsuario("Você chegou ao último registro.");
            } else {
            // Preenchimento dos campos com os valores atuais
            TelaDePesquisaController.preencherCampos(idAtual, nomeAtual, emailAtual);

            // Habilitação do botão de voltar
            TelaDePesquisaController.habilitarVoltar();

            // Notificação de erro ao usuário
            TelaDePesquisaController.notificarUsuario("Você chegou ao último registro.");
        }
        // Fechamento do statement
        stmSqlProximoRegistro.close();
            } catch (Exception e) {
        // Notificação de erro ao usuário
        TelaDePesquisaController.notificarUsuario("Não foi possível encontrar o último registro! Por favor, verifique e tente novamente.");

        // Impressão do erro no console
        System.err.println("Erro: " + e);
                }
            }
    }
  