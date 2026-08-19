# DesignPattern - REST API com Java e Spring Boot

Este projeto foi desenvolvido como meu primeiro projeto utilizando **Spring Boot**, com foco no estudo e aplicação de **Design Patterns** em uma **API REST**.

A aplicação simula um fluxo de checkout de pedidos, aplicando padrões de projeto para organizar responsabilidades, processar pagamentos, calcular fretes e integrar com uma API externa de endereço via CEP.

## Objetivo do Projeto

O objetivo principal deste projeto é praticar conceitos fundamentais do desenvolvimento backend com Java e Spring Boot, incluindo:

- Criação de APIs REST
- Organização em camadas
- Uso de DTOs
- Persistência com Spring Data JPA
- Banco de dados em memória H2
- Integração com API externa usando OpenFeign
- Aplicação prática de Design Patterns

## Design Patterns Utilizados

### Facade

O padrão **Facade** foi utilizado para centralizar e simplificar o fluxo de checkout.

A classe responsável por orquestrar o processo de compra concentra chamadas para cálculo de frete, processamento de pagamento e persistência do pedido.

Esse padrão ajuda a esconder a complexidade interna do sistema e oferece uma interface mais simples para o controller.

### Factory

O padrão **Factory** foi aplicado na escolha do serviço de frete.

Dependendo do tipo de frete informado na requisição, a aplicação seleciona a implementação correta para calcular o valor do frete.

Exemplos de tipos de frete:

- Frete Normal
- Frete Expresso

### Strategy

O padrão **Strategy** foi utilizado para o processamento de pagamentos.

Cada forma de pagamento possui sua própria estratégia de processamento, permitindo que novas formas de pagamento sejam adicionadas com menor impacto no restante da aplicação.

Exemplos de estratégias:

- Pagamento via Pix
- Pagamento via Cartão

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Spring Cloud OpenFeign
- H2 Database
- Maven

## Estrutura do Projeto
```

text src/main/java/br/com/bradescoSantanderDio/DesignPattern ├── client │ └── ViaCepClient.java ├── controller │ └── CheckoutController.java ├── dto │ ├── EnderecoDTO.java │ ├── PedidoRequestDTO.java │ └── PedidoResponseDTO.java ├── enums │ ├── StatusPagamento.java │ ├── TipoFrete.java │ └── TipoPagamento.java ├── model │ └── Pedido.java ├── repository │ └── PedidoRepository.java ├── service │ ├── facade │ │ └── CheckoutFacade.java │ ├── factory │ │ ├── FreteExpresso.java │ │ ├── FreteFactory.java │ │ ├── FreteNormal.java │ │ └── ServicoFrete.java │ └── strategy │ ├── EstrategiaPagamento.java │ ├── PagamentoCartao.java │ └── PagamentoPix.java └── DesignPatternApplication.java``` 

## Funcionalidades

- Finalização de pedido via API REST
- Cálculo de frete conforme o tipo selecionado
- Processamento de pagamento conforme a estratégia escolhida
- Consulta de endereço por CEP utilizando a API ViaCEP
- Salvamento do pedido no banco H2
- Retorno de uma resposta com informações do pedido processado

## Endpoint Principal

### Finalizar Checkout
```

http POST /api/checkout``` 

### Exemplo de Requisição
```

json { "cep": "01001000", "valorItens": 150.00, "tipoFrete": "NORMAL", "tipoPagamento": "PIX" }``` 

### Exemplo de Resposta
```

json { "id": 1, "valorFrete": 10.00, "valorTotal": 160.00, "statusPagamento": "APROVADO", "mensagem": "Pedido orquestrado e processado com sucesso!" }``` 

> Observação: os valores de enum, como `tipoFrete` e `tipoPagamento`, devem seguir os nomes definidos no projeto.

## Banco de Dados H2

O projeto utiliza o banco de dados em memória **H2**, ideal para testes e estudos.

Após iniciar a aplicação, o console do H2 pode ser acessado em:
```

text http://localhost:8080/h2-console``` 

Configurações principais:
```

text JDBC URL: jdbc:h2:mem:checkoutdb User: sa Password:``` 

## Como Executar o Projeto

### Pré-requisitos

Antes de começar, é necessário ter instalado:

- Java
- Maven

### Passos para execução

Clone o repositório:
```

bash git clone <url-do-repositorio>``` 

Acesse a pasta do projeto:
```

bash cd DesignPattern``` 

Execute o projeto com Maven:
```

bash ./mvnw spring-boot:run``` 

No Windows, também é possível executar:
```

bash mvnw.cmd spring-boot:run``` 

A aplicação ficará disponível em:
```

text http://localhost:8080``` 

## Integração com ViaCEP

O projeto utiliza o **Spring Cloud OpenFeign** para consumir a API pública do ViaCEP.

Essa integração permite buscar informações de endereço a partir de um CEP informado na requisição.

API utilizada:
```

text https://viacep.com.br/ws/{cep}/json/``` 

## Aprendizados

Durante o desenvolvimento deste projeto, foram praticados conceitos importantes como:

- Criação de endpoints REST com Spring Boot
- Separação de responsabilidades por camadas
- Uso de interfaces para desacoplamento
- Implementação prática dos padrões Facade, Factory e Strategy
- Persistência de dados com JPA
- Consumo de APIs externas
- Uso do banco H2 para testes locais

## Possíveis Melhorias Futuras

Algumas melhorias que podem ser implementadas futuramente:

- Validação dos dados de entrada com Bean Validation
- Tratamento global de exceções com `@ControllerAdvice`
- Documentação da API com Swagger/OpenAPI
- Testes unitários e de integração
- Autenticação e autorização
- Persistência em banco de dados externo, como PostgreSQL ou MySQL
- Dockerização da aplicação

## Status do Projeto

Projeto desenvolvido para fins de estudo e prática com Java, Spring Boot, REST API e Design Patterns.

## Autor

Desenvolvido por `Guest4hu` como primeiro projeto com Spring Boot.
```
