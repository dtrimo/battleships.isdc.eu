package eu.isdc.internship.db.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.isdc.internship.db.dto.UserDTO;
import eu.isdc.internship.db.model.User;

@Component
public class UserAdapter extends GenericAdapter<User,UserDTO>{

	@Autowired
	private StatisticsAdapter statAdapter;
	
	@Override
	public User toModel(UserDTO dto) {
		if (dto==null){
			return null;
		}
		User user = new User();
		user.setUser_id(dto.getUser_id());
		user.setBirthDate(dto.getBirthDate());
		user.setName(dto.getName());
		user.setPassword(dto.getPassword());
		
		user.setStatistic(statAdapter.toModel(dto.getStatistics()));
		return user;
	}

	@Override
	public UserDTO toDTO(User model) {
		if (model==null){
			return null;
		}
		UserDTO dto = new UserDTO();
		dto.setUser_id(model.getUser_id());
		dto.setBirthDate(model.getBirthDate());
		dto.setName(model.getName());
		dto.setPassword(model.getPassword());
		
		dto.setStatistics(statAdapter.toDTO(model.getStatistic()));
		return dto;
	}
}
