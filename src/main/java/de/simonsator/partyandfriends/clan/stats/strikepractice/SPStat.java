package de.simonsator.partyandfriends.clan.stats.strikepractice;

import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.clan.api.Clan;
import de.simonsator.partyandfriends.clan.api.ClanStat;
import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.util.ArrayList;
import java.util.List;

public class SPStat implements ClanStat {
	private final SPConnection CONNECTION;
	private final ConfigurationCreator MESSAGES_CONFIG;

	public SPStat(SPConnection connection, ConfigurationCreator messagesConfig) {
		this.CONNECTION = connection;
		this.MESSAGES_CONFIG = messagesConfig;
	}

	public void stats(OnlinePAFPlayer pSender, Clan pClan) {
		List<PAFPlayer> players = pClan.getAllPlayers();
		List<PlayerData> playerData = new ArrayList<>();
		for (PAFPlayer player : players) {
			PlayerData data = this.CONNECTION.getPlayerData(player.getUniqueId());
			if (data != null)
				playerData.add(data);
		}
		int deaths = 0;
		int kills = 0;
		double kds = 0.0D;
		for (PlayerData data : playerData) {
			deaths += data.deaths;
			kds += data.kd;
			kills += data.kills;
		}
		kds /= playerData.size();
		if (kds != kds)
			kds = 0.0D;
		pSender.sendMessage(this.MESSAGES_CONFIG.getString("ClanStats.KD").replace("[KD]", Math.round(kds * 100.0) / 100.0 + ""));
		pSender.sendMessage(this.MESSAGES_CONFIG.getString("ClanStats.Kills").replace("[KILLS]", kills + ""));
		pSender.sendMessage(this.MESSAGES_CONFIG.getString("ClanStats.Deaths").replace("[DEATHS]", deaths + ""));
	}

	public String getName() {
		return MESSAGES_CONFIG.getString("ClanStats.StatName");
	}
}
