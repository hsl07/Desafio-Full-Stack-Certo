package acc.br.Desafio.FullStack.dto;

import acc.br.Desafio.FullStack.entity.Empresa;

public class EmpresaDTO {
    private String nomeFantasia;
    private String cnpj;
    private Long id;

        public EmpresaDTO() {
        }

        public EmpresaDTO(Empresa empresa) {
            nomeFantasia = empresa.getNomeFantasia();
            cnpj = empresa.getCnpj();
            id = empresa.getId();
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
}
