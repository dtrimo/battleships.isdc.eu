package eu.isdc.internship.db.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import eu.isdc.internship.db.model.Friend;
import eu.isdc.internship.db.model.User;

@Repository
public class FriendDAO extends GenericDAO<Friend, Long>{
	
	/**
	 * Get a list of Friends of given user
	 * @param user
	 * 		User for which to get friends
	 * @return List of friends of user
	 */
	@SuppressWarnings("unchecked")
	public List<Friend> getFriendsOfUser(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Friend.class);
		criteria.add(Restrictions.or(Restrictions.eq("user1", user), Restrictions.eq("user2", user)));
		return (List<Friend>)hibernateTemplate.findByCriteria(criteria);
	}
}
