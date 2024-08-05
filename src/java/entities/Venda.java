package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Venda implements Serializable, PersistentEntity {

    @Temporal(TemporalType.DATE)
    private Date dataVenda;
    
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;
    private boolean entrega;
    private double total;
    
    @ManyToOne
    private Cliente cliente;
    
    @ManyToMany
    private List<Instrumento> instrumentos;
    
    public void addInstrumento(Instrumento instrumento){
        instrumentos.add(instrumento);
    }



    public Venda() {}

    public Venda(Date dataVenda, FormaPagamento formaPagamento, double total, boolean entrega) {
        this.dataVenda = dataVenda;
        this.formaPagamento = formaPagamento;
        this.total = total;
        this.entrega = entrega;
    }

    public enum FormaPagamento {
        DINHEIRO("Dinheiro"),
        CARTAO("Cartão"),
        BOLETO("Boleto");

        private final String label;

        FormaPagamento(String label) {
            this.label = label;
        }

        public String getLabel() {
            return this.label;
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Instrumento> getInstrumentos() {
        return instrumentos;
    }

    public void setInstrumentos(List<Instrumento> instrumentos) {
        this.instrumentos = instrumentos;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }
    
    
    

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }
    
    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isEntrega() {
        return entrega;
    }

    public void setEntrega(boolean entrega) {
        this.entrega = entrega;
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Venda[ id=" + id + " ]";
    }
}
