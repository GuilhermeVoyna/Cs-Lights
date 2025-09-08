package org.example;


import org.example.csgsi.GameState;
import org.example.csgsi.MapState;
import org.example.utils.json.JsonParser;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
        public static void main(String[] args) throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
            String sJson = """
{
  "map": {
    "mode": "deathmatch",
    "name": "de_mirage",
    "phase": "live",
    "round": 0 ,
    "team_ct": {
      "score": 0,
      "consecutive_round_losses": 0,
      "timeouts_remaining": 1,
      "matches_won_this_series": 0
    },
    "team_t": {
      "score": 0,
      "consecutive_round_losses": 0,
      "timeouts_remaining": 1,
      "matches_won_this_series": 0
    },
    "num_matches_to_win_series": 0
  },
  "player": {
    "steamid": "76561198122067482",
    "name": "old guerra",
    "observer_slot": 11,
    "team": "CT",
    "activity": "playing",
    "match_stats": {
      "kills": 2,
      "assists": 0,
      "deaths": 2,
      "mvps": 0,
      "score": 24
    },
    "state": {
      "health": 100,
      "armor": 100,
      "helmet": true,
      "flashed": 0,
      "smoked": 0,
      "burning": 0,
      "money": 0,
      "round_kills": 0,
      "round_killhs": 0,
      "equip_value": 1200
    },
    "weapons": {
      "weapon_0": {
        "name": "weapon_knife_ursus",
        "paintkit": "aq_forced",
        "type": "Knife",
        "state": "holstered"
      },
      "weapon_1": {
        "name": "weapon_usp_silencer",
        "paintkit": "cu_kaiman",
        "type": "Pistol",
        "ammo_clip": 12,
        "ammo_clip_max": 12,
        "ammo_reserve": 24,
        "state": "active"
      }
    }
  },
  "provider": {
    "name": "Counter-Strike: Global Offensive",
    "appid": 730,
    "version": 14094,
    "steamid": "76561198122067482",
    "timestamp": 1754793916
  },
  "round": {
    "phase": "live"
  },
  "auth": {
    "token": "CSGSI"
  }
}

""";
MapState mapState = new MapState(MapState.GameMode.COMPETITIVE,"dust", MapState.GamePhase.LIVE,1,null,null);
            GameState gameState = new GameState(null,null);
            gameState.setMap(mapState);
            System.out.println(sJson);
            var v = JsonParser.parseJson(sJson,gameState);

            long start = System.nanoTime(); // ou System.currentTimeMillis()
            for (int i = 0 ;i<2;i++) {
              var v2 =  JsonParser.parseJson(sJson, gameState);
            }
            long end = System.nanoTime(); // ou System.currentTimeMillis()

            long durationNs = end - start;
            double durationMs = durationNs / 1_000_000.0;  // converter para milissegundos

            System.out.println("Tempo de execução: " + durationMs + " ms");


        }

}

