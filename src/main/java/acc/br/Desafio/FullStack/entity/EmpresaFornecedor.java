package acc.br.Desafio.FullStack.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "empresa_fornecedor")
public class EmpresaFornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    private Empresa empresa;
    @ManyToOne
    private Fornecedor fornecedor;
    @ManyToOne
    private FornecedorPessoaFisica fornecedorPF;

    public EmpresaFornecedor() {
    }

    public EmpresaFornecedor(Long id, Empresa empresa, Fornecedor fornecedor, FornecedorPessoaFisica pessoaFisica) {
        this.id = id;
        this.empresa = empresa;
        this.fornecedor = fornecedor;
        this.fornecedorPF = pessoaFisica;
    }

    public EmpresaFornecedor(Empresa empresa, Fornecedor fornecedor){
        this.empresa = empresa;
        this.fornecedor = fornecedor;
    }

    public EmpresaFornecedor(Empresa empresa, FornecedorPessoaFisica fornecedorPF){
        this.empresa = empresa;
        this.fornecedorPF = fornecedorPF;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public FornecedorPessoaFisica getPessoaFisica() {
        return fornecedorPF;
    }

    public void setPessoaFisica(FornecedorPessoaFisica pessoaFisica) {
        this.fornecedorPF = pessoaFisica;
    }

    @Override
    public String toString() {
        return "EmpresaFornecedor{" +
                "id=" + id +
                ", empresa=" + empresa +
                ", fornecedor=" + fornecedor +
                ", pessoaFisica=" + fornecedorPF +
                '}';
    }
}
