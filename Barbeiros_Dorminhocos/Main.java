import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        int capacidadeCadeiras = 5;
        Barbearia barbearia = new Barbearia(capacidadeCadeiras);

        Barbeiro barbeiro = new Barbeiro(barbearia);
        barbeiro.start();

        // Simula a chegada de vários clientes
        for (int i = 1; i <= 10; i++) {
            Cliente cliente = new Cliente(barbearia);
            cliente.start();
            try {
                TimeUnit.SECONDS.sleep(1); // Intervalo entre a chegada de cada cliente
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        
        // Mostra o número total de clientes rejeitados no log após o loop
        System.out.println("Clientes rejeitados (total): " + barbearia.getClientesRejeitados());

    }
}
