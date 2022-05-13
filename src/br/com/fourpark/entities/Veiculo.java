package br.com.fourpark.entities;

public class Veiculo {
	
	private String placa, modelo, tipo;
	

	public Veiculo() {
		
	}
	
	public Veiculo(String placa, String modelo, String tipo) {
		this.placa = placa;
		this.modelo = modelo;
		this.tipo = tipo;
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
	
	public String toString() {
		return "Placa: " + placa
				+" | Modelo: "+ modelo
				+" | Tipo: " + tipo;
	}
	
}
