/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Filme;
import java.io.Serializable;
import util.RequestParameters;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import services.FilmeService;

/**
 *
 * @author Cristian
 */
@Named(value = "filmeBean")
@ViewScoped
public class FilmeBean implements Serializable {

    @EJB
    private FilmeService filmeService;

    @Inject
    private RequestParameters parameters;

    private Filme value;

    private boolean consultar;

    private List<Filme> filmesFiltrados;

    @PostConstruct
    public void init() {
        value = new Filme();
    }

    public List<Filme> getFilmesFiltrados() {
        return filmesFiltrados;
    }

    public void setFilmesFiltrados(List<Filme> filmesFiltrados) {
        this.filmesFiltrados = filmesFiltrados;
    }

    public Filme getValue() {
        return value;
    }

    public void setValue(Filme value) {
        this.value = value;
    }

    public boolean getConsultar() {
        return consultar;
    }

    public void setConsultar(boolean consultar) {
        this.consultar = consultar;
    }

    public void reset() {
        value = new Filme();
    }

    public void inserir() {
        reset();
        consultar = false;
    }

    public List<Filme> getAll() {
        return filmeService.getAll();
    }

    public String save() {
        // Verifica se os campos obrigatórios estão preenchidos

        if (value.getTitulo() == null || value.getTitulo().isEmpty()) {
            return null; // Retorna null para manter a página atual
        }
        filmeService.create(value);
        reset(); // Limpa o formulário após salvar
        return null;
    }

    public String update() {
        filmeService.edit(value);
        return null;
    }

    public String delete() {
        filmeService.remove(value);
        return null;
    }

    public void consultarFilme(Filme value) {
        setValue(value);
        consultar = true;
    }

    public SelectItem[] getOptionsOscarMelhorFilme() {
        return new SelectItem[]{
            new SelectItem(String.valueOf(""), ""),
            new SelectItem(Boolean.TRUE.toString(), "sim"),
            new SelectItem(Boolean.FALSE.toString(), "nao")
        };
    }

    public SelectItem[] getGeneros(boolean filtrar) {
        SelectItem[] items;
        int length = Filme.Genero.values().length;
        int n = 0;
        if (filtrar) {
            items = new SelectItem[length + 1];
            items[0] = new SelectItem("", "");
            n++;
        } else {
            items = new SelectItem[length];
        }
        for (Filme.Genero genero : Filme.Genero.values()) {
            items[n++] = new SelectItem(genero, genero.getLabel());
        }
        return items;
    }

}
