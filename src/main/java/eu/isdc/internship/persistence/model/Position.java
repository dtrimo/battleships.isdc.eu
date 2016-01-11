package eu.isdc.internship.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "POSITIONS")
public class Position {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "POSITION_ID", nullable = false)
  private Long position_id;
  
  @Column(name = "X", nullable = false)
  private int x;
  
  @Column(name = "Y", nullable = false)
  private int y;
  
  @ManyToOne
  @JoinColumn(name = "model_id")
  private BattleshipModel model;

  public Position(){}
  public Position(int x, int y){
	  this.x=x;
	  this.y=y;
  }
  /**
   * @return the position_id
   */
  public Long getPosition_id() {
    return position_id;
  }

  /**
   * @param position_id
   *          the position_id to set
   */
  public void setPosition_id(final Long position_id) {
    this.position_id = position_id;
  }

  /**
   * @return the x
   */
  public int getX() {
    return x;
  }

  /**
   * @param x
   *          the x to set
   */
  public void setX(final int x) {
    this.x = x;
  }

  /**
   * @return the y
   */
  public int getY() {
    return y;
  }

  /**
   * @param y
   *          the y to set
   */
  public void setY(final int y) {
    this.y = y;
  }

  /**
   * @return the model
   */
  public BattleshipModel getModel() {
    return model;
  }

  /**
   * @param model
   *          the model to set
   */
  public void setModel(final BattleshipModel model) {
    this.model = model;
  }

}
