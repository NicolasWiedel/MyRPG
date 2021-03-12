package de.javadevblog.myrpg.map;

import java.util.Hashtable;

public class MapFactory {

	/**
	 * hier werden die gesamten Maps der Welt gehalten
	 */
	private static Hashtable<MapType, Map> mapTable = new Hashtable<MapFactory.MapType, Map>();

	public enum MapType {
		TOWN
	}

	public static Map getMap(MapType mapType) {
		Map map = null;
		switch(mapType) {
			case TOWN:
				map = mapTable.get(MapType.TOWN);
				if(map == null) {
					map = new TownMap();
					mapTable.put(MapType.TOWN, map);
				}
				break;
			default:
				break;
		}
		return map;
	}
}
