package com.oozeander.service.embedded;

import com.oozeander.model.embedded.Gamer;
import com.oozeander.model.embedded.GamerId;

public interface GamerService {
	Gamer get(GamerId gamerId);

	void save(Gamer gamer);
}