package com.alekaue.alekfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alekaue.alekfood.api.assember.GrupoModelAssembler;
import com.alekaue.alekfood.api.model.GrupoModel;
import com.alekaue.alekfood.domain.model.Usuario;
import com.alekaue.alekfood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {
	
	@Autowired
    private CadastroUsuarioService cadastroUsuario;
    
    @Autowired
    private GrupoModelAssembler grupoModelAssembler;
    
    @GetMapping
    public List<GrupoModel> listar(@PathVariable Long usuarioId) {
    	Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
    	
    	return grupoModelAssembler.toCollectionModel(usuario.getGrupos());
    }
    
    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
    	cadastroUsuario.desassociar(usuarioId, grupoId);
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
    	cadastroUsuario.associar(usuarioId, grupoId);
    }
}
