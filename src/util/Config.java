package util;

public class Config {
	public static String beige = "#B2AA8E";

	public static final String COVER_LETTER_APP_SAVE_FILE_JSON = "/CoverLetterApp/SaveFile.json";
	// colours
	public static String cream = "#FAF3DD";
	public static String darkBlue = "#33658A";
	public static String darkBrown = "#2C0915";
	public static String grey = "#515052";
	// help documentation
	public static String help = "Overview:\n"
			+ "This is an application to sort the contents of cover letters using key words and sorting the sentences that contain them.  By grouping the information together in one place you can organize the information you wish to present to potential employers.  You are able to enter the skills you want to extract from the cover letters and then import the cover letters individually or in a folder.  You can also import a job description and see the skills and sentences relative to the job.\n"
			+ "Steps to use: \n" + "add a new skill(s) by clicking the new skill button \n"
			+ "after you have entered the skills you would like select the import cover letter or import cover letters from the menu under file\n"
			+ "the relevant sentences will be sorted into he skills\n"
			+ "view one or multiple skills by selecting a skill on the left side menu and the select multiple skills check box \n"
			+ "export the list through the button or the file menu \n"
			+ "edit the sentences and scenarios under a skill using the manage skills button or the  edit skill  and view skill menu items.\n"
			+ "Details:\n" + "The following is a description of each page of the application:\n" + "Main Screen:\n"
			+ "    Menu:\n" + "      File:\n" + "       Import Cover Letter: import one cover letter \n"
			+ "       Import Cover Letters: import a folder of cover letters\n"
			+ "       Import Job: import a job description to search against\n"
			+ "       Export All: Save a text file containing all the Skills and associated sentences and scenarios\n"
			+ "       Export Current List: Save a text file containing the selected Skills and associated sentences and scenarios.\n"
			+ "       Quit: exits the program \n\n" + "      Edit:\n"
			+ "       Edit Skill: will bring up the skill management window\n"
			+ "       Delete all data: will erase all information in the application \n" + "      View:\n"
			+ "       Skill: will bring up the skill management window\n" + "       Help: shows help documentation\n\n"
			+ "    The list on the left shows the skills you have when one is selected the information will show on the right \n\n"
			+ "    New Skill button: prompts you to enter a key word for a new skill \n"
			+ "    Manage skills : will bring up the skill management window\n"
			+ "    Select Multiple Skills: will allow the information from multiple skills to show on the right if selected\n"
			+ "    Export List will export what is showing on the right side of the screen to a text file\n"
			+ "Skill Management Screen:\n"
			+ "    Displayed on the screen are the lists of sentences and scenarios associated with the selected skill shown in the dropdown list\n\n"
			+ "    add sentence button: adds a sentence to the displayed skill\n"
			+ "    add scenario button: adds a scenario to the displayed skill\n2"
			+ "    change the skill by selecting a different one on the dropdown list\n"
			+ "    add skill button: prompts you to enter a key word for a new skill \n"
			+ "    the move and delete buttons will only be enabled in a sentence or scenario is selected\n"
			+ "    Delete button: deletes that sentence from the skill\n"
			+ "    Move button:  moves the selected sentence or scenario to another skill, this can copy ( keep in both skills) or be moved ( will be removed from the current skill)\n";
	public static String NEWFILE = "newfile";
	public static String purple = "#351431";
	public static String redPurple = "#731A33";
	public static String teal = "#0892A5";

	// groups of 5 colours
	// going from ligh to dark
	// []0 = text pane backround
	// [1] = button
	// [2] = background

	// 1
	// public static String[] cGroup1=

	public static String turquoise = "#07BEB8";

}
