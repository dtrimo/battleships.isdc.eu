package eu.isdc.internship;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.isdc.internship.db.adapters.*;
import eu.isdc.internship.db.dto.*;
import eu.isdc.internship.db.model.*;

@Component
public class RepositoryDB {
	
	@Autowired
	private UserAdapter userAdapter;
	@Autowired
	private StartConfAdapter startConfAdapter;
	@Autowired
	private FriendAdapter friendAdapter;
	@Autowired 
	private StatisticsAdapter statisticsAdapter;
	@Autowired
	private MoveAdapter moveAdapter;
	@Autowired
	private GameAdapter gameAdapter; 
	@Autowired
	private BattleshipModelAdapter battleshipModelAdapter;
	@Autowired
	private AvailableBattleshipAdapter availableBattleshipsAdapter;
	
	
	private Session session;
	private static SessionFactory factory;
	
	public void registerUser(String username, String password, Date d){
		session = factory.openSession();
		final User user=new User();
		user.setBirthDate(d);
		user.setName(username);
		user.setPassword(password);
		session.saveOrUpdate(user);
		session.close();
	}
	
	public boolean checkLogin(String username){
		 UserDTO user=getUserByUsername(username);
		  if (user!=null)
			  return true;
		  else 
			  return false;			  
	}
	
	private UserDTO getUserById(Long userId){
		session = factory.openSession();
		Query q = session.createQuery("from Users u where u.user_id=?");
		q.setParameter(0, userId);
		User user = (User)q.uniqueResult();
		UserDTO dto = userAdapter.toDTO(user);
		session.close();
		return dto;
	}
	
	private UserDTO getUserByUsername(String username){
		session = factory.openSession();
		Query q = session.createQuery("from Users u where u.name=?");
		q.setParameter(0, username);
		User user = (User)q.uniqueResult();
		UserDTO dto = userAdapter.toDTO(user);
		session.close();
		return dto;
	}
	
	//get User Statistics
	public StatisticsDTO getUserStatistics(User user){
		session = factory.openSession();
		Query q =session.createQuery("from USER_STATISTICS s where s.user_id=?" );
		q.setParameter(0,user.getUser_id());
		Statistics stat=(Statistics)q.uniqueResult();
		StatisticsDTO dto=statisticsAdapter.toDTO(stat);
		session.close();
		return dto;		
	}
	
	//get userFriends
	public List<FriendDTO> getFriends(UserDTO user){
		session = factory.openSession();
		Query q = session.createQuery("from FRIENDS f where f.friendship_id=?");
		q.setParameter(0, user.getUser_id());
		List<Friend> list=(List<Friend>) q.list();
		List<FriendDTO> listDto=friendAdapter.toDTO(list);
		session.close();
		return listDto;		
	}
	
	public List<StartConfigDTO> getStartConfigsForUser(Long userId){
		session = factory.openSession();
		Query q = session.createQuery("from START_CONFIG st where st.user_id=?");
		q.setParameter(0, userId);
		List<StartConfig> list=(List<StartConfig>) q.list();
		List<StartConfigDTO> listDto=startConfAdapter.toDTO(list);
		session.close();
		return listDto;		
	}
	
	public List<MoveDTO> getUserMoves(UserDTO user){
		session = factory.openSession();
		Query q = session.createQuery("from MOVES m where m.user_id=?");
		q.setParameter(0, user.getUser_id());
		List<Move> list=(List<Move>) q.list();
		List<MoveDTO> listDto=moveAdapter.toDTO(list);
		session.close();
		return listDto;		
	}
	
	
	private int getUserRound(UserDTO user){
		Query q = session.createQuery("select max(round) from MOVES m where m.user_id=?");
		q.setParameter(0, user.getUser_id());
		Integer maxRound = (Integer)q.uniqueResult();
		return maxRound;
		
	}
	
	//save User Move
	public void saveUserMove(UserDTO user, GameDTO game, StartConfigDTO st, Date date, int x, int y){
		session = factory.openSession();
		Move move=new Move();
		move.setRound(getUserRound(user)+1);
		move.setDate(new Date());
		move.setGame(gameAdapter.toModel(game));
		move.setStartConfig(startConfAdapter.toModel(st));
		move.setUser(userAdapter.toModel(user));
		move.setX(x);
		move.setY(y);
		userAdapter.toModel(user).getMove().add(move);
		gameAdapter.toModel(game).getMoves().add(move);
		startConfAdapter.toModel(st).getMove().add(move);
		session.saveOrUpdate(move);
		session.close();		
	}
	
	
	/*
	 * This will need to be redone later since user models no longer map a list of friends
	public void saveFriendship(UserDTO user1, UserDTO user2){	
		session = factory.openSession();
		final Friend f=new Friend(userAdapter.toModel(user1), userAdapter.toModel(user2));
		userAdapter.toModel(user1).getFriend().add(f);
		userAdapter.toModel(user2).getFriend().add(f);
		session.saveOrUpdate(user1);
		session.saveOrUpdate(user2);
		session.close();
	}
	*/
	
	public void savePosition( int x, int y, BattleshipModelDTO model){
		session = factory.openSession();
		Position p =new Position();
		p.setX(x);
		p.setY(y);
		p.setModel(battleshipModelAdapter.toModel(model));
		battleshipModelAdapter.toModel(model).getPositions().add(p);
		session.saveOrUpdate(p);
		session.close();	
	}

	public void savebattleshipModel(String nume){
		session = factory.openSession();
		BattleshipModel model=new BattleshipModel();
		model.setName(nume);
		session.saveOrUpdate(model);
		session.close();		
	}
	
	public void saveAvailableBT(int count, BattleshipModelDTO model, GameDTO game){
		session = factory.openSession();
		AvailableBattleship av=new AvailableBattleship();
		av.setCount(count);
		//av.setGame(gameAdapter.toModel(game));
		av.setModel(battleshipModelAdapter.toModel(model));
		battleshipModelAdapter.toModel(model).getAv_BT().add(av);
		//gameAdapter.toModel(game).getAvailableBTs().add(av);
		session.saveOrUpdate(av);
		session.close();			
	}
		
	public void saveBatleshipPosition(AvailableBattleshipDTO av, StartConfigDTO st, int flagRot,int flagReflx, int flagReflY, int tX, int tY){
		session = factory.openSession();
		BattleshipPosition bt=new BattleshipPosition();
		bt.setFlagReflX(flagReflx);
		bt.setFlagReflY(flagReflY);
		bt.setFlagRotate(flagRot);
		bt.setTranslateX(tX);
		bt.setTranslateY(tY);
		bt.setAv_BT(availableBattleshipsAdapter.toModel(av));
		bt.setStartConfig(startConfAdapter.toModel(st));	
		
	}
	public void addGame(Long userId, StartConfigDTO startConfigDTO, List<MoveDTO> moves, List<AvailableBattleshipDTO> avBT){
		session = factory.openSession();
		Game game = new Game();
		
		//don't assign ids!
		session.saveOrUpdate(game);
		session.close();
	}
}
