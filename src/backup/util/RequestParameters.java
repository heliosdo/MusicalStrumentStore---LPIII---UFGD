/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Cristian
 */

import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

public class RequestParameters implements Serializable{
    
    private Map<String, String> params;
    
    @PostConstruct
    public void init(){
        FacesContext faces_context = FacesContext.getCurrentInstance();
        params = faces_context.getExternalContext().getRequestParameterMap();
    }
    
    public String get(String param){ return params.get(param);}
    
}
