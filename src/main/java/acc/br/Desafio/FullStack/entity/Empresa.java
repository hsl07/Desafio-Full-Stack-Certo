package acc.br.Desafio.FullStack.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

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
    @Column(name = "cnpj_em",unique = true)
    private String cnpj;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "empresa")
    private EnderecoEmpresa enderecoEmpresa;
    @OneToMany(mappedBy = "empresa")
    private List<EmpresaFornecedor> fornecedors;


    public Empresa(){}

    public Empresa(Optional<Empresa> empresa) {
        this.id = empresa.get().getId();
        this.nomeFantasia = empresa.get().getNomeFantasia();
        this.cep = empresa.get().getCep();
        this.cnpj = empresa.get().getCnpj();
        this.enderecoEmpresa = empresa.get().getEndereco();
    }

    public Empresa(String nomeFantasia, String cep, String cpnj) {
        this.nomeFantasia = nomeFantasia;
        this.cep = cep;
        this.cnpj = cpnj;
    }

    public void addEmpresaFornecedor(EmpresaFornecedor empresaFornecedor){
        empresaFornecedor.setEmpresa(this);
        this.fornecedors.add(empresaFornecedor);
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

    public void setEndereco(EnderecoEmpresa enderecoFonecedor) {
        this.enderecoEmpresa = enderecoFonecedor;
    }

    public EnderecoEmpresa getEndereco() {
        return enderecoEmpresa;
    }
}
