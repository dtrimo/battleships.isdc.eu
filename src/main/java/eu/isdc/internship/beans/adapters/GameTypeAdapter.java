package eu.isdc.internship.beans.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.isdc.internship.beans.GameTypeBean;
import eu.isdc.internship.db.adapters.GenericAdapter;
import eu.isdc.internship.db.dto.GameTypeDTO;

@Component("gameTypeBeanAdapter")
public class GameTypeAdapter extends GenericAdapter<GameTypeBean, GameTypeDTO> {

	@Autowired
	private BattleshipModelAdapter battleshipModelAdapter;
	
	@Override
	public GameTypeBean toModel(GameTypeDTO dto) {
		if (dto==null){
			return null;
		}
		GameTypeBean bean = new GameTypeBean();
		
		bean.setM(dto.getM());
		bean.setN(dto.getN());
		bean.setName(dto.getName());
		bean.setShortDescription(dto.getShortDescription());
		bean.setBattleships(battleshipModelAdapter.toModel(dto.getAvailableBTs()));
		bean.setId(dto.getGame_type_id());
		return bean;
	}

	@Override
	public GameTypeDTO toDTO(GameTypeBean model) {		
		throw new UnsupportedOperationException();
	}
}
