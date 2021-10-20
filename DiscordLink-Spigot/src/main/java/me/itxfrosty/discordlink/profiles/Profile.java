package me.itxfrosty.discordlink.profiles;

import lombok.Getter;
import me.itxfrosty.database.QueryHandler;
import me.itxfrosty.database.managers.AccountLinkManager;
import me.itxfrosty.database.tables.AccountLink;
import org.bukkit.entity.Player;

public class Profile {

	@Getter private final Player player;
	@Getter private long discordID;

	public Profile(Player player) {
		this.player = player;

		final QueryHandler<AccountLink> accountLink = AccountLinkManager.getAccountLinkByPlayer(player.getUniqueId());
		accountLink.onComplete(complete -> {
			if (complete != null) {
				this.discordID = Long.parseLong(complete.getDiscordID());
			}

		}).execute();
	}

}
