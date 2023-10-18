package com.ldsystems.santander.service.impl;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ldsystems.santander.model.vo.Emprestimo;
import com.ldsystems.santander.model.vo.Parcela;
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

        validateParcelas(usuario.getListEmprestimo());

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
        // Valida Empréstimos:
        validateParcelas(usuario.getListEmprestimo());
        usuarioCharged.setListEmprestimo(usuario.getListEmprestimo());

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

    private void validateParcelas(List<Emprestimo> listEmprestimo) {
        Locale localeBrasil = new Locale("pt", "BR");
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(localeBrasil);

        if (listEmprestimo != null
                && !listEmprestimo.isEmpty()) {
            listEmprestimo.stream()
                    .forEach(emprestimo -> {
                        if (emprestimo.getListParcela() == null
                                || emprestimo.getListParcela().isEmpty()) {
                            StringBuilder str = new StringBuilder();
                            str.append("Informe as Parcelas para todos os empréstimos.");
                            str.append("\nEmpréstimo de Valor: " + formatoMoeda.format(emprestimo.getValor())
                                    + " sem Parcelas informadas.");
                            str.append("\n\nVerifique!");
                            throw new BusinessException(str.toString());
                        } else if (emprestimo.getValor() != null
                                && emprestimo.getValor().compareTo(
                                        emprestimo.getListParcela().stream()
                                                .map(Parcela::getValor)
                                                .reduce(BigDecimal.ZERO, BigDecimal::add)) != 0) {
                            StringBuilder strAux = new StringBuilder().append("");
                            strAux.append(
                                    "Somatório das Parcelas deve ser igual ao Valor Total do Contrato de Empréstimo.");
                            strAux.append("\nEmpréstimo de Valor: " + formatoMoeda.format(emprestimo.getValor()));
                            strAux.append("\nSoma Parcelas: " + formatoMoeda.format(emprestimo.getListParcela().stream()
                                    .map(Parcela::getValor)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add)));
                            strAux.append("\n\nVerifique!");

                            throw new BusinessException(strAux.toString());
                        }
                    });
        }
        ;
    }

}
