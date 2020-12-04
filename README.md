# TheDevConf-2020-ReactiveParadigm-Example
Demo para a palestra "Paradigma reativo? Por dentro da programação reativa com Spring Webflux e Projeto Reactor" da trilha Design de Código e XP da The Developers Conference Porto Alegre Online 2020

## ⚙ Tecnologias

- Java
- Spring WebFlux
- Spring Data Reactive MongoDB
- Spring Docs OpenAPi Webflux UI

## 📥 Executar esse projeto no seu computador

- clone esse repositório: 
- instale as dependências (necessário java e maven instalado)

 ### Para conectar com o mongodb utilizando o docker (banco de dados utilizado na aplicação):

baixar a imagem do mongodb:
 ```
 docker pull mongo
 ```

rodar o container do mongodb
 ```
 docker run --name mongodb -p 27017:27017 -d mongo
 ```

verificar os processos em execução
```
docker ps
```

- execute a classe Main: ReactiveParadigmApplication
