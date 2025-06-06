# 🛍️ Sistema de Gerenciamento de Produtos com Java Servlet

Este projeto é uma aplicação web desenvolvida em **Java** utilizando **Servlets**, com foco no gerenciamento de produtos e autenticação de usuários.  

## ✨ Funcionalidades

### 🔐 Autenticação de Usuário
- Login com verificação de credenciais.
- Envio de e-mail de notificação em caso de login bem-sucedido.
- Encerramento de sessão (logout).

### 📦 Gerenciamento de Produtos
- **Cadastro de produtos** com nome, valor, quantidade, data de criação e categoria.
- **Atualização** e **exclusão** de produtos.
- **Listagem** de todos os produtos cadastrados.
- Integração com categorias para associação a cada produto.

## 📁 Estrutura das Servlets

### `LoginServlet.java`
Responsável pela autenticação de usuários:
- `POST /login`: realiza login e envia notificação por e-mail.
- `GET /login`: encerra a sessão do usuário (logout).

### `ProductServlet.java`
Responsável por todas as operações relacionadas a produtos:
- `POST /product`:  
  - `action=register`: cadastra novo produto  
  - `action=update`: atualiza um produto existente  
  - `action=delete`: remove um produto  
- `GET /product`:  
  - `action=list`: lista todos os produtos  
  - `action=open-edition-form`: abre o formulário de edição  
  - `action=open-form-register`: abre o formulário de cadastro  

## 🛠️ Tecnologias Utilizadas
- Java EE (Servlets)
- JSP
- JDBC (via DAO)
- HTML/CSS (Front-end)
- SMTP (envio de e-mails)
- Padrões: **DAO Pattern**, **Factory Pattern**, **MVC**

## 🧱 Camadas Envolvidas
- **Model**: `Product`, `Category`, `User`
- **DAO**: Interfaces e implementações para acesso ao banco
- **BO**: `EmailBo` para envio de e-mails
- **View**: `JSP` (formulários de login, cadastro, edição e listagem)
- **Controller**: `ProductServlet`, `LoginServlet`

## 📧 Envio de E-mails
Utiliza a classe `EmailBo` com tratamento de exceções (`EmailException`) para enviar notificações automáticas ao usuário ao realizar login com sucesso.

## 📋 Pré-requisitos
- Java 17 ou superior
- Servidor Apache Tomcat
- IDE como Eclipse ou IntelliJ
- Banco de dados Oracle (Oracle Database)
- Configuração do SMTP para envio de e-mails

## 🚀 Como Executar
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
