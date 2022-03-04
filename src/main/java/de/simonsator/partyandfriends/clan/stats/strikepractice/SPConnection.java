package de.simonsator.partyandfriends.clan.stats.strikepractice;


import de.simonsator.partyandfriends.communication.sql.SQLCommunication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;


public class SPConnection extends SQLCommunication {

	protected SPConnection(String pDatabase, String pURL, String pUserName, String pPassword, boolean pSSL) {
		super(pDatabase, pURL, pUserName, pPassword, pSSL);
	}


	public PlayerData getPlayerData(UUID pUUID) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			rs = (stmt = con.createStatement()).executeQuery("select Deaths, Kills from `" + this.DATABASE +
					"`.stats WHERE uuid='" + pUUID.toString() + "' LIMIT 1");
			if (rs.next()) {
				return new PlayerData(rs.getInt("deaths"), rs.getInt("kills"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			close(rs, stmt);
		}
		return null;
	}

}