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
		
		Veiculo veiculo = new Veiculo();	
		Estacionamento estacionamento = new Estacionamento();
		Historico historico = new Historico();
		
		Veiculo corsa = new Veiculo("HXM-8MM", "CHEVROLET", "Carro","18:20");
		Veiculo fazer = new Veiculo("LOP-9813","HONDA", "Moto","01:17");
		Veiculo m4 = new Veiculo("POI-8466", "BMW", "Carro","09:15");
		Veiculo GTR = new Veiculo("GTR-2022","Nissan", "Super-Carro","01:45");
		
		estacionamento.setVagas(corsa,0);
		estacionamento.setVagas(fazer, 1);
		estacionamento.setVagas(m4, 3);
		estacionamento.setVagas(GTR, 48);
		
		
		do {
			//Fun��o Menu ficara na main
			menu();
			System.out.println("\nVagas Livres = "+ estacionamento.totalVagasLivres());
			System.out.print("\nInforme a op��o: ");
			escolha = sc.nextInt();
			
		switch(escolha) {
			
			case 0:
				System.out.print("\nSistema Encerrado.");
				break;
		
			case 1:
				preencherVaga(sc, sdf, veiculo, estacionamento);
				break;
			
			case 2:
				estacionamento.vagasDisponiveis();
				break;
				
			case 3:
				estacionamento.vagasOcupadas();
				break;
				
			case 4:
				esvaziarVaga(estacionamento, sc, sdf, veiculo,historico);
				break;
			
			case 5: 
				historico.imprimiHistorico();
				break;
				
			case 6:
				historico.calculaTotalDia();
				break;
				
			default:
				System.out.print("\nOp��o inv�lida.\n");
				break;
		}
		}while(escolha != 0);
		

		sc.close();
	}
	
	public static void menu() {
		
		System.out.print("\n|------------- Menu --------------|\n"
				+ "| 1 - Ocupar Vaga                 |\n"
				+ "| 2 - Consultar vagas dispon�veis |\n"
				+ "| 3 - Consultar vagas ocupadas    |\n"
				+ "| 4 - Dar baixa na vaga.          |\n"
				+ "| 5 - Exibir hist�rico            |\n"
				+ "| 6 - Exibir valor total recebido |\n"
				+ "| 0 - Sair.                       |\n"
				+ "|---------------------------------|");
	}
	
	public static void preencherVaga(Scanner sc, SimpleDateFormat sdf,Veiculo veiculo, 
			Estacionamento estacionamento) {
		int posicao;
		String horaEntrada = null;
		boolean validaPosicao;
		
		if(estacionamento.totalVagasLivres() > 0) {

			System.out.print("\nInforme a placa do Ve�culo: ");
			sc.nextLine();
			veiculo.setPlaca(sc.next());
			System.out.print("Informe o modelo do Ve�culo: ");
			veiculo.setModelo(sc.next());
			System.out.print("Informe o tipo do Ve�culo: ");
			veiculo.setTipo(sc.next());
			while(true) {
				System.out.print("\nInforme a hora de entrada: ");
				try {
					Date hora = sdf.parse(sc.next());
					horaEntrada = sdf.format(hora);
					veiculo.setHorarioDeEntrada(horaEntrada);
					break;
					
				}catch (ParseException e) {
					System.out.print("\nErro: Formato de hora inv�lida\n");
					System.out.println("Formato aceito HH:mm");
				}
			}
			
			while(true) {
				do {
					System.out.print("\nDigite a vaga que ir� ocupar: ");
					 posicao = sc.nextInt();
					validaPosicao = posicao > estacionamento.getVagas().length || posicao < 1;
					
					if(validaPosicao) {
						System.out.print("Erro: posi��o de vaga n�o existe !.");
					}
					
				}while(validaPosicao);
				estacionamento.setPosicao(posicao);
				
				if(estacionamento.verificaVagaOcupada()) {
					estacionamento.setVagas(veiculo, estacionamento.getPosicao());
					break;
				}
				System.out.print("Informe a vaga novamente.");
			}
			
		}else {
			System.out.println("\nEstacionamento Lotado !!!");
		}
	}
	
	public static void esvaziarVaga(Estacionamento estacionamento,Scanner sc, SimpleDateFormat sdf,
			Veiculo veiculo, Historico historico) {
		Date hora = null;
		int posicao;
		boolean validaPosicao;
		double valor;
		
		do {
			System.out.print("\nEscolha a vaga para esvaziar: ");
			 posicao = sc.nextInt();
			validaPosicao = posicao > estacionamento.getVagas().length || posicao < 1;
			if(validaPosicao) {
				System.out.println("Erro: posi��o de vaga n�o existe !.");
			}
		}while(validaPosicao);
		estacionamento.setPosicao(posicao);
		
		if (estacionamento.verificaVagaVazia()) {
			while(true) {
				System.out.print("\nInforme a Hora de sa�da: ");
				try {
					hora = sdf.parse(sc.next());
					
					if(estacionamento.validaHora(hora, veiculo)) {
						estacionamento.getVagas()[estacionamento.getPosicao()].setHorarioDeSaida(sdf.format(hora));
						break;	
					}
				}catch (ParseException e) {
					System.out.println("\nErro: Formato de hora inv�lida.");
					System.out.println("Formato aceito HH:mm");
				}
				System.out.print("\nDigite novamente.");
			}
			System.out.print("\nInforme o valor por hora: ");
			valor = sc.nextDouble();
			valor = estacionamento.calculaValorHora(valor);
			System.out.printf("\nValor a pagar = R$%.2f\n",valor);
			historico.setHistorico(estacionamento.getVagas()[estacionamento.getPosicao()], valor);
			
			estacionamento.esvaziarVaga();

		}else {
			System.out.println("\nVaga n�o possuia ve�culo !");
		}
	}

}
