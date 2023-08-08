package ru.ibragim.model;

import java.io.Serializable;
import java.util.Collection;


import jakarta.persistence.*;

import ru.ibragim.*;

@Entity
@Table(name="Solution")
public class Turn implements Serializable
{
	@Id
	@Column(name = "MapID")
	private long map;

	@Id
	@Column(name = "\"Order\"")
	private int order;

	@Id
	@Column(name = "Solver")
	private String solver;

	@Column(name = "Direction", nullable = false)
	@Enumerated
	Direction dir;

	public Turn()
	{
		order = 0;
		map = 0;
	}

	public Turn(Map m, int order, Direction dir, String solver)
	{
		this.dir = dir;
		this.order =  order;
		this.solver = solver;
		this.map = m.getId();
	}

	public static Turn[] fromCursors(Collection<Direction> cnt, ru.ibragim.Map mp, String solver)
	{
		Turn[] ret = new Turn[cnt.size()];
		int i = 0;
		for (Direction cs : cnt)
		{
			ret[i] = new Turn(mp, i, cs, solver);
			i++;
		}
		return ret;
	}

	public long getMap() {
		return map;
	}

	public int getOrder() {
		return order;
	}

	public String getSolver() {
		return solver;
	}

	@Override
	public String toString()
	{
		return String.format("Turn { .map = %d\t.order = %d\t.solver = \"%s\"\t.dir = %s }", map, order, solver, dir.toString());
	}
}