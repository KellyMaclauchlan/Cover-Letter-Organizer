package logic;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import gui.MainScreen;
import guiControllers.MainScreenController;
import guiControllers.SkillsScreenController;
import models.Skill;
import util.Observer;

public class OrginizerMain implements Observer {
	Data data;
	FileHandler fileHandler = new FileHandler();
	MainScreen frame;
	MainScreenController mainScreenController = new MainScreenController();
	Parser parser = new Parser();
	SaveFile saveFile = new SaveFile();
	SkillsScreenController skillsScreenController = new SkillsScreenController();

	public OrginizerMain() {
		// check if there is a saved file on start up if so import the data else
		// new data object
		if (this.saveFile.isSaved()) {
			this.data = this.saveFile.readData();
		} else {
			this.data = Data.getInstance();
		}
		// start main screen
		this.mainScreenController.setObserver(this);
		this.mainScreenController.showScreen(this.data.getKeys());
		this.skillsScreenController.setObserver(this);
	}

	// adds a scenario to the selected skill
	private void addSenario() {
		String newSenario = JOptionPane
				.showInputDialog("Enter a senario for " + this.skillsScreenController.getSelectedSkill() + ":");
		this.data.addScenarioTo(this.skillsScreenController.getSelectedSkill(), newSenario);
		this.skillsScreenController
				.updateScenarios(this.data.getSenarios(this.skillsScreenController.getSelectedSkill()));
	}

	// adds a sentence to the selected skill
	private void addSentence() {
		String newSentence = JOptionPane
				.showInputDialog("Enter a sentence for " + this.skillsScreenController.getSelectedSkill() + ":");
		this.data.addSentenceTo(this.skillsScreenController.getSelectedSkill(), newSentence);
		this.skillsScreenController
				.updateSentences(this.data.getSentences(this.skillsScreenController.getSelectedSkill()));
	}

	// add skill
	private void addSkill() {
		this.addSkillPrompt();
		this.skillsScreenController.updateSkills(this.data.getKeys());
		this.refreshMainScreen();
	}

	// displays the pop-up prompt for the user to ente a new skill
	private void addSkillPrompt() {
		String newSkill = JOptionPane.showInputDialog("Enter a keyword to match as a skill");
		this.data.addSkill(newSkill);
		this.mainScreenController.updateSkills(this.data.getKeys());
		this.refreshMainScreen();
	}

	// saves the program on quit
	private void close() {
		this.saveFile.saveData(this.data);
	}

	// deletes all the data in the application
	public void deleteAll() {
		int result = JOptionPane.showConfirmDialog(null, "Do you want to delete all the data?", null,
				JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			this.data.deleteAll();
		}
		this.refreshMainScreen();
	}

	// deletes the selected sentence
	private void deleteSentence() {
		String skill = this.skillsScreenController.getSelectedSkill();
		String sentence = this.skillsScreenController.getSelectedSentence();
		int result = JOptionPane.showConfirmDialog(null, "Do you want to delete: " + sentence + " from " + skill + "?",
				null, JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			this.data.deleteSentence(skill, sentence);
		}
		this.skillsScreenController.updateSentences(this.data.getSentences(skill));
		this.skillsScreenController.updateScenarios(this.data.getSenarios(skill));
		this.refreshMainScreen();
	}

	// deletes the selected skill
	private void deleteSkill() {
		Object[] possibilities = this.data.getKeys().toArray();
		String skill = (String) JOptionPane.showInputDialog(null,
				"Select a skill to delete, all assosiated sentences will also be deleted", "Customized Dialog",
				JOptionPane.PLAIN_MESSAGE, null, possibilities, possibilities[0]);
		this.data.deleteSkill(skill);
		this.refreshMainScreen();
	}

	// exports all the data to a text file
	private void exportAll() {
		this.fileHandler.exportToText(this.data.printAll());
	}

	// exports the selected list to a text file
	private void exportList() {
		this.fileHandler.exportToText(this.mainScreenController.getText());
	}

	// gets a single cover letter to read from the user
	private void importCoverLetter() {
		// gets the file the user selected and imports into the program.
		this.data = this.parser.toPlace(this.data,
				this.fileHandler.getfile(this.mainScreenController.getReadFromFile()));

		this.refreshMainScreen();
	}

	// gets a folder of cover letters to read from the user
	private void importCoverLetters() {
		// gets the file the user selected and imports into the program.
		File[] files = this.mainScreenController.getReadFromFile().listFiles();
		for (File f : files) {
			this.data = this.parser.toPlace(this.data, this.fileHandler.getfile(f));
		}
		this.refreshMainScreen();
	}

	// reads in a job description and displays the relative skills to the user
	private void importJobDescription() {
		String text = this.fileHandler.getfile(this.mainScreenController.getReadFromFile());
		String sentences = this.parser.containsSkills(this.data, text);
		this.mainScreenController.setText(sentences);
	}

	// opens the skill management window
	private void manageSkill() {
		if (!this.data.getKeys().isEmpty()) {
			ArrayList<String> skills = new ArrayList<String>();
			skills.addAll(this.data.getKeys());
			this.skillsScreenController.showSkills(this.data.getKeys(), this.data.getSentences(skills.get(0)),
					this.data.getSenarios(skills.get(0)));
		}
	}

	// moves a sentence/ scenario between skills either through a move or a copy
	private void moveSentence() {
		String skill = this.skillsScreenController.getSelectedSkill();
		String sentence = this.skillsScreenController.getSelectedSentence();

		String newSkill = (String) JOptionPane.showInputDialog(null,
				"Enter the skill you want to move " + sentence + " to.", "Customized Dialog", JOptionPane.PLAIN_MESSAGE,
				null, this.data.getKeys().toArray(), "0");
		String[] options = new String[] { "Copy", "Move" };
		int response = JOptionPane.showOptionDialog(null,
				"Would you like to copy or move the line? warning moving will delete from the curent skill", " ",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if (response == 0) {
			if (this.data.getSkill(skill).sentence.contains(sentence)) {
				this.data.addSentenceTo(newSkill, sentence);
			} else {
				this.data.addScenarioTo(newSkill, sentence);
			}
		} else {
			if (this.data.getSkill(skill).sentence.contains(sentence)) {
				this.data.addSentenceTo(newSkill, sentence);
			} else {
				this.data.addScenarioTo(newSkill, sentence);
			}
			this.data.getSkill(skill).removeSentence(sentence);
		}
		this.skillsScreenController.updateSentences(this.data.getSentences(skill));
		this.skillsScreenController.updateScenarios(this.data.getSenarios(skill));

		this.refreshMainScreen();
	}

	// adds a new skill
	private void newSkillMain() {
		this.addSkillPrompt();
	}

	// refreshes the main screen to display relevant information and reflect
	// changes
	public void refreshMainScreen() {
		this.mainScreenController.updateSkills(this.data.getKeys());
		this.mainScreenController.setSelectedSkill(0);
		String skill = this.mainScreenController.getSelectedSkill();
		if (this.mainScreenController.multiple) {
			this.data.selectMultiple(skill);
			this.mainScreenController.setText(this.data.printAllSelected());
		}
		if (this.data.getKeys() != null) {
			this.data.selectOne(skill);
			this.mainScreenController.setText(this.data.printAllSelected());
		}
	}

	// Selecting multiple skills toggle
	private void selectManyClicked() {
		this.mainScreenController.multiple = !this.mainScreenController.multiple;
	}

	// Selecting a skill from the side bar on the main screen and display the
	// information
	private void selectSkillMainBar() {

		Skill skill = this.data.getSkill(this.mainScreenController.getSelectedSkill());
		String s = skill.toString();
		if (this.mainScreenController.multiple) {
			s = this.mainScreenController.getText() + s;
			this.data.selectMultiple(skill.keyword);
			s = this.data.printAllSelected();
			this.mainScreenController.setText(s);
		} else {
			this.data.selectOne(skill.keyword);
			this.mainScreenController.setText(s);
		}
	}

	// select a new skill with the skill organizer
	private void selectSkillSkillOrg() {
		this.skillsScreenController
				.updateSentences(this.data.getSentences(this.skillsScreenController.getSelectedSkill()));
		this.skillsScreenController
				.updateScenarios(this.data.getSenarios(this.skillsScreenController.getSelectedSkill()));
	}

	// handles the update message received from the gui windows
	@Override
	public void update(String message) {

		switch (message) {
		case "importCL":
			this.importCoverLetter();
			break;
		case "importCLs":
			this.importCoverLetters();
			break;
		case "importJD":
			this.importJobDescription();
			break;
		case "exportAll":
			this.exportAll();
			break;
		case "exportList":
			this.exportList();
			break;
		case "selectSkillMainBar":
			this.selectSkillMainBar();
			break;
		case "newSkillMain":
			this.newSkillMain();
			break;
		case "manageSkill":
			this.manageSkill();
			break;
		case "deleteSkill":
			this.deleteSkill();
			break;
		case "deleteAll":
			this.deleteAll();
			break;
		case "selectMany":
			this.selectManyClicked();
			break;
		case "selectSkillSkillOrg":
			this.selectSkillSkillOrg();
			break;
		case "addSentence":
			this.addSentence();
			break;
		case "addSenario":
			this.addSenario();
			break;
		case "addSkill":
			this.addSkill();
			break;
		case "moveSentence":
			this.moveSentence();
			break;
		case "deleteSentence":
			this.deleteSentence();
			break;
		case "quit":
			this.close();
			break;
		default:
			System.out.println(message + " is not handled");
		}
		;
	}
}
