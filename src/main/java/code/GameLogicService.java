package code;

import java.util.List;
import jsonObjects.GoldInfo;
import jsonObjects.GoldSync;
import sqlTableObjects.BaseObj;

public class GameLogicService {
	public static double getGoldPerSec(String username) {
		List<BaseObj> bases = QueryService.getUserBases(username);
		double prod = 0;
		for (BaseObj b : bases) {
			prod += b.prodRate;
		}
		return prod / GameSettings.prodForOneGPerSec;
	}
	
	public static GoldSync syncGold(String username) {
		GoldInfo goldInfo = QueryService.getGold(username);
		double goldPerSec = GameLogicService.getGoldPerSec(username);
		long now = System.currentTimeMillis();
		double gold = goldInfo.gold + (now - goldInfo.lastUpdate) / 1000 * goldPerSec;
		
		QueryService.syncGold(username, gold, now);
		return new GoldSync(gold, goldPerSec);
	}
}
