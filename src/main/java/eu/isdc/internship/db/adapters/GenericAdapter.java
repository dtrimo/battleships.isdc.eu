package eu.isdc.internship.db.adapters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 
 * @author Eliza.Nitoi
 *
 * @param <U> the type of the model class
 * @param <V> the type of the DTO class
 */
public abstract class GenericAdapter<U,V> {

	/**
	 * Adapt a single dto to the corresponding model object
	 * @param dto
	 * @return model
	 */
	public abstract U toModel(V dto); 
	
	/**
	 * Adapt a single model to the corresponding dto object
	 * @param model
	 * @return dto
	 */
	public abstract V toDTO(U model);
	
	/**
	 * Adapt a collection of dto objects to the respective model objects
	 * @param dtos
	 * @return list of respective models for each dto
	 */
	public List<U> toModel(Collection<V> dtos){
		if (dtos==null || dtos.isEmpty()){
			return new ArrayList<U>();
		}
		final List<U> models = new ArrayList<U>();
		for (V dto : dtos){
			U model = toModel(dto);
			if (model!=null){
				models.add(model);
			}
		}
		return models;
	}
	
	/**
	 * Adapt a collection of model objects to the respective dto objects
	 * @param models
	 * @return list of respectie dtos fr each model
	 */
	public List<V> toDTO(Collection<U> models){
		if (models==null || models.isEmpty()){
			return new ArrayList<V>();
		}
		final List<V> dtos = new ArrayList<V>();
		for (U model : models){
			V dto = toDTO(model);
			if (dto!=null){
				dtos.add(dto);
			}
		}
		return dtos;
	}
}
