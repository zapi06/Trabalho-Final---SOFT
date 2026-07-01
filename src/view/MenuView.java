package view;

import model.Conta;
import java.util.Scanner;

public class MenuView {
    private Scanner scanner;

    public MenuView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void exibirMenuPrincipal() {
        System.out.println("\n=== Banco UDESC ===");
        System.out.println("1 - Login");
        System.out.println("2 - Cadastrar (Pessoa Física)");
        System.out.println("0 - Sair");
        System.out.print("Opção: ");
    }

    public void exibirMenuCliente() {
        System.out.println("\n=== Menu do Cliente ===");
        System.out.println("1 - Abrir Conta");
        System.out.println("2 - Depósito");
        System.out.println("3 - Saque");
        System.out.println("4 - Transferência");
        System.out.println("5 - Consultar Saldo");
        System.out.println("6 - Extrato");
        System.out.println("0 - Logout");
        System.out.print("Opção: ");
    }

    public String lerOpcao() {
        return scanner.nextLine().trim();
    }

    public String lerCampo(String campo) {
        System.out.print(campo + ": ");
        return scanner.nextLine().trim();
    }

    public double lerValor() {
        System.out.print("Valor: R$ ");
        try {
            return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void exibirMensagem(String msg) {
        System.out.println(msg);
    }
}
