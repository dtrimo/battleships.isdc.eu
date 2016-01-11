package eu.isdc.internship.persistence.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.isdc.internship.persistence.dto.BattleshipModelDTO;
import eu.isdc.internship.persistence.model.BattleshipModel;

@Component
public class BattleshipModelAdapter extends GenericAdapter<BattleshipModel,BattleshipModelDTO>{

	@Autowired
	private PositionAdapter positionAdapter;
	
	@Override
	public BattleshipModel toModel(BattleshipModelDTO dto) {
		if(dto==null) { 
			return null;
		}
		BattleshipModel battleshipModel = new BattleshipModel();
		battleshipModel.setModelId(dto.getModelId());
		battleshipModel.setName(dto.getName());
		
		battleshipModel.setPositions(positionAdapter.toModel(dto.getPositions()));
		return battleshipModel;
	}

	@Override
	public BattleshipModelDTO toDTO(BattleshipModel model) {
		if(model == null) {
			return null;
		}
		BattleshipModelDTO dto = new BattleshipModelDTO();
		dto.setModelId(model.getModelId());
		dto.setName(model.getName());
		
		dto.setPositions(positionAdapter.toDTO(model.getPositions()));
		return dto;
	}
}
