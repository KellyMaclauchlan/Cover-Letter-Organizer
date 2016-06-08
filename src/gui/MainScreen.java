package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import util.Config;
import util.Observer;
import util.Subject;

public class MainScreen extends JFrame implements Subject {

	private JMenuItem deleteSkillMenuItem;
	private JMenuItem deleteSkillsMenuItem;
	private JMenu editMenu;
	private JMenuItem editSkillMenuItem;
	private JMenuItem exitMenuItem;
	private JMenuItem exportAllMenuItem;
	private JMenuItem exportCurrentListMenuItem;
	private JButton exportListButton;
	JFileChooser fc = new JFileChooser();
	private JMenu fileMenu;
	private JMenu helpMenu;
	private JMenuItem helpMenuItem;
	private JMenuItem importCoverLetterMenuItem;
	private JMenuItem importCoverLettersMenuItem;
	private JMenuItem importJobMenuItem;
	private JMenuBar jMenuBar;
	private JPanel jPanel1;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane3;
	private JPopupMenu.Separator jSeparator1;
	private JPopupMenu.Separator jSeparator2;
	private JSeparator jSeparator3;
	private JSplitPane jSplitPane1;
	private JTextPane jTextPane;
	private JButton manageSkillsButton;
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private JButton newSkillButton;
	ArrayList<Observer> observers = new ArrayList<Observer>();
	private File readFromFile;
	private String selectedSkill = "";
	private JCheckBox selectMultipleSkillsCheckBox;
	private JList<String> skillsList;
	private JMenu viewMenu;
	private JMenuItem viewSkillsMenuItem;

	public MainScreen() {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.initComponents();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				MainScreen.this.closing();
			}
		});
	}

	public void closing() {
		this.notifyObservers("quit");
		System.exit(0);
	}

	private void deleteSkillMenuItemActionPerformed(ActionEvent evt) {

		this.notifyObservers("deleteSkill");
	}

	private void deleteSkillsMenuItemActionPerformed(ActionEvent evt) {

		this.notifyObservers("deleteAll");
	}

	// edit menu items
	private void editSkillMenuItemActionPerformed(ActionEvent evt) {

		this.notifyObservers("manageSkill");
	}

	private void exitMenuItemActionPerformed(ActionEvent evt) {
		this.closing();
	}

	private void exportAllMenuItemActionPerformed(ActionEvent evt) {

		// popup asking what format to get file. pdf or txt maybe word
		this.notifyObservers("exportAll");
	}

	private void exportCurrentListMenuItemActionPerformed(ActionEvent evt) {

		// popup asking what format to get file. pdf or txt maybe word
		this.notifyObservers("exportList");
	}

	private void exportListButtonActionPerformed(ActionEvent evt) {

		this.notifyObservers("exportList");
	}

	public File getReadFromFile() {
		return this.readFromFile;
	}

	public String getSelectedSkill() {
		return this.selectedSkill;
	}

	public String getText() {
		return this.jTextPane.getText();
	}

	// help menu items
	private void helpMenuItemActionPerformed(ActionEvent evt) {

		JTextArea text = new JTextArea(Config.help);
		JScrollPane scroll = new JScrollPane(text);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		scroll.setPreferredSize(new Dimension(500, 500));
		JOptionPane.showMessageDialog(this, scroll, "", JOptionPane.YES_OPTION);
	}

	// file menu items
	private void importCoverLetterMenuItemActionPerformed(ActionEvent evt) {

		this.fc.setDialogTitle("Select file to import");
		this.fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = this.fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = this.fc.getSelectedFile();
			this.setReadFromFile(file);
			this.notifyObservers("importCL");

		}

	}

	private void importCoverLettersMenuItemActionPerformed(ActionEvent evt) {

		this.fc.setDialogTitle("Select folder to import");
		this.fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = this.fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = this.fc.getSelectedFile();
			this.setReadFromFile(file);
			this.notifyObservers("importCLs");

		}

	}

	private void importJobMenuItemActionPerformed(ActionEvent evt) {

		this.fc.setDialogTitle("Select file to import");
		this.fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = this.fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = this.fc.getSelectedFile();
			this.setReadFromFile(file);
			this.notifyObservers("importJD");
		}

	}

	// gui creation
	private void initComponents() {
		this.jSplitPane1 = new JSplitPane();
		this.jScrollPane1 = new JScrollPane();
		this.skillsList = new JList<String>();
		this.jScrollPane3 = new JScrollPane();
		this.jTextPane = new JTextPane();
		this.jPanel1 = new JPanel();
		this.newSkillButton = new JButton();
		this.manageSkillsButton = new JButton();
		this.jSeparator3 = new JSeparator();
		this.selectMultipleSkillsCheckBox = new JCheckBox();
		this.exportListButton = new JButton();
		this.jMenuBar = new JMenuBar();
		this.fileMenu = new JMenu();
		this.importCoverLetterMenuItem = new JMenuItem();
		this.importCoverLettersMenuItem = new JMenuItem();
		this.importJobMenuItem = new JMenuItem();
		this.jSeparator1 = new JPopupMenu.Separator();
		this.exportAllMenuItem = new JMenuItem();
		this.exportCurrentListMenuItem = new JMenuItem();
		this.jSeparator2 = new JPopupMenu.Separator();
		this.exitMenuItem = new JMenuItem();
		this.editMenu = new JMenu();
		this.editSkillMenuItem = new JMenuItem();
		this.deleteSkillMenuItem = new JMenuItem();
		this.deleteSkillsMenuItem = new JMenuItem();
		this.viewMenu = new JMenu();
		this.viewSkillsMenuItem = new JMenuItem();
		this.helpMenu = new JMenu();
		this.helpMenuItem = new JMenuItem();

		this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Cover Letter");

		this.skillsList.setModel(this.model);
		this.skillsList.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				MainScreen.this.skillsListMouseClicked(evt);
			}
		});

		this.jScrollPane1.setViewportView(this.skillsList);

		this.jSplitPane1.setLeftComponent(this.jScrollPane1);

		this.jTextPane.setEditable(false);
		this.jTextPane.setText("");
		this.jScrollPane3.setViewportView(this.jTextPane);

		this.jSplitPane1.setRightComponent(this.jScrollPane3);

		this.newSkillButton.setText("New Skill");
		this.newSkillButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainScreen.this.newSkillButtonActionPerformed(evt);
			}
		});

		this.manageSkillsButton.setText("Manage Skills");
		this.manageSkillsButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainScreen.this.manageSkillsButtonActionPerformed(evt);
			}
		});

		this.jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

		this.selectMultipleSkillsCheckBox.setText("Select Multiple Skills");
		this.selectMultipleSkillsCheckBox.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainScreen.this.selectMultipleSkillsCheckBoxActionPerformed(evt);
			}
		});

		this.exportListButton.setText("Export List");
		this.exportListButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainScreen.this.exportListButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
		this.jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(this.manageSkillsButton, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(this.newSkillButton, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(this.jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
						.addComponent(this.selectMultipleSkillsCheckBox)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)

						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(this.exportListButton).addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(16, 16, 16).addComponent(this.newSkillButton)
						.addGap(18, 18, 18).addComponent(this.manageSkillsButton).addContainerGap(31, Short.MAX_VALUE))
				.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(this.jSeparator3)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
								.addGap(40, 40, 40)
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(this.selectMultipleSkillsCheckBox)
										.addComponent(this.exportListButton))
								.addGap(0, 0, Short.MAX_VALUE)))
						.addContainerGap()));

		this.fileMenu.setText("File");

		this.importCoverLetterMenuItem.setText("Import Cover Letter");
		this.importCoverLetterMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainScreen.this.importCoverLetterMenuItemActionPerformed(evt);
			}
		});
		this.fileMenu.add(this.importCoverLetterMenuItem);
		this.importCoverLetterMenuItem.getAccessibleContext().setAccessibleName("importCoverLetterMenuItem");

		this.importCoverLettersMenuItem.setText("Import Cover Letters");
		this.importCoverLettersMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainScreen.this.importCoverLettersMenuItemActionPerformed(evt);
			}

		});
		this.fileMenu.add(this.importCoverLettersMenuItem);
		this.importCoverLettersMenuItem.getAccessibleContext().setAccessibleName("importCoverLettersMenuItem");

		this.importJobMenuItem.setText("Import Job");
		this.importJobMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainScreen.this.importJobMenuItemActionPerformed(evt);
			}
		});
		this.fileMenu.add(this.importJobMenuItem);
		this.importJobMenuItem.getAccessibleContext().setAccessibleName("importJobMenuItem");

		this.fileMenu.add(this.jSeparator1);

		this.exportAllMenuItem.setText("Export All");
		this.exportAllMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainScreen.this.exportAllMenuItemActionPerformed(evt);
			}
		});
		this.fileMenu.add(this.exportAllMenuItem);

		this.exportCurrentListMenuItem.setText("Export Current List");
		this.exportCurrentListMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainScreen.this.exportCurrentListMenuItemActionPerformed(evt);
			}
		});
		this.fileMenu.add(this.exportCurrentListMenuItem);
		this.fileMenu.add(this.jSeparator2);

		this.exitMenuItem.setText("Exit");
		this.exitMenuItem.setToolTipText("");
		this.exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainScreen.this.exitMenuItemActionPerformed(evt);
			}
		});
		this.fileMenu.add(this.exitMenuItem);

		this.jMenuBar.add(this.fileMenu);

		this.editMenu.setText("Edit");

		this.editSkillMenuItem.setText("Edit Skill");
		this.editSkillMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainScreen.this.editSkillMenuItemActionPerformed(evt);
			}
		});
		this.editMenu.add(this.editSkillMenuItem);

		this.deleteSkillMenuItem.setText("Delete Skill");
		this.deleteSkillMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainScreen.this.deleteSkillMenuItemActionPerformed(evt);
			}
		});
		this.editMenu.add(this.deleteSkillMenuItem);
		this.deleteSkillsMenuItem.setText("Delete all data");
		this.deleteSkillsMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainScreen.this.deleteSkillsMenuItemActionPerformed(evt);
			}
		});
		this.editMenu.add(this.deleteSkillsMenuItem);

		this.jMenuBar.add(this.editMenu);

		this.viewMenu.setText("View");
		this.viewMenu.setToolTipText("");

		this.viewSkillsMenuItem.setText("Skills");
		this.viewSkillsMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainScreen.this.viewSkillsMenuItemActionPerformed(evt);
			}
		});
		this.viewMenu.add(this.viewSkillsMenuItem);

		this.jMenuBar.add(this.viewMenu);

		this.helpMenu.setText("Help");

		this.helpMenuItem.setText("User guide");
		this.helpMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainScreen.this.helpMenuItemActionPerformed(evt);
			}
		});
		this.helpMenu.add(this.helpMenuItem);

		this.jMenuBar.add(this.helpMenu);

		this.setJMenuBar(this.jMenuBar);
		javax.swing.GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addComponent(this.jSplitPane1));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(this.jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(this.jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		this.pack();
	}

	private void manageSkillsButtonActionPerformed(ActionEvent evt) {
		this.notifyObservers("manageSkill");
	}

	private void newSkillButtonActionPerformed(ActionEvent evt) {
		this.notifyObservers("newSkillMain");
	}

	@Override
	public void notifyObservers(String message) {
		for (Observer o : this.observers) {
			o.update(message);
		}
	}

	@Override
	public void registerObserver(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		this.observers.remove(observer);
	}

	public void selectFile() {
		int returnVal = this.fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = this.fc.getSelectedFile();
			this.setReadFromFile(file);
			this.notifyObservers(Config.NEWFILE);
		}
	}

	private void selectMultipleSkillsCheckBoxActionPerformed(ActionEvent evt) {
		this.notifyObservers("selectMany");
	}

	public void selectSkill(int index) {
		this.skillsList.setSelectedIndex(index);
		if (index < this.skillsList.getModel().getSize()) {
			this.setSelectedSkill(this.skillsList.getSelectedValue().toString());
		}

	}

	public void setBackgroundColours(String colour) {
		Color c = Color.decode(colour);
		this.setBackground(c);
		this.getContentPane().setBackground(c);
		this.jPanel1.setBackground(c);
		this.jSplitPane1.setBackground(c);
		this.jScrollPane3.setBackground(c);
	}

	public void setButtonColours(String colour) {
		Color c = Color.decode(colour);
		this.exportListButton.setForeground(c);
		this.manageSkillsButton.setForeground(c);
		this.newSkillButton.setForeground(c);
	}

	public void setMenuColours(String colour) {
		Color c = Color.decode(colour);
		this.jMenuBar.setBackground(c);
		this.jMenuBar.setForeground(c);
	}

	public void setReadFromFile(File readFromFile) {
		this.readFromFile = readFromFile;
	}

	public void setSelectedSkill(String selectedSkill) {
		this.selectedSkill = selectedSkill;
	}

	public void setTextColours(String colour) {
		Color c = Color.decode(colour);
		this.jTextPane.setBackground(c);
		this.skillsList.setBackground(c);
		this.selectMultipleSkillsCheckBox.setForeground(c);
	}

	private void skillsListMouseClicked(java.awt.event.MouseEvent evt) {

		this.setSelectedSkill(this.skillsList.getSelectedValue().toString());
		this.notifyObservers("selectSkillMainBar");

	}

	public void updateSkills(Set<String> skills) {
		this.model.clear();
		for (String s : skills) {
			this.model.addElement(s);
		}
	}

	public void updateText(String s) {
		this.jTextPane.setText(s);
	}

	// view menu items
	private void viewSkillsMenuItemActionPerformed(ActionEvent evt) {
		this.notifyObservers("manageSkill");
	}

}
