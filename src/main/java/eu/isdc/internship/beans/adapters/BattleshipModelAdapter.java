package eu.isdc.internship.beans.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.isdc.internship.beans.BattleshipModelBean;
import eu.isdc.internship.persistence.adapters.GenericAdapter;
import eu.isdc.internship.persistence.dto.BattleshipModelDTO;

@Component("battleshipModelBeanAdapter")
public class BattleshipModelAdapter extends GenericAdapter<BattleshipModelBean, BattleshipModelDTO>{

	@Autowired
	private PositionAdapter positionAdapter;

	@Override
	public BattleshipModelBean toModel(BattleshipModelDTO dto) {
		if (dto==null){
			return null;
		}
		BattleshipModelBean bean = new BattleshipModelBean();
		bean.setCells(positionAdapter.toModel(dto.getPositions()));
		bean.setBattleshipModelId(dto.getModelId());
		return bean;
	}

	@Override
	public BattleshipModelDTO toDTO(BattleshipModelBean model) {
		throw new UnsupportedOperationException();
	}
}

