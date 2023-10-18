package com.ldsystems.santander.model.dto;

import com.ldsystems.santander.model.vo.Funcionalidade;

public record FuncionalidadeDTO(Long id, String icone, String descricao) {

    public FuncionalidadeDTO(Funcionalidade model) {
        this(model.getId(), model.getIcone(), model.getDescricao());
    }

    public Funcionalidade toModel() {
        Funcionalidade model = new Funcionalidade();

        model.setId(this.id);
        model.setIcone(this.icone);
        model.setDescricao(this.descricao);

        return model;
    }

}
