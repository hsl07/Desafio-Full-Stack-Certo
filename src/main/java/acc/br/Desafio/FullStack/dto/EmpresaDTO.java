package acc.br.Desafio.FullStack.dto;

import acc.br.Desafio.FullStack.entity.Empresa;
import jakarta.persistence.Column;

public class EmpresaDTO {
    private String nomeFantasia;
    private String cnpj;
    private Long id;

    private String cep;

        public EmpresaDTO() {
        }

        public EmpresaDTO(Empresa empresa) {
            nomeFantasia = empresa.getNomeFantasia();
            cnpj = empresa.getCnpj();
            id = empresa.getId();
            cep = empresa.getCep();
        }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public Long getId() {
        return id;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    @Override
    public String toString() {
        return "EmpresaDTO{" +
                "nomeFantasia='" + nomeFantasia + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", id=" + id +
                ", cep='" + cep + '\'' +
                '}';
    }
}
