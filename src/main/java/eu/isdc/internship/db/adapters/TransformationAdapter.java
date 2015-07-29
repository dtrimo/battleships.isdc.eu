/**
 * 
 * @author Dragos.Florea
 * TransformationAdapter class - used to convert Model to DTO or DTO to Model
 */

package eu.isdc.internship.db.adapters;

import org.springframework.beans.factory.annotation.Autowired;

import eu.isdc.internship.db.dto.TransformationDTO;
import eu.isdc.internship.db.model.Transformation;

public class TransformationAdapter extends GenericAdapter<Transformation, TransformationDTO>{
	
	@Autowired
	private BattleshipPositionAdapter bsAdapter;

	@Override
	public Transformation toModel(TransformationDTO dto) {
		if(dto == null){
			return null;
		}
		Transformation transformation = new Transformation();
		transformation.setTransformation_id(dto.getTransformation_id());
		transformation.setransformationOrder(dto.getTransformationOrder());
		transformation.setType(dto.getType());
		transformation.setBattleshipPosition(bsAdapter.toModel(dto.getBattleshipPosition()));
		return transformation;
	}

	@Override
	public TransformationDTO toDTO(Transformation model) {
		if(model == null){
			return null;
		}
		TransformationDTO transformationDTO = new TransformationDTO();
		transformationDTO.setTransformation_id(model.getTransformation_id());
		transformationDTO.setTransformationOrder(model.getTransformationOrder());
		transformationDTO.setType(model.getType());
		transformationDTO.setBattleshipPosition(bsAdapter.toDTO(model.getBattleshipPosition()));
		return transformationDTO;
	}
	
	
}
