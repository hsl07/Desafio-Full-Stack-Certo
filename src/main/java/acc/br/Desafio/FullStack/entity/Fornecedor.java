package acc.br.Desafio.FullStack.entity;

import jakarta.persistence.*;

@Entity
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private long id;
    @Column(name = "cnpj_cpf", nullable = false)
    private String cnpjEcpf;
    @Column(name = "nomeF", nullable = false)
    private String nome;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "cep", nullable = false)
    private String cep;

    public Fornecedor(){}

    public Fornecedor(String cnpjEcpf, String nome, String email, String cep) {
        this.cnpjEcpf = cnpjEcpf;
        this.nome = nome;
        this.email = email;
        this.cep = cep;
    }

    public long getId() {
        return id;
    }


    public String getCnpjEcpf() {
        return cnpjEcpf;
    }

    public void setCnpjEcpf(String cnpjEcpf) {
        this.cnpjEcpf = cnpjEcpf;
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
}
