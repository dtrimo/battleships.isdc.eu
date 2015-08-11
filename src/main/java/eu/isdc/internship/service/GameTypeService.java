package eu.isdc.internship.service;

import java.util.List;

import eu.isdc.internship.beans.GameTypeBean;

public interface GameTypeService {

	GameTypeBean getGameType(Long gameTypeId);
	
	List<GameTypeBean> getGameTypes();
	
}
