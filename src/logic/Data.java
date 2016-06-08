package logic;

import java.util.HashMap;
import java.util.Set;

import models.Skill;

public class Data {
	private static Data instance = null;

	public static Data getInstance() {
		if (instance == null) {
			instance = new Data();
		}
		return instance;

	}

	private HashMap<String, Skill> skills;

	protected Data() {
		this.skills = new HashMap<String, Skill>();
	}

	// add scenario to skill
	public void addScenarioTo(String key, String scenario) {
		this.skills.get(key).addScenario(scenario);
	}

	// adds a sentence to skill
	public void addSentenceTo(String key, String sentence) {
		this.skills.get(key).addSentence(sentence);
	}

	// add a skill
	public void addSkill(String skill) {
		this.skills.put(skill, new Skill(skill));
	}

	// deletes all skills
	public void deleteAll() {
		this.skills.clear();
	}

	// deletes sentence from skill
	public void deleteSentence(String skill, String sentence) {
		this.skills.get(skill).removeSentence(sentence);
	}

	// deletes a skill
	public void deleteSkill(String skill) {
		this.skills.remove(skill);
	}

	// gets the set of skills
	public Set<String> getKeys() {
		return this.skills.keySet();
	}

	// gets the scenarios for a skill
	public Set<String> getSenarios(String skill) {
		return this.skills.get(skill).scenario;
	}

	// gets the sentences for a skill
	public Set<String> getSentences(String skill) {
		return this.skills.get(skill).sentence;
	}

	// gets a skill given the word associated with it
	public Skill getSkill(String k) {
		return this.skills.get(k);
	}

	// text representation of all skills
	public String printAll() {
		String string = "";
		for (Skill s : this.skills.values()) {
			string += s.toString();
		}
		return string;
	}

	// text representation of selected skill(s)
	public String printAllSelected() {
		String string = "";
		for (Skill s : this.skills.values()) {
			if (s.selected) {
				string += s.toString();
			}
		}
		return string;
	}

	// select the given skill other selected skills don't matter
	public void selectMultiple(String skill) {
		this.skills.get(skill).selected = true;
	}

	// deselects other skills before selecting the new skill
	public void selectOne(String skill) {
		if (!this.skills.isEmpty()) {
			for (Skill s : this.skills.values()) {
				s.selected = false;
			}
			this.skills.get(skill).selected = true;
		}
	}
}
