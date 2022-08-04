package br.com.glandata.api.model;

import java.util.List;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categorias")
public class CategoriaModel {

	public CategoriaModel() {}
	
	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter
	@Column(length = 50)
	@NotBlank(message = "* O nome da categoria não pode ser vazio")
	@Size(max = 20)
	private String nome;
	
	@Getter @Setter
	private String descricao;
	
	@Getter @Setter
	@OneToMany(mappedBy = "categoria")
	private List<ProdutoModel> produtos;
}
