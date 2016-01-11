package eu.isdc.internship.beans.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.isdc.internship.beans.GameRole;
import eu.isdc.internship.beans.StartConfigBean;
import eu.isdc.internship.persistence.adapters.GenericAdapter;
import eu.isdc.internship.persistence.dto.StartConfigDTO;

@Component
public class StartConfigAdapter extends GenericAdapter<StartConfigBean, StartConfigDTO> {

	@Autowired
	private BattleshipPositionAdapter battleshipPositionAdapter;

	@Override
	public StartConfigBean toModel(StartConfigDTO dto) {
		if (dto == null) {
			return null;
		}
		StartConfigBean bean = new StartConfigBean();
		bean.setGameId(dto.getGameId());
		bean.setStartConfigId(dto.getStartConfigId());
		bean.setUserId(dto.getUserId());
		bean.setSelectedPositions(battleshipPositionAdapter.toModel(dto.getBattleshipPositions()));
		bean.setGameRole(GameRole.valueOf(dto.getGameRole()));
		return bean;
	}

	@Override
	public StartConfigDTO toDTO(StartConfigBean model) {
		if (model == null) {
			return null;
		}
		// TODO: revisit this
		return null;
	}

}
