# Serasa Challenge API

API RESTful desenvolvida para o desafio técnico do Serasa, hospedando um sistema de gestão de pedidos e usuários. A aplicação foi construída em Java 21 e utiliza Docker para gerenciar os containers necessários.

## Sumário

- [Funcionalidades](#funcionalidades)
- [Requisitos](#requisitos)
- [Clone o repositório](#clone-o-repositório).
- [Configuração](#configuração)
- [Acesso à Documentação](#acesso-à-documentação)

## Funcionalidades

A Library Challenge API oferece as seguintes funcionalidades:

- **Gestão de Pedidos:** Permite realizar operações CRUD nos pedidos.
- **Gestão de Usuários:** Permite realizar operações CRUD nos usuários.

A API está dividida em dois serviços principais:

- **Serviço de Pedidos** (porta 8080)
- **Serviço de Usuários** (porta 8082)

## Requisitos

Para rodar a aplicação, é necessário ter:

- Java 21 ou superior
- Maven 3.8 ou superior
- Docker

###  Clone o repositório

Clone este repositório para sua máquina local:

```bash
git clone https://github.com/gabiqassis/library-challenge.git](https://github.com/gabiqassis/serasa_challeng.git
```

## Configuração

**Instalação do JDK, Maven e Docker:**

- [Instruções para instalação do JDK](https://docs.oracle.com/en/java/javase/21/install/overview-jdk-installation.html)
- [Instruções para instalação do Maven](https://maven.apache.org/install.html)
- [Docker para Windows](https://docs.docker.com/docker-for-windows/install/)
- [Docker para Mac](https://docs.docker.com/docker-for-mac/install/)
- [Docker para Linux](https://docs.docker.com/docker-for-linux/install/)

### 1. Configuração de Maven

Para instalar as dependências do Maven, execute:

```bash
mvn install
```

### 2. Iniciando os containers Docker

O projeto utiliza dois arquivos `docker-compose.yml` para configurar os containers:

- **Banco de dados PostgreSQL** para o gerenciamento de usuários.
- **Banco de dados MySQL** para o serviço de pedidos.
- **Redis** para o gerenciamento de sessões.

Para iniciar os containers, execute o seguinte comando no diretório raiz do projeto:

```bash
docker-compose -f docker-compose-users.yml up --build
docker-compose -f docker-compose-orders.yml up --build
```
Isso iniciará os dois conjuntos de containers necessários para rodar a API.

### Acesso à Documentação

#### Postman (preferência)

Ambas as collections estão no diretório `collections`:
[collections](collections)

Importe ambas as collections para o Postman e teste os serviços
