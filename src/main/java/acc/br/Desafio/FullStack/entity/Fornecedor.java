package acc.br.Desafio.FullStack.entity;

import acc.br.Desafio.FullStack.dto.FornecedorDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Fornecedor")
    private long id;
    @Column(name = "cnpj", nullable = false)
    private String cnpj;
    @Column(name = "nomeF", nullable = false)
    private String nome;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "cep", nullable = false)
    private String cep;
    @OneToMany(mappedBy = "fornecedor",cascade = CascadeType.ALL)
    private List<EmpresaFornecedor> empresas;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "fornecedor")
    private EnderecoFonecedor enderecoFonecedor;

    public Fornecedor(){}

    public Fornecedor(String cnpj, String nome, String email, String cep) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.email = email;
        this.cep = cep;
    }

    public Fornecedor(FornecedorDTO fornecedorDTO) {
        this.cnpj = fornecedorDTO.getCnpj();
        this.nome = fornecedorDTO.getNome();
        this.email = fornecedorDTO.getEmail();
        this.cep = fornecedorDTO.getCep();
    }

    public Fornecedor(Optional<Fornecedor> fornecedorDTO) {
        this.cnpj = fornecedorDTO.get().getCnpj();
        this.nome = fornecedorDTO.get().getNome();
        this.email = fornecedorDTO.get().getEmail();
        this.cep = fornecedorDTO.get().getCep();
    }


    public void addEmpresaFornecedor(EmpresaFornecedor empresaFornecedor){
        empresaFornecedor.setFornecedor(this);
        this.empresas.add(empresaFornecedor);
    }

    public long getId() {
        return id;
    }


    public String getCnpjEcpf() {
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

    public String getCnpj() {
        return cnpj;
    }

    public List<EmpresaFornecedor> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<EmpresaFornecedor> empresas) {
        this.empresas = empresas;
    }

    public EnderecoFonecedor getEndereco() {
        return enderecoFonecedor;
    }

    public void setEndereco(EnderecoFonecedor enderecoFonecedor) {
        this.enderecoFonecedor = enderecoFonecedor;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "cnpj='" + cnpj + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}
