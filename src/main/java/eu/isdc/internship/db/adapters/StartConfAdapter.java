package eu.isdc.internship.db.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.isdc.internship.db.dto.*;
import eu.isdc.internship.db.model.*;

@Component
public class StartConfAdapter extends GenericAdapter<StartConfig,StartConfigDTO>{

	@Autowired
	private BattleshipPositionAdapter battleshipPositionAdapter;
	@Override
	public StartConfig toModel(StartConfigDTO dto) {

		if(dto==null)		
		return null;
		StartConfig st=new StartConfig();
		st.setStartConfig_id(dto.getStartConfig_id());
		st.setGame(dto.getGame());
		st.setBT_Positions(battleshipPositionAdapter.toModel(dto.getBT_Positions()));
		st.setUser(dto.getUser());
		return st;
	}

	@Override
	public StartConfigDTO toDTO(StartConfig model) {
		if(model == null) {
			return null;
		}
		StartConfigDTO dto = new StartConfigDTO();
		dto.setStartConfig_id(model.getStartConfig_id());
		dto.setGame(model.getGame());
		dto.setBT_Positions(battleshipPositionAdapter.toDTO(model.getBT_Positions()));
		dto.setUser(model.getUser());
		return dto;
		
	}
	
	

	
	
}
