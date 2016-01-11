package eu.isdc.internship.service.livegame;

public interface LiveGameService extends GameEventConsumer{

	void startGame(Long gameId);
	
}
