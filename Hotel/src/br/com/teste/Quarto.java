package br.com.teste;

import java.util.ArrayList;
import java.util.List;

public class Quarto {

	private int numero;
	private boolean vago;
	private List<Hospede> hospedes;
	private boolean chaveNaRecepcao;

	public Quarto(int numero) {
	    this.numero = numero;
	    vago = true;
	    hospedes = new ArrayList<>();
	    chaveNaRecepcao = true;
	}

	public synchronized boolean isVago() {
	    return vago;
	}

	public synchronized List<Hospede> getHospedes() {
	    return hospedes;
	}

	public synchronized void adicionarHospede(Hospede hospede) {
	    hospedes.add(hospede);
	    vago = false;
	}

	public synchronized void removerHospede(Hospede hospede) {
	    hospedes.remove(hospede);
	    if (hospedes.isEmpty()) {
	        vago = true;
	        chaveNaRecepcao = true;
	    }
	}

	public synchronized boolean isChaveNaRecepcao() {
	    return chaveNaRecepcao;
	}

	public synchronized void setChaveNaRecepcao(boolean chaveNaRecepcao) {
	    this.chaveNaRecepcao = chaveNaRecepcao;
	}

	public String getNumero() {
		// TODO Auto-generated method stub
		return null;
	}
}
