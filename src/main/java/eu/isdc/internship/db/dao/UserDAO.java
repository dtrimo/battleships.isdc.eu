package eu.isdc.internship.db.dao;


import java.util.ArrayList;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import eu.isdc.internship.db.model.User;

/**
 * 
 * @author Horia.Galbenu
 * UserDAO class - used for accessing User entities
 */
@Repository
public class UserDAO extends GenericDAO<User, Long>{
	
	/**
	 * Get user by unique username
	 * @param userName
	 * 		Username represented as a string
	 * @return User with given username
	 */
	public User getUserByName(final String userName) {
		if(StringUtils.isEmpty(userName)) {
			return null;
		}
		final DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("USERNAME", userName));
		return (User)((Criteria)criteria).uniqueResult();
	}
	
	/**
	 * Get a list of users with given Ids
	 * @param userIds
	 * 		The list of user Ids to check for
	 * @return List of Users with given Ids
	 */
	public List<User> getAllUsersByIds(final List<Long> userIds) {
		if (userIds == null || userIds.isEmpty()) {
			return new ArrayList<User>();
		}
		final DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.in("USER_ID", userIds));
		return readWithPagination(criteria, -1, -1);
	}
}
