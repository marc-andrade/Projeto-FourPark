package br.com.fourpark.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Estacionamento {
	
	private static final int TOTAL_VAGAS = 50;
	private Integer posicao, ocupadas, livres;
	//Outra opcão criar um HashMap e passar Objeto veiculo, HoraEntrada e Saida como parametros
	private Veiculo vagas[] = new Veiculo[TOTAL_VAGAS];
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
	public Integer getPosicao() {
		return posicao;
	}

	public void setPosicao(Integer posicao) {
		this.posicao = posicao - 1;
	}

	public Veiculo[] getVagas() {
		return vagas;
	}
	
	public void setVagas(Veiculo veiculo, int posicao) {
		vagas[posicao] = veiculo;
	}

	public static int getTotalVagas() {
		return TOTAL_VAGAS;
	}
	
	public boolean verificaVagaOcupada() {
		
		if(getVagas()[getPosicao()] == null) {
			
			System.out.println("Registro realizado !!");
			return true;
		}else {
			System.out.println("Erro: Vaga ocupada !!");
			return false;
		}
	}
	
	
	public Integer totalVagasLivres() {
		ocupadas = 0;
		
		for(int i=0; i < getVagas().length; i++) {
			if(getVagas()[i] != null) {
				ocupadas++;
			}	
		}
		livres = TOTAL_VAGAS - ocupadas;
		return  livres;
	}
	
	public void vagasDisponiveis() {
		
		if(totalVagasLivres() > 0) {
			System.out.print("\n------------------ Vagas Disponíveis ------------------\n");
			for(int i=0; i < getVagas().length; i++) {
				if(getVagas()[i] == null) {
					System.out.printf(" | %d",(i+1));
					if((i+1) % 10 == 0) {
						System.out.println();
					}
				}	
			}
		}else {
			System.out.println("Estacionamento Lotado !!!.");
		}
		
	}
	
	public void vagasOcupadas() {
		
		if(ocupadas > 0) {
			System.out.print("\n-------------------- Vagas Ocupadas --------------------\n");
			for(int i=0; i < getVagas().length; i++) {
				if(getVagas()[i] != null) {
					System.out.printf(" | %d",(i+1));
					if((i+1) % 10 == 0) {
						System.out.println();
					}
				}	
			}
			System.out.println();
			for(int i=0; i< getVagas().length ; i++) {
				if(getVagas()[i] != null) {
					System.out.print("\nVaga:"+(i+1)+" - "+ getVagas()[i]);
				}
			}
			System.out.println();
		}else {
			System.out.println("\nNão possuem vagas ocupadas.");
		}
		
	}
	
	public boolean verificaVagaVazia() {
		
		if ( getVagas()[getPosicao()] != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean validaHora(Date hora, Veiculo veiculo) {
		boolean validador = false;
			try {
				if(hora.before(sdf.parse(getVagas()[getPosicao()].getHorarioDeEntrada()))) {
					System.out.println("Erro: Hora de saída menor que de entrada !!"); 
					validador = false;
				}else if(hora.equals(sdf.parse(getVagas()[getPosicao()].getHorarioDeEntrada()))) {
					System.out.println("Erro: Hora de saída igual a de entrada !!");
			}else {
				System.out.println("Hora de saida aceita.");
				validador = true;
			}
		}catch(ParseException e) {
			System.out.println("Erro: Não foi possível comparar a hora" + e.getStackTrace());
			validador = false;
		}
			return validador;
	}
	
	public void esvaziarVaga() {
		
		getVagas()[getPosicao()] = null;
		System.out.println("\nVaga esvaziada !");
	}
	
	public double calculaValorHora(double valor) {
		
		double valorMinuto;
		int hora,minutos,horaEntrada,minutosEntrada,horaSaida,minutosSaida;
		
		horaEntrada =  Integer.parseInt(getVagas()[getPosicao()].getHorarioDeEntrada().substring(0,2));
		minutosEntrada = Integer.parseInt(getVagas()[getPosicao()].getHorarioDeEntrada().substring(3,5));
		
		horaSaida =  Integer.parseInt(getVagas()[getPosicao()].getHorarioDeSaida().substring(0,2));
		minutosSaida = Integer.parseInt(getVagas()[getPosicao()].getHorarioDeSaida().substring(3,5));
		
		hora = horaSaida - horaEntrada;
		minutos = minutosSaida - minutosEntrada;
		
		
		valorMinuto = valor / 60;
		
		valor = (minutos*valorMinuto) + hora * valor;
		
		return valor;
	}
}
