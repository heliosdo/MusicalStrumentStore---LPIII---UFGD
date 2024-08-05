package beans;

import entities.Instrumento;
import entities.Venda;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import services.VendaService;
import util.RequestParameters;

@Named(value = "vendaBean")
@ViewScoped
public class VendaBean implements Serializable {

    @EJB
    private VendaService vendaService;
    
    @Inject 
    private RequestParameters parameters;
    
    private Venda value;
    private boolean entrega;
    private boolean consultar;
    private Date dataVenda;
    private double total;
    List<Venda> vendasFiltradas; 

    @PostConstruct 
    public void init() { 
        String id = parameters.get("id"); 
        if(id == null) {
            value = new Venda(); 
        } else {
            value = vendaService.find(Long.valueOf(id)); 
        }
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }


    public Venda getValue() {
        return value;
    }

    public void setValue(Venda value) {
        this.value = value;
    }

    public boolean isEntrega() {
        return entrega;
    }

    public void setEntrega(boolean entrega) {
        this.entrega = entrega;
    }

    public boolean isConsultar() { 
        return consultar;
    }

    public void setConsultar(boolean consultar) { 
        this.consultar = consultar;
    }
    
    public void consultar(Venda value) { 
        setValue(value); 
        setConsultar(true);
    }

    public List<Venda> getVendasFiltradas() {
        return vendasFiltradas;
    }

    public void setVendasFiltradas(List<Venda> vendasFiltradas) {
        this.vendasFiltradas = vendasFiltradas;
    }


    public void reset() {
        value = new Venda();
        dataVenda = null;
        entrega = false;
        total = 0;
    }

    
    public void inserir() { 
        reset();
        setConsultar(false); 
    }
    
    public SelectItem[] getOptionsEntrega() {
        return new SelectItem[]{
            new SelectItem("", ""),
            new SelectItem(Boolean.TRUE.toString(), "Sim"),
            new SelectItem(Boolean.FALSE.toString(), "Não")
        };
    }
    
    public SelectItem[] getFormaPagamento(boolean filtrar) {
        SelectItem[] items;
        int length = Venda.FormaPagamento.values().length;
        int n = 0;
        if (filtrar) {
            items = new SelectItem[length + 1];
            items[0] = new SelectItem("", "");
            n++;
        } else {
            items = new SelectItem[length];
        }
        for (Venda.FormaPagamento formaPagamento : Venda.FormaPagamento.values()) {
            items[n++] = new SelectItem(formaPagamento, formaPagamento.getLabel());
        }
        return items;
    }


    public String save() {
        try {
            value.setEntrega(entrega);
            value.setDataVenda(dataVenda);
            calculateTotal();
            vendaService.create(value);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Venda salva com sucesso!"));
            reset();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar venda", e.getMessage()));
            return null; 
        }
    }


    public void update() {
        try {
            value.setEntrega(entrega);
            value.setDataVenda(dataVenda);
            calculateTotal(); // Calcular o total antes de atualizar.
            vendaService.edit(value);
            reset();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Venda atualizada com sucesso!"));
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar venda", e.getMessage()));
        }
    }



    public void delete() {
        try {
            vendaService.remove(value);
            reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Venda> getAll() {
        return vendaService.getAll();
    }

    
    //Função para somar cada instrumento e termos o valor total da venda.
    public void calculateTotal() {
        double total = 0;
        for (Instrumento instrumento : value.getInstrumentos()) {
            total += instrumento.getPreco();
        }
        value.setTotal(total);
    }

}
