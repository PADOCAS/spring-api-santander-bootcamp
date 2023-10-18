package com.ldsystems.santander.model.dto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.ldsystems.santander.model.vo.Usuario;

public record UsuarioDTO(
                Long id,
                String nome,
                LocalDate dataNascimento,
                ContaDTO conta,
                CartaoDTO cartao,
                List<FuncionalidadeDTO> listFuncionalidade,
                List<NoticiaDTO> listNoticia,
                List<EmprestimoDTO> listEmprestimo) {

        public UsuarioDTO(Usuario model) {
                this(model.getId(),
                                model.getNome(),
                                model.getDataNascimento(),
                                Optional.ofNullable(model.getConta()).map(ContaDTO::new).orElse(null),
                                Optional.ofNullable(model.getCartao()).map(CartaoDTO::new).orElse(null),
                                Optional.ofNullable(model.getListFuncionalidade()).orElse(Collections.emptyList())
                                                .stream()
                                                .map(FuncionalidadeDTO::new).collect(Collectors.toList()),
                                Optional.ofNullable(model.getListNoticia()).orElse(Collections.emptyList()).stream()
                                                .map(NoticiaDTO::new).collect(Collectors.toList()),
                                Optional.ofNullable(model.getListEmprestimo()).orElse(Collections.emptyList()).stream()
                                                .map(EmprestimoDTO::new).collect(Collectors.toList()));
        }

        public Usuario toModel() {
                Usuario model = new Usuario();

                model.setId(this.id);
                model.setNome(this.nome);
                model.setDataNascimento(this.dataNascimento);
                model.setConta(Optional.ofNullable(this.conta).map(ContaDTO::toModel).orElse(null));
                model.setCartao(Optional.ofNullable(this.cartao).map(CartaoDTO::toModel).orElse(null));
                model.setListFuncionalidade(
                                Optional.ofNullable(this.listFuncionalidade).orElse(Collections.emptyList()).stream()
                                                .map(FuncionalidadeDTO::toModel)
                                                .collect(Collectors.toList()));
                model.setListNoticia(
                                Optional.ofNullable(this.listNoticia).orElse(Collections.emptyList()).stream()
                                                .map(NoticiaDTO::toModel)
                                                .collect(Collectors.toList()));
                model.setListEmprestimo(
                                Optional.ofNullable(this.listEmprestimo).orElse(Collections.emptyList()).stream()
                                                .map(EmprestimoDTO::toModel)
                                                .collect(Collectors.toList()));

                return model;
        }

}
