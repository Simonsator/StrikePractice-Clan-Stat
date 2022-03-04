package de.simonsator.partyandfriends.clan.stats.strikepractice;


public class PlayerData {
	public final double kd;
	public final int deaths;
	public final int kills;

	public PlayerData(int deaths, int kills) {
		if (deaths != 0)
			this.kd = (double) kills / deaths;
		else
			this.kd = kills;
		this.deaths = deaths;
		this.kills = kills;
	}
}