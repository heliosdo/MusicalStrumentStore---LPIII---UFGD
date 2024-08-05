/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

/**
 *
 * @author Cristian
 */
import entities.Ator;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@FacesConverter(forClass = Ator.class)
@Named
public class AtorConverter extends EntityConverter<Ator> {
    
    public AtorConverter(){
        super(Ator.class);
    }
    
}
