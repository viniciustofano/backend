// Pacote onde a classe está localizada
package model;
// Importação de classes do pacote controller
import controller.*;
// Importação de classes do pacote java.sql
import java.sql.*;
// Importação de classes do pacote java.util
import java.util.*;
// Classe TelaDeAtualizacaoModel
public class TelaDeAtualizacaoModel {

    // Método estático para popular os IDs
    public static void popularIdsModel() {
        try {
            // Criação de uma lista temporária para armazenar os IDs
            ArrayList<String> idsTemp = new ArrayList<>();
            // Adição do item "Selecione aqui o id" à lista
            idsTemp.add("Selecione aqui o id");
            // Conexão com o banco de dados
            Connection conexao = MySQLConnector.conectar();
            // Criação da query para selecionar todos os registros da tabela tbl_senac ordenados por ID em ordem ascendente
            String strSqlPopularIds = "select * from `db_senac`.`tbl_senac` order by `id` asc;";
            // Criação do statement para executar a query
            Statement stmSqlPopularIds = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Execução da query e armazenamento do resultado em um ResultSet
            ResultSet rstSqlPopularIds = stmSqlPopularIds.executeQuery(strSqlPopularIds);

            // Loop para iterar sobre os registros do ResultSet
            while (rstSqlPopularIds.next()) {
                // Adição do ID do registro à lista
                idsTemp.add(rstSqlPopularIds.getString("id"));
            }

            // Envio dos IDs para o controller
            TelaDeAtualizacaoController.enviarIds(idsTemp.toArray(new String[0]));

            // Fechamento do statement
            stmSqlPopularIds.close();
        } catch (Exception e) {
            // Notificação de erro ao usuário
            TelaDeAtualizacaoController.notificarUsuario("Não foi possível encontrar os ids! Por favor, verifique e tente novamente.");

            // Impressão do erro no console
            System.err.println("Erro: " + e);
            }
        }

        public static void atualizarCadastroModel(String atualizarId, String atualizarNome, String atualizarEmail, String atualizarSenha) {
            try { 
                Connection conexao = MySQLConnector.conectar();
                String strSqlAtualizarId = "update `db_senac`, `tbl_senac` set " + atualizarNome + atualizarEmail + atualizarSenha + "where `id` = " + atualizarId + ";";
                Statement stmSqlAtualizarId = conexao.createStatement();
                stmSqlAtualizarId.addBatch(strSqlAtualizarId);
                stmSqlAtualizarId.executeBatch();
                TelaDeAtualizacaoController.registrarAtualizacao();
                stmSqlAtualizarId.close();
                TelaDeAtualizacaoController.notificarUsuario("O id" + atualizarId + " foi atualizado com sucesso");
           }catch (Exception e) {
                TelaDeAtualizacaoController.notificarUsuario("Não foi possível encontrar os ids! Por favor, verifique e tente novamente.");
                System.err.println("Erro: " + e);
           }  
        }
    }