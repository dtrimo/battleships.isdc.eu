package eu.isdc.internship.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import eu.isdc.internship.persistence.model.BattleshipModel;

@Repository
public class BattleshipModelDAO extends GenericDAO<BattleshipModel, Long> {
	
	/**
	 * Get a list of battleship models with given name
	 * @param modelName
	 * 		Battleship model name represented as a string
	 * @return List of battleships with given name
	 */
	@SuppressWarnings("unchecked")
	public List<BattleshipModel> getBattleshipModelsByName(final String modelName) {
		if(StringUtils.isEmpty(modelName)) {
			return null;
		}
		final DetachedCriteria criteria = DetachedCriteria.forClass(BattleshipModel.class);
		criteria.add(Restrictions.eq("name", modelName));
		return (List<BattleshipModel>)hibernateTemplate.findByCriteria(criteria);
	}
	
	/**
	 * Get a list of battleship models with given Ids
	 * @param userIds
	 * 		The list of battleship model ids
	 * @return List of BattleshipModel with given Ids
	 */
	public List<BattleshipModel> getAllBattleshipModelsByIds(final List<Long> battleshipIds) {
		if (battleshipIds == null || battleshipIds.isEmpty()) {
			return new ArrayList<BattleshipModel>();
		}
		final DetachedCriteria criteria = DetachedCriteria.forClass(BattleshipModel.class);
		criteria.add(Restrictions.in("model_id", battleshipIds));
		return readWithPagination(criteria, -1, -1);
	}
}
