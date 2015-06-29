package eu.isdc.internship.db.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
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
@SuppressWarnings("unchecked")
public abstract class GenericDAO<T, ID extends Serializable> {
	protected HibernateTemplate hibernateTemplate;
	
	private final Class<T> persistentClass;
	{
		persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
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
		return (List<T>)hibernateTemplate.find("select ent from " + persistentClass.getSimpleName() + " ent");
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
	 * Force flush and reload entity from the database
	 * @param entity
	 * @return
	 */
	public T refresh(T entity) {
		hibernateTemplate.flush();
		hibernateTemplate.refresh(entity);
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
	
	/**
   * Reads entities from the database with paging support, based on a hql query
   * @param hql
   *          String representing the hql query
   * @param startIndex
   *          The start index of the results
   * @param maxResults
   *          The number of maximum results to be returned
   * @return List of entities retrieved based on hql query
   */
	@SuppressWarnings("rawtypes")
	protected List<T> readWithPagination(final String hql, final int startIndex, final int maxResults) {
		return hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session) throws HibernateException, SQLException {
		    final Query query = session.createQuery(hql);
		    if (startIndex != -1) {
		      query.setFirstResult(startIndex);
		    }
		    if (maxResults != -1) {
		      query.setMaxResults(maxResults);
		    }
		    return query.list();
		  }
		});
	}
	
	/**
   * Reads entities from the database with paging support, based on a criteria
   * @param criteria
   *          Criteria to filter by
   * @param startIndex
   *          The start index
   * @param maxResults
   *          The max results
   * @return List of entities retrieved based on criteria
   */
	protected List<T> readWithPagination(final DetachedCriteria criteria, final int startIndex, final int maxResults) {

    final Criteria executableCriteria = criteria.getExecutableCriteria(hibernateTemplate.getSessionFactory().getCurrentSession());
    if (startIndex != -1) {
      executableCriteria.setFirstResult(startIndex);
    }
    if (maxResults >= 0) {
      executableCriteria.setMaxResults(maxResults);
    }
    return executableCriteria.list();
  }
 }
