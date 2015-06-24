package eu.isdc.internship.db.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * 
 * @author Horia.Galbenu
 * Class containing implementations for common entity CRUD operations
 * @param <T>
 * 		Generic type of entities to manipulate
 * @param <ID>
 * 		Generic type of the unique identifier of above entities
 */
public abstract class GenericDAO<T, ID extends Serializable> {
	protected HibernateTemplate hibernateTemplate;
	
	private final Class<T> persistentClass;
	private DetachedCriteria crt;
	
	{
		persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		crt = DetachedCriteria.forClass(persistentClass);
	}
	
	/**
	 * Set the session factory for the hibernate template
	 * @param factory
	 */
	@Autowired
	public void setSessionFactory(SessionFactory factory) {
		hibernateTemplate = new HibernateTemplate(factory);
	}
	
	/**
	 * List all entities of type T
	 * @return List containing all entities of type T
	 */
	public List<T> readAll() {
		return hibernateTemplate.find("SELECT Ent FROM " + persistentClass.getSimpleName() + "Ent");
	}
	
	/**
	 * Retrieve specific entity by its unique id
	 * @param id
	 * 		unique identifier of entity to be retrieved
	 * @return
	 */
	public T read(ID id){
		return hibernateTemplate.get(persistentClass, id);
	}
	
	/**
	 * Save an entity to the database
	 * @param entity 
	 * 		Entity to be saved
	 * @return Saved entity
	 */
	public T save(T entity) {
		hibernateTemplate.saveOrUpdate(entity);
		return entity;
	}
	
	/**
	 * Delete an entity from the database
	 * @param entity
	 * 		Entity to be deleted
	 * @return Deleted entity
	 */
	public T delete(final T entity) {
		hibernateTemplate.delete(entity);
		return entity;
	}	
}
