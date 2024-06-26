<h1>Desafio de Projeto BootCamp Santander - DIO 2023 - Spring Java API</h1>
RESTful API para o BootCamp Santander da DIO, utilizando como base para o projeto - Figma (Santander).

<h5>Segue link do Bootcamp para quem tiver interesse: <a href="https://web.dio.me/track/santander-bootcamp-2023-fullstack-java-angular/" target="_blank" rel="noreferrer">FullStack Java+Angular</a></h5>


<h3>Principais Tecnologias</h3>

* Java 17

* Spring Boot 3.1.4

* Spring Data JPA

* OpenAPI (Swagger)

* Railway

* Payara 6 (Micro)

<h2><a href="https://www.figma.com/file/0ZsjwjsYlYd3timxqMWlbj/SANTANDER---Projeto-Web%2FMobile?type=design&node-id=1421-432&mode=design">Figma</a></h2>

O Figma foi utilizado para a abstração do domínio desta API, sendo útil na análise e projeto da solução.

<h2>Diagrama de Classes:</h2>

- Implementado validações de campos, relacionamentos entre as tabelas (ida e volta), registro do vínculo dos relacionamentos em tabela.
- Implementado uma funcionalidade de Empréstímos para os usuários, com sua lista de Parcelas para os pagamentos.
- Validação das parcelas x Empréstimo

```mermaid
classDiagram
  class Usuario {
    nome: String
    conta: Conta
    dataNascimento: DataNascimento
    listFuncionalidade: listFuncionalidade[]
    cartao: Cartao
    listNoticia: listNoticia[]
    listEmprestimo: listEmprestimo[]
  }

  class Conta {
    numero: String
    agencia: String
    saldo: Float
    limiteConta: Float
  }

  class Funcionalidade {
    icone: String
    descricao: String
  }

  class Cartao {
    numero: String
    limite: Float
  }

  class Noticia {
    icone: String
    descricao: String
  }

  class Emprestimo {
      nome: String
      dataEmissao: String
      valor: Float
      listParcelas: listParcelas[]
  }

  class Parcela {
      dataVencimento: String
      valor: Float
      dataPagamento: String
      jurosMora: Float
  }

  Usuario "1" --> "1" Conta
  Usuario "1" --> "*" Funcionalidade
  Usuario "1" --> "1" Cartao
  Usuario "1" --> "*" Noticia
  Usuario "0..*" --> "0..*" Emprestimo 
  Emprestimo "1" --> "1..*" Parcela 

```

<h3>Documentação da API - Swagger para testes:</h3>

<h4><a href="https://spring-api-santander-bootcamp-production.up.railway.app/swagger-ui/index.html">Swagger API Railway</a></h4>-> Esta API(Railway) ficará disponível por um período de tempo limitado.

<h4><a href="https://ldsystems.com.br:40080/spring-api-santander-bootcamp/swagger-ui/index.html">Swagger API Payara</a></h4>
