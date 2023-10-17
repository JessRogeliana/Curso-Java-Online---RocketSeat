package br.com.jessicarodrigues.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.jessicarodrigues.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/* Tudo o que temos de web, springboot, outros frameworks java é baseado no servlet */

@Component // Pedindo ao spring gerenciar minha classe component, se não ele não entende que deve passar pelo código/classe a baixo. Component é a classe mais generica do spring

/* public class FilterTaskAuth implements Filter -> Pelo próprio Servelet -> Dá apenas o servelet request. Abaixo utilizamos do springboot pois não precisa da conversão de http para response/request e fica mais simples*/
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

            // Validar a rota antes de começar as validações/filtros
            var serveletPath = request.getServletPath();
            if(serveletPath.startsWith("/tasks/")) {
                
                //Pegar a autentificação (usuario e senha)
                var authorization = request.getHeader("Authorization");

                // Substring é um metodo para dividir/separar texto/string -> recebe um valor ou dois, e eu falo o tamanho que quero remover. Nesse caso eestamos pedindo para ele remover o tamanho da palavra que não queremos (poderiamos tbm colocar a qtd de caracteres exatos. O trim() serve para remover todos os espaços, ou seja, ficaria só a 2a palavra da string. )
                var authEncoded = authorization.substring("Basic".length()).trim();

                //Decodificar em byte (array)
                byte[] authDecoded = Base64.getDecoder().decode(authEncoded);

                //Transformar o byte em string
                var authString = new String(authDecoded);

                // ["usuario", "senha"]
                String[] credentials = authString.split(":");
                String username = credentials[0];
                String password = credentials[1];

                // Validar usuário
                var user = this.userRepository.findByUsername(username);
                if(user == null) {
                    response.sendError(401); //"usuário sem autorização"
                } else {

                    // validar senha
                    var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

                    if (passwordVerify.verified) {

                        // Não precisar do usuario passar o id user, spring passar automaticamente
                        request.setAttribute("idUser", user.getId());

                        filterChain.doFilter(request, response);
                    } else {
                        response.sendError(401);
                    }

                }
                
                System.out.println("Authorization");
                System.out.println(username);
                System.out.println(password);
                 

                filterChain.doFilter(request, response);
            
            } else {
                filterChain.doFilter(request, response);
            }

        
        }

    
}
