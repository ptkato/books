/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.bean;

import financas.dao.DAO;
import financas.entity.Conta;
import financas.entity.Movimentacao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.transaction.Transactional;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author ciro
 */
@Named
@SessionScoped
public class ContaBean implements Serializable {

    private Conta conta = new Conta();
    private Movimentacao movimentacao = new Movimentacao();
    private List<Conta> contas = new ArrayList<>();
    private List<Movimentacao> movimentacoes = new ArrayList<>();
    private List<Conta> contasFiltradas;

    /**
     * Creates a new instance of ContaBackBean
     */
    public ContaBean() {
        DAO dao = new DAO(Conta.class);
        contas = dao.listarGenerico("Conta.listarTodas");
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    public List<Conta> getContasFiltradas() {
        return contasFiltradas;
    }

    public void setContasFiltradas(List<Conta> contasFiltradas) {
        this.contasFiltradas = contasFiltradas;
    }

    public String procurar() {
        DAO dao = new DAO(Conta.class);
        contas = dao.listarGenerico("Conta.consultarPorNome", '%' + conta.getTitular() + '%');
        return null;
    }

    public String gravar() {
        DAO dao = new DAO(Conta.class);
        dao.adicionar(conta);
        conta = new Conta();
        return null;
    }

    @Transactional
    public String excluir(Conta c) throws Exception {
        DAO dao = new DAO(Conta.class);
        dao.excluir(c.getId());
        contas.remove(c);
        FacesMessage msg = new FacesMessage("Conta excluída", c.getTitular());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return null;
    }

    public String editar(Conta c) {
        conta = c;
        return "/editar_conta";
    }

    public String alterar() {
        DAO dao = new DAO(Conta.class);
        dao.alterar(conta);
        return "/index";
    }

    public String paginaInicial() {
        conta = new Conta();
        DAO dao = new DAO(Conta.class);
        contas = dao.listarGenerico("Conta.listarTodas");
        return "/index";
    }

    public String paginaNovaConta() {
        conta = new Conta();
        return "/nova_conta";
    }

    public String paginaMovimentacoes(Conta c) {
        conta = c;
        DAO dao = new DAO(Movimentacao.class);
        movimentacoes = dao.listarGenerico("Movimentacao.listarMovimentacao", conta);
        return "/movimentacao";
    }

    public String paginaNovaMovimentacao() {
        movimentacao = new Movimentacao();
        return "/nova_movimentacao";
    }

    public String gravarMovimentacao() {
        movimentacao.setConta(conta);
        DAO dao = new DAO(Movimentacao.class);
        dao.adicionar(movimentacao);
        movimentacao = new Movimentacao();
        return null;
    }

    public boolean consultarPorNome(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null
                : filter.toString().trim();
        String valueText = (value == null) ? null
                : value.toString();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        if (valueText == null) {
            return false;
        }
        return valueText.matches("(?i).*" + filterText + ".*");
    }

    public void onEdit(RowEditEvent event) {
        Conta c = (Conta) event.getObject();
        DAO<Conta> dao = new DAO(Conta.class);
        dao.alterar(c);
        FacesMessage msg = new FacesMessage("Conta atualizada",
                                            c.getTitular());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
     public void onCancel(RowEditEvent event) {
        Conta c = (Conta) event.getObject();
        FacesMessage msg = new FacesMessage("Atualização cancelada",
                                            c.getTitular());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
