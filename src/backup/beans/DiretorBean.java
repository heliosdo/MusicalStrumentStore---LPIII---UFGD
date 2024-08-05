/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Diretor;
import java.io.Serializable;
import util.RequestParameters;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import services.DiretorService;

/**
 *
 * @author Cristian
 */
@Named(value = "diretorBean")
@ViewScoped
public class DiretorBean implements Serializable{
    
    @EJB
    private DiretorService diretorService;
    
    @Inject
    private RequestParameters parameters;
    
    private Diretor value;
    
    private boolean consultar;
    
    private List<Diretor> diretoresFiltrados;
    
    @PostConstruct
    public void init(){
        value = new Diretor();
    }
    
    public List<Diretor> getDiretoresFiltrados(){
        return diretoresFiltrados;
    }
    
    public void setDiretoresFiltrados(List<Diretor> diretoresFiltrados){
        this.diretoresFiltrados = diretoresFiltrados;
    }
    
    public Diretor getValue(){ return value;}
    
    public void setValue(Diretor value){ this.value = value;}
    
    public boolean getConsultar() { return consultar; }
    
    public void setConsultar(boolean consultar) { this.consultar = consultar; }
    
    public void reset(){ value = new Diretor();}
    
    public void inserir(){ 
        reset();
        consultar = false;  
    }
    
    public List<Diretor> getAll(){ return diretorService.getAll();}
    
    public String save() {
        // Verifica se os campos obrigatórios estão preenchidos
        
        if (value.getNome() == null || value.getNome().isEmpty()) {
            return null; // Retorna null para manter a página atual
        }
        diretorService.create(value);
        reset(); // Limpa o formulário após salvar
        return null;
    }

    
    public String update(){
        diretorService.edit(value);
        return null;
    }
    
    public String delete(){
        diretorService.remove(value);
        return null;
    }
    
    public void consultarDiretor (Diretor value) { 
        setValue(value);
        consultar = true;
    }
    
    public List<Diretor> completaDiretores(String prefixo){
        return diretorService.filter(prefixo);
    }

}