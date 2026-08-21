
# DesignPattern - REST API com Java e Spring Boot

## Versão 2.0

Na versão **2.0**, o projeto evoluiu com a aplicação de conceitos de **segurança em APIs REST**, incluindo autenticação de usuários, autorização por perfil e utilização de **Token JWT** para proteger endpoints da aplicação.

Além dos conceitos de **Design Patterns**, esta versão passa a contar com um fluxo de cadastro e login de usuários, onde o usuário autenticado recebe um token JWT que deve ser enviado nas próximas requisições para acessar recursos protegidos.

---

Este projeto foi desenvolvido como meu primeiro projeto utilizando **Spring Boot**, com foco no estudo e aplicação de **Design Patterns** em uma **API REST**.

A aplicação simula um fluxo de checkout de pedidos, aplicando padrões de projeto para organizar responsabilidades, processar pagamentos, calcular fretes, integrar com uma API externa de endereço via CEP e proteger rotas utilizando autenticação com JWT.

## Objetivo do Projeto

O objetivo principal deste projeto é praticar conceitos fundamentais do desenvolvimento backend com Java e Spring Boot, incluindo:

- Criação de APIs REST
- Organização em camadas
- Uso de DTOs
- Persistência com Spring Data JPA
- Banco de dados em memória H2
- Integração com API externa usando OpenFeign
- Aplicação prática de Design Patterns
- Autenticação e autorização com Spring Security
- Geração e validação de Token JWT
- Controle de acesso por perfil de usuário

## Novidades da Versão 2.0

A versão **2.0** adiciona uma camada de segurança ao projeto, com os seguintes recursos:

- Cadastro de usuários
- Login com autenticação
- Geração de token JWT após login válido
- Validação do token recebido no header das requisições
- Proteção de endpoints com Spring Security
- Controle de usuários por tipo/perfil
- Separação entre usuários comuns e administradores
- Configuração de chave secreta para assinatura dos tokens

## Segurança com JWT

O projeto utiliza **JWT - JSON Web Token** para autenticação stateless.

Após realizar login com credenciais válidas, a API retorna um token JWT. Esse token deve ser enviado no header `Authorization` das requisições protegidas, no seguinte formato:


text Authorization: Bearer seu-token-jwt

O token é assinado com uma chave secreta configurada na aplicação e possui tempo de expiração, garantindo mais segurança no acesso aos recursos da API.

## Perfis de Usuário

O projeto possui controle de perfil de usuário, permitindo diferenciar permissões dentro da aplicação.

Tipos de usuário utilizados:

- `USER`: usuário comum da aplicação
- `ADMIN`: usuário administrador

Esse controle permite evoluir o projeto futuramente para proteger rotas específicas de acordo com o perfil do usuário autenticado.

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
- Spring Security
- JWT
- Spring Cloud OpenFeign
- H2 Database
- Maven

## Estrutura do Projeto


text src/main/java/br/com/bradescoSantanderDio/DesignPattern ├── auth │ ├── AuthorizationService.java │ ├── SecurityConfig.java │ ├── SecurityFilter.java │ └── TokenService.java ├── client │ └── ViaCepClient.java ├── controller │ ├── AuthController.java │ └── CheckoutController.java ├── dto │ ├── EnderecoDTO.java │ ├── LoginRequestDTO.java │ ├── LoginResponseDTO.java │ ├── PedidoRequestDTO.java │ ├── PedidoResponseDTO.java │ └── RegisterRequestDTO.java ├── enums │ ├── StatusPagamento.java │ ├── TipoFrete.java │ ├── TipoPagamento.java │ └── TipoUsuario.java ├── model │ ├── Pedido.java │ └── Usuario.java ├── repository │ ├── PedidoRepository.java │ └── UsuarioRepository.java ├── service │ ├── facade │ │ └── CheckoutFacade.java │ ├── factory │ │ ├── FreteExpresso.java │ │ ├── FreteFactory.java │ │ ├── FreteNormal.java │ │ └── ServicoFrete.java │ └── strategy │ ├── EstrategiaPagamento.java │ ├── PagamentoCartao.java │ └── PagamentoPix.java └── DesignPatternApplication.java``` 

## Funcionalidades

- Cadastro de usuários
- Login de usuários
- Geração de token JWT
- Validação de token JWT
- Proteção de rotas com Spring Security
- Controle de perfil de usuário
- Finalização de pedido via API REST
- Cálculo de frete conforme o tipo selecionado
- Processamento de pagamento conforme a estratégia escolhida
- Consulta de endereço por CEP utilizando a API ViaCEP
- Salvamento do pedido no banco H2
- Retorno de uma resposta com informações do pedido processado

## Endpoints de Autenticação

### Registrar Usuário

http POST /auth/register

### Login

http POST /auth/login 

### Exemplo de Requisição de Login


json { "login": "usuario@email.com", "senha": "123456" }

### Exemplo de Resposta de Login


json { "token": "token-jwt-gerado-pela-api" } 

## Endpoint Principal

### Finalizar Checkout

http POST /api/checkout

Para acessar esse endpoint, envie o token JWT no header da requisição:


text Authorization: Bearer seu-token-jwt

### Exemplo de Requisição


json { "cep": "01001000", "valorItens": 150.00, "tipoFrete": "NORMAL", "tipoPagamento": "PIX" }```

### Exemplo de Resposta


json { "id": 1, "valorFrete": 10.00, "valorTotal": 160.00, "statusPagamento": "APROVADO", "mensagem": "Pedido orquestrado e processado com sucesso!" }``` 

> Observação: os valores de enum, como `tipoFrete`, `tipoPagamento` e `tipoUsuario`, devem seguir os nomes definidos no projeto.

## Banco de Dados H2

O projeto utiliza o banco de dados em memória **H2**, ideal para testes e estudos.

Após iniciar a aplicação, o console do H2 pode ser acessado em:


text http://localhost:8080/h2-console
Configurações principais:


text JDBC URL: jdbc:h2:mem:checkoutdb User: sa Password:

## Configuração de Segurança

A chave secreta utilizada para assinar os tokens JWT é configurada no arquivo `application.properties`.

Exemplo:


properties api.security.token.secret=sua-chave-secreta

> Em projetos reais, essa chave não deve ficar exposta no código-fonte. O ideal é utilizar variáveis de ambiente ou serviços próprios para gerenciamento de secrets.

## Como Executar o Projeto

### Pré-requisitos

Antes de começar, é necessário ter instalado:

- Java
- Maven

### Passos para execução

Clone o repositório:


bash git clone <url-do-repositorio> 

Acesse a pasta do projeto:


bash cd DesignPattern

Execute o projeto com Maven:


bash ./mvnw spring-boot:run

No Windows, também é possível executar:


bash mvnw.cmd spring-boot:run

A aplicação ficará disponível em:

text http://localhost:8080

## Integração com ViaCEP

O projeto utiliza o **Spring Cloud OpenFeign** para consumir a API pública do ViaCEP.

Essa integração permite buscar informações de endereço a partir de um CEP informado na requisição.

API utilizada:


text https://viacep.com.br/ws/{cep}/json/

## Aprendizados

Durante o desenvolvimento deste projeto, foram praticados conceitos importantes como:

- Criação de endpoints REST com Spring Boot
- Separação de responsabilidades por camadas
- Uso de interfaces para desacoplamento
- Implementação prática dos padrões Facade, Factory e Strategy
- Persistência de dados com JPA
- Consumo de APIs externas
- Uso do banco H2 para testes locais
- Implementação de autenticação com Spring Security
- Geração e validação de tokens JWT
- Proteção de endpoints em APIs REST
- Controle de acesso por tipo de usuário

## Possíveis Melhorias Futuras

Algumas melhorias que podem ser implementadas futuramente:

- Validação dos dados de entrada com Bean Validation
- Tratamento global de exceções com `@ControllerAdvice`
- Documentação da API com Swagger/OpenAPI
- Testes unitários e de integração
- Refresh Token
- Criptografia de senha com BCrypt
- Persistência em banco de dados externo, como PostgreSQL ou MySQL
- Dockerização da aplicação

## Status do Projeto

Projeto atualizado para a versão **2.0**, com aplicação de conceitos de segurança, autenticação e utilização de token JWT.

Desenvolvido para fins de estudo e prática com Java, Spring Boot, REST API, Design Patterns e Spring Security.

## Autor

Desenvolvido por `<seu-nome>` como primeiro projeto com Spring Boot.
