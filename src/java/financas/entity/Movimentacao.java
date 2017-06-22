package financas.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ciro
 */
@NamedQueries ({
    @NamedQuery(name = "Movimentacao.listarMovimentacao", query = "select m from Movimentacao m where m.conta = ?1 order by m.data")
})
@Entity
public class Movimentacao extends AbstractEntity {
    private Double valor;
    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;
    private String descricao;
    @Temporal(TemporalType.DATE)
    private Date data;
    @ManyToOne
    private Conta conta = new Conta();

    public Movimentacao() {
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
    
}
