/**
 * 
 * @author Dragos.Florea
 * TransformationDAO class - used for accessing Tranformation entities
 */


package eu.isdc.internship.persistence.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import eu.isdc.internship.persistence.model.Transformation;

@Repository
public class TransformationDAO extends GenericDAO<Transformation, Long>{
	
	
	/**
	 * Get transformation by id
	 * @param transformationId
	 * 		id represented as a long
	 * @return Transformation with given id
	 */
	Transformation getTransformationById(final Long transformationId){
		if(transformationId == null){
			return null;
		}
		final DetachedCriteria criteria = DetachedCriteria.forClass(Transformation.class);
		criteria.add(Restrictions.eq("transformation_id", transformationId));
		@SuppressWarnings("unchecked")
		List<Transformation> results = (List<Transformation>)hibernateTemplate.findByCriteria(criteria);
		if(results.size() == 0){
			return null;
		}
		Transformation transformation = results.get(0);
		return transformation;
	}
	
}
