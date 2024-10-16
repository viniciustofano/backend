import java.util.*;

public class Classe1 {
    public static void main(String[] args) {
    Scanner scninputUsuario = new Scanner(System.in);//serve para criar o input de entrada do usuário
    System.out.println("Olá, bem vindo a calculadora de java. Digite o primeiro número a ser calculado e tecle: \"Enter\" ");
    int intinputUsuario1 = scninputUsuario.nextInt();
    System.out.println(" Digite o segundo número a ser calculado e tecle: \"Enter\" ");
    int intinputUsuario2 = scninputUsuario.nextInt();
    System.out.println("Digite o número da opção desejada e tecle: \"Enter\" .");
    String[] opcoes = Classe2.mostrarOpcoes();
   
    for (int count = 0; count < opcoes.length; count++) {
        System.out.println(count + " - " + opcoes[count]);
    }

    int opcaoEscolhida = scninputUsuario.nextInt();
    
    System.out.println("O resultado da " + opcoes[opcaoEscolhida] + " é: " + Classe2.resultado(intinputUsuario1, intinputUsuario2, opcaoEscolhida));
    scninputUsuario.close();
    }
}