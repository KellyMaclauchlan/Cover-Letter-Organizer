package models;

import java.util.HashSet;
import java.util.Set;

public class Skill {
	public String keyword;
	public Set<String> scenario;
	public boolean selected;
	public Set<String> sentence;

	public Skill(String word) {
		this.keyword = word;
		this.scenario = new HashSet<String>();
		this.sentence = new HashSet<String>();
	}

	// adds a scenario
	public void addScenario(String senario) {
		this.scenario.add(senario);
	}

	// add sentence to the skill
	public void addSentence(String sentence) {
		this.sentence.add(sentence);
	}

	// remove sentence from the skill
	public void removeSentence(String sentence) {
		if (this.sentence.contains(sentence)) {
			this.sentence.remove(sentence);
		} else {
			this.scenario.remove(sentence);
		}
	}

	// string representation of a skill
	@Override
	public String toString() {
		String string = "";
		string += "***" + this.keyword + "***\n";
		string += "Sentences:\n";
		int count = 1;
		for (String s : this.sentence) {
			string += " " + count + ". " + s + "\n";
			count++;
		}
		string += "Senarios:\n";
		count = 1;
		for (String s : this.scenario) {
			string += " " + count + ". " + s + "\n";
			count++;
		}
		return string;
	}
}
