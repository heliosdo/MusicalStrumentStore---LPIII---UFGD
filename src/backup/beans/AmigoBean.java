/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Amigo;
import java.io.Serializable;
import util.RequestParameters;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.FlowEvent;
import services.AmigoService;


/**
 *
 * @author Cristian
 */
@Named(value = "amigoBean")
@ViewScoped
public class AmigoBean implements Serializable{
    
    @EJB
    private AmigoService amigoService;
    
    @Inject
    private RequestParameters parameters;
    
    private Amigo value;
    
    private boolean consultar;
    
    private List<Amigo> amigosFiltrados;
    
    @PostConstruct
    public void init(){
        value = new Amigo();
    }
    
    public List<Amigo> getAmigosFiltrados(){
        return amigosFiltrados;
    }
    
    public void setAmigosFiltrados(List<Amigo> amigosFiltrados){
        this.amigosFiltrados = amigosFiltrados;
    }
    
    public Amigo getValue(){ return value;}
    
    public void setValue(Amigo value){ this.value = value;}
    
    public boolean getConsultar() { return consultar; }
    
    public void setConsultar(boolean consultar) { this.consultar = consultar; }
    
    public List<Amigo> getAll(){ return amigoService.getAll();}
    
    public String onFlowProcess(FlowEvent event){ return event.getNewStep();}
    
    public void reset(){ value = new Amigo();}
    
    public void inserir(){
        reset();
        consultar = false;
    }
    
    public String save() {
        // Verifica se os campos obrigatórios estão preenchidos
        
        if (value.getNome() == null || value.getNome().isEmpty()) {
            return null; // Retorna null para manter a página atual
        }
        amigoService.create(value);
        reset(); // Limpa o formulário após salvar
        return null;
    }

    
    public String update(){
        amigoService.edit(value);
        return null;
    }
    
    public String delete(){
        amigoService.remove(value);
        return null;
    }
    
    public void consultarAmigo (Amigo value) { 
        setValue(value);
        consultar = true;
    }
    
    public SelectItem[] getEstadosCivis(boolean filtrar){
        SelectItem[] items;
        int length = Amigo.EstadoCivil.values().length;
        int n = 0;
        if(filtrar){
            items = new SelectItem[length + 1];
            items[0] = new SelectItem("", "");
            n++;
        }else items = new SelectItem[length];
        for(Amigo.EstadoCivil estado_civil : Amigo.EstadoCivil.values()){
            items[n++] = new SelectItem(estado_civil, estado_civil.getLabel());
        }
        return items;
    }
    
    public SelectItem[] getSexos(boolean filtrar) {
        String[][] sexos = {
            {"feminino", "Feminino"},
            {"masculino", "Masculino"}
        };
        
        SelectItem[] items;
        int length = sexos.length;
        int n = 0;
        
        if (filtrar) {
            items = new SelectItem[length + 1];
            items[0] = new SelectItem("", "");
            n++;
        } else {
            items = new SelectItem[length];
        }
        
        for (String[] sexo : sexos) {
            items[n++] = new SelectItem(sexo[0], sexo[1]);
        }
        
        return items;
    }

}