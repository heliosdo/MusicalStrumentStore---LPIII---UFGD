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
import entities.Diretor;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@FacesConverter(forClass = Diretor.class)
@Named
public class AmigoConverter extends EntityConverter<Diretor> {
    
    public AmigoConverter(){
        super(Diretor.class);
    }
    
}
