package br.com.jessicarodrigues.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

/* Interface é um modelo/contrato que possuimos dentro da aplicação. Na interface possuimos metodos mas não a iplementação, só definição. Para executar apenas com uma classe
* O extends pemite que a interface extenda tbm tudo o que tenho dentro do jpa repository
* O sinal <> significa que a classe recebe um generator, que são atributos dinamicos
*/
public interface IUserRepository extends JpaRepository<UserModel, UUID> {

    //O spring data permite definir/criar um metodo simples como o abaixo. Ele já entende que é para fazer um 'select'/procura dentro da coluna atribuida, no caso username
    UserModel findByUsername(String username);
}
