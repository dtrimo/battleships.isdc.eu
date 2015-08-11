package eu.isdc.internship.beans.adapters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.isdc.internship.beans.AvailableBattleshipModelBean;
import eu.isdc.internship.db.adapters.GenericAdapter;
import eu.isdc.internship.db.dto.AvailableBattleshipDTO;
import eu.isdc.internship.db.dto.PositionDTO;

@Component("availableBattleshipBeanAdapter")
public class AvailableBattleshipAdapter extends GenericAdapter<AvailableBattleshipModelBean, AvailableBattleshipDTO>{

	@Autowired
	private BattleshipModelAdapter battleshipModelAdapter;
	
	@Override
	public AvailableBattleshipModelBean toModel(AvailableBattleshipDTO dto) {
		if (dto==null){
			return null;
		}
		AvailableBattleshipModelBean bean = new AvailableBattleshipModelBean();
		bean.setAvailableBattleshipModelId(dto.getAv_BT_id());
		bean.setBattleshipModel(battleshipModelAdapter.toModel(dto.getModel()));
		bean.setGameTypeId(dto.getGameTypeId());
		return bean;
	}

	@Override
	public AvailableBattleshipDTO toDTO(AvailableBattleshipModelBean model) {
		throw new UnsupportedOperationException();
	}

}
