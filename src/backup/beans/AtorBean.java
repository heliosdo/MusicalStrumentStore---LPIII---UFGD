/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Ator;
import java.io.Serializable;
import util.RequestParameters;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import services.AtorService;

/**
 *
 * @author Cristian
 */
@Named(value = "atorBean")
@ViewScoped
public class AtorBean implements Serializable {

    @EJB
    private AtorService atorService;

    @Inject
    private RequestParameters parameters;

    private Ator value;

    private boolean consultar;

    private List<Ator> atoresFiltrados;

    @PostConstruct
    public void init() {
        value = new Ator();
    }

    public List<Ator> getAtoresFiltrados() {
        return atoresFiltrados;
    }

    public void setAtoresFiltrados(List<Ator> atoresFiltrados) {
        this.atoresFiltrados = atoresFiltrados;
    }

    public Ator getValue() {
        return value;
    }

    public void setValue(Ator value) {
        this.value = value;
    }

    public boolean getConsultar() {
        return consultar;
    }

    public void setConsultar(boolean consultar) {
        this.consultar = consultar;
    }

    public void reset() {
        value = new Ator();
    }

    public void inserir() {
        reset();
        consultar = false;
    }

    public List<Ator> getAll() {
        return atorService.getAll();
    }

    public String save() {
        if (value.getNome() == null || value.getNome().isEmpty()) {
            return null; // Retorna null para manter a página atual
        }
        atorService.create(value);
        reset(); // Limpa o formulário após salvar
        return null;
    }

    public String update() {
        atorService.edit(value);
        return null;
    }

    public String delete() {
        atorService.remove(value);
        return null;
    }

    public void consultarAtor(Ator value) {
        setValue(value);
        consultar = true;
    }

    public List<Ator> completaAtores(String prefixo) {
        return atorService.filter(prefixo);
    }

}
