package br.com.fourpark.entities;

import java.util.ArrayList;

public class Historico {
	
	private ArrayList<Estacionamento> lista = new ArrayList<>();
	
	public void setHistorico(Estacionamento estacionamento) {
		lista.add(estacionamento);
	}
	
	public void imprimirHistorico() {
		System.out.println("\n|------------------- Histórico de veículos -------------------|\n");
		if(lista.size() != 0) {
			for(Estacionamento item: lista) {
				System.out.println(item + 
				"| Saída: " + item.getHorarioDeSaida()
				+String.format("| Valor Pago: %.2f", item.getValorPagamento()));
			}
		}else {
			System.out.println("Ainda não possuem Históricos.");
		}
		
	}
	
	public void calculaTotalDia() {
		double totalDia = 0;
		for (Estacionamento item : lista ) {
			totalDia += item.getValorPagamento();
		}
		System.out.printf("\nValor total do dia: %.2f\n",totalDia);
	}
}

