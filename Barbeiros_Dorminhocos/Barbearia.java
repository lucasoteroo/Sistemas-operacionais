public class Barbearia {
    private int cadeirasEspera;
    private boolean barbeiroOcupado;
    private int clientesRejeitados; // Novo atributo para contar os clientes rejeitados


    public Barbearia(int capacidadeCadeiras) {
        this.cadeirasEspera = capacidadeCadeiras;
        this.barbeiroOcupado = false;
        this.clientesRejeitados = 0; // Inicializa o contador de clientes rejeitados como 0

    }

    public synchronized void sentarEsperar() {
        if (cadeirasEspera == 0) {
            System.out.println("Não há cadeiras disponíveis. Cliente saindo.");
            clientesRejeitados++; // Incrementa o contador de clientes rejeitados
            return;
        }

        cadeirasEspera--;
        System.out.println("Cliente sentou em uma cadeira de espera. Cadeiras disponíveis: " + cadeirasEspera);
        notify(); // Notifica o barbeiro de que há um cliente esperando.
    }

    public synchronized void acordarBarbeiro() {
        barbeiroOcupado = true;
        System.out.println("Barbeiro acordou e está atendendo o cliente.");
        notify(); // Notifica o barbeiro de que há um cliente para atender.
    }

    public synchronized void cortarCabelo() {
        while (!barbeiroOcupado) {
            try {
                System.out.println("Barbeiro está dormindo.");
                wait(); // Barbeiro espera até que um cliente o acorde.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Barbeiro está cortando o cabelo do cliente.");
        barbeiroOcupado = false;
        cadeirasEspera++;
    }
        // Método adicional para obter o número de clientes rejeitados
    public int getClientesRejeitados()
    {
        return clientesRejeitados;
    }
}
