package com.soulsspeedruns.organizer.main;


import java.awt.Color;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.soulsspeedruns.organizer.data.OrganizerManager;
import com.soulsspeedruns.organizer.games.Game;
import com.soulsspeedruns.organizer.listeners.ProfileListener;
import com.soulsspeedruns.organizer.listeners.SaveListener;
import com.soulsspeedruns.organizer.profileconfig.Profile;
import com.soulsspeedruns.organizer.savelist.Folder;
import com.soulsspeedruns.organizer.savelist.ReadOnlyButton;
import com.soulsspeedruns.organizer.savelist.Save;
import com.soulsspeedruns.organizer.savelist.SaveListEntry;
import com.soulsspeedruns.organizer.settings.SettingsButton;
import com.soulsspeedruns.organizer.settings.SettingsContextMenu;

import jiconfont.icons.Elusive;
import jiconfont.icons.FontAwesome;
import jiconfont.icons.Iconic;
import jiconfont.swing.IconFontSwing;


/**
 * Bottom segment of the main window.
 * <p>
 * Contains the import, load, read-only and help buttons.
 *
 * @author Kahmul (www.twitch.tv/kahmul78)
 * @date 27 Sep 2015
 */
public class ButtonPanel extends JPanel
{

	private static final long serialVersionUID = 4450835782973692167L;


	/**
	 * Creates a new ButtonPanel for the main window.
	 */
	protected ButtonPanel()
	{
		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		ReadOnlyButton readOnlyButton = new ReadOnlyButton(OrganizerManager.getSelectedGame().getSaveFileLocation(),
				new ImageIcon(OrganizerManager.readOnlyIconMedium));

		JButton importButton = createImportButton();
		JButton loadButton = createLoadButton();
		JButton replaceButton = createReplaceButton();
		JButton settingsButton = createSettingsButton();
//		SettingsButton settingsButton = new SettingsButton();

		Component glue = Box.createHorizontalGlue();

		// Horizontal
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		hGroup.addGroup(layout.createParallelGroup().addComponent(importButton));

		hGroup.addGroup(layout.createParallelGroup().addComponent(loadButton));
		hGroup.addGroup(layout.createParallelGroup().addComponent(replaceButton));
		hGroup.addGap(10);
		hGroup.addGroup(layout.createParallelGroup().addComponent(readOnlyButton));
		hGroup.addGroup(layout.createParallelGroup().addComponent(glue));
		hGroup.addGroup(layout.createParallelGroup().addComponent(settingsButton));

		layout.setHorizontalGroup(hGroup);

		// Vertical
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		vGroup.addGroup(layout.createParallelGroup(Alignment.CENTER).addComponent(importButton).addComponent(loadButton)
				.addComponent(replaceButton).addComponent(readOnlyButton).addComponent(glue).addComponent(settingsButton));
		vGroup.addGap(10);

		layout.setVerticalGroup(vGroup);

		setLayout(layout);

		addButtonListeners(loadButton, replaceButton);
	}


	/**
	 * Creates the 'Import Savestate' button.
	 * 
	 * @return the import button
	 */
	private JButton createImportButton()
	{
		JButton importButton = new JButton("Import Savestate");
		importButton.setIcon(IconFontSwing.buildIcon(Iconic.CURVED_ARROW, 16, new Color(30, 144, 255)));
		importButton.addActionListener(event -> {
			Profile profile = OrganizerManager.getSelectedProfile();
			if (profile.getRoot() != null)
			{
				OrganizerManager.importSavefile(null);
				return;
			}
			JOptionPane.showMessageDialog(null,
					"Create a profile before trying to import a savefile! You can do this in the profile configuration settings.",
					"Warning", JOptionPane.WARNING_MESSAGE);
		});
		return importButton;
	}


	/**
	 * Creates the 'Load Savestate' button.
	 * 
	 * @param readOnlyButton the read-only button of this panel
	 * @return the load button
	 */
	private JButton createLoadButton()
	{
		JButton loadButton = new JButton("Load Savestate");
		loadButton.setIcon(IconFontSwing.buildIcon(Elusive.REPEAT, 15, new Color(39, 174, 96)));
		loadButton.addActionListener(event -> {
			SaveListEntry entry = OrganizerManager.getSelectedEntry();
			if (entry instanceof Folder)
				return;
			OrganizerManager.loadSave((Save) entry);
		});
		loadButton.setEnabled(false);
		return loadButton;
	}


	/**
	 * Creates the 'Replace Savestate' button.
	 * 
	 * @return the replace button
	 */
	private JButton createReplaceButton()
	{
		JButton replaceButton = new JButton("Replace Savestate");
		replaceButton.setIcon(IconFontSwing.buildIcon(Elusive.REFRESH, 15, new Color(255, 168, 0)));
		replaceButton.addActionListener(event -> {
			Save selectedSave = (Save) OrganizerManager.getSelectedEntry();
			int confirm = JOptionPane.showConfirmDialog(getParent(), "Do you really want to replace '" + selectedSave.getName() + "'?",
					"Replace " + selectedSave.getName(), JOptionPane.YES_NO_OPTION);
			if (confirm != 0)
				return;
			OrganizerManager.importAndReplaceSavefile(selectedSave);
		});
		replaceButton.setEnabled(false);
		return replaceButton;
	}
	
	/**
	 * Creates the settings button.
	 * 
	 * @return the settings button
	 */
	private JButton createSettingsButton()
	{
		JButton settingsButton = new JButton(IconFontSwing.buildIcon(FontAwesome.COG, 17, Color.GRAY));
		settingsButton.addActionListener(event -> {
			new SettingsContextMenu(settingsButton);
		});
		return settingsButton;
	}


	/**
	 * Adds the listeners to enable/disable the load and replace button
	 * 
	 * @param loadButton the load button
	 */
	private void addButtonListeners(JButton loadButton, JButton replaceButton)
	{
		OrganizerManager.addSaveListener(new SaveListener() {

			@Override
			public void entrySelected(SaveListEntry entry)
			{
				if (entry != null)
				{
					loadButton.setEnabled(entry instanceof Save);
					replaceButton.setEnabled(entry instanceof Save);
					return;
				}
				loadButton.setEnabled(false);
				replaceButton.setEnabled(false);
			}


			@Override
			public void entryCreated(SaveListEntry entry)
			{
			}


			@Override
			public void entryRenamed(SaveListEntry entry)
			{
			}


			@Override
			public void saveLoadStarted(Save save)
			{
			}


			@Override
			public void saveLoadFinished(Save save)
			{
			}


			@Override
			public void gameFileWritableStateChanged(boolean writable)
			{
			}

		});

		OrganizerManager.addProfileListener(new ProfileListener() {

			@Override
			public void profileDeleted(Profile profile)
			{
			}


			@Override
			public void profileCreated(Profile profile)
			{
			}


			@Override
			public void profileDirectoryChanged(Game game)
			{
			}


			@Override
			public void changedToProfile(Profile profile)
			{
				loadButton.setEnabled(false);
			}


			@Override
			public void changedToGame(Game game)
			{
				loadButton.setEnabled(false);
			}

		});
	}
}
