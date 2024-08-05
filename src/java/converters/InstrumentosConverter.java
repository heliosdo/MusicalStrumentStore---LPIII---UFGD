package converters;

import entities.Instrumento; 
import javax.faces.convert.FacesConverter; 
import javax.inject.Named; 


@FacesConverter (forClass = Instrumento.class) 
@Named 
public class InstrumentosConverter extends EntityConverter<Instrumento>{
	public InstrumentosConverter() { 
	super(Instrumento.class) ; 
	}
}
