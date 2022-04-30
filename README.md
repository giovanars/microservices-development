# Microservices Development

Esse é um repositório destinado a entrega final do trabalho final da disciplina de Microservices Development do curso de MBA em Desenvolvimento Full Stack da FIAP.

Baseado nessa solução nesse repositório tem parte da solução técnica, onde desenvolvemos três dos microserviços mostrados na solução e uma front-end:
- ms-order (Ms Pedido, como está no desenho) 
- ms-menu  (MS Cardápio, como está no desenho) 
- ms-kitchen (MS Cozinha, como está no desenho)
- front-end

# Sobre o projeto

 Estamos utilizando docker para a orquestração e automatização dos containers. Os microserviços ms-order e ms-menu tem as imagens pré-compilada e as mesmas foram adicionadas no dokcer hub.
 
 Endereço das imagens:
- ms-order: giirochas/ms-menu
- ms-menu: giirochas/ms-order

No arquivo `docker-compose.yaml`(que está an raiz do projeto) temos configurado: 
- MongoDB
- RabbitMQ
- MS Order
- MS Menu

# Como configurar o projeto?

Para rodar o projeto basta seguir os passos:
- Rodar o comando `docker-compose up -d` na raiz do projeto para subir o ms-order e ms-menu juntamente com o Mongo e RabbitMQ.
- Entrar no caminho `front-end/cozinha/ms-cozinha` e rodar o comando `npm install && ng server` para subir o front-end
- Rodar o projeto ms-kitchen 

Após esses passos os microserviços ficaram disponiveis nas seguintes portas:
- MS Order: http://localhost:9000/health
- MS Menu: http://localhost:8000/health
- Front-end: http://localhost:4200

# Documentação Swagger

- http://localhost:9000/swagger-ui.html
- http://localhost:8000/swagger-ui.html
