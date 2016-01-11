package eu.isdc.internship.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eu.isdc.internship.db.model.Game;
import eu.isdc.internship.db.model.StartConfig;
import eu.isdc.internship.db.model.User;

/**
 * 
 * @author Horia.Galbenu
 * StartConfigDAO class - used for accessing StartConfig entities
 */
@Repository
public class StartConfigDAO extends GenericDAO<StartConfig, Long> {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private GameDAO gameDAO;
	
	/**
	 * Delete is overridden to make sure the StartConfig instance is also deleted from the collection of its parent entities
	 */
	@Override
	public StartConfig delete(StartConfig conf) {
		User user = conf.getUser();
		if(user != null) {
			user.getStartConfig().remove(conf);
			userDAO.save(user);
		}
		Game game = conf.getGame();
		if(game != null) {
			game.getStartConfigs().remove(conf);
			gameDAO.save(game);
		}
		hibernateTemplate.delete(conf);
		return conf;
	}
}
