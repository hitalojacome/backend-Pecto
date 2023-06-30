package br.com.openmind.pecto.controller;

import br.com.openmind.pecto.model.Usuarios;
import br.com.openmind.pecto.repository.UsuariosRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @PostMapping("/login") //método que confirma se email == senha
    public boolean confirmacaoUsuarios(@RequestBody Usuarios usuario) {
        Usuarios userFind = usuariosRepository.findByEmail(usuario.getEmail());

        if (userFind != null && userFind.getSenha().equals(usuario.getSenha())) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/{idUsuario}") // método para buscar um usuário através do Id
    public Optional<Usuarios> mostrarUsuarios(@PathVariable("idUsuario") Integer idUsuario) {
        return usuariosRepository.findById(idUsuario);
    }

    // crud = CREATE, READ, UPDATE and DELETE

    @PostMapping // método create (criar novos usuários)
    public String cadastrarUsuarios(@RequestBody @Valid Usuarios users) {
        try {
            usuariosRepository.save(users);
            return "Usuário cadastrado com sucesso!";
        } catch (Exception e) {
            // Capturar a exceção e retornar resposta de erro
            return "Erro ao cadastrar usuário: " + e.getMessage();
        }
    }

    @GetMapping  // método read (mostrar usuários)
    public List<Usuarios> listarUsuarios() { return usuariosRepository.findAll(); }

    @PutMapping // método updtade (atualiza alguma info dos usuários)
    public String atualizarUsuarios(@RequestBody Usuarios users) {
        usuariosRepository.save(users);
        return "Usuário atualizado com sucesso!";
    }

    @DeleteMapping("/{idUsuario}") // método delete (deleta o usuário pelo id)
    public String deletarUsuarios(@PathVariable("idUsuario") Integer idUsuario) {
        usuariosRepository.deleteById(idUsuario);
        return "Usuário deletado com sucesso!";
    }


    // Validação de erros
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
