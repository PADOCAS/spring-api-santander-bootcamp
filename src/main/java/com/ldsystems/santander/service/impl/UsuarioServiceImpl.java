package com.ldsystems.santander.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ldsystems.santander.model.vo.Usuario;
import com.ldsystems.santander.repository.UsuarioRepository;
import com.ldsystems.santander.service.UsuarioService;
import com.ldsystems.santander.service.exception.BusinessException;
import com.ldsystems.santander.service.exception.NotFoundException;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Long UNCHANGEABLE_USER_ID = 1L;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    @Override
    public Usuario create(Usuario usuario) {
        Optional.ofNullable(usuario)
                .orElseThrow(() -> new BusinessException("Usuário a ser criado não pode ser nulo."));

        Optional.ofNullable(usuario.getConta())
                .orElseThrow(() -> new BusinessException("Conta do Usuário deve ser preenchida."));

        Optional.ofNullable(usuario.getCartao())
                .orElseThrow(() -> new BusinessException("Cartão do Usuário deve ser preenchido."));

        this.validateChangeableId(usuario.getId(), "Criado");

        if (usuarioRepository.existsByContaNumero(usuario.getConta().getNumero())) {
            throw new BusinessException("Este número de conta já existe!\nVerifique!!");
        }
        if (usuarioRepository.existsByCartaoNumero(usuario.getCartao().getNumero())) {
            throw new BusinessException("Este número de cartão já existe!\nVerifique!!");
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional
    @Override
    public Usuario update(Long id, Usuario usuario) {
        this.validateChangeableId(id, "alteração");

        Usuario usuarioCharged = this.findById(id);

        if (!usuarioCharged.getId().equals(usuario.getId())) {
            throw new BusinessException("Os ID's de atualização devem ser iguais.");
        }

        usuarioCharged.setNome(usuario.getNome());
        usuarioCharged.setConta(usuario.getConta());
        usuarioCharged.setCartao(usuario.getCartao());
        usuarioCharged.setListFuncionalidade(usuario.getListFuncionalidade());
        usuarioCharged.setListNoticia(usuario.getListNoticia());

        return usuarioRepository.save(usuarioCharged);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        this.validateChangeableId(id, "Exclusão");
        Usuario usuarioCharged = this.findById(id);
        usuarioRepository.delete(usuarioCharged);
    }

    private void validateChangeableId(Long id, String operation) {
        if (UNCHANGEABLE_USER_ID.equals(id)) {
            throw new BusinessException(
                    "Usuário com ID %d não pode ser %s.".formatted(UNCHANGEABLE_USER_ID, operation));
        }
    }
}
