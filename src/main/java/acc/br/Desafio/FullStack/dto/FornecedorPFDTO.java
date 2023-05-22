package acc.br.Desafio.FullStack.dto;

import acc.br.Desafio.FullStack.entity.FornecedorPessoaFisica;
import jakarta.persistence.Column;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class FornecedorPFDTO {

    private String cpf;

    private String nome;

    private String email;

    private String cep;

    private String RG;

    private LocalDate dataNascimento;

    public FornecedorPFDTO(String cpf, String nome, String email, String cep, String RG, LocalDate dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.cep = cep;
        this.RG = RG;
        this.dataNascimento = dataNascimento;
    }

    public FornecedorPFDTO(FornecedorPessoaFisica fornecedorPessoaFisica) {
        this.cpf = fornecedorPessoaFisica.getCpf();
        this.nome = fornecedorPessoaFisica.getNome();
        this.email = fornecedorPessoaFisica.getEmail();
        this.cep = fornecedorPessoaFisica.getCep();;
        this.RG = fornecedorPessoaFisica.getRG();
        this.dataNascimento = fornecedorPessoaFisica.getDataNascimento();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
