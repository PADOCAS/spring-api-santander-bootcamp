package com.ldsystems.santander.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ldsystems.santander.model.vo.Parcela;

public record ParcelaDTO(Long id, LocalDate dataVencimento, LocalDate dataPagamento, BigDecimal valor,
        BigDecimal jurosMora) {

    public ParcelaDTO(Parcela model) {
        this(model.getId(), model.getDataVencimento(), model.getDataPagamento(), model.getValor(),
                model.getJurosMora());
    }

    public Parcela toModel() {
        Parcela model = new Parcela();

        model.setId(this.id);
        model.setDataVencimento(this.dataVencimento);
        model.setDataPagamento(this.dataPagamento);
        model.setValor(this.valor);
        model.setJurosMora(this.jurosMora);

        return model;
    }

}
