package eu.isdc.internship.persistence.adapters;

import org.springframework.stereotype.Component;

import eu.isdc.internship.persistence.dto.FriendDTO;
import eu.isdc.internship.persistence.model.Friendship;

@Component
public class FriendAdapter extends GenericAdapter<Friendship,FriendDTO> {

	@Override
	public Friendship toModel(FriendDTO dto) {
		if (dto==null){
			return null;
		}
		Friendship fr=new Friendship();
//		fr.setUser1(dto.getUser1());
//		fr.setUser2(dto.getUser2());
		fr.setFriendshipId(dto.getFriendshipId());
		return fr;
	}

	@Override
	public FriendDTO toDTO(Friendship model) {
		if (model == null) {
			return null;
		}
		FriendDTO dto = new FriendDTO();
//		dto.setUser1(model.getUser1());
//		dto.setUser2(model.getUser2());
		dto.setFriendshipId(model.getFriendshipId());
		return dto;
	} 

}
