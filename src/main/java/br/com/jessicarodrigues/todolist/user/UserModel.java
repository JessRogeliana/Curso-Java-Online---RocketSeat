package br.com.jessicarodrigues.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data; //Biblioteca project lombok adicionada ao pom.

@Data //Esse método permite que para cada atributo do model ele já add um getter e um setter para cada um. Existe tbm a opção de colocar apenas @Getters ou @Setters. Você também pode chamar o método dentro de um escopo que ele funcionará apenas nos itens que forem declarados após ele (em sequencia)
@Entity(name = "tb_users") //Essa anotação serve para relacionar dados java com a tabela utilizada

public class UserModel {
    
    /* Quando não vem especificado o modificador antes do tipo de retorno, significa que ele vai ser public. Caso seja outro precisa ser declarado antes */

    @Id // Anotation para o banco entender que esse será o id principal
    @GeneratedValue(generator = "UUID") // Anotation para o Spring data ficar responsavel pela geração automatica desse id, e que o tipo seja uuid
    
    private UUID id; //UUID cria um identificador unico.

    /*@Column(name = "usuario") -> Essa anotation é para caso eu quisesse que no banco o nome da coluna ficasse como 'usuário' mesmo aqui sendo referenciado como username. No caso vamos manter com os nomes abaixo e ele entende pelo '@data' que os valores abaixo serão colunas na table*/
    
    @Column(unique = true) // Essa anotation é como uma validação para que no banco só aceite um valor para cada atributo, uma constrição para que cada atributo seja único. Caso já exista um valor igual no banco, ele vai retornar um erro.
    private String username;
    private String name;
    private String password;

    @CreationTimestamp // Isso serve para quando o dado for gerado de forma automatica, já vai ter a info da data
    private LocalDateTime createdAt;

    /* Métodos: getters e setters -> todo atributo que queremos buscar informaçãio utilizamos o metodo get apontando pro atributo. Agora para redefinir ou inserir um valor para um atributo privado utiliza-se o set.

    // Utilizando o método Set
    public void setUsername(String username) {
        this.username = username;
    }
    //Utilizando o método Get
    public String getUserName() {
        return username;
    }
    */ 


}
