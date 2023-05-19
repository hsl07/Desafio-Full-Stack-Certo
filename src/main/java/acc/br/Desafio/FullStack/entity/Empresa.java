package acc.br.Desafio.FullStack.entity;

import jakarta.persistence.*;

@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private long id;
    @Column(name = "nomeF")
    private String nomeFantasia;
    @Column(name = "cep")
    private String cep;
    @Column(name = "cnpj_em")
    private String cnpj;
    public Empresa(){}

    public Empresa(String nomeFantasia, String cep, String cpnj) {
        this.nomeFantasia = nomeFantasia;
        this.cep = cep;
        this.cnpj = cpnj;
    }

    public long getId() {
        return id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
