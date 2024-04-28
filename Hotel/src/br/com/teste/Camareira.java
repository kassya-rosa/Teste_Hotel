package br.com.teste;

public class Camareira extends Thread{

	public Hotel hotel;

	public Camareira(Hotel hotel) {
	    this.hotel = hotel;
	}

	@Override
	public void run() {
	    while (true) {
	        synchronized (hotel) {
	            for (Quarto quarto : hotel.quartos) {
	                if (quarto.isChaveNaRecepcao()) {
	                    quarto.setChaveNaRecepcao(false);
	                    System.out.println("Camareira está limpando o quarto " + quarto.getNumero());
	                    try {
	                        Thread.sleep(5000); // Tempo para limpar o quarto
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                    quarto.setChaveNaRecepcao(true);
	                    System.out.println("Camareira terminou de limpar o quarto " + quarto.getNumero());
	                    hotel.notifyAll();
	                    break;
	                }
	            }
	        }
	    }
	}
}
