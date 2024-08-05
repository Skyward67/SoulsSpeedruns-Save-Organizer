package com.soulsspeedruns.organizer.about;

import java.awt.Color;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import com.soulsspeedruns.organizer.components.HyperLink;
import com.soulsspeedruns.organizer.managers.IconsAndFontsManager;
import com.soulsspeedruns.organizer.managers.OrganizerManager;
import com.soulsspeedruns.organizer.managers.VersionManager;

import jiconfont.icons.Elusive;
import jiconfont.swing.IconFontSwing;

/**
 * AboutPanel.
 * <p>
 * Panel containing information about the program.
 * </p>
 *
 * @author Kahmul (www.twitch.tv/kahmul78)
 * @date 5 Jun 2016
 */
public class AboutPanel extends JPanel {

	/**
	 * Creates a new AboutPanel.
	 */
	public AboutPanel() {
		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		JLabel versionLabel = new JLabel("<html><b>Version:</b></html>");
		versionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel versionNumberLabel = new JLabel(VersionManager.getVersion());

		JLabel developerLabel = new JLabel("<html><b>Original Developer:</b></html>");
		developerLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel discordLabel = createDiscordLabel("Kahmul");
		HyperLink devLink = hyperLinkFactory("Kahmul78", OrganizerManager.TWITTER_URL, IconFontSwing.buildIcon(Elusive.TWITTER, 20, new Color(64, 153, 255)));
		HyperLink githubLink = hyperLinkFactory("Kahmul78", OrganizerManager.GITHUB_REPO_URL, IconFontSwing.buildIcon(Elusive.GITHUB, 20));

		JLabel forkDeveloperLabel = new JLabel("<html><b>Developer:</b></html>");
		forkDeveloperLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel forkDiscordLabel = createDiscordLabel("Skyward67");
		HyperLink forkDevLink = hyperLinkFactory("DoDoxys", OrganizerManager.YOUTUBE_URL_FORK, IconFontSwing.buildIcon(Elusive.YOUTUBE, 20, new Color(255, 0, 0)));
		HyperLink forkGithubLink = hyperLinkFactory("Skyward67", OrganizerManager.GITHUB_REPO_URL_FORK, IconFontSwing.buildIcon(Elusive.GITHUB, 20));

		// Horizontal
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		hGroup.addGroup(layout.createParallelGroup(Alignment.TRAILING)
				.addComponent(versionLabel)
				.addComponent(developerLabel)
				.addComponent(discordLabel)
				.addComponent(devLink)
				.addComponent(githubLink)
				.addComponent(forkDeveloperLabel)
				.addComponent(forkDiscordLabel)
				.addComponent(forkDevLink)
				.addComponent(forkGithubLink));

		hGroup.addGroup(layout.createParallelGroup(Alignment.LEADING)
				.addComponent(versionNumberLabel));

		layout.setHorizontalGroup(hGroup);

		// Vertical
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(versionLabel)
				.addComponent(versionNumberLabel));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(developerLabel));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(discordLabel));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(devLink));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(githubLink));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(forkDeveloperLabel));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(forkDiscordLabel));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(forkDevLink));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(forkGithubLink));

		layout.setVerticalGroup(vGroup);

		setLayout(layout);
	}

	private JLabel createDiscordLabel(String text) {
		JLabel discordLabel = new JLabel(text);
		discordLabel.setIcon(IconsAndFontsManager.getDiscordIcon());
		return discordLabel;
	}

	private HyperLink hyperLinkFactory(String text, String url, Icon icon){
		HyperLink devLink = new HyperLink(text, url);
		devLink.setHorizontalAlignment(SwingConstants.RIGHT);
		devLink.setIcon(icon);
		return devLink;
	}
}
