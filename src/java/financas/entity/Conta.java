package financas.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQueries({
    @NamedQuery(name = "Conta.listarTodas", query = "select c from Conta c order by c.titular"),
    @NamedQuery(name = "Conta.consultarPorNome",  query = "select c from Conta c where c.titular like ?1 order by c.titular")
})
@Entity
public class Conta extends AbstractEntity {
    private String titular;
    private String banco;
    private String agencia;
    private Integer numero;
    @OneToMany(cascade=CascadeType.REMOVE, mappedBy = "conta")
    private List<Movimentacao> movimentacoes = new ArrayList<>();

    public Conta() {
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }
        
}
