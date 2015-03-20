package code;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import jsonObjects.GoldInfo;
import jsonObjects.GoldSync;
import jsonObjects.Point;
import sqlTableObjects.BaseObj;
import sqlTableObjects.WormHoleObj;

public class GameLogicService {
	private static Random rand = new Random();
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
	
	public static List<WormHoleObj> createNewWormholes(BaseObj reference) {
		List<WormHoleObj> toReturn = Lists.newArrayList();
		if (rand.nextInt(2) == 1) {
			// TODO: find a random wormhole to match it with
			toReturn.add(new WormHoleObj(reference, new Point(1, 1), 1));
		}
		if (rand.nextInt(2) == 1) {
			// TODO: find a random wormhole to match it with
			toReturn.add(new WormHoleObj(reference, new Point(-1, 1), 1));
		}
		if (rand.nextInt(2) == 1) {
			// TODO: find a random wormhole to match it with
			toReturn.add(new WormHoleObj(reference, new Point(-1, -1), 1));
		}
		if (rand.nextInt(2) == 1) {
			// TODO: find a random wormhole to match it with
			toReturn.add(new WormHoleObj(reference, new Point(1, -1), 1));
		}
		return toReturn;
	}
}
