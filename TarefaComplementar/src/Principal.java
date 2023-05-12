import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        String nomeCliente = JOptionPane.showInputDialog("Digite o nome do cliente:");

        List<Conta> listaContas = lerArquivoContas();
        Banco banco = criarBancoComContas(listaContas);

        String nomeBanco = JOptionPane.showInputDialog("Digite o nome do banco:");

        double saldoTotal = banco.calcularSaldoTotal();

        escreverArquivoSaldoGeral(nomeBanco, saldoTotal);
    }

    public static List<Conta> lerArquivoContas() {
        List<Conta> contas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("ArquivoT.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 3) {
                    String agencia = partes[0];
                    String numero = partes[1];
                    double saldo = Double.parseDouble(partes[2]);
                    Conta conta = new Conta(agencia, numero, saldo);
                    contas.add(conta);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contas;
    }

    public static Banco criarBancoComContas(List<Conta> contas) {
        Banco banco = new Banco("Nome do Banco: C:\\Users\\NOTEDELL\\IdeaProjects");
        for (Conta conta : contas) {
            banco.adicionarConta(conta);
        }
        return banco;
    }

    public static void escreverArquivoSaldoGeral(String nomeBanco, double saldoTotal) {
        try (FileWriter writer = new FileWriter("saldo_final.txt")) {
            writer.write("Banco " + nomeBanco + " possui o saldo final de contas de: " + saldoTotal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
