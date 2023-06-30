package br.com.openmind.pecto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Validated
@Entity
@Table(name="tb_usuarios")
public class Usuarios {

    @Id //Define como PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT
    private Integer idUsuario;

    @NotBlank(message = "O campo nome é obrigatório.")
    @Pattern(regexp = "^[A-Z].*", message = "O nome deve iniciar com letra maiúscula.")
    @Size(min = 10, message = "O nome deve ter no mínimo 10 caracteres.")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "O campo email é obrigatório.")
    @Email(message = "Email inválido.")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "O campo CPF é obrigatório.")
    @CPF(message = "CPF inválido.")
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false)
    private LocalDate nascimento;

    @NotBlank(message = "O campo senha é obrigatório.")
    @Size(min = 8, message = "A senha deve conter no mínimo 8 caracteres")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%&*\\[{;/:]).+$",
            message = "A senha deve conter pelo menos uma letra minúscula, uma letra maiúscula, um número e um caractere especial."
    )
    @Column(nullable = false)
    private String senha;

    //Getters and Setters

    public Integer getIdUsuario() { return idUsuario; }

    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }

    public LocalDate getNascimento() { return nascimento; }

    public void setNascimento(LocalDate nascimento) { this.nascimento = nascimento; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; };
}