package acc.br.Desafio.FullStack.dto;

import acc.br.Desafio.FullStack.entity.Fornecedor;
import jakarta.persistence.Column;

import java.util.Optional;

public class FornecedorDTO {

    private String cnpj;

    private String nome;

    private String email;

    private String cep;

    public FornecedorDTO() {
    }

    public FornecedorDTO(String cnpj, String nome, String email, String cep) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.email = email;
        this.cep = cep;
    }

    public FornecedorDTO(Fornecedor fornecedor) {
        this.cnpj = fornecedor.getCnpj();
        this.nome = fornecedor.getNome();
        this.email = fornecedor.getEmail();
        this.cep = fornecedor.getCep();
    }

    public FornecedorDTO(Optional<Fornecedor> fornecedor) {
        this.cnpj = fornecedor.get().getCnpj();
        this.nome = fornecedor.get().getNome();
        this.email = fornecedor.get().getEmail();
        this.cep = fornecedor.get().getCep();
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    @Override
    public String toString() {
        return "FornecedorDTO{" +
                "cnpj='" + cnpj + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}
