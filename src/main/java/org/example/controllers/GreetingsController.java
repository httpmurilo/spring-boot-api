package org.example.controllers;

import org.example.Model.User;
import org.example.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

    @Autowired
    private IUserRepository userRepository;
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }

    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {
        User usuario = new User();
        usuario.setNome(nome);
        userRepository.save(usuario);
        return nome;
    }

    @GetMapping(value = "listatodos")
    @ResponseBody
    public ResponseEntity<List<User>> listarUsuarios(){
        List<User> users = userRepository.findAll();
        return new ResponseEntity<List<User>>(users,HttpStatus.OK)
    }

    @PostMapping(value = "salvar")
    @ResponseBody
    public ResponseEntity<User> salvar(@RequestBody User user){
        User userToReturn = userRepository.save(user);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long id){
        userRepository.deleteById(id);
        return new ResponseEntity<String>("user deletado com sucesso",HttpStatus.OK);
    }

    @GetMapping(value = "buscaruserid/{id}")
    @ResponseBody
    public ResponseEntity<User> buscarUserById(@RequestParam Long id){
        User user = userRepository.findById(id).get();
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }



}
