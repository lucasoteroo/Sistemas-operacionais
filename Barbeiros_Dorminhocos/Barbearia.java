public class Barbearia {
    private int cadeirasEspera;         // Número de cadeiras de espera disponíveis.
    private boolean barbeiroOcupado;    // Indica se o barbeiro está ocupado atendendo um cliente.
    private int clientesRejeitados;     // Novo atributo para contar os clientes rejeitados.

    public Barbearia(int capacidadeCadeiras) {
        this.cadeirasEspera = capacidadeCadeiras;
        this.barbeiroOcupado = false;
        this.clientesRejeitados = 0;    // Inicializa o contador de clientes rejeitados como 0.
    }

    // Método que representa um cliente sentando-se para esperar.
    public synchronized void sentarEsperar() {
        if (cadeirasEspera == 0) {
            System.out.println("Não há cadeiras disponíveis. Cliente saindo.");
            clientesRejeitados++;   // Incrementa o contador de clientes rejeitados.
            return;
        }

        cadeirasEspera--;
        System.out.println("Cliente sentou em uma cadeira de espera. Cadeiras disponíveis: " + cadeirasEspera);
        notify();   // Notifica o barbeiro de que há um cliente esperando.
    }

    // Método que acorda o barbeiro para atender um cliente.
    public synchronized void acordarBarbeiro() {
        barbeiroOcupado = true;
        System.out.println("Barbeiro acordou e está atendendo o cliente.");
        notify();   // Notifica o barbeiro de que há um cliente para atender.
    }

    // Método que representa o corte de cabelo realizado pelo barbeiro.
    public synchronized void cortarCabelo() {
        while (!barbeiroOcupado) {
            try {
                System.out.println("Barbeiro está dormindo.");
                wait();   // Barbeiro espera até que um cliente o acorde.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Barbeiro está cortando o cabelo do cliente.");
        barbeiroOcupado = false;
        cadeirasEspera++;
    }

    // Método adicional para obter o número de clientes rejeitados.
    public int getClientesRejeitados() {
        return clientesRejeitados;
    }
}
Os principais pontos a serem observados neste código são:

A utilização de métodos sincronizados (synchronized) para garantir que apenas uma thread possa acessar os métodos críticos da classe por vez, evitando problemas de concorrência.
O uso de wait() e notify() para fazer a comunicação entre os clientes e o barbeiro, permitindo que o barbeiro espere até que haja um cliente disponível para atender.
O atributo clientesRejeitados, que é incrementado sempre que um cliente não consegue encontrar uma cadeira de espera disponível.




