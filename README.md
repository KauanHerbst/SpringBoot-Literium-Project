[JAVA_BADGE]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[SPRING_BADGE]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[RENDER_BADGE]: https://img.shields.io/badge/Render-%46E3B7.svg?style=for-the-badge&logo=render&logoColor=white
[JWT_BADGE]: https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens
[MAVEN_BADGE]: https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white
[POSTGRES_BADGE]: https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white
[DOCKER_BADGE]: https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white

<h1 align="center" style="font-weight: bold;">Literium API 💻</h1>

![java][JAVA_BADGE]
![maven][MAVEN_BADGE]
![spring][SPRING_BADGE]
![jwt][JWT_BADGE]
![postgres][POSTGRES_BADGE]
![render][RENDER_BADGE]
![docker][DOCKER_BADGE]

<p align="center">
  <a href="#about">Sobre</a> • 
 <a href="#started">Começando</a> • 
  <a href="#routes">API Endpoints</a> • 
  <a href="#front">Front-End Literium</a> 
</p>

<p align="center">
  <br>Web API desenvolvida para o projeto Literium</b>
</p>

<h2 id="about">📌 Sobre</h2>

A Literium API é uma web API voltada para atender às demandas do front-end do Projeto [Literium](https://github.com/KauanHerbst/React-Literium-Project/), um e-commerce online especializado na venda de livros.
Essa API é responsável pela gestão de entidades cruciais no contexto do projeto, tais como Usuario, Livro, Categoria, Pedidos e Favoritos. Para implementar essa solução,
foram utilizadas tecnologias, como o framework Spring Boot, em conjunto com o Spring Data JPA. O Spring Security, integrado com JWT (JSON Web Token),
proporciona autenticação e segurança eficazes à aplicação. O PostgreSQL desempenha o papel de sistema de gerenciamento de banco de dados relacional. Além disso,
o Maven é empregado como ferramenta para automação de construção e gerenciamento de dependências.

<h2 id="started">🚀 Começando</h2>

Como rodar o projeto localmente

<h3>Pré-requisitos</h3>

Requisitos para rodar o projeto localmente

- [Git](https://maven.apache.org/download.cgi)
- [Java](https://www.java.com/pt-BR/download/windows_manual.jsp)
- [JDK](https://www.oracle.com/br/java/technologies/downloads/)
- [Maven](https://maven.apache.org/download.cgi)

<h3>Clonando</h3>

Para clonar o projeto localmente

```bash
git clone https://github.com/KauanHerbst/SpringBoot-Literium-Project.git
```

<h3>Variáveis de ambiente</h2>

Configure o `application.properties` de acordo com o banco de dados a ser utilizado, como exemplificado abaixo para o banco de dados PostgreSQL.

```yaml
spring.jpa.database=POSTGRESQL
spring.datasource.platform=postgres
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
spring.jpa.properties.hibernate.format_sql=true

spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
api.security.token.secret=${JWT_SECRET}
```

Substitua `${DATABASE_URL}`, `${DATABASE_USERNAME}` e `${DATABASE_PASSWORD}` pela URL do banco de dados, nome de usuário (username) e senha (password), respectivamente.

Substitua `${JWT_SECRET}` por uma key para o JWT

<h3>Iniciando</h3>

Para iniciar o projeto localmente

Baixar as dependências do projeto

```bash
cd SpringBoot-Literium-Project
mvn clean install
```

Iniciar a aplicação

```bash
mvn spring-boot:run
```

<h2 id="routes">📍 API Endpoints</h2>

Lista de principais rotas e seus corpos de requisição esperados (request bodies).
​
| Rota | Descrição  
|----------------------|-----------------------------------------------------
| <kbd>POST /auth/login</kbd> | Recebe o e-mail e senha do usuário e retorna as informações do usuário, juntamente com o token de autenticação [Request-Response](#get-auth-login)
| <kbd>POST /auth/valid</kbd> | Recebe um token e verifica se o token é válido, retornando um HTTP Status Code 200 [Request](#post-auth-valid)
| <kbd>POST /users</kbd> | Recebe os dados do usuário para cadastrar e retorna as informações do usuário, juntamente com o id do usuário [Request-Response](#post-users)
| <kbd>GET /users</kbd> | Recupera um Pageable com as informações dos usuários cadastrados [Response](#get-users)
| <kbd>GET /users/{id}</kbd> | Recupera as informações do usuário de acordo com o ID passado no parâmetro [Response](#get-users-id)
| <kbd>PUT /users{id}</kbd> | Recebe os dados do usuário para atualização e retorna os dados já atualizados [Request-Response](#put-users)
| <kbd>DELETE /users/{id}</kbd> | Deleta um usuário de acordo com o ID passado no parâmetro, retornando um HTTP Status Code 204
| <kbd>POST /books</kbd> | Recebe os dados do livro para cadastrar e retorna as informações do livro, juntamente com o id do livro [Request-Response](#post-books)
| <kbd>GET /books</kbd> | Recupera um Pageable com as informações dos livros cadastrados [Response](#get-books)
| <kbd>GET /books/{id}</kbd> | Recupera as informações do livro de acordo com o ID passado no parâmetro [Response](#get-books-id)
| <kbd>GET /books/{name}</kbd> | Recupera as informações do livro de acordo com o NAME passado no parâmetro [Response](#get-books-name)
| <kbd>PUT /books{id}</kbd> | Recebe os dados do livro para atualização e retorna os dados já atualizados [Request-Response](#put-books)
| <kbd>DELETE /books/{id}</kbd> | Deleta um livro de acordo com o ID passado no parâmetro, retornando um HTTP Status Code 204
| <kbd>POST /category</kbd> | Recebe os dados de uma categoria para cadastrar e retorna as informações do livro, juntamente com o id do livro [Request-Response](#post-category)
| <kbd>GET /category</kbd> | Recupera um Pageable com as informações das categorias cadastrados [Response](#get-category)
| <kbd>GET /category/{id}</kbd> | Recupera as informações de uma categoria de acordo com o ID passado no parâmetro [Response](#get-category-id)
| <kbd>GET /category/{id}/books</kbd> | Recupera um Pageable de livros de uma categoria de acordo com o ID passado no parâmetro [Response](#get-books-category)
| <kbd>PUT /category{id}</kbd> | Recebe os dados de uma categoria para atualização e retorna os dados já atualizados [Request-Response](#put-category)
| <kbd>DELETE /category/{id}</kbd> | Deleta uma categoria de acordo com o ID passado no parâmetro, retornando um HTTP Status Code 204
| <kbd>POST /favorites/{id_user}/{id_book}</kbd> | Recebe os ID do usuário e do livro para a criação de uma relação e retorna as informações salvas [Response](#post-favorites)
| <kbd>GET /favorites/{id}</kbd> | Recupera um Pageable com as informações dos favoritos de acordo com o ID passado no parâmetro [Response](#get-favorites)
| <kbd>DELETE /favorites/{id_user}/{id_book}</kbd> | Deleta uma categoria de acordo com o ID passado no parâmetro, retornando um HTTP Status Code 204
| <kbd>POST /orders/{id_user}/{id_book}</kbd> | Recebe os ID do usuário e do livro para a criação de uma relação e retorna as informações salvas [Request-Response](#post-orders)
| <kbd>GET /orders/{id}</kbd> | Recupera um Pageable com as informações dos pedidos de acordo com o ID passado no parâmetro [Response](#get-orders)
| <kbd>PUT /orders{id}</kbd> | Recebe os dados de uma pedido para atualização e retorna os dados já atualizados [Request-Response](#put-orders)
| <kbd>DELETE /orders/{id_user}/{id_book}</kbd> | Deleta um pedido de acordo com o ID passado no parâmetro, retornando um HTTP Status Code 204

<h3 id="get-auth-login">POST /auth/login</h3>

**REQUEST**

```json
{
	"email": "literium@literium.com"
	"password": "77777"
}
```

**RESPONSE**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6Im",
  "name": "Literium",
  "id": 1,
  "email": "literium@literium.com",
  "roles": [
    {
      "id": 1,
      "authority": "ROLE_USER"
    }
  ]
}
```

<h3 id="post-auth-valid">POST /auth/valid</h3>

**REQUEST**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6Im"
}
```

<h3 id="post-users">POST /users</h3>

<span> 🔒 Precisa de Token autenticado e role ADMIN</span>

**REQUEST**

```json
{
  "name": "Literium",
  "email": "literium@literium.com",
  "password": "literium",
  "roles": [
    {
      "id": 1
    }
  ]
}
```

**RESPONSE**

```json
{
  "id": 1,
  "name": "Literium",
  "email": "literium@literium.com",
  "roles": [
    {
      "id": 1,
      "authority": "ROLE_USER"
    }
  ]
}
```

<h3 id="get-users">GET /users</h3>

<span> 🔒 Precisa de Token autenticado e role ADMIN</span>

**RESPONSE**

```json
{
	"content": [
		{
			"id": 1,
			"name": ""Literium",",
			"email": "literium@literium.com",
			"roles": [
				{
					"id": 1,
					"authority": "ROLE_USER"
				}
			]
		}

	],
	"pageable": {
		"pageNumber": 0,
		"pageSize": 20,
		"sort": {
			"empty": true,
			"sorted": false,
			"unsorted": true
		},
		"offset": 0,
		"paged": true,
		"unpaged": false
	},
	"last": true,
	"totalElements": 1,
	"totalPages": 1,
	"size": 20,
	"number": 0,
	"sort": {
		"empty": true,
		"sorted": false,
		"unsorted": true
	},
	"first": true,
	"numberOfElements": 1,
	"empty": false
}
```

<h3 id="get-users-id">GET /users/{id}</h3>

<span> 🔒 Precisa de Token autenticado e role ADMIN</span>

**RESPONSE**

```json
{
  "id": 1,
  "name": "Literium",
  "email": "literium@literium.com",
  "roles": [
    {
      "id": 1,
      "authority": "ROLE_USER"
    }
  ]
}
```

<h3 id="put-users">PUT /users/{id}</h3>

<span> 🔒 Precisa de Token autenticado e role ADMIN</span>

**REQUEST**

```json
{
  "name": "Literium",
  "email": "literium@literium.com",
  "roles": [
    {
      "id": 1,
      "authority": "ROLE_USER"
    }
  ]
}
```

**RESPONSE**

```json
{
  "id": 1,
  "name": "Literium ATUALIZADO",
  "email": "literium@literium.com",
  "roles": [
    {
      "id": 1,
      "authority": "ROLE_USER"
    }
  ]
}
```

<h3 id="post-books">POST /books</h3>

<span> 🔒 Precisa de Token autenticado e role ADMIN</span>

**REQUEST**

```json
{
  "name": "Livro 1",
  "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Molestie nunc non blandit massa. Volutpat sed cras ornare arcu dui. In egestas erat imperdiet sed euismod nisi porta. Hac habitasse platea dictumst quisque sagittis purus sit amet volutpat.",
  "year": "2024",
  "author": "Author #1",
  "price": 999.0,
  "imageUrl": "https://raw.githubusercontent.com/KauanHerbst/React-Literium-Project/main/src/images/BookYellow.webp",
  "categories": [
    {
      "id": 1,
      "name": "Romance"
    }
  ]
}
```

**RESPONSE**

```json
{
  "id": 1,
  "name": "Livro 1",
  "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Molestie nunc non blandit massa. Volutpat sed cras ornare arcu dui. In egestas erat imperdiet sed euismod nisi porta. Hac habitasse platea dictumst quisque sagittis purus sit amet volutpat.",
  "year": "2024",
  "author": "Author #1",
  "price": 999.0,
  "imageUrl": "https://raw.githubusercontent.com/KauanHerbst/React-Literium-Project/main/src/images/BookYellow.webp",
  "categories": [
    {
      "id": 1,
      "name": "Romance"
    }
  ]
}
```

<h3 id="get-books">GET /books</h3>

**RESPONSE**

```json
{
  "content": [
    {
      "id": 1,
      "name": "Livro 1",
      "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Molestie nunc non blandit massa. Volutpat sed cras ornare arcu dui. In egestas erat imperdiet sed euismod nisi porta. Hac habitasse platea dictumst quisque sagittis purus sit amet volutpat.",
      "year": "2024",
      "author": "Autor #1",
      "price": 999.0,
      "imageUrl": "https://raw.githubusercontent.com/KauanHerbst/React-Literium-Project/main/src/images/BookYellow.webp",
      "categories": [
        {
          "id": 1,
          "name": "Romance"
        }
      ]
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 1,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 1,
  "totalElements": 1,
  "last": false,
  "size": 1,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "numberOfElements": 1,
  "first": true,
  "empty": false
}
```

<h3 id="get-books-id">GET /books/{id}</h3>

**RESPONSE**

```json
{
  "id": 1,
  "name": "Livro 1",
  "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Molestie nunc non blandit massa. Volutpat sed cras ornare arcu dui. In egestas erat imperdiet sed euismod nisi porta. Hac habitasse platea dictumst quisque sagittis purus sit amet volutpat.",
  "year": "2024",
  "author": "Autor 1",
  "price": 999.0,
  "imageUrl": "https://raw.githubusercontent.com/KauanHerbst/React-Literium-Project/main/src/images/BookYellow.webp",
  "categories": [
    {
      "id": 1,
      "name": "Romance"
    }
  ]
}
```

<h3 id="get-books-name">GET /books/{name}</h3>

**RESPONSE**

```json
{
  "id": 1,
  "name": "Livro 1",
  "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Molestie nunc non blandit massa. Volutpat sed cras ornare arcu dui. In egestas erat imperdiet sed euismod nisi porta. Hac habitasse platea dictumst quisque sagittis purus sit amet volutpat.",
  "year": "2024",
  "author": "Autor 1",
  "price": 999.0,
  "imageUrl": "https://raw.githubusercontent.com/KauanHerbst/React-Literium-Project/main/src/images/BookYellow.webp",
  "categories": [
    {
      "id": 1,
      "name": "Romance"
    }
  ]
}
```

<h3 id="put-books">PUT /books/{id}</h3>

<span> 🔒 Precisa de Token autenticado e role ADMIN</span>

**REQUEST**

```json
{
  "name": "Livro 1 ATUALIZADO",
  "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Molestie nunc non blandit massa. Volutpat sed cras ornare arcu dui. In egestas erat imperdiet sed euismod nisi porta. Hac habitasse platea dictumst quisque sagittis purus sit amet volutpat.",
  "year": "2024",
  "author": "Autor 1",
  "price": 999.0,
  "imageUrl": "https://raw.githubusercontent.com/KauanHerbst/React-Literium-Project/main/src/images/BookYellow.webp",
  "categories": [
    {
      "id": 1,
      "name": "Romance"
    }
  ]
}
```

**RESPONSE**

```json
{
  "id": 1,
  "name": "Livro 1 ATUALIZADO",
  "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Molestie nunc non blandit massa. Volutpat sed cras ornare arcu dui. In egestas erat imperdiet sed euismod nisi porta. Hac habitasse platea dictumst quisque sagittis purus sit amet volutpat.",
  "year": "2024",
  "author": "Autor 1",
  "price": 999.0,
  "imageUrl": "https://raw.githubusercontent.com/KauanHerbst/React-Literium-Project/main/src/images/BookYellow.webp",
  "categories": [
    {
      "id": 1,
      "name": "Romance"
    }
  ]
}
```

<h3 id="post-category">POST /category</h3>

<span> 🔒 Precisa de Token autenticado e role ADMIN</span>

**REQUEST**

```json
{
  "name": "Romance"
}
```

**RESPONSE**

```json
{
  "id": 1,
  "name": "Romance"
}
```

<h3 id="get-category">GET /category</h3>

**RESPONSE**

```json
{
  "content": [
    {
      "id": 1,
      "name": "Romance"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 1,
  "totalElements": 1,
  "last": true,
  "size": 20,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "numberOfElements": 1,
  "first": true,
  "empty": false
}
```

<h3 id="get-category-id">GET /category/{id}</h3>

**RESPONSE**

```json
{
  "id": 1,
  "name": "Romance"
}
```

<h3 id="get-books-category">GET /category/{id}/books</h3>

**RESPONSE**

```json
{
  "content": [
    {
      "id": 1,
      "name": "Livro 1",
      "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Molestie nunc non blandit massa. Volutpat sed cras ornare arcu dui. In egestas erat imperdiet sed euismod nisi porta. Hac habitasse platea dictumst quisque sagittis purus sit amet volutpat.",
      "year": "2024",
      "author": "Autor 1",
      "price": 999.0,
      "imageUrl": "https://raw.githubusercontent.com/KauanHerbst/React-Literium-Project/main/src/images/BookYellow.webp",
      "categories": [
        {
          "id": 1,
          "name": "Romance"
        }
      ]
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 1,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 8,
  "totalElements": 8,
  "last": false,
  "size": 1,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "numberOfElements": 1,
  "first": true,
  "empty": false
}
```

<h3 id="put-category">PUT /category/{id}</h3>

<span> 🔒 Precisa de Token autenticado e role ADMIN</span>

**REQUEST**

```json
{
  "name": "Romance ATUALIZADO"
}
```

**RESPONSE**

```json
{
  "id": 1,
  "name": "Romance Atualizado"
}
```

<h3 id="post-favorites">POST /favorites/insert/{id_usuario}/{id_livro}</h3>

<span> 🔒 Precisa de Token autenticado</span>

**RESPONSE**

```json
{
  "id": 1,
  "userId": 1,
  "bookId": 1
}
```

<h3 id="get-favorites">GET /favorites/{id_usuario}</h3>

<span> 🔒 Precisa de Token autenticado</span>

**RESPONSE**

```json
{
  "content": [
    {
      "id": 1,
      "userId": 1,
      "bookId": 1
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalPages": 0,
  "totalElements": 0,
  "size": 20,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "first": true,
  "numberOfElements": 0,
  "empty": true
}
```

<h3 id="post-orders">POST /orders/insert/{id_usuario}/{id_livro}</h3>

<span> 🔒 Precisa de Token autenticado</span>

**REQUEST**

```json
{
  "amount": 1
}
```

**RESPONSE**

```json
{
  "id": 1,
  "userId": 1,
  "bookId": 1,
  "amount": 1,
  "price": 999.0
}
```

<h3 id="get-orders">GET /orders/{id_usuario}</h3>

<span> 🔒 Precisa de Token autenticado</span>

**RESPONSE**

```json
{
  "content": [
    {
      "id": 1,
      "userId": 1,
      "bookId": 1,
      "amount": 1,
      "price": 999.0
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 1,
  "totalElements": 1,
  "last": true,
  "size": 20,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "numberOfElements": 1,
  "first": true,
  "empty": false
}
```

<h3 id="put-orders">PUT /orders/{id_usuario}</h3>

<span> 🔒 Precisa de Token autenticado</span>

**REQUEST**

```json
{
  "amount": 7
}
```

**RESPONSE**

```json
{
  "id": 1,
  "userId": 1,
  "bookId": 1,
  "amount": 7,
  "price": 999.0
}
```

<h2 id="front">🚪 Front-End Literium</h2>

O projeto Literium foi concebido com um Front-end que se destaca por ser uma Single Page Application (SPA), sendo desenvolvido através da utilização das tecnologias React, Javascript e Sass.

Link para o Projeto ➡️ [Literium](https://github.com/KauanHerbst/React-Literium-Project/)
