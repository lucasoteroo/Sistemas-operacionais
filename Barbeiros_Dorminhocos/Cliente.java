import java.util.concurrent.TimeUnit;

public class Cliente extends Thread {
    private Barbearia barbearia;

    public Cliente(Barbearia barbearia) {
        this.barbearia = barbearia;
    }

    @Override
    public void run() {
        barbearia.sentarEsperar(); // Cliente chega e tenta sentar em uma cadeira de espera

        // Verifica se conseguiu uma cadeira antes de acordar o barbeiro
        if (barbearia.getClientesRejeitados() == 0) {
            // Simula o tempo que o barbeiro leva para atender o cliente
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            barbearia.acordarBarbeiro(); // Cliente acorda o barbeiro após conseguir uma cadeira
        }
    }
}
