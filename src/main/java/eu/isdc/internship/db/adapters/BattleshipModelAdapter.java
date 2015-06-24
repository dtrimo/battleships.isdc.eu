package eu.isdc.internship.db.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.isdc.internship.db.dto.BattleshipModelDTO;
import eu.isdc.internship.db.model.BattleshipModel;

@Component
public class BattleshipModelAdapter extends GenericAdapter<BattleshipModel,BattleshipModelDTO>{

	@Autowired
	private PositionAdapter positionAdapter;
	
	@Override
	public BattleshipModel toModel(BattleshipModelDTO dto) {
		if(dto==null)
		return null;
		BattleshipModel btm=new BattleshipModel();
		btm.setModel_id(dto.getModel_id());
		btm.setName(dto.getName());
		btm.setPositions(positionAdapter.toModel(dto.getPositions()));
		return btm;	
	}

	@Override
	public BattleshipModelDTO toDTO(BattleshipModel model) {
		if(model == null) {
			return null;
		}
		BattleshipModelDTO dto = new BattleshipModelDTO();
		dto.setModel_id(model.getModel_id());
		dto.setName(model.getName());
		dto.setPositions(positionAdapter.toDTO(model.getPositions()));
		return dto;
	}
}
