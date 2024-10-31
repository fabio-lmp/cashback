package br.com.fabioporto.cashback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabioporto.cashback.entity.Usuario;
import br.com.fabioporto.cashback.entity.dto.AuthenticationDTO;
import br.com.fabioporto.cashback.entity.dto.LoginResponseDTO;
import br.com.fabioporto.cashback.entity.dto.RegisterDTO;
import br.com.fabioporto.cashback.entity.dto.UsuarioDTO;
import br.com.fabioporto.cashback.security.TokenService;
import br.com.fabioporto.cashback.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    
    private AuthenticationManager authenticationManager;
    private UsuarioService usuarioService;
    private TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UsuarioService usuarioService,
            TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
    }

    @SuppressWarnings({ "rawtypes"})
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        Usuario usuario = (Usuario) auth.getPrincipal();
        var token = tokenService.generateToken(usuario); 

        return ResponseEntity.ok(new LoginResponseDTO(token, new UsuarioDTO(usuario.getLogin(), usuario.getRole().toString())));
    }
    
    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO data) {
        //if (this.usuarioRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        //String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        //Usuario newUser = new Usuario(data.login(), encryptedPassword, data.role());
        Usuario newUser = new Usuario(data.login(), data.password(), data.role());

        usuarioService.create(newUser);
        
        return ResponseEntity.ok().build();
    }
}
