import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        int capacidadeCadeiras = 5; // Define a capacidade de cadeiras de espera na barbearia
        Barbearia barbearia = new Barbearia(capacidadeCadeiras); // Cria a barbearia com a capacidade definida

        Barbeiro barbeiro = new Barbeiro(barbearia); // Cria o barbeiro, passando a barbearia onde ele trabalhará
        barbeiro.start(); // Inicia a thread do barbeiro

        // Simula a chegada de vários clientes
        for (int i = 1; i <= 10; i++) {
            Cliente cliente = new Cliente(barbearia); // Cria um cliente, passando a barbearia onde será atendido
            cliente.start(); // Inicia a thread do cliente
            try {
                TimeUnit.SECONDS.sleep(1); // Intervalo de 1 segundo entre a chegada de cada cliente
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Mostra o número total de clientes rejeitados no log após o loop
        System.out.println("Clientes rejeitados (total): " + barbearia.getClientesRejeitados());

    }
}
