package eu.isdc.internship.persistence.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.isdc.internship.persistence.dto.AvailableBattleshipDTO;
import eu.isdc.internship.persistence.model.AvailableBattleship;

/*test-comment*/
@Component
public class AvailableBattleshipAdapter extends GenericAdapter<AvailableBattleship, AvailableBattleshipDTO> {

	@Autowired
	private BattleshipModelAdapter battleshipModelAdapter;

	@Autowired
	private BattleshipPositionAdapter battleshipPositionAdapter;

	@Override
	public AvailableBattleship toModel(AvailableBattleshipDTO dto) {
		if (dto == null) {
			return null;
		}

		AvailableBattleship avb = new AvailableBattleship();
		avb.setAvailableBattleshipId(dto.getAvailableBattleshipId());

		avb.setBattleshipModel(battleshipModelAdapter.toModel(dto.getModel()));
		return avb;
	}

	@Override
	public AvailableBattleshipDTO toDTO(AvailableBattleship model) {
		if (model == null) {
			return null;
		}
		AvailableBattleshipDTO dto = new AvailableBattleshipDTO();
		dto.setAvailableBattleshipId(model.getAvailableBattleshipId());

		dto.setModel(battleshipModelAdapter.toDTO(model.getBattleshipModel()));
		dto.setGameTypeId(model.getGameType().getGameTypeId());
		return dto;
	}
}
