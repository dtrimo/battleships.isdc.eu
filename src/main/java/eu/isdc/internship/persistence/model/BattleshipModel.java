package eu.isdc.internship.db.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "BATLLESHIP_MODELS")
public class BattleshipModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO) 
  @Column(name = "MODEL_ID", nullable = false)
  private Long model_id;
  
  @Column(name = "BNAME")
  private String name;
  
  @OneToMany(mappedBy = "model",fetch=FetchType.LAZY) //1-n cu Positions
  @Cascade(value = CascadeType.ALL)
  private List<Position> positions;
  
  @OneToMany(mappedBy = "model", fetch=FetchType.LAZY) //1-n cu AvailableBT
  @Cascade(value = CascadeType.ALL)
  private List<AvailableBattleship> Av_BT;

  
  public BattleshipModel(){}
  public BattleshipModel( String nume){
	  this.name=nume;
  }
  
  public List<AvailableBattleship> getAv_BT() {
	return Av_BT;
}

public void setAv_BT(List<AvailableBattleship> av_BT) {
	Av_BT = av_BT;
}

/**
   * @return the model_id
   */
  public Long getModel_id() {
    return model_id;
  }

  /**
   * @param model_id
   *          the model_id to set
   */
  public void setModel_id(final Long model_id) {
    this.model_id = model_id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * @return the positions
   */
  public List<Position> getPositions() {
    return positions;
  }

  /**
   * @param positions
   *          the positions to set
   */
  public void setPositions(final List<Position> positions) {
    this.positions = positions;
  }

}
