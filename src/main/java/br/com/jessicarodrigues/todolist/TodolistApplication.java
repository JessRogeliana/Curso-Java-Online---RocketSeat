
/* 1a lição: Classes no java precisam ficar dentro de um pacote (package) */

package br.com.jessicarodrigues.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/* O "@" seguido de um nome (como o abaixo) se trata de uma anotação/annotation. Nesta caso essa anottation é uma interface que vai executar algo. Toda annotatio é uma função por trás dela que o springboot já consegue identificar*/

@SpringBootApplication /* Define que essa é a classe principal desse projeto */
public class TodolistApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	} /* Método main é o metodo principal no java para executar algo dentro da aplicação. Nesse caso o que vai inicializar a aplicação */

}

/* Tomcat é como se fosse um tradutor que intermedia a liguagem java para HTTP atraves de servlets para as requisições e respostas da aplicação */