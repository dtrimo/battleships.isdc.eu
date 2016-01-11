package eu.isdc.internship.beans.adapters;

import org.springframework.stereotype.Component;

import eu.isdc.internship.beans.OccupiedPositionBean;
import eu.isdc.internship.persistence.adapters.GenericAdapter;
import eu.isdc.internship.persistence.dto.OccupiedPositionDTO;

@Component("occupiedPositionBeanAdapter")
public class OccupiedPositionAdapter extends GenericAdapter<OccupiedPositionBean, OccupiedPositionDTO>{

	@Override
	public OccupiedPositionBean toModel(OccupiedPositionDTO dto) {
		if (dto == null){
			return null;
		}
		OccupiedPositionBean bean = new OccupiedPositionBean();
		bean.setX(dto.getX());
		bean.setY(dto.getY());
		return bean;
	}

	@Override
	public OccupiedPositionDTO toDTO(OccupiedPositionBean bean) {
		if (bean == null){
			return null;
		}
		OccupiedPositionDTO dto = new OccupiedPositionDTO();
		dto.setX(bean.getX());
		dto.setY(bean.getY());
		return dto;
	}

}
