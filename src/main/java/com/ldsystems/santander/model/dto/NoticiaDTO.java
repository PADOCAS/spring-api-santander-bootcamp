package com.ldsystems.santander.model.dto;

import com.ldsystems.santander.model.vo.Noticia;

public record NoticiaDTO(Long id, String icone, String descricao) {

    public NoticiaDTO(Noticia model) {
        this(model.getId(), model.getIcone(), model.getDescricao());
    }

    public Noticia toModel() {
        Noticia model = new Noticia();

        model.setId(this.id);
        model.setIcone(this.icone);
        model.setDescricao(this.descricao);

        return model;
    }

}
