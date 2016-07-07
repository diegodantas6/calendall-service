package br.com.calendall.dto.out;

public class UsuarioAtividadeOUT {

	private Long id;
	private String nome;
	private Double preco;
	private Integer tempo;
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Double getPreco() {
		return preco;
	}
	
	public Integer getTempo() {
		return tempo;
	}
	
	public UsuarioAtividadeOUT(Long id, String nome, Double preco, Integer tempo) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.tempo = tempo;
	}
	
}
