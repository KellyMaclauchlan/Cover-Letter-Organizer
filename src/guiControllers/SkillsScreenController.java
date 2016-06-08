package guiControllers;

import java.util.HashSet;
import java.util.Set;

import gui.SkillScreen;
import util.Config;
import util.Observer;

public class SkillsScreenController {
	SkillScreen s = new SkillScreen();
	SkillScreen screen = new SkillScreen();
	private Set<String> skills = new HashSet<String>();

	public SkillsScreenController() {

	}

	// gets the selected sentence
	public String getSelectedSentence() {
		return this.screen.getSelectedSentence();
	}

	// gets the selected skill
	public String getSelectedSkill() {
		return this.screen.getSelectedSkill();
	}

	// sets observer for gui
	public void setObserver(Observer ob) {
		this.screen.registerObserver(ob);
	}

	// displays the screen and updates the lists
	public void showSkills(Set<String> skill, Set<String> sentence, Set<String> senario) {
		this.skills = skill;
		this.screen.showScreen(skill);
		this.screen.setSentencesList(sentence);
		this.screen.setScenarioList(senario);
		this.screen.setBackgroundColours(Config.redPurple);
		this.screen.setButtonColours(Config.purple);
		this.screen.setTextColours(Config.cream);
		this.screen.setSkills(skill);

	}

	// updates the scenario list
	public void updateScenarios(Set<String> senarios) {
		this.screen.setScenarioList(senarios);
	}

	// updates the sentence list
	public void updateSentences(Set<String> set) {
		this.screen.setSentencesList(set);
	}

	// updates the skills list
	public void updateSkills(Set<String> skill) {
		this.skills = skill;
		this.screen.setSkills(skill);
	}

}
