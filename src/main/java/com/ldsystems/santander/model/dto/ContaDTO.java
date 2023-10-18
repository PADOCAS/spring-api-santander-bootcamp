package com.ldsystems.santander.model.dto;

import java.math.BigDecimal;
import com.ldsystems.santander.model.vo.Conta;

public record ContaDTO(Long id, String numero, String agencia, BigDecimal saldo, BigDecimal limiteconta) {

    public ContaDTO(Conta model) {
        this(model.getId(), model.getNumero(), model.getAgencia(), model.getSaldo(), model.getLimiteConta());
    }

    public Conta toModel() {
        Conta model = new Conta();

        model.setId(this.id);
        model.setNumero(this.numero);
        model.setAgencia(this.agencia);
        model.setSaldo(this.saldo);
        model.setLimiteConta(this.limiteconta);

        return model;
    }
}
