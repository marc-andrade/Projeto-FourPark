package br.com.fourpark.entities;

import java.util.HashMap;

public class Historico {
	
	HashMap<Veiculo,Double> lista = new HashMap<>();
	
	public void setHistorico(Veiculo veiculo,Double valor) {
		lista.put(veiculo,valor);
	}
	
	public void imprimiHistorico() {
		System.out.println("\n|------------------- Histórico de veículos -------------------|\n");
		if(lista.size() != 0) {
			
			for(Veiculo item: lista.keySet()) {
				
				System.out.println(item + 
				"| Entrada: " + item.getHorarioDeEntrada()+
				"| Saída: " + item.getHorarioDeSaida()
				+String.format("| Valor Pago: %.2f", lista.get(item)));
			}
		}else {
			System.out.println("Ainda não possuem Históricos.");
		}
		
	}
	
	public void calculaTotalDia() {
		double totalDia = 0;
		for (Veiculo item: lista.keySet()) {
			totalDia += lista.get(item);
		}
		System.out.printf("\nValor total do dia: %.2f\n",totalDia);
	}
}

