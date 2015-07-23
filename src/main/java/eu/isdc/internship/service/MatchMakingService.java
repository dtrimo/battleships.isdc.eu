package eu.isdc.internship.service;

import eu.isdc.internship.beans.GameRequest;
import eu.isdc.internship.beans.GameRequestResponse;
import eu.isdc.internship.exception.MatchMakingException;

public interface MatchMakingService {

	GameRequestResponse requestGame(GameRequest gameRequest) throws MatchMakingException;
	
}
