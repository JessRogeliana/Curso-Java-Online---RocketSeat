package br.com.jessicarodrigues.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

/* O nome da classe precisa ser o mesmo do arquivo. O nome de clásse começa sempre com letra maiuscula */

@RestController
@RequestMapping("/users")

public class UserController {

    /* Modificadores (Tipo de acesso):
    * Public; Private; Protected
    */
    
    /* Tipos de rotorno do metodo:
     * String; Intenger(Int); Double(NumCasasDecimais); Float(numeroDecimalTbm); Char(A C ); Date; Void(sem retorno)
    */

    @Autowired //Essa anotation serve para o spring gerenciar, instanciar.. todo o ciclo de vida o spring fica responsável
    private IUserRepository userRepository; // Aqui chamo a interface
    
    @PostMapping("/")

    /* @RequestBody significa que eu quero que determinada info venha dentro do corpo para identificação do springboot */

    /* O print/console.log do java é "System.out.printl()" */
    
    //O tipo ResponseEntity serve para retornar casos de suesso ou erro (mais de um retorno/resposta)
    public ResponseEntity crate(@RequestBody UserModel userModel) {

        var user = this.userRepository.findByUsername(userModel.getUsername());

        if(user != null) {
            //return ResponseEntity.status(400) -> pode colocar o numero caso já saiba qual status
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

        /*Utilizando a lib BCrypt (add ao pom) para criptografar as senhas - Importante para a segurança dos dados da aplicação.*/
        var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashred);

        var userCreated = this.userRepository.save(userModel);
        //return userCreated;
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }

}
