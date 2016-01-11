package eu.isdc.internship.persistence.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.isdc.internship.persistence.dto.StartConfigDTO;
import eu.isdc.internship.persistence.model.StartConfig;

@Component
public class StartConfAdapter extends GenericAdapter<StartConfig, StartConfigDTO> {

	@Autowired
	private UserAdapter userAdapter;

	@Autowired
	private MoveAdapter moveAdapter;

	@Autowired
	private BattleshipPositionAdapter battleshipPositionAdapter;

	@Override
	public StartConfig toModel(StartConfigDTO dto) {
		if (dto == null) {
			return null;
		}
		StartConfig st = new StartConfig();
		st.setStartConfigId(dto.getStartConfigId());
		st.setBattleshipPositions(battleshipPositionAdapter.toModel(dto.getBattleshipPositions()));
		st.setMoves(moveAdapter.toModel(dto.getMoves()));
		st.setGameRole(dto.getGameRole());
		return st;
	}

	@Override
	public StartConfigDTO toDTO(StartConfig model) {
		if (model == null) {
			return null;
		}
		StartConfigDTO dto = new StartConfigDTO();
		dto.setStartConfigId(model.getStartConfigId());
		dto.setBT_Positions(battleshipPositionAdapter.toDTO(model.getBattleshipPositions()));
		dto.setMoves(moveAdapter.toDTO(model.getMoves()));
		dto.setUserId(model.getUser().getUserId());
		dto.setGameId(model.getGame().getGameId());
		dto.setGameRole(model.getGameRole());
		return dto;
	}
}
