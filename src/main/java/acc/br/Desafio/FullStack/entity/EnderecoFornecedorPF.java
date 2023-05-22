package acc.br.Desafio.FullStack.entity;

import acc.br.Desafio.FullStack.dto.EnderecoDTO;
import jakarta.persistence.*;

@Entity
public class EnderecoFornecedorPF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Endereco")
    private Long id;
    @Column
    private String cep;
    @Column
    private String logradouro;
    @Column
    private String complemento;
    @Column
    private String bairro;
    @Column
    private String localidade;
    @Column
    private String uf;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private FornecedorPessoaFisica fornecedor_PF;

    public EnderecoFornecedorPF() {
    }

    public EnderecoFornecedorPF(Long id, String cep, String logradouro, String complemento, String bairro, String localidade, String uf, FornecedorPessoaFisica fornecedorPF) {
        this.id = id;
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.fornecedor_PF = fornecedorPF;
    }

    public EnderecoFornecedorPF(EnderecoDTO enderecoDTO) {
        this.cep = enderecoDTO.getCep();
        this.logradouro = enderecoDTO.getLogradouro();
        this.complemento = enderecoDTO.getComplemento();
        this.bairro = enderecoDTO.getBairro();
        this.localidade = enderecoDTO.getLocalidade();
        this.uf = enderecoDTO.getUf();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public FornecedorPessoaFisica getFornecedor_PF() {
        return fornecedor_PF;
    }

    public void setFornecedor_PF(FornecedorPessoaFisica fornecedor_PF) {
        this.fornecedor_PF = fornecedor_PF;
    }
}
