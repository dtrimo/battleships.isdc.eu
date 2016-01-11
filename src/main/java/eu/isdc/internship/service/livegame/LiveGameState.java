package eu.isdc.internship.service.livegame;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.isdc.internship.beans.BattleshipPositionBean;
import eu.isdc.internship.beans.GameRole;
import eu.isdc.internship.beans.OccupiedPositionBean;
import eu.isdc.internship.beans.StartConfigBean;
import eu.isdc.internship.persistence.dto.GameDTO;
import eu.isdc.internship.persistence.dto.StartConfigDTO;

public class LiveGameState {

	private GameDTO gameDTO;
	private GameState gameState;
	private Date currentStateStart;
	private Integer currentRound = 1;
	private int totalShipCellCount;
	private Map<GameRole, StartConfigBean> startConfigs = new HashMap<GameRole, StartConfigBean>();
	private Map<GameRole, HashMap<Integer, OccupiedPositionBean>> userShots = new HashMap<GameRole, HashMap<Integer, OccupiedPositionBean>>();
	private Map<GameRole, Boolean> startConfigsSubmitted = new HashMap<GameRole, Boolean>();
	private Map<GameRole, Long> users = new HashMap<GameRole, Long>();

	public LiveGameState(GameDTO gameDTO) {
		this.gameDTO = gameDTO;
		this.currentStateStart = new Date();
		this.gameState = GameState.CREATED;
		userShots.put(GameRole.PLAYER_1, new HashMap<Integer, OccupiedPositionBean>());
		userShots.put(GameRole.PLAYER_2, new HashMap<Integer, OccupiedPositionBean>());
		startConfigsSubmitted.put(GameRole.PLAYER_1, false);
		startConfigsSubmitted.put(GameRole.PLAYER_2, false);
		users.put(GameRole.valueOf(gameDTO.getStartConfigs().get(0).getGameRole()),
				gameDTO.getStartConfigs().get(0).getUserId());
		users.put(GameRole.valueOf(gameDTO.getStartConfigs().get(1).getGameRole()),
				gameDTO.getStartConfigs().get(1).getUserId());
		totalShipCellCount = gameDTO.getGameType().getTotalShipCellCount();
	}

	public GameDTO getGameDTO() {
		return gameDTO;
	}

	public void setGameDTO(GameDTO gameDTO) {
		this.gameDTO = gameDTO;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public Date getCurrentStateStart() {
		return currentStateStart;
	}

	public void setCurrentStateStart(Date currentStateStart) {
		this.currentStateStart = currentStateStart;
	}

	public Integer getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(Integer currentRound) {
		this.currentRound = currentRound;
	}

	public Map<GameRole, StartConfigBean> getStartConfigs() {
		return startConfigs;
	}

	public void setStartConfigs(Map<GameRole, StartConfigBean> startConfigs) {
		this.startConfigs = startConfigs;
	}

	public Map<GameRole, Long> getUsers() {
		return users;
	}

	public void setUsers(Map<GameRole, Long> users) {
		this.users = users;
	}

	public void setStartConfigsSubmitted(Map<GameRole, Boolean> startConfigsSubmitted) {
		this.startConfigsSubmitted = startConfigsSubmitted;
	}

	public Map<GameRole, HashMap<Integer, OccupiedPositionBean>> getUserShots() {
		return userShots;
	}

	public void setUserShots(Map<GameRole, HashMap<Integer, OccupiedPositionBean>> userShots) {
		this.userShots = userShots;
	}

	public Long getGameId() {
		return gameDTO.getGameId();
	}

	public Map<GameRole, Boolean> getStartConfigsSubmitted() {
		return startConfigsSubmitted;
	}

	public List<Long> getUserIds() {
		List<Long> userIds = new ArrayList<Long>();
		for (final StartConfigDTO startConfigDTO : gameDTO.getStartConfigs()) {
			userIds.add(startConfigDTO.getUserId());
		}
		return userIds;
	}

	public GameRole getRoleForUser(Long userId) {
		for (Map.Entry<GameRole, StartConfigBean> entry : startConfigs.entrySet()) {
			if (entry.getValue() != null && entry.getValue().getUserId().equals(userId)) {
				return entry.getKey();
			}
		}
		return null;
	}

	public StartConfigBean getStartConfig(Long userId) {
		GameRole gameRole = getRoleForUser(userId);
		if (gameRole == null) {
			return null;
		} else {
			return startConfigs.get(gameRole);
		}
	}

	public Map<Integer, OccupiedPositionBean> getShots(Long userId) {
		GameRole gameRole = getRoleForUser(userId);
		if (gameRole == null) {
			return null;
		} else {
			return userShots.get(gameRole);
		}
	}

	public boolean allUsersFired(Integer round) {
		for (final Map<Integer, OccupiedPositionBean> userShotsList : userShots.values()) {
			if (!userShotsList.containsKey(round)) {
				return false;
			}
		}
		return true;
	}

	public boolean allUsersSubmittedConfig() {
		for (final Boolean submitted : startConfigsSubmitted.values()) {
			if (!submitted) {
				return false;
			}
		}
		return true;
	}

	public boolean isHit(Long userId, Integer round) {
		GameRole gameRole = getRoleForUser(userId);
		if (gameRole == null) {
			return false;
		}
		OccupiedPositionBean currentHit = getShots(userId).get(round);
		if (currentHit == null) {
			return false;
		}
		StartConfigBean opponentStartConfig = (gameRole == GameRole.PLAYER_1 ? startConfigs.get(GameRole.PLAYER_2)
				: startConfigs.get(GameRole.PLAYER_1));
		for (final BattleshipPositionBean battleshipPositionBean : opponentStartConfig.getSelectedPositions()) {
			for (final OccupiedPositionBean occupiedPosition : battleshipPositionBean.getOccupiedPositions()) {
				if (currentHit.getX() == occupiedPosition.getX() && currentHit.getY() == occupiedPosition.getY()) {
					return true;
				}
			}
		}
		return false;
	}

	private int getHitCountForRole(GameRole gameRole) {
		int count = 0;
		Long userId = users.get(gameRole);
		for (int round = 1; round <= currentRound; round++) {
			count += isHit(userId, round) ? 1 : 0;
		}
		return count;
	}

	public GameRole getWinner() {
		List<GameRole> winners = new ArrayList<GameRole>();
		for (final GameRole role : userShots.keySet()) {
			if (getHitCountForRole(role) == totalShipCellCount) {
				winners.add(role);
			}
		}
		if (winners.size() != 1) {
			return null;
		} else {
			return winners.get(0);
		}
	}

}
