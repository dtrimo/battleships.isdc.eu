package eu.isdc.internship.db.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.isdc.internship.db.dto.*;
import eu.isdc.internship.db.model.*;

@Component
public class StartConfAdapter extends GenericAdapter<StartConfig,StartConfigDTO>{

	@Autowired
	private UserAdapter userAdapter;
	
	@Autowired
	private MoveAdapter moveAdapter;
	
	@Autowired
	private BattleshipPositionAdapter battleshipPositionAdapter;
	
	@Override
	public StartConfig toModel(StartConfigDTO dto) {
		if(dto==null) {	
			return null;
		}
		StartConfig st = new StartConfig();
		st.setStartConfig_id(dto.getStartConfig_id());
		st.setBT_Positions(battleshipPositionAdapter.toModel(dto.getBT_Positions()));
		st.setMove(moveAdapter.toModel(dto.getMove()));
		st.setUser(userAdapter.toModel(dto.getUser()));
		return st;
	}

	@Override
	public StartConfigDTO toDTO(StartConfig model) {
		if(model == null) {
			return null;
		}
		StartConfigDTO dto = new StartConfigDTO();
		dto.setStartConfig_id(model.getStartConfig_id());
		dto.setBT_Positions(battleshipPositionAdapter.toDTO(model.getBT_Positions()));
		dto.setMove(moveAdapter.toDTO(model.getMove()));
		dto.setUser(userAdapter.toDTO(model.getUser()));
		return dto;
	}	
}
