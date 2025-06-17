Global Vinicius De Souza Sant Anna

Projeto: "Catálogo de Livros API"

Descrição: API RESTful em Spring Boot para gerenciar livros.
Tecnologias: Lista as principais tecnologias (Java, Spring Boot, JPA, H2, Maven, Swagger).
Funcionalidades: Detalha as operações suportadas pela API (criar, listar, buscar por ID, atualizar, deletar, validação, documentação).

Como Executar: Clonar o repostório e rodar o projeto localmente.

Exemplos de Requisições: Modelos de requisições POST, GET, PUT, DELETE para facilitar o teste.

exemplo de uma requisição Post:

POST /livros
Content-Type: application/json

{
    "titulo": "Homem Aranha",
    "author": "Stan Lee",
    "anoPublicacao": 1960,
    "isbn": "9780345391803"
}
