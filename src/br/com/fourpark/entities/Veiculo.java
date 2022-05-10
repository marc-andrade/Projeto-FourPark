package br.com.fourpark.entities;

public class Veiculo {
	
	private String placa, modelo, tipo;
	private String horarioDeEntrada, horarioDeSaida;

	public Veiculo() {
		
	}
	
	public Veiculo(String placa, String modelo, String tipo) {
		this.placa = placa;
		this.modelo = modelo;
		this.tipo = tipo;
	}
	
	public Veiculo(String placa, String modelo, String tipo, String horarioDeEntrada) {
		this.placa = placa;
		this.modelo = modelo;
		this.tipo = tipo;
		this.horarioDeEntrada = horarioDeEntrada;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public String toString() {
		return "Placa:" + placa
				+" |Modelo:"+ modelo
				+" |Tipo:" + tipo;
	}
	
}
