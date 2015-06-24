package eu.isdc.internship.db.adapters;

import org.springframework.stereotype.Component;

import eu.isdc.internship.db.dto.FriendDTO;
import eu.isdc.internship.db.model.Friend;

@Component
public class FriendAdapter extends GenericAdapter<Friend,FriendDTO> {

	@Override
	public Friend toModel(FriendDTO dto) {
		if (dto==null){
			return null;
		}
		Friend fr=new Friend();
		fr.setUser1(dto.getUser1());
		fr.setUser2(dto.getUser2());
		fr.setFriendship_id(dto.getFriendship_id());
		return fr;
	}

	@Override
	public FriendDTO toDTO(Friend model) {
		if (model == null) {
			return null;
		}
		FriendDTO dto = new FriendDTO();
		dto.setUser1(model.getUser1());
		dto.setUser2(model.getUser2());
		dto.setFriendship_id(model.getFriendship_id());
		return dto;
	} 

}
