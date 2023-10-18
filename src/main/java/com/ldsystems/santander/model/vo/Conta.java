package com.ldsystems.santander.model.vo;

import java.math.BigDecimal;
import org.hibernate.annotations.ForeignKey;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name = "conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, unique = true, nullable = false)
    private String numero;

    @Column(length = 10, nullable = false)
    private String agencia;

    @Column(precision = 13, scale = 2, nullable = false)
    private BigDecimal saldo;

    @Column(name = "limite_conta", precision = 13, scale = 2, nullable = false)
    private BigDecimal limiteConta;

    @JsonBackReference
    @OneToOne
    @JoinColumn(nullable = false, name = "usuario_id", referencedColumnName = "id")
    @ForeignKey(name = "conta_fk1")
    private Usuario usuario;

    public Conta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getLimiteConta() {
        return limiteConta;
    }

    public void setLimiteConta(BigDecimal limiteConta) {
        this.limiteConta = limiteConta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        Conta other = (Conta) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Conta [id=" + id + ", numero=" + numero + ", agencia=" + agencia + ", saldo=" + saldo + ", limiteConta="
                + limiteConta + "]";
    }
}
