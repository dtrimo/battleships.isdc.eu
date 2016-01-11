package eu.isdc.internship.beans.adapters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import eu.isdc.internship.beans.Transformation;
import eu.isdc.internship.persistence.adapters.GenericAdapter;
import eu.isdc.internship.persistence.dto.TransformationDTO;

@Component("transformationBeanAdapter")
public class TransformationAdapter extends GenericAdapter<Transformation, TransformationDTO> {

	@Override
	public Transformation toModel(TransformationDTO dto) {
		if (dto == null){
			return null;
		}
		return Transformation.valueOf(dto.getType());
	}

	@Override
	public TransformationDTO toDTO(Transformation model) {
		if (model == null){
			return null;
		}
		TransformationDTO dto = new TransformationDTO();
		dto.setType(model.name());
		return dto;
	}

	@Override
	public List<TransformationDTO> toDTO(Collection<Transformation> model) {
		if (model == null){
			return null;
		}
		int order = 0;
		List<TransformationDTO> dtos = new ArrayList<TransformationDTO>();
		for (final Transformation transformation : model){
			TransformationDTO dto = toDTO(transformation);
			if (dto!=null){
				dto.setTransformationOrder(order);
				order++;
				dtos.add(dto);
			}
		}
		return dtos;
	}
	
}
