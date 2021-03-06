package br.com.fourpark.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Estacionamento {
	
	private static final int TOTAL_VAGAS = 50;
	private Integer posicao, ocupadas, livres;
	double valorPagamento;
	//Outra opc?o criar um HashMap e passar Objeto veiculo, HoraEntrada e Saida como parametros
	//HashMap<Integer,Estacionamento> vagas = new HashMap<>();
	private Estacionamento vaga[] = new Estacionamento[TOTAL_VAGAS];
	private Veiculo veiculo = new Veiculo();
	private String horarioDeEntrada, horarioDeSaida;
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
	
	public Estacionamento() {
		
	}
	
	public Estacionamento(Veiculo veiculo, String horaEntrada) {
		this.veiculo = veiculo;
		this.horarioDeEntrada = horaEntrada;
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public String getHorarioDeEntrada() {
		return horarioDeEntrada;
	}

	public void setHorarioDeEntrada(String horarioDeEntrada) {
		
		this.horarioDeEntrada = horarioDeEntrada;
	}

	public String getHorarioDeSaida() {
		return horarioDeSaida;
	}

	public void setHorarioDeSaida(String horarioDeSaida) {
		this.horarioDeSaida = horarioDeSaida;
	}

	public Double getValorPagamento() {
		return valorPagamento;
	}
	
	public void setValorPagamento(Double valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	
	public Integer getPosicao() {
		return posicao;
	}

	public void setPosicao(Integer posicao) {
		this.posicao = posicao - 1;
	}

	public Estacionamento[] getVagas() {
		return vaga;
	}
	
	public void setVagas(Estacionamento estacionamento, int posicao) {
		vaga[posicao] = estacionamento;
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
			System.out.print("\n------------------ Vagas Dispon?veis ------------------\n");
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
					System.out.print("\nVaga:"+(i+1)+" - "+ getVagas()[i].getVeiculo());
				}
			}
			System.out.println();
		}else {
			System.out.println("\nN?o possuem vagas ocupadas.");
		}
		
	}
	
	public boolean verificaVagaVazia() {
		
		if ( getVagas()[getPosicao()] != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean validaHora(Date hora) {
		
		boolean validador = false;
			try {
				if(hora.before(sdf.parse(getVagas()[getPosicao()].getHorarioDeEntrada()))) {
					System.err.println("Erro: Hora de sa?da menor que de entrada !!"); 
					validador = false;
				}else if(hora.equals(sdf.parse(getVagas()[getPosicao()].getHorarioDeEntrada()))) {
					System.err.println("Erro: Hora de sa?da igual a de entrada !!");
					validador = false;
			}else {
				System.out.println("Hora de saida aceita.");
				validador = true;
			}
		}catch(ParseException e) {
			System.err.println("Erro: N?o foi poss?vel comparar a hora" + e.getStackTrace());
			validador = false;
		}
			return validador;
	}
	
	public void esvaziarVaga() {
		
		getVagas()[getPosicao()] = null;
		System.out.println("\nVaga esvaziada !");
	}
	
	public double calculaValorHora(double valorPagamento) {
		
		double valorMinuto;
		int hora,minutos,horaEntrada,minutosEntrada,horaSaida,minutosSaida;
		// 18:30 
		horaEntrada =  Integer.parseInt(getVagas()[getPosicao()].getHorarioDeEntrada().substring(0,2));
		minutosEntrada = Integer.parseInt(getVagas()[getPosicao()].getHorarioDeEntrada().substring(3,5));
		
		horaSaida =  Integer.parseInt(getVagas()[getPosicao()].getHorarioDeSaida().substring(0,2));
		minutosSaida = Integer.parseInt(getVagas()[getPosicao()].getHorarioDeSaida().substring(3,5));
		
		hora = horaSaida - horaEntrada;
		minutos = minutosSaida - minutosEntrada;
		
		valorMinuto = valorPagamento / 60;
		
		valorPagamento = (minutos*valorMinuto) + hora * valorPagamento;
		
		return valorPagamento;
	}

	@Override
	public String toString() {
		return veiculo + " | Entrada = " + horarioDeEntrada;
	}
	
	
}
