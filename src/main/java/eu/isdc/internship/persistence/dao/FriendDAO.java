package eu.isdc.internship.persistence.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import eu.isdc.internship.persistence.model.Friendship;
import eu.isdc.internship.persistence.model.User;

@Repository
public class FriendDAO extends GenericDAO<Friendship, Long>{
	
	/**
	 * Get a list of Friends of given user
	 * @param user
	 * 		User for which to get friends
	 * @return List of friends of user
	 */
	@SuppressWarnings("unchecked")
	public List<Friendship> getFriendsOfUser(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Friendship.class);
		criteria.add(Restrictions.or(Restrictions.eq("user1", user), Restrictions.eq("user2", user)));
		return (List<Friendship>)hibernateTemplate.findByCriteria(criteria);
	}
}
