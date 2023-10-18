package com.ldsystems.santander.model.vo;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.ForeignKey;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "parcela")
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(precision = 13, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(name = "juros_mora", precision = 13, scale = 2, nullable = false)
    private BigDecimal jurosMora;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(nullable = false, name = "emprestimo_id", referencedColumnName = "id")
    @ForeignKey(name = "parcela_fk1")
    private Emprestimo emprestimo;

    public Parcela() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getJurosMora() {
        return jurosMora;
    }

    public void setJurosMora(BigDecimal jurosMora) {
        this.jurosMora = jurosMora;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Parcela other = (Parcela) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Parcela [id=" + id + ", dataVencimento=" + dataVencimento + ", dataPagamento=" + dataPagamento
                + ", valor=" + valor + ", jurosMora=" + jurosMora + "]";
    }

}
