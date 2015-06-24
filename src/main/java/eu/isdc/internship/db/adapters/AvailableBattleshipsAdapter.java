package eu.isdc.internship.db.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.isdc.internship.db.dto.AvailableBattleshipDTO;
import eu.isdc.internship.db.model.AvailableBattleship;

@Component
public class AvailableBattleshipsAdapter extends GenericAdapter<AvailableBattleship, AvailableBattleshipDTO>{

	@Autowired
	private BattleshipPositionAdapter battleshipPositionAdapter;
	
	@Override
	public AvailableBattleship toModel(AvailableBattleshipDTO dto) {
		if (dto==null)
		return null;
		AvailableBattleship av=new AvailableBattleship();
		av.setAv_BT_id(dto.getAv_BT_id());
		av.setCount(dto.getCount());
		av.setBT_Positions(battleshipPositionAdapter.toModel(dto.getBT_Positions()));
		av.setModel(dto.getModel());
		return av;		
	}

	@Override
	public AvailableBattleshipDTO toDTO(AvailableBattleship model) {
		if (model==null) {
			return null;
		}
		AvailableBattleshipDTO dto = new AvailableBattleshipDTO();
		dto.setAv_BT_id(model.getAv_BT_id());
		dto.setCount(model.getCount());
		dto.setBT_Positions(battleshipPositionAdapter.toDTO(model.getBT_Positions()));
		dto.setModel(model.getModel());
		return dto;
	}
}
