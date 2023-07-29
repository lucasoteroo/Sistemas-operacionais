import java.util.concurrent.TimeUnit;

public class Barbeiro extends Thread {
    private Barbearia barbearia;

    public Barbeiro(Barbearia barbearia) {
        this.barbearia = barbearia;
    }

    @Override
    public void run() {
        while (true) {
            barbearia.cortarCabelo(); // Barbeiro corta o cabelo do cliente
            // Simula o tempo que o barbeiro leva para cortar o cabelo
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
