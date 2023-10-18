package com.ldsystems.santander.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.ldsystems.santander.model.vo.Emprestimo;

public record EmprestimoDTO(Long id, String nome, LocalDate dataEmissao, BigDecimal valor,
        List<ParcelaDTO> listParcela) {

    public EmprestimoDTO(Emprestimo model) {
        this(model.getId(), model.getNome(), model.getDataEmissao(), model.getValor(),
                Optional.ofNullable(model.getListParcela()).orElse(Collections.emptyList()).stream()
                        .map(ParcelaDTO::new).collect(Collectors.toList()));
    }

    public Emprestimo toModel() {
        Emprestimo model = new Emprestimo();

        model.setId(this.id);
        model.setNome(this.nome);
        model.setDataEmissao(this.dataEmissao);
        model.setValor(this.valor);
        model.setListParcela(
                Optional.ofNullable(this.listParcela).orElse(Collections.emptyList()).stream().map(ParcelaDTO::toModel)
                        .collect(Collectors.toList()));

        return model;
    }
}