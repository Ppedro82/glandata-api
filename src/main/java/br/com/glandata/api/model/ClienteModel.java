package br.com.glandata.api.model;

import java.time.LocalDate;

//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientes")
public class ClienteModel {
	
	public ClienteModel() {}
	
	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter
	@Size(min= 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
	@NotBlank(message = "* O nome do cliente não pode ser vazio")
	private String nome;
	
	@Getter @Setter
	@CPF(message = "* Informe um CPF válido")
	@NotBlank(message = "* CPF não pode ser vazio")
	private String cpf;
	
	@Getter @Setter
	@Email
	private String email;
	
	@Getter @Setter 
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
}
