package br.com.jessicarodrigues.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_tasks") // Nova tabela

public class TaskModel {
    
    /*ID; Usuário(id_user); descrição; Titulo; Data de Inicio e termino; Prioridade */

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;

    @Column(length = 50) //limitando qtd caracteres do titulo
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;
    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // Criar uma exceção para quando usuário ultrapassar o limite de 50 caracteres do titulo. Sempre que criar uma exceção deve conter tbm uma tratativa para este erro
    public void setTitle(String title) throws Exception {

        if(title.length() > 50) {
           throw new Exception("O campo title deve conter no máximo 50 caracteres");
        }
        this.title = title;
    }

}
