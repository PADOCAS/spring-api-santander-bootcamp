package com.ldsystems.santander.controller;

import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ldsystems.santander.model.dto.UsuarioDTO;
import com.ldsystems.santander.model.vo.Usuario;
import com.ldsystems.santander.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Controle de Usuários", description = "RESTful API para gerenciar usuários.") // TAG para swagger
public class UsuarioController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UsuarioService usuarioService;

    /**
     * (GET) Retorno de todos os usuarios via DTO!
     * 
     * @return
     */
    @GetMapping
    // Anotações para Swagger:
    @Operation(summary = "Buscar todos os usuários", description = "Recuperar uma lista de todos os usuários.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida!")
    })
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        var users = usuarioService.findAll();
        var usersDto = users.stream().map(UsuarioDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(usersDto);
        // return ResponseEntity.ok().body(users); // SEM utilizar DTO, passando direto
        // nosso objeto Usuario!
    }

    // SEM utilizar DTO, passando direto nosso objeto Usuario!
    // public ResponseEntity<List<Usuario>> findAll() {
    // var users = usuarioService.findAll();
    // return ResponseEntity.ok().body(users);
    // }

    /**
     * (GET) Retorno de um usuário pelo seu ID via DTO!
     * 
     * @return
     */
    @GetMapping("/{id}")
    // Anotações para Swagger:
    @Operation(summary = "Buscar um usuário por ID", description = "Recuperar um usuário especifico baseado no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida!"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        var user = usuarioService.findById(id);
        return ResponseEntity.ok(new UsuarioDTO(user));
    }

    // Sem utilizar DTO, passando direto nosso objeto Usuario!
    // public ResponseEntity<Usuario> findById(@PathVariable Long id) {
    // var user = usuarioService.findById(id);
    // return ResponseEntity.ok().body(user);
    // }

    /**
     * (POST) criando um novo usuário!!
     * 
     * @param usuarioDto
     * @return
     */
    @PostMapping
    @Operation(summary = "Criar um novo usuário", description = "Criar um novo usuário e retorne os dados do usuário criado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso!"),
            @ApiResponse(responseCode = "422", description = "Dados de usuário fornecedidos são inválidos.")
    })
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        var user = usuarioService.create(usuario);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).body(user); // Sem utilizar DTO, passando direto nosso objeto Usuario!
    }

    // VIA DTO!!
    // public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO usuarioDto)
    // {
    // var user = usuarioService.create(usuarioDto.toModel());
    // URI location = ServletUriComponentsBuilder.fromCurrentRequest()
    // .path("/{id}")
    // .buildAndExpand(user.getId())
    // .toUri();
    // return ResponseEntity.created(location).body(new UsuarioDTO(user));
    // }

    /**
     * (PUT) ALTERANDO UM USUÁRIO!
     * 
     * @param id
     * @param userDto
     * @return
     */
    @PutMapping("/{id}")
    @Operation(summary = "Alterar um usuário", description = "Alterar os dados existentes de um usuário baseado no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário alterado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado."),
            @ApiResponse(responseCode = "422", description = "Dados de usuário fornecedidos são inválidos.")
    })
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        var user = usuarioService.update(id, usuario);
        return ResponseEntity.ok(user);
    }

    // VIA DTO:
    // public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody
    // UsuarioDTO userDto) {
    // var user = usuarioService.update(id, userDto.toModel());
    // return ResponseEntity.ok(new UsuarioDTO(user));
    // }

    /**
     * (DELETE) Delete usuário pelo ID!
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Remover um usuário", description = "Excluir um usuário baseado no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
