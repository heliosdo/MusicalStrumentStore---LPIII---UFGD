/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Cristian
 */
@Entity
public class Amigo implements Serializable, PersistentEntity{
    
    public enum EstadoCivil{
        solteiro("solteiro"), casado("casado"), divorciado("divorciado"), viúvo("viúvo");
        
        private final String label;
        
        private EstadoCivil(String label){ this.label = label;}
        public String getLabel(){ return this.label;}
    }

    private String nome;
    private String apelido;
    private String sexo;
    @Enumerated (EnumType.STRING)
    private static final long serialVersionUID = 1L;
    private EstadoCivil estadoCivil;
    @Temporal (TemporalType.DATE)
    private Date dataNascimento;
    private String cidade;
    
    @Pattern(regexp = "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")
    private String email;
    @Pattern(regexp = "((\\(\\d{2}\\)?)|(\\d{2}-))?(\\d{5}|\\d{4})-\\d{4}")
    private String whatsapp;
    private String facebook;
    private String instagram;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getApelido(){
        return apelido;
    }
    
    public void setApelido(String apelido){
        this.apelido = apelido;
    }
    
    public String getSexo(){
        return sexo;
    }
    
    public void setSexo(String sexo){
        this.sexo = sexo;
    }
    
    public Date getDataNascimento(){
        return dataNascimento;
    }
    
    public void setDataNascimento(Date dataNascimento){
        this.dataNascimento = dataNascimento;
    }
    
    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
    
    public String getCidade(){
        return cidade;
    }
    
    public void setCidade(String cidade){
        this.cidade = cidade;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getWhatsapp(){
        return whatsapp;
    }
    
    public void setWhatsapp(String whatsapp){
        this.whatsapp = whatsapp;
    }
    
    public String getFacebook(){
        return facebook;
    }
    
    public void setFacebook(String facebook){
        this.facebook = facebook;
    }
    
    public String getInstagram(){
        return instagram;
    }
    
    public void setInstagram(String instagram){
        this.instagram = instagram;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Amigo)) {
            return false;
        }
        Amigo other = (Amigo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Amigo[ id=" + id + " ]";
    }
    
}
