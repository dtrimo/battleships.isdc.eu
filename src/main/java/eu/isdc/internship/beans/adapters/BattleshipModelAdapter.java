package eu.isdc.internship.beans.adapters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import eu.isdc.internship.beans.BattleshipModelBean;
import eu.isdc.internship.db.adapters.GenericAdapter;
import eu.isdc.internship.db.dto.AvailableBattleshipDTO;
import eu.isdc.internship.db.dto.PositionDTO;

@Component("battleshipModelBeanAdapter")
public class BattleshipModelAdapter extends GenericAdapter<BattleshipModelBean, AvailableBattleshipDTO>{

	@Override
	public BattleshipModelBean toModel(AvailableBattleshipDTO dto) {
		if (dto==null){
			return null;
		}
		final BattleshipModelBean bean = new BattleshipModelBean();
		List<Integer> x = new ArrayList<Integer>();
		List<Integer> y = new ArrayList<Integer>();
		
		for (final PositionDTO position: dto.getModel().getPositions()){
			x.add(position.getX());
			y.add(position.getY());
		}
		
		bean.setX(x);
		bean.setY(y);
		return bean;
	}

	@Override
	public AvailableBattleshipDTO toDTO(BattleshipModelBean model) {
		throw new UnsupportedOperationException();
	}

}
