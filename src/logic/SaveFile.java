package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import util.Config;

public class SaveFile {

	Gson gson;

	public SaveFile() {
		this.gson = new GsonBuilder().create();
	}

	// Checks to see if a save file exists on the users computer
	public boolean isSaved() {
		String path = System.getProperty("user.home");
		return new File(path + Config.COVER_LETTER_APP_SAVE_FILE_JSON).exists();
	}

	// reads the save file from the users computer
	public Data readData() {
		Data data = null;
		Reader reader;
		try {
			String path = System.getProperty("user.home");
			reader = new InputStreamReader(new FileInputStream(path + Config.COVER_LETTER_APP_SAVE_FILE_JSON));
			data = this.gson.fromJson(reader, Data.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	// saves the program information to the users computer
	public void saveData(Data data) {
		try {
			String path = System.getProperty("user.home");
			File file = new File(path + "/CoverLetterApp");
			file.mkdirs();
			Writer writer = new FileWriter(path + Config.COVER_LETTER_APP_SAVE_FILE_JSON);
			this.gson.toJson(data, writer);
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
