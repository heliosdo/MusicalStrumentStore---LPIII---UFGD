package util;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;




public class RequestParameters implements Serializable {
    private Map<String, String> params;

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        params = facesContext.getExternalContext().getRequestParameterMap();
    }

    public String get(String param) {
        return params.get(param);
    }
}