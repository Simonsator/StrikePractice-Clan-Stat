package de.simonsator.partyandfriends.clan.stats.strikepractice;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.clan.commands.ClanCommands;
import de.simonsator.partyandfriends.clan.commands.subcommands.Stats;
import de.simonsator.partyandfriends.utilities.ConfigurationCreator;
import de.simonsator.partyandfriends.utilities.Language;

import java.io.File;
import java.io.IOException;

public class SPStatMain extends PAFExtension {
	private ConfigurationCreator config;

	public void onEnable() {
		ConfigurationCreator messagesConfig = null;
		try {
			this.config = new SPConfig(new File(getDataFolder(), "config.yml"), this);
			messagesConfig = new SPMessages(Language.OWN, new File(getDataFolder(), "messages.yml"), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SPConnection connection = new SPConnection(this.config.getString("database.db"), "jdbc:mysql://" + this.config.getString("database.host") + ":" + this.config.getInt("database.port"), this.config.getString("database.user"), this.config.getString("database.password"), config.getBoolean("database.ssl"));
		((Stats) ClanCommands.getInstance().getSubCommand(Stats.class)).registerClanStats(new SPStat(connection, messagesConfig), this);
		registerAsExtension();
	}
}