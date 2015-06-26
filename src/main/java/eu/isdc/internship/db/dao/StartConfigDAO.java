package eu.isdc.internship.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eu.isdc.internship.db.model.StartConfig;
import eu.isdc.internship.db.model.User;

@Repository
public class StartConfigDAO extends GenericDAO<StartConfig, Long> {

	@Autowired
	private UserDAO userDAO;
	
	/**
	 * Delete is overridden to make sure the StartConfig instance is also deleted from the collection of its parent User entity
	 */
	@Override
	public StartConfig delete(StartConfig conf) {
		User user = conf.getUser();
		user.getStartConfig().remove(conf);
		userDAO.save(user);
		hibernateTemplate.delete(conf);
		return conf;
	}
	
}
