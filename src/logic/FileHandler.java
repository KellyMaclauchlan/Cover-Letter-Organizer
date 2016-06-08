package logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class FileHandler {
	File file;

	public FileHandler() {
	}

	// exports the given string to a text file
	public void exportToText(String string) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save To");
		JFrame parentFrame = new JFrame();
		int selected = fileChooser.showSaveDialog(parentFrame);
		if (selected == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			try {
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(string);
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// gets the text representation of the given file
	public String getfile(File file) {
		this.file = file;
		if (file.getName().endsWith("pdf")) {
			return this.pdfFile();
		} else if (file.getName().endsWith("txt")) {
			return this.textFile();

		} else if (file.getName().endsWith("docx")) {
			return this.wordxFile();
		} else if (file.getName().endsWith("doc")) {
			return this.wordFile();
		}
		return "Error unsupported file";
	}

	// input is pdf
	public String pdfFile() {
		System.out.println("this is a pdf");
		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		try {
			PDFParser parser = new PDFParser(new FileInputStream(this.file));
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(5);
			String parsedText = pdfStripper.getText(pdDoc);
			return parsedText;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	// input is .txt
	public String textFile() {
		System.out.println("this is a text");
		String data = null;
		try {
			data = FileUtils.readFileToString(this.file, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(data);
		return data;
	}

	// input is word
	public String wordFile() {
		System.out.println("this is a word doc");
		HWPFDocument doc = null;
		try {
			doc = new HWPFDocument(new FileInputStream(this.file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		WordExtractor we = new WordExtractor(doc);
		String text = we.getText();
		try {
			we.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}

	// input is docx
	public String wordxFile() {
		System.out.println("this is a word docx");
		String text = null;
		try {
			XWPFDocument docx = new XWPFDocument(new FileInputStream(this.file));
			XWPFWordExtractor we = new XWPFWordExtractor(docx);
			text = we.getText();
			we.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}

}
