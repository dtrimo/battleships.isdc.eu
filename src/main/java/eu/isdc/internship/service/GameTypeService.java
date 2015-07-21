package eu.isdc.internship.service;

import java.util.List;
import eu.isdc.internship.db.dto.GameTypeDTO;

public interface GameTypeService {

	List<GameTypeDTO> getGameTypes();
	
}
