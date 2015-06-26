package eu.isdc.internship.db.adapters;

import org.springframework.stereotype.Component;

import eu.isdc.internship.db.dto.*;
import eu.isdc.internship.db.model.*;
@Component
public class StatisticsAdapter extends GenericAdapter<Statistics,StatisticsDTO>{

	@Override
	public Statistics toModel(StatisticsDTO dto) {
		if(dto==null)
		return null;
		
		Statistics st=new Statistics();
		//st.setUser_id(dto.getUser_id());
		st.setNrOfPlayedGames(dto.getNrOfPlayedGames());
		st.setNrOfRoundsToWin(dto.getNrOfRoundsToWin());
		st.setNrOfRundsToLose(dto.getNrOfRundsToLose());
		st.setNrOfWins(dto.getNrOfWins());
		
		return st;
	}

	@Override
	public StatisticsDTO toDTO(Statistics model) {
		if(model == null) {
			return null;
		}
		StatisticsDTO dto=new StatisticsDTO();
		//dto.setUser_id(model.getUser_id());
		dto.setNrOfPlayedGames(model.getNrOfPlayedGames());
		dto.setNrOfRoundsToWin(model.getNrOfRoundsToWin());
		dto.setNrOfRundsToLose(model.getNrOfRundsToLose());
		dto.setNrOfWins(model.getNrOfWins());
		
		return dto;
	}

}
