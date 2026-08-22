
# 📦 Design Pattern - E-commerce com I.A Simples

## 📌 README 3.0

Projeto desenvolvido em Java com Spring Boot, aplicando conceitos de **Design Patterns**, **Spring Data JPA** e integração com uma **Inteligência Artificial simples** para auxiliar usuários em um cenário de e-commerce de tecnologia.

Nesta versão, o sistema conta com um assistente virtual capaz de receber comandos em texto e também interpretar comandos de voz para auxiliar no cálculo de fretes.

---

## 🚀 Sobre o Projeto

Este projeto simula uma aplicação de e-commerce de tecnologia, onde o usuário pode interagir com um assistente virtual inteligente para obter informações relacionadas a entregas e fretes.

A aplicação utiliza uma I.A simples integrada ao backend para interpretar mensagens do usuário e acionar ferramentas internas do sistema, como o cálculo de frete.

---

## 🧠 Novidade da Versão 3.0

A versão 3.0 adiciona uma camada de Inteligência Artificial ao projeto.

Agora o sistema possui um assistente virtual que pode:

- Receber perguntas em linguagem natural;
- Interpretar comandos do usuário;
- Auxiliar no cálculo de fretes;
- Responder de forma amigável em Português do Brasil;
- Processar comandos enviados por texto;
- Processar comandos enviados por áudio.

---

## 🛠️ Tecnologias Utilizadas

- Java 25
- Spring Boot
- Spring Data JPA
- Jakarta EE
- Spring AI
- API de modelo generativo
- Maven
- Banco de dados relacional
- IntelliJ IDEA

---

## 🧩 Conceitos Aplicados

O projeto utiliza conceitos importantes de desenvolvimento backend, como:

- Design Patterns
- Injeção de Dependência
- Services
- Controllers
- Repositories
- Entidades JPA
- Separação de responsabilidades
- Integração com Inteligência Artificial
- Uso de ferramentas internas por meio da I.A

---

## 🤖 Funcionalidade de I.A

A aplicação possui um assistente virtual configurado para atuar como atendente de um e-commerce de tecnologia.

O assistente foi preparado para:

- Ser educado e objetivo;
- Responder sempre em Português do Brasil;
- Ajudar clientes com dúvidas sobre frete;
- Utilizar ferramentas internas para buscar valores reais;
- Interpretar mensagens simples enviadas pelo usuário;
- Extrair informações de áudio quando necessário.

Exemplo de interação por texto:


text Usuário: Quanto fica o frete para o CEP 01001-000?
Assistente: Claro! Vou verificar o valor do frete para esse CEP.

Exemplo de interação por voz:


text Usuário envia um áudio dizendo: "Quero saber o frete para entregar um notebook no CEP 20040-020."
Assistente: "Claro! Vou calcular o frete para entrega do notebook no CEP informado."

---

## 🎙️ Processamento de Áudio

Além das mensagens em texto, a versão 3.0 permite que o usuário envie arquivos de áudio.

O sistema utiliza a I.A para interpretar o conteúdo falado no áudio e identificar se o usuário está solicitando o cálculo de um frete.

Caso o áudio contenha informações suficientes, o assistente pode acionar automaticamente a ferramenta de frete e retornar uma resposta ao cliente.

---

## 📦 Cálculo de Frete com Ferramentas

A I.A não responde com valores inventados.

Ela foi configurada para utilizar ferramentas internas da aplicação sempre que precisar calcular ou consultar valores de frete.

Isso torna as respostas mais confiáveis e alinhadas com as regras reais do sistema.

---

## 🧱 Estrutura Geral do Projeto

A estrutura do projeto pode seguir uma organização semelhante a esta:


text src └── main ├── java │ └── br.com.projeto │ ├── controller │ ├── service │ ├── repository │ ├── model │ ├── dto │ └── ai └── resources ├── application.properties └── application.yml```

---

## ⚙️ Configuração da I.A

Para utilizar a integração com a I.A, é necessário configurar as credenciais do provedor utilizado.

Exemplo de configuração:


properties spring.ai.model.api-key={AI_API_KEY} spring.ai.model.base-url={AI_BASE_URL}

> Nunca coloque chaves reais diretamente no código-fonte.
> Utilize variáveis de ambiente ou arquivos de configuração seguros.

Exemplo de variável de ambiente:


bash AI_API_KEY=sua-chave-aqui AI_BASE_URL=https://api.exemplo.com

---

## ▶️ Como Executar o Projeto

### 1. Clone o repositório


bash git clone https://github.com/seu-usuario/seu-repositorio.git 

### 2. Acesse a pasta do projeto


bash cd seu-repositorio

### 3. Configure as variáveis de ambiente

Configure a chave da I.A e demais informações necessárias para o banco de dados.

Exemplo:


bash AI_API_KEY=sua-chave-aqui DB_URL=jdbc:postgresql://localhost:5432/nome_do_banco DB_USER=seu_usuario DB_PASSWORD=sua_senha 

### 4. Execute a aplicação


bash mvn spring-boot:run

Ou execute diretamente pela sua IDE.

---

## 📡 Possíveis Endpoints

A aplicação pode disponibilizar endpoints semelhantes aos exemplos abaixo:

### Enviar comando em texto


http POST /assistente/texto Content-Type: application/json

Exemplo de corpo da requisição:


json { "mensagem": "Quanto fica o frete para o CEP 01001-000?" }

### Enviar comando por áudio


http POST /assistente/audio Content-Type: multipart/form-data 

Exemplo de campo enviado:


text arquivo: audio.mp3

---

## ✅ Exemplo de Resposta


json { "resposta": "O frete para o CEP informado fica em R$ 24,90 e o prazo estimado é de 5 dias úteis." }

---

## 📚 Aprendizados da Versão 3.0

Nesta versão foram praticados conceitos como:

- Como integrar uma I.A em uma aplicação Spring Boot;
- Como criar um assistente virtual simples;
- Como trabalhar com prompts de sistema;
- Como processar mensagens em linguagem natural;
- Como enviar arquivos de áudio para interpretação;
- Como acionar ferramentas internas a partir da I.A;
- Como manter a responsabilidade de negócio dentro da aplicação.

---

## 🔐 Boas Práticas

Algumas boas práticas adotadas ou recomendadas:

- Não armazenar chaves de API no código;
- Usar variáveis de ambiente;
- Separar regras de negócio em services;
- Manter controllers simples;
- Validar entradas do usuário;
- Tratar erros de integração com serviços externos;
- Evitar que a I.A gere valores críticos sem consultar ferramentas internas;
- Registrar logs úteis para depuração.

---

## 🧪 Melhorias Futuras

Algumas ideias para próximas versões:

- Criar histórico de conversas;
- Adicionar autenticação de usuários;
- Integrar cálculo real de frete com transportadoras;
- Salvar solicitações de frete no banco de dados;
- Criar interface web para conversar com o assistente;
- Melhorar tratamento de erros em áudio;
- Adicionar suporte a mais formatos de mídia;
- Criar testes automatizados para os serviços de I.A;
- Adicionar monitoramento das chamadas feitas ao modelo de I.A.

---

## 🏷️ Versões

### Versão 1.0

- Estrutura inicial do projeto;
- Aplicação de Design Patterns;
- Criação das entidades e serviços principais.

### Versão 2.0

- Integração com banco de dados;
- Uso de Spring Data JPA;
- Melhor separação das camadas da aplicação.

### Versão 3.0

- Integração com I.A simples;
- Assistente virtual para e-commerce;
- Processamento de mensagens em texto;
- Processamento de comandos por voz;
- Uso de ferramentas internas para cálculo de frete.

---

## 👨‍💻 Autor

Projeto desenvolvido para fins de estudo, prática de arquitetura backend, Design Patterns e integração com Inteligência Artificial em aplicações Java.

---

## 📄 Licença

Este projeto é de uso educacional.

Sinta-se livre para estudar, modificar e evoluir a aplicação.
```
