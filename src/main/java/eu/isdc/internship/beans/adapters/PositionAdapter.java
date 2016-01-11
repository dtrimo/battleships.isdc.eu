package eu.isdc.internship.beans.adapters;

import org.springframework.stereotype.Component;

import eu.isdc.internship.beans.PositionBean;
import eu.isdc.internship.persistence.adapters.GenericAdapter;
import eu.isdc.internship.persistence.dto.PositionDTO;

@Component("positionBeanAdapter")
public class PositionAdapter extends GenericAdapter<PositionBean,PositionDTO>{

	@Override
	public PositionBean toModel(PositionDTO dto) {
		if (dto==null){
			return null;
		}
		PositionBean bean = new PositionBean();
		bean.setX(dto.getX());
		bean.setY(dto.getY());
		return bean;
	}

	@Override
	public PositionDTO toDTO(PositionBean model) {
		if (model==null){
			return null;
		}
		PositionDTO dto = new PositionDTO();
		dto.setX(model.getX());
		dto.setY(model.getY());
		return dto;
	}



}
