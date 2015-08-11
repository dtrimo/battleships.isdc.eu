package eu.isdc.internship.beans;

import java.util.List;

public class BattleshipModelBean {

	private Long battleshipModelId;
	private List<PositionBean> cells;

	public Long getBattleshipModelId() {
		return battleshipModelId;
	}

	public void setBattleshipModelId(Long battleshipModelId) {
		this.battleshipModelId = battleshipModelId;
	}

	public List<PositionBean> getCells() {
		return cells;
	}

	public void setCells(List<PositionBean> cells) {
		this.cells = cells;
	}

}
