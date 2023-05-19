package acc.br.Desafio.FullStack.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Entity
public class FornecedorPessoaFisica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Fornecedor")
    private long id;
    @Column(name = "CPF", nullable = false)
    private String cpf;
    @Column(name = "nomeF", nullable = false)
    private String nome;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "cep", nullable = false)
    private String cep;
    @Column(name = "RG", nullable = false)
    private String RG;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "Data_Nascimento", nullable = false)
    private LocalDate dataNascimento;
}
