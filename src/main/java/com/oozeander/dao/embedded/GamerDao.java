package com.oozeander.dao.embedded;

import com.oozeander.model.embedded.Gamer;
import com.oozeander.model.embedded.GamerId;

public interface GamerDao {
	Gamer get(GamerId gamerId);

	void save(Gamer gamer);
}