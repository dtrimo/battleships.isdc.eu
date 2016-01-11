package eu.isdc.internship.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.isdc.internship.beans.BattleshipPositionBean;
import eu.isdc.internship.beans.GameRole;
import eu.isdc.internship.beans.GameTypeBean;
import eu.isdc.internship.beans.StartConfigBean;
import eu.isdc.internship.beans.adapters.BattleshipPositionAdapter;
import eu.isdc.internship.beans.adapters.StartConfigAdapter;
import eu.isdc.internship.persistence.adapters.GameAdapter;
import eu.isdc.internship.persistence.adapters.GameTypeAdapter;
import eu.isdc.internship.persistence.adapters.OccupiedPositionAdapter;
import eu.isdc.internship.persistence.adapters.PositionAdapter;
import eu.isdc.internship.persistence.adapters.StartConfAdapter;
import eu.isdc.internship.persistence.adapters.TransformationAdapter;
import eu.isdc.internship.persistence.dao.AvailableBattleshipDAO;
import eu.isdc.internship.persistence.dao.GameDAO;
import eu.isdc.internship.persistence.dao.GameTypeDAO;
import eu.isdc.internship.persistence.dao.StartConfigDAO;
import eu.isdc.internship.persistence.dao.UserDAO;
import eu.isdc.internship.persistence.dto.BattleshipPositionDTO;
import eu.isdc.internship.persistence.dto.GameDTO;
import eu.isdc.internship.persistence.dto.StartConfigDTO;
import eu.isdc.internship.persistence.model.AvailableBattleship;
import eu.isdc.internship.persistence.model.BattleshipPosition;
import eu.isdc.internship.persistence.model.Game;
import eu.isdc.internship.persistence.model.GameType;
import eu.isdc.internship.persistence.model.OccupiedPosition;
import eu.isdc.internship.persistence.model.Position;
import eu.isdc.internship.persistence.model.StartConfig;
import eu.isdc.internship.persistence.model.Transformation;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameDAO gameDAO;

	@Autowired
	private StartConfigDAO startConfigDAO;

	@Autowired
	private GameAdapter gameAdapter;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private AvailableBattleshipDAO availableBattleshipDAO;

	@Autowired
	private OccupiedPositionAdapter occupiedPositionAdapter;

	@Autowired
	@Qualifier("positionBeanAdapter")
	private eu.isdc.internship.beans.adapters.PositionAdapter positionBeanAdapter;

	@Autowired
	private PositionAdapter positionAdapter;

	@Autowired
	private TransformationAdapter transformationAdapter;

	@Autowired
	private StartConfigAdapter startConfigBeanAdapter;

	@Autowired
	private StartConfAdapter startConfigAdapter;

	@Autowired
	@Qualifier("battleshipPositionBeanAdapter")
	private BattleshipPositionAdapter battleshipPositionBeanAdapter;

	@Autowired
	@Qualifier("battleshipPositionAdapter")
	private eu.isdc.internship.persistence.adapters.BattleshipPositionAdapter battleshipPositionAdapter;

	@Autowired
	private GameTypeDAO gameTypeDao;

	@Autowired
	private GameTypeDAO gameTypeDAO;

	@Autowired
	private GameTypeAdapter gameTypeAdapter;

	@Autowired
	@Qualifier("gameTypeBeanAdapter")
	private eu.isdc.internship.beans.adapters.GameTypeAdapter gameTypeBeanAdapter;

	@Transactional
	public GameDTO getGame(Long gameId) {
		return gameAdapter.toDTO(gameDAO.read(gameId));
	}

	@Transactional
	public void submitStartConfiguration(StartConfigBean startConfig) {
		Game game = gameDAO.read(startConfig.getGameId());
		StartConfig config = startConfigDAO.read(startConfig.getStartConfigId());
		config.setGame(game);
		config.setUser(userDAO.read(startConfig.getUserId()));
		// config.moves will not be set
		List<BattleshipPositionDTO> battleshipPositionDTOs = battleshipPositionBeanAdapter
				.toDTO(startConfig.getSelectedPositions());
		List<BattleshipPosition> battleshipPositions = new ArrayList<BattleshipPosition>();

		for (final BattleshipPositionDTO dto : battleshipPositionDTOs) {
			final BattleshipPosition position = new BattleshipPosition();
			position.setAvailableBattleship(availableBattleshipDAO.read(dto.getAvailableBattleshipId()));
			position.setStartConfig(config);
			List<OccupiedPosition> occupiedPositions = occupiedPositionAdapter.toModel(dto.getOccupiedPositions());
			for (final OccupiedPosition occupiedPosition : occupiedPositions) {
				occupiedPosition.setBattleshipPosition(position);
			}
			position.setOccupiedPositions(occupiedPositions);
			List<Transformation> transformations = transformationAdapter.toModel(dto.getTransformations());
			for (final Transformation transformation : transformations) {
				transformation.setBattleshipPosition(position);
			}
			position.setTransformations(transformations);
			position.setTranslateX(dto.getTranslateX());
			position.setTranslateY(dto.getTranslateY());
			battleshipPositions.add(position);
		}

		config.getBattleshipPositions().clear();
		config.getBattleshipPositions().addAll(battleshipPositions);
		startConfigDAO.save(config);
	}

	@Transactional
	public StartConfigBean getStartConfiguration(Long startConfigId) {
		StartConfig config = startConfigDAO.read(startConfigId);
		StartConfigDTO dto = startConfigAdapter.toDTO(config);
		StartConfigBean bean = startConfigBeanAdapter.toModel(dto);
		for (final BattleshipPositionBean positionBean : bean.getSelectedPositions()) {
			AvailableBattleship availableBattleship = availableBattleshipDAO
					.read(positionBean.getAvailableBattleshipId());
			List<Position> positions = availableBattleship.getBattleshipModel().getPositions();
			positionBean.setAvailableBattleshipPositions(positionBeanAdapter.toModel(positionAdapter.toDTO(positions)));
		}
		return bean;
	}

	@Transactional
	public GameDTO createGame(Long gametypeId, List<Long> userIds) {
		GameRole[] roles = new GameRole[] { GameRole.PLAYER_1, GameRole.PLAYER_2 };
		Game game = new Game();
		GameType gameType = gameTypeDao.read(gametypeId);
		game.setGameType(gameType);
		ArrayList<StartConfig> confList = new ArrayList<StartConfig>();
		for (int i = 0; i < userIds.size(); i++) {
			StartConfig conf = new StartConfig();
			conf.setUser(userDAO.read(userIds.get(i)));
			conf.setGameRole(roles[i].name());
			conf.setGame(game);
			confList.add(conf);
		}
		game.setStartConfigs(confList);
		game.setDate(new Date());

		gameDAO.save(game);
		return gameAdapter.toDTO(game);
	}

	@Transactional
	public List<GameTypeBean> getGameTypes() {
		return gameTypeBeanAdapter.toModel(gameTypeAdapter.toDTO(gameTypeDAO.readAll()));
	}

}
