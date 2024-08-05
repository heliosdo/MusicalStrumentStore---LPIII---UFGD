package beans;

import entities.Instrumento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import services.InstrumentoService;

@Named(value = "instrumentoBean")
@ViewScoped
public class InstrumentoBean implements Serializable {

    @EJB
    private InstrumentoService instrumentoService;

    private Instrumento value;
    private boolean tipoProfissional;
    private boolean consultar;
     private List<Instrumento> instrumentosFiltrados;

    @PostConstruct
    public void init() {
        value = new Instrumento();
    }

    public Instrumento getValue() {
        return value;
    }

    public void setValue(Instrumento value) {
        this.value = value;
    }

    public boolean isTipoProfissional() {
        return tipoProfissional;
    }

    public void setTipoProfissional(boolean tipoProfissional) {
        this.tipoProfissional = tipoProfissional;
    }

    public boolean isConsultar() { 
        return consultar;
    }

    public void setConsultar(boolean consultar) { 
        this.consultar = consultar;
    }
    
    public void consultar(Instrumento value) { 
        setValue(value); 
        setConsultar(true);
    }
    
    public void setInstrumentosFiltrados(List<Instrumento> instrumentosFiltrados) {
        this.instrumentosFiltrados = instrumentosFiltrados;
    }

    public void reset() {
        value = new Instrumento();
    }
    
    public void inserir() { 
        reset();
        setConsultar(false); 
    }
    
    
    public SelectItem[] getTipoInstrumento(boolean filtrar) {
        SelectItem[] items;
        int length = Instrumento.TipoInstrumento.values().length;
        int n = 0;
        if (filtrar) {
            items = new SelectItem[length + 1];
            items[0] = new SelectItem("", "");
            n++;
        } else {
            items = new SelectItem[length];
        }
        for (Instrumento.TipoInstrumento tipoInstrumento : Instrumento.TipoInstrumento.values()) {
            items[n++] = new SelectItem(tipoInstrumento, tipoInstrumento.getLabel());
        }
        return items;
    }


    public String save() {
        try {
            if (value.getFabricante() == null || value.getFabricante().isEmpty() ||
                value.getModelo() == null || value.getModelo().isEmpty()) {
                // Exibe uma mensagem de erro para o usuário
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - Fabricante e Modelo são campos obrigatórios.", "");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return null;
            }
            value.setTipoProfissional(tipoProfissional);
            instrumentoService.create(value);
            reset();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
    }



    public void update() {
        try {
            // Verifica se os campos obrigatórios estão preenchidos
            if (value.getFabricante() == null || value.getFabricante().isEmpty() ||
                value.getModelo() == null || value.getModelo().isEmpty()) {
                // Exibe uma mensagem de erro para o usuário
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro - Fabricante e Modelo são campos obrigatórios.", "");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }

            value.setTipoProfissional(tipoProfissional);
            instrumentoService.edit(value);
            reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void delete() {
        try {
            instrumentoService.remove(value);
            reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Instrumento> getAll() {
        return instrumentoService.getAll();
    }
    
        public List<Instrumento> completaInstrumentos(String prefixo) {
        return instrumentoService.filter(prefixo);
    }

}
