# Desafio-Full-Stack-Certo

## Apresentação
Olá, sou o Augusto e esse é o projeto Desafio Full Stack.
Consegui fazer toda a parte do back-end, utilizando Java 17 com Spring Boot 3.1.0
## Dependências: 
Spring Data JPA, MySQL Driver, Spring Boot DevTools, Spring Web, httpclient, gson.

## Breve introdução
O projeto consistem em: 
Primeiro será feito a criação de uma empresa. Onde atráves do Cep informado, será consumida a API ViaCep, que vai trazer dados do endereço encontrado através de um Cep.
A criação de um Fornecedor onde se o fornecedor for pessoa fisica será necessário inserir Rg e Data de Nascimento (Existem endpoints diferentes para fazer o CRUD de cada Fornecedor).
Existem duas entidades de Fornecedor, Fornecedor e FornecedorPessoaFisica.
E para a criação do Fornecedor deverá ser necessario passar o ID da empresa para fazer o relacionamento.
Caso a Empresa seja do Paraná (UF=PR), não será permitido cadastrar um fornecedor pessoa fisica menor de idade.
A listagem dos Fornecedores pode ser feito por filtros de Nome e CPF/CNPJ.

## EndPoints Utilizados:
#### Para utilizar  CNPJ, CPF é necessário utilizar um Gerador de CNPJ OU CPF, caso contrário não será feito a solicitação. 
https://www.4devs.com.br/gerador_de_cpf * Exemplo de gerador *

### Empresa
 GET - http://localhost:8080/empresa (Lista todas as Empresas).<br/>
 POST - http://localhost:8080/empresa (Cria Empresa) Json {"nomeFantasia": "É do Paraná","cnpj": "74.289.218/0001-44","cep":"84130000"}.<br/>
 GET - http://localhost:8080/empresa/9 (Busca empresa por ID).<br/>
 PUT - http://localhost:8080/empresa/9 (Atualiza Empresa) Passar o corpo Json igual ao de criar uma nova empresa.<br/>
DELETE - http://localhost:8080/empresa/11 (Deleta Empresa).<br/>

### Fornecedor
POST - http://localhost:8080/fornecedor/14 (Cria Fornecedor) {
		"nome": "tet",
		"cnpj": "82937607000167",
		"cep":"58360000",
	"email":"henrqiue.test"
	}<br/>
GET - http://localhost:8080/fornecedor/10 (Busca por ID)<br/>
PUT - http://localhost:8080/fornecedor/10 (Atualiza) Passar o corpo Json igual ao de criar uma nova empresa.<br/>
GET  - http://localhost:8080/fornecedor/cnpj?cnpj=2 (Busca fornecedor por CNPJ) Ele vai retornar um PAGE.<br/>
GET - http://localhost:8080/fornecedor/nome?nome=a (Busca fornecedor por Nome) Ele vai retornar um PAGE.<br/>
DELETE - http://localhost:8080/fornecedor/10 (Deleta Fornecedor)<br/>

### Fornecedor Pessoa Fisica
POST - http://localhost:8080/fornecedorPF/16 (Cria Fornecedor) {
		"nome": "tet",
		"cpf": "345.569.520-50",
		"cep":"58360000",
	"email":"henrqiue.test",
	"rg":"123456",
	"dataNascimento":"10/05/2001"
	}<br/>
PUT - http://localhost:8080/fornecedorPF/2 (Atualiza Fornecedor) {
		"nome": "teSTPUT",
		"cpf": "188.002.180-32",
		"cep":"58360000",
	"email":"testPUT",
	"rg":"123456",
	"dataNascimento":"10/05/1985"
	}<br/>
GET -  http://localhost:8080/fornecedorPF/name?name=tet (Busca por nome) Ele vai retornar um PAGE.<br/>
GET - http://localhost:8080/fornecedorPF/cpf?cpf=45 (Busca por cpf) Ele vai retornar um PAGE.<br/>

