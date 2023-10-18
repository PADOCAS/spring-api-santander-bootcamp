package com.ldsystems.santander.model.dto;

import java.math.BigDecimal;
import com.ldsystems.santander.model.vo.Cartao;

public record CartaoDTO(Long id, String numero, BigDecimal limite) {

    public CartaoDTO(Cartao model) {
        this(model.getId(), model.getNumero(), model.getLimite());
    }

    public Cartao toModel() {
        Cartao model = new Cartao();

        model.setId(this.id);
        model.setNumero(this.numero);
        model.setLimite(this.limite);

        return model;
    }

}
