package eu.isdc.internship.beans.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import eu.isdc.internship.beans.BattleshipPositionBean;
import eu.isdc.internship.persistence.adapters.GenericAdapter;
import eu.isdc.internship.persistence.dto.AvailableBattleshipDTO;
import eu.isdc.internship.persistence.dto.BattleshipPositionDTO;

@Component("battleshipPositionBeanAdapter")
public class BattleshipPositionAdapter extends GenericAdapter<BattleshipPositionBean, BattleshipPositionDTO>{

	@Autowired
	@Qualifier("occupiedPositionBeanAdapter")
	private OccupiedPositionAdapter occupiedPositionAdapter;
	
	@Autowired
	@Qualifier("transformationBeanAdapter")
	private TransformationAdapter transformationAdapter;
	
	@Override
	public BattleshipPositionBean toModel(BattleshipPositionDTO dto) {
		if (dto == null){
			return null;
		}
		BattleshipPositionBean bean = new BattleshipPositionBean();
		bean.setAvailableBattleshipId(dto.getAvailableBattleshipId());
		bean.setOccupiedPositions(occupiedPositionAdapter.toModel(dto.getOccupiedPositions()));
		bean.setTransformations(transformationAdapter.toModel(dto.getTransformations()));
		bean.setxOffset(dto.getTranslateX());
		bean.setyOffset(dto.getTranslateY());
		return bean;
	}

	@Override
	public BattleshipPositionDTO toDTO(BattleshipPositionBean model) {
		if (model == null){
			return null;
		}
		BattleshipPositionDTO dto = new BattleshipPositionDTO();
		dto.setTransformations(transformationAdapter.toDTO(model.getTransformations()));
		dto.setOccupiedPositions(occupiedPositionAdapter.toDTO(model.getOccupiedPositions()));
		dto.setTranslateX(model.getxOffset());
		dto.setTranslateY(model.getyOffset());
		dto.setAvailableBattleshipId(model.getAvailableBattleshipId());
		return dto;
	}

}
