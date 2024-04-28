package br.com.teste;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.ArrayList;
import java.util.List;

public class Hotel {

	List<Quarto> quartos;
	private BlockingQueue<Hospede> filaEspera;
	List<Camareira> camareiras;
	List<Recepcionista> recepcionistas;
	
	public Hotel() {
	    quartos = new ArrayList<>();
	    filaEspera = new LinkedBlockingQueue<>();
	    camareiras = new ArrayList<>();
	    recepcionistas = new ArrayList<>();
	    
	    // Inicializar os quartos
	    for (int i = 0; i < 10; i++) {
	        quartos.add(new Quarto(i + 1));
	    }
	    
	    // Inicializar as camareiras
	    for (int i = 0; i < 10; i++) {
	        camareiras.add(new Camareira(this));
	    }
	    
	    // Inicializar os recepcionistas
	    for (int i = 0; i < 5; i++) {
	        recepcionistas.add(new Recepcionista(this));
	    }
	}

	public synchronized boolean checkIn(Hospede hospede) {
	    for (Quarto quarto : quartos) {
	        if (quarto.isVago()) {
	            quarto.adicionarHospede(hospede);
	            return true;
	        }
	    }
	    return false;
	}

	public synchronized void checkOut(Hospede hospede) {
	    for (Quarto quarto : quartos) {
	        if (quarto.getHospedes().contains(hospede)) {
	            quarto.removerHospede(hospede);
	            break;
	        }
	    }
	    // Notificar camareiras para limpeza do quarto
	    notifyAll();
	}

	public synchronized boolean adicionarFilaEspera(Hospede hospede) {
	    return filaEspera.offer(hospede);
	}

	public synchronized Hospede proximoFilaEspera() {
	    return filaEspera.poll();
	}
}
