package eu.isdc.internship.db.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.isdc.internship.db.dto.AvailableBattleshipDTO;
import eu.isdc.internship.db.model.AvailableBattleship;
/*test-comment*/
@Component
public class AvailableBattleshipAdapter extends GenericAdapter<AvailableBattleship, AvailableBattleshipDTO>{

	@Autowired
	private BattleshipModelAdapter battleshipModelAdapter;
	
	@Autowired
	private BattleshipPositionAdapter battleshipPositionAdapter;
	
	@Override
	public AvailableBattleship toModel(AvailableBattleshipDTO dto) {
		if (dto==null) {
			return null;
		}
		AvailableBattleship avb = new AvailableBattleship();
		avb.setAv_BT_id(dto.getAv_BT_id());
		avb.setCount(dto.getCount());
		
		avb.setModel(battleshipModelAdapter.toModel(dto.getModel()));
		avb.setBT_Positions(battleshipPositionAdapter.toModel(dto.getBT_Positions()));
		return avb;
	}

	@Override
	public AvailableBattleshipDTO toDTO(AvailableBattleship model) {
		if (model==null) {
			return null;
		}
		AvailableBattleshipDTO dto = new AvailableBattleshipDTO();
		dto.setAv_BT_id(model.getAv_BT_id());
		dto.setCount(model.getCount());
		
		dto.setModel(battleshipModelAdapter.toDTO(model.getModel()));
		dto.setBT_Positions(battleshipPositionAdapter.toDTO(model.getBT_Positions()));
		dto.setGameTypeId(model.getGameType().getGame_type_id());
		return dto;
	}
}
