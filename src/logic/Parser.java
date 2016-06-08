package logic;

public class Parser {

	public Parser() {
	}

	// gets the skills contained in the file for a job description
	public String containsSkills(Data data, String file) {
		String response = "";
		for (String key : data.getKeys()) {
			if (file.toLowerCase().contains(key.toLowerCase())) {
				response += data.getSkill(key).toString();
				response += "\n";
			}
		}
		return response;
	}

	// takes the text from a cover letter and sorts it into the program
	public Data toPlace(Data data, String file) {
		file = file.replace('!', ' ');
		String[] sentences = file.split("[.\\n]");
		for (String s : sentences) {
			for (String key : data.getKeys()) {
				if (s.toLowerCase().contains(key.toLowerCase())) {
					data.addSentenceTo(key, s);
				}
			}
		}
		return data;
	}

}
