package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class Instrumento implements Serializable, PersistentEntity {
    
    private String fabricante;
    private String modelo;
    private TipoInstrumento tipoInstrumento;
    private double preco;
    private boolean tipoProfissional;
    
    @ManyToMany(mappedBy = "instrumentos") 
    private List<Venda> vendas;



    public Instrumento() {
    }

    public Instrumento(String fabricante, String modelo, TipoInstrumento tipoInstrumento, double preco, boolean tipoProfissional) {
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.tipoInstrumento = tipoInstrumento;
        this.preco = preco;
        this.tipoProfissional = tipoProfissional;
    }
    
    
        public enum TipoInstrumento {
        VIOLAO("Violão"),
        TECLADO("Teclado"),
        BATERIA("Bateria");

        private final String label;

        private TipoInstrumento(String label) {
            this.label = label;
        }

        public String getLabel() {
            return this.label;
        }
    }

    public TipoInstrumento getTipoInstrumento() {
        return tipoInstrumento;
    }

    public void setTipoInstrumento(TipoInstrumento tipoInstrumento) {
        this.tipoInstrumento = tipoInstrumento;
    }
        
        


    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isTipoProfissional() {
        return tipoProfissional;
    }

    public void setTipoProfissional(boolean tipoProfissional) {
        this.tipoProfissional = tipoProfissional;
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
        if (!(object instanceof Instrumento)) {
            return false;
        }
        Instrumento other = (Instrumento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bateria [id=" + id + "]";
    }
}
