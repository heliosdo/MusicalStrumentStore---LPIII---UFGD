package beans;

import entities.Cliente;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.FlowEvent;
import services.ClienteService;
import util.RequestParameters;

@Named(value = "clienteBean")
@ViewScoped
public class ClienteBean implements Serializable {

    @EJB
    private ClienteService clienteService;

    @Inject
    private RequestParameters parameters;
    private Cliente value;
    private boolean consultar;
    private List<Cliente> clientesFiltrados;

    @PostConstruct
    public void init() {
        String id = parameters.get("id");
        if (id == null) {
            value = new Cliente();
        } else {
            value = clienteService.find(Long.valueOf(id));
        }
    }

    public Cliente getValue() {
        return value;
    }

    public void setValue(Cliente value) {
        this.value = value;
    }

    public boolean isConsultar() {
        return consultar;
    }

    public void setConsultar(boolean consultar) {
        this.consultar = consultar;
    }

    public List<Cliente> getClientesFiltrados() {
        return clientesFiltrados;
    }

    public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
        this.clientesFiltrados = clientesFiltrados;
    }

    public void reset() {
        value = new Cliente();
    }

    public void inserir() {
        reset();
        setConsultar(false);
    }

    public void consultar(Cliente value) {
        setValue(value);
        setConsultar(true);
    }
    
    public List<Cliente> getAll() { 
        return clienteService.getAll();
    }

    public void save() {
        try {
            clienteService.create(value);
            reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        try {
            clienteService.edit(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            clienteService.remove(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SelectItem[] getEstadosCivis(boolean filtrar) {
        SelectItem[] items;
        int length = Cliente.EstadoCivil.values().length;
        int n = 0;
        if (filtrar) {
            items = new SelectItem[length + 1];
            items[0] = new SelectItem("", "");
            n++;
        } else {
            items = new SelectItem[length];
        }
        for (Cliente.EstadoCivil estadoCivil : Cliente.EstadoCivil.values()) {
            items[n++] = new SelectItem(estadoCivil, estadoCivil.getLabel());
        }
        return items;
    }

    public SelectItem[] getSexos(boolean filtrar) {
        SelectItem[] items;
        int length = 3;
        int n = 0;

        if (filtrar) {
            items = new SelectItem[length];
            items[0] = new SelectItem("", "");
            n++;
        } else {
            items = new SelectItem[length - 1];
        }

        items[1] = new SelectItem("masculino", "Masculino");
        items[2] = new SelectItem("feminino", "Feminino");

        return items;
    }
    
    public SelectItem[] getInstrumentos(boolean filtrar) {
        SelectItem[] items;
        int length = Cliente.Instrumento.values().length;
        int n = 0;
        if (filtrar) {
            items = new SelectItem[length + 1];
            items[0] = new SelectItem("", "");
            n++;
        } else {
            items = new SelectItem[length];
        }
        for (Cliente.Instrumento instrumento : Cliente.Instrumento.values()) {
            items[n++] = new SelectItem(instrumento, instrumento.getLabel());
        }
        return items;
    }
    
    public List<Cliente> completaClientes(String prefixo) {
        return clienteService.filter(prefixo);
    }


    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }
}
