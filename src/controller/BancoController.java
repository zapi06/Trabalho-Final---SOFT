package controller;

import model.*;
import model.ContaFactory;
import model.ContaCorrenteFactory;
import model.ContaPoupancaFactory;
import view.MenuView;

import java.time.LocalDate;
import java.util.ArrayList;

public class BancoController {
    private ArrayList<Cliente> clientes;
    private MenuView view;
    private int contadorConta = 1000;

    public BancoController(MenuView view) {
        this.view = view;
        this.clientes = new ArrayList<>();
    }

    public void iniciar() {
        boolean rodando = true;
        while (rodando) {
            view.exibirMenuPrincipal();
            String opcao = view.lerOpcao();
            switch (opcao) {
                case "1": login(); break;
                case "2": cadastrarPessoaFisica(); break;
                case "0":
                    rodando = false;
                    view.exibirMensagem("Sistema encerrado.");
                    break;
                default: view.exibirMensagem("Opção inválida.");
            }
        }
    }

    private void cadastrarPessoaFisica() {
        String cpf = view.lerCampo("CPF");
        if (buscarClientePorLogin(cpf) != null) {
            view.exibirMensagem("CPF já cadastrado.");
            return;
        }
        String senha = view.lerCampo("Senha");
        String nome = view.lerCampo("Nome");
        String telefone = view.lerCampo("Telefone");
        String email = view.lerCampo("E-mail");

        PessoaFisica pf = new PessoaFisica(cpf, senha, nome, telefone, email, LocalDate.now());
        clientes.add(pf);
        view.exibirMensagem("Cadastro realizado com sucesso!");
    }

    private void login() {
        String login = view.lerCampo("Login (CPF/CNPJ)");
        String senha = view.lerCampo("Senha");
        Cliente cliente = buscarClientePorLogin(login);
        if (cliente == null || !cliente.autenticar(senha)) {
            view.exibirMensagem("Login inválido.");
            return;
        }
        view.exibirMensagem("Bem-vindo, " + cliente.getNome() + "!");
        menuCliente(cliente);
    }

    private void menuCliente(Cliente cliente) {
        boolean logado = true;
        while (logado) {
            view.exibirMenuCliente();
            String opcao = view.lerOpcao();
            switch (opcao) {
                case "1": abrirConta(cliente); break;
                case "2": realizarDeposito(cliente); break;
                case "3": realizarSaque(cliente); break;
                case "4": realizarTransferencia(cliente); break;
                case "5": consultarSaldo(cliente); break;
                case "6": verExtrato(cliente); break;
                case "0":
                    logado = false;
                    view.exibirMensagem("Logout realizado.");
                    break;
                default: view.exibirMensagem("Opção inválida.");
            }
        }
    }

    private void abrirConta(Cliente cliente) {
        view.exibirMensagem("Tipo: 1-Corrente / 2-Poupança");
        String tipo = view.lerOpcao();
        String numero = String.valueOf(++contadorConta);

        ContaFactory factory;
        if (tipo.equals("1")) {
            factory = new ContaCorrenteFactory(1000.0);
        } else {
            factory = new ContaPoupancaFactory(0.005);
        }

        Conta conta = factory.criarConta(numero);
        cliente.adicionarConta(conta);
        view.exibirMensagem("Conta aberta! Número: " + numero);
    }

    private void realizarDeposito(Cliente cliente) {
        String numero = view.lerCampo("Número da conta");
        double valor = view.lerValor();
        try {
            Conta conta = buscarContaDoCliente(cliente, numero);
            conta.depositar(valor);
            view.exibirMensagem("Depósito realizado.");
        } catch (Exception e) {
            view.exibirMensagem(e.getMessage());
        }
    }

    private void realizarSaque(Cliente cliente) {
        String numero = view.lerCampo("Número da conta");
        double valor = view.lerValor();
        try {
            Conta conta = buscarContaDoCliente(cliente, numero);
            conta.sacar(valor);
            view.exibirMensagem("Saque realizado.");
        } catch (Exception e) {
            view.exibirMensagem(e.getMessage());
        }
    }

    private void realizarTransferencia(Cliente cliente) {
        String numeroOrigem = view.lerCampo("Sua conta");
        String numeroDestino = view.lerCampo("Conta destino");
        double valor = view.lerValor();
        try {
            Conta origem = buscarContaDoCliente(cliente, numeroOrigem);
            Conta destino = buscarContaGlobal(numeroDestino);
            origem.transferir(destino, valor);
            view.exibirMensagem("Transferência realizada.");
        } catch (Exception e) {
            view.exibirMensagem(e.getMessage());
        }
    }

    private void consultarSaldo(Cliente cliente) {
        String numero = view.lerCampo("Número da conta");
        try {
            double saldo = cliente.consultarSaldo(numero);
            view.exibirMensagem("Saldo: R$ " + String.format("%.2f", saldo));
        } catch (Exception e) {
            view.exibirMensagem(e.getMessage());
        }
    }

    private void verExtrato(Cliente cliente) {
        String numero = view.lerCampo("Número da conta");
        try {
            Conta conta = buscarContaDoCliente(cliente, numero);
            view.exibirMensagem("=== Extrato ===");
            for (Transacao t : conta.getExtrato()) {
                view.exibirMensagem(t.toString());
            }
        } catch (Exception e) {
            view.exibirMensagem(e.getMessage());
        }
    }

    private Cliente buscarClientePorLogin(String login) {
        for (Cliente c : clientes) {
            if (c.getLogin().equals(login)) return c;
        }
        return null;
    }

    private Conta buscarContaDoCliente(Cliente cliente, String numero) {
        for (Conta c : cliente.getContas()) {
            if (c.getNumero().equals(numero)) return c;
        }
        throw new IllegalArgumentException("Conta não encontrada.");
    }

    private Conta buscarContaGlobal(String numero) {
        for (Cliente cliente : clientes) {
            for (Conta c : cliente.getContas()) {
                if (c.getNumero().equals(numero)) return c;
            }
        }
        throw new IllegalArgumentException("Conta destino não encontrada.");
    }

    // Getters para testes
    public ArrayList<Cliente> getClientes() { return clientes; }
}
