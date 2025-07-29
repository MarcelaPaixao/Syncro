# Syncro

## Objetivo

O **Syncro** é uma aplicação de gestão cuja função é centralizar a organização de trabalhos em grupo, deixando visível tudo o que deve ser feito, por quem deve ser feito, os devidos prazos de cada tarefa, feedbacks e o progresso entre os membros, tudo em um só lugar.

A aplicação resolve o problema de falha comunicativa entre os membros de um grupo, evitando, assim, retrabalhos ou projetos "Frankenstein" (quando os membros fazem suas partes separadamente e, ao juntá-las, elas não ficam coesas).

## Frameworks e Tecnologias Utilizadas

Para a implementação deste projeto, foram utilizados:

* **Backend:** Spring Boot
* **Frontend:** Vue.js
* **Automação de Build:** Maven
* **Gerenciador de Pacotes:** npm
* **Versionamento:** Git (GitHub)
* **Banco de Dados:** PostgreSQLL

## Como Executar o Sistema

Siga os passos abaixo para executar a aplicação em seu ambiente local.

### **Pré-requisitos**

* Java (versão 17 ou superior)
* Maven
* Node.js e npm
* PostgreSQL

### Passos de Instalação e Execução

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/MarcelaPaixao/Syncro](https://github.com/MarcelaPaixao/Syncro)
    ```

2.  **Abra o repositório** na sua IDE de preferência e localize o arquivo `application.properties` no seguinte caminho:
    `Syncro/src/main/resources/application.properties`

3.  **Adapte as configurações do banco de dados** para sua preferência. Você pode alterar o nome de usuário, senha e a porta do PostgreSQL se desejar.

4.  **Abra o terminal e conecte-se ao PostgreSQL:**
    ```sql
    psql -U (nome do seu usuário)
    ```

5.  **Ainda no terminal, crie a base de dados `syncrodb`:**
    ```sql
    CREATE DATABASE syncrodb;
    ```

6.  **Abra um novo terminal** e navegue até a pasta do backend:
    ```bash
    cd Syncro/Syncro
    ```

7.  **Inicie o Spring Boot:**
    ```bash
    mvn spring-boot:run
    ```

8.  **Em outro terminal,** navegue até a pasta do frontend e instale as dependências:
    ```bash
    cd Syncro/frontend_syncro
    npm install
    ```

9.  **Execute o frontend:**
    ```bash
    npm run serve
    ```

10. **Abra o navegador** e acesse `http://localhost:8081` para visualizar a aplicação.


## Geração da Documentação

Para gerar a documentação com Javadoc, siga os passos:

1.  **Navegue até a pasta do projeto:**
    * No terminal, acesse o diretório que contém o arquivo `pom.xml`:
      ```bash
      cd Syncro/Syncro
      ```

2.  **Execute o comando Maven:**
    ```bash
    mvn javadoc:javadoc
    ```

3.  **Visualize a documentação:**
    * Para visualizar a documentação, abra o arquivo `index.html` em seu navegador. Ele estará no seguinte diretório:
      `Syncro/Syncro/target/reports/apidocs`
