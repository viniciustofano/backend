package controller;
import model.*;
import view.*;
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.*;
import  javax.swing.*;

public class TelaDeAtualizacaoController extends TelaDeAtualizacaoView {
    public static void popularIds() {
        TelaDeAtualizacaoModel.popularIdsModel();
    }

    public static void enviarIds(String[] idsView) {
        ids = idsView;
    }

    public static void atualizarId() {
        try {
            String atualizarNome = "";
            String atualizarEmail = "";
            String atualizarSenha = "";
            String atualizarImagem = "";

            if (txtNome.getText().trim().equals(nomeAtual) == false) {
                atualizarNome = "`nome` = '" + txtNome.getText() + "'";
            }

            if (txtEmail.getText().trim().equals(emailAtual) == false) {
                if (atualizarNome.length() > 0) {
                    atualizarEmail = " , ";
                }
                atualizarEmail += "`email` = '" + txtEmail.getText() + "'";
            }

            if (String.valueOf(txtSenha.getPassword()).trim().equals(senhaAtual) == false) {
                if (atualizarNome.length() > 0 || atualizarEmail.length() > 0) {
                    atualizarSenha = " , ";
                }
                atualizarSenha += "`senha` = '" + String.valueOf(txtSenha.getPassword()) + "'";
            }

            if (txtImagem.getText().trim().equals("") == false) {
                if (atualizarNome.length() > 0 || atualizarEmail.length() > 0 || atualizarSenha.length() > 0) {
                    atualizarSenha = " , ";
                }
                atualizarImagem += "`img` = '" + txtImagem.getText() + "'";
            }

            if (atualizarNome.length() > 0 || atualizarEmail.length() > 0 || atualizarSenha.length() > 0) {
                TelaDeAtualizacaoModel.atualizarCadastroModel(cbxId.getSelectedItem().toString(), atualizarNome, atualizarEmail, atualizarSenha, atualizarImagem);
            } else {
                lblNotificacoes.setText("Não foram encontradas alterações para atualizar o id " + cbxId.getSelectedItem().toString());
            }
        } catch (Exception e) {
            lblNotificacoes.setText(setHtmlFormat("Não foi possível atualizar o id! Por favor, verifique e tente novamente."));
            System.err.println("Erro: " + e);
        }
    }

    public static void limparCampos() {
        txtNome.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
        cbxId.setSelectedIndex(0);
    }

    public static void atualizarCampos(String id) {
        if (cbxId.getSelectedIndex() > 0) {
            TelaDeAtualizacaoModel.atualizarCamposModel(String.valueOf(cbxId.getSelectedIndex()));
            // aqui será chamado o método que vai atualizar os campos via model
        } else {
            lblNotificacoes.setText("Selecione um id para continuar.");
            limparCampos();
        }
    }

    public static void enviarCampos(String nome, String email, String senha) {
        txtNome.setText(nome);
        nomeAtual = txtNome.getText();
        txtEmail.setText(email);
        emailAtual = txtEmail.getText();
        txtSenha.setText(senha);
        senhaAtual = String.valueOf(txtSenha.getPassword());
    }

    public static void notificarUsuario(String txt) {
        lblNotificacoes.setText(setHtmlFormat(txt));
    }

    public static void registrarAtualizacao() {
        nomeAtual = txtNome.getText();
        emailAtual = txtEmail.getText();
        senhaAtual = String.valueOf(txtSenha.getPassword());
    }

    public static void carregarImagem() {
        String fileName = "";
        try {
            JFileChooser chooser = new JFileChooser();

            chooser.setDialogTitle("Selecione o arquivo que deseja carregar");
            chooser.setApproveButtonText("Carregar arquivo");
            int returnVal1 = chooser.showOpenDialog(null);
            String fileFullPath = "";
            if(returnVal1 == JFileChooser.APPROVE_OPTION) {
                fileFullPath = chooser.getSelectedFile().getAbsolutePath();
                fileName = chooser.getSelectedFile().getName();
            } else {
                System.out.println("Que pena!");
                return;
            }

            String folderFullPath = InterfaceController.localViewFolder;

            String newFileName = InterfaceController.gerarNomeAleatorio() +  "-" 
            + fileName;

            Path pathOrigin = Paths.get(fileFullPath);
            Path pathDestination = Paths.get(folderFullPath + "\\" + newFileName);
            if (fileFullPath.length() > 0 && folderFullPath.length() > 0) {
                Files.copy(pathOrigin, pathDestination, REPLACE_EXISTING);
                System.out.println("Arquivo " + fileName + " copiado/colado com sucesso!");
            } else {
                System.out.println("Ops! Não foi possível copiar o arquivo. Por favor, verifique e tente novamente.");
            }
        } catch (Exception e) {
            System.err.println("Não foi possível copiar o arquivo! Tente novamente mais tarde.");
        }

        Icon imgCarregada = new ImageIcon(InterfaceController.localViewImgFolder + "\\" + fileName);

        lblImagem.setIcon(imgCarregada);
        txtEmail.setText(fileName);
    }

    public static void removerImagem() {
        
    }
}
