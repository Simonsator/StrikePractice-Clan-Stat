package de.simonsator.partyandfriends.clan.stats.strikepractice;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.utilities.Language;
import de.simonsator.partyandfriends.utilities.LanguageConfiguration;

import java.io.File;
import java.io.IOException;

public class SPMessages extends LanguageConfiguration {
	public SPMessages(Language pLanguage, File pFile, PAFExtension pPlugin) throws IOException {
		super(pLanguage, pFile, pPlugin, true);
		readFile();
		loadDefaultValues();
		saveFile();
		process();
	}

	private void loadDefaultValues() {
		set("ClanStats.StatName", "StrikePractice");
		set("ClanStats.KD", "&7The average K/D of the clan is &a[KD]&7.");
		set("ClanStats.Kills", "&7The people of the clan have killed &a[KILLS] &7people.");
		set("ClanStats.Deaths", "&7The people of the clan have died &a[DEATHS]&7 times.");
	}
}