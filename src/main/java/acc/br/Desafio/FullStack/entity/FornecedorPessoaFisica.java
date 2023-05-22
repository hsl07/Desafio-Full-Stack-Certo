package acc.br.Desafio.FullStack.entity;

import acc.br.Desafio.FullStack.dto.FornecedorPFDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

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
  //  @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern ="dd/MM/yyyy")
    @Column(name = "Data_Nascimento", nullable = false)
    private LocalDate dataNascimento;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "fornecedor_PF")
    private EnderecoFornecedorPF endereco;

    @OneToMany(mappedBy = "fornecedorPF",cascade = CascadeType.ALL)
    private List<EmpresaFornecedor> empresas;

    public void addEmpresaFornecedor(EmpresaFornecedor empresaFornecedor){
        empresaFornecedor.setPessoaFisica(this);
        this.empresas.add(empresaFornecedor);
    }

    public FornecedorPessoaFisica() {
    }

    public FornecedorPessoaFisica(String cpf, String nome, String email, String cep, String RG, LocalDate dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.cep = cep;
        this.RG = RG;
        this.dataNascimento = dataNascimento;
    }

    public FornecedorPessoaFisica(FornecedorPFDTO fornecedorPFDTO){
        this.cpf = fornecedorPFDTO.getCpf();
        this.nome = fornecedorPFDTO.getNome();
        this.email = fornecedorPFDTO.getEmail();
        this.cep = fornecedorPFDTO.getCep();
        this.RG = fornecedorPFDTO.getRG();
        this.dataNascimento = fornecedorPFDTO.getDataNascimento();
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EnderecoFornecedorPF getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoFornecedorPF endereco) {
        this.endereco = endereco;
    }
}
