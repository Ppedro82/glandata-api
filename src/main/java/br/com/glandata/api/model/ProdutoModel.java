package br.com.glandata.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

import lombok.Getter;
import lombok.Setter;

@Entity 
@Table(
		name = "produtos", 
		indexes = {@Index(name = "id_produto", columnList = "id")}
		)
public class ProdutoModel {
	
	public ProdutoModel() {}

	@Getter @Setter
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@Getter @Setter
	@NotBlank(message = "Informe o nome do produto")
	private String nome;
	
	@Getter @Setter
	private String descricao;
	
	@Getter @Setter
	@NotNull(message = "Informe o preço do produto")
	@Column(name = "preco", precision = 19, scale = 2, columnDefinition = "decimal(19,2)")
	@NumberFormat(pattern = "###.##")
	private BigDecimal preco;
	
	@Getter @Setter
	@NotNull(message = "Selecione uma categoria")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_categoria"))
	private CategoriaModel categoria;
	
	@Getter @Setter
	private LocalDate dataCadastro = LocalDate.now();
	
}
