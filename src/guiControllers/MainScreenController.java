package guiControllers;

import java.io.File;
import java.util.Set;

import gui.MainScreen;
import util.Config;
import util.Observer;

public class MainScreenController {
	public boolean multiple = false;
	MainScreen screen = new MainScreen();

	public MainScreenController() {

	}

	// gets the file from the user
	public File getReadFromFile() {
		return this.screen.getReadFromFile();
	}

	// gets the selected skill
	public String getSelectedSkill() {
		return this.screen.getSelectedSkill();
	}

	// gets the displayed text
	public String getText() {
		return this.screen.getText();
	}

	// sets the observer for the gui
	public void setObserver(Observer ob) {
		this.screen.registerObserver(ob);
	}

	// sets the selected skill
	public void setSelectedSkill(int index) {
		this.screen.selectSkill(index);
	}

	// sets the text displayed
	public void setText(String s) {
		this.screen.updateText(s);
	}

	// shows the screen and updates the lists and sets colour of screen
	public void showScreen(Set<String> skills) {
		this.screen.updateSkills(skills);
		this.screen.setVisible(true);
		this.screen.setBackgroundColours(Config.darkBrown);
		this.screen.setButtonColours(Config.teal);
		this.screen.setTextColours(Config.cream);
		this.screen.setMenuColours(Config.grey);

	}

	// updates the list of skills
	public void updateSkills(Set<String> skills) {
		this.screen.updateSkills(skills);
	}
}
