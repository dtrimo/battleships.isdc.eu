/**
 * 
 * @author Dragos.Florea
 * TransformationAdapter class - used to convert Model to DTO or DTO to Model
 */

package eu.isdc.internship.persistence.adapters;

import org.springframework.stereotype.Component;

import eu.isdc.internship.persistence.dto.TransformationDTO;
import eu.isdc.internship.persistence.model.Transformation;

@Component
public class TransformationAdapter extends GenericAdapter<Transformation, TransformationDTO> {

	@Override
	public Transformation toModel(TransformationDTO dto) {
		if (dto == null) {
			return null;
		}
		Transformation transformation = new Transformation();
		transformation.setTransformationId(dto.getTransformationId());
		transformation.setransformationOrder(dto.getTransformationOrder());
		transformation.setType(dto.getType());
		return transformation;
	}

	@Override
	public TransformationDTO toDTO(Transformation model) {
		if (model == null) {
			return null;
		}
		TransformationDTO transformationDTO = new TransformationDTO();
		transformationDTO.setTransformationId(model.getTransformationId());
		transformationDTO.setTransformationOrder(model.getTransformationOrder());
		transformationDTO.setType(model.getType());
		return transformationDTO;
	}

}
