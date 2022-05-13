package br.com.fourpark.main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import br.com.fourpark.entities.Estacionamento;
import br.com.fourpark.entities.Historico;
import br.com.fourpark.entities.Veiculo;

public class Program {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		int escolha;
			
		Estacionamento estacionamento = new Estacionamento();
		Historico historico = new Historico();
		
		Estacionamento corsa = new Estacionamento(new Veiculo("HXM-8MM", "CHEVROLET", "Carro"),"22:15") ;
		Estacionamento fazer = new Estacionamento(new Veiculo("LOP-9813","HONDA", "Moto"),"18:20");
		Estacionamento m4 = new Estacionamento(new Veiculo("POI-8466", "BMW", "Carro"),"02:02");
		Estacionamento GTR = new Estacionamento(new Veiculo("GTR-2022","Nissan", "Super-Carro"),"22:05") ;
		
		estacionamento.setVagas(corsa,0);
		estacionamento.setVagas(fazer, 1);
		estacionamento.setVagas(m4, 3);
		estacionamento.setVagas(GTR, 48);
		
		
		do {
			//Função Menu ficara na main
			menu();
			System.out.println("\nVagas Livres = "+ estacionamento.totalVagasLivres());
			System.out.print("\nInforme a opção: ");
			escolha = sc.nextInt();
			
		switch(escolha) {
			
			case 0:
				System.out.print("\nSistema Encerrado.");
				break;
		
			case 1:
				preencherVaga(sc, sdf, estacionamento);
				break;
			
			case 2:
				estacionamento.vagasDisponiveis();
				break;
				
			case 3:
				estacionamento.vagasOcupadas();
				break;
				
			case 4:
				esvaziarVaga(estacionamento, sc, sdf, historico);
				break;
			
			case 5: 
				historico.imprimirHistorico();
				break;
				
			case 6:
				historico.calculaTotalDia();
				break;
				
			default:
				System.out.print("\nOpção inválida.\n");
				break;
		}
		}while(escolha != 0);
		

		sc.close();
	}
	

	public static void menu() {
		
		System.out.print("\n|------------- Menu --------------|\n"
				+ "| 1 - Ocupar Vaga                 |\n"
				+ "| 2 - Consultar vagas disponíveis |\n"
				+ "| 3 - Consultar vagas ocupadas    |\n"
				+ "| 4 - Dar baixa na vaga.          |\n"
				+ "| 5 - Exibir histórico            |\n"
				+ "| 6 - Exibir valor total recebido |\n"
				+ "| 0 - Sair.                       |\n"
				+ "|---------------------------------|");
	}
	
	public static void preencherVaga(Scanner sc, SimpleDateFormat sdf, Estacionamento estacionamento) {
		
		Veiculo veiculo = new Veiculo();
		int posicao;
		String horaEntrada = null;
		boolean validaPosicao;
		
		if(estacionamento.totalVagasLivres() > 0) {

			System.out.print("\nInforme a placa do Veículo: ");
			sc.nextLine();
			veiculo.setPlaca(sc.next());
			System.out.print("Informe o modelo do Veículo: ");
			veiculo.setModelo(sc.next());
			System.out.print("Informe o tipo do Veículo: ");
			veiculo.setTipo(sc.next());
			estacionamento.setVeiculo(veiculo);
			
			while(true) {
				System.out.print("\nInforme a hora de entrada: ");
				try {
					Date hora = sdf.parse(sc.next());
					horaEntrada = sdf.format(hora);
					estacionamento.setHorarioDeEntrada(horaEntrada);
					break;
					
				}catch (ParseException e) {
					System.err.print("\nErro: Formato de hora inválida\n");
					System.err.println("Formato aceito HH:mm");
				}
			}
			
			while(true) {
				do {
					System.out.print("\nDigite a vaga que irá ocupar: ");
					 posicao = sc.nextInt();
					validaPosicao = posicao > estacionamento.getVagas().length || posicao < 1;
					
					if(validaPosicao) {
						System.err.print("Erro: posição de vaga não existe !.");
					}
					
				}while(validaPosicao);
				estacionamento.setPosicao(posicao);
				
				if(estacionamento.verificaVagaOcupada()) {
					estacionamento.setVagas(estacionamento, estacionamento.getPosicao());
					break;
				}
				System.out.print("Informe a vaga novamente.");
			}
			
		}else {
			System.out.println("\nEstacionamento Lotado !!!");
		}
	}
	
	public static void esvaziarVaga(Estacionamento estacionamento,Scanner sc, SimpleDateFormat sdf, Historico historico) {
		
		Date hora = null;
		int posicao;
		boolean validaPosicao;
		double valor;
		
		do {
			System.out.print("\nEscolha a vaga para esvaziar: ");
			 posicao = sc.nextInt();
			validaPosicao = posicao > estacionamento.getVagas().length || posicao < 1;
			if(validaPosicao) {
				System.out.println("Erro: posição de vaga não existe !.");
			}
		}while(validaPosicao);
		estacionamento.setPosicao(posicao);
		
		if (estacionamento.verificaVagaVazia()) {
			while(true) {
				System.out.print("\nInforme a Hora de saída: ");
				try {
					hora = sdf.parse(sc.next());
					
					if(estacionamento.validaHora(hora)) {
						estacionamento.getVagas()[estacionamento.getPosicao()].setHorarioDeSaida(sdf.format(hora));
						break;	
					}
				}catch (ParseException e) {
					System.err.println("\nErro: Formato de hora inválida.");
					System.err.println("Formato aceito HH:mm");
				}
				System.out.print("\nDigite novamente.");
			}
			System.out.print("\nInforme o valor por hora: ");
			valor = sc.nextDouble();
			estacionamento.getVagas()[estacionamento.getPosicao()].setValorPagamento(estacionamento.calculaValorHora(valor));
			System.out.printf("\nValor a pagar = R$%.2f\n",estacionamento.getVagas()[estacionamento.getPosicao()].getValorPagamento());
			historico.setHistorico(estacionamento.getVagas()[estacionamento.getPosicao()]);
			
			estacionamento.esvaziarVaga();

		}else {
			System.out.println("\nVaga não possuia veículo !");
		}
	}

}
