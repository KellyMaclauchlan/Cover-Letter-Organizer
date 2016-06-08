package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import util.Observer;
import util.Subject;

public class SkillScreen extends JFrame implements Subject {

	private JButton addScenarioButton;
	private JButton addSentenceButton;
	private JButton addSkillButton;
	private JPanel contentPane;
	private JButton deleteSentenceButton;
	private JList<String> detailsList;
	private JList<String> detailsList1;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane3;
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private JButton moveButton;
	public ArrayList<Observer> observers = new ArrayList<Observer>();
	public String SelectedSentence = "";
	public String SelectedSkill = "";
	private DefaultListModel<String> senarioModel = new DefaultListModel<String>();
	private JComboBox<String> skillComboBox;
	private DefaultComboBoxModel<String> skillModel = new DefaultComboBoxModel<String>();

	public SkillScreen() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.setContentPane(this.contentPane);
	}

	private void addScenarioButtonActionPerformed(ActionEvent evt) {
		this.notifyObservers("addSenario");
	}

	private void addSentenceButtonActionPerformed(ActionEvent evt) {
		this.notifyObservers("addSentence");
	}

	private void addSkillButtonActionPerformed(ActionEvent evt) {
		this.notifyObservers("addSkill");
	}

	private void deleteSentenceButtonActionPerformed(ActionEvent evt) {
		this.notifyObservers("deleteSentence");
	}

	protected void detailsList1MouseClicked(MouseEvent evt) {
		this.moveButton.setEnabled(true);
		this.deleteSentenceButton.setEnabled(true);
		this.SelectedSentence = this.detailsList1.getSelectedValue();
	}

	private void detailsListMouseClicked(java.awt.event.MouseEvent evt) {
		this.moveButton.setEnabled(true);
		this.deleteSentenceButton.setEnabled(true);
		this.SelectedSkill = this.detailsList.getSelectedValue();
	}

	public String getSelectedSentence() {
		return this.detailsList.getSelectedValue();
	}

	public String getSelectedSkill() {
		return this.skillComboBox.getSelectedItem().toString();
	}

	// sets up the screen
	private void initComponents() {
		this.addSentenceButton = new JButton();
		this.addScenarioButton = new JButton();
		this.addSkillButton = new JButton();
		this.deleteSentenceButton = new JButton();
		this.moveButton = new JButton();
		this.skillComboBox = new JComboBox();
		this.jScrollPane2 = new JScrollPane();
		this.detailsList = new JList<String>();
		this.jScrollPane3 = new JScrollPane();
		this.detailsList1 = new JList<String>();
		this.jLabel1 = new JLabel();
		this.jLabel2 = new JLabel();

		this.setTitle("Manage Skills");

		this.addSentenceButton.setText("Add Sentence");
		this.addSentenceButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				SkillScreen.this.addSentenceButtonActionPerformed(evt);
			}
		});

		this.addScenarioButton.setText("Add Scenario");
		this.addScenarioButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				SkillScreen.this.addScenarioButtonActionPerformed(evt);
			}
		});

		this.addSkillButton.setText("Add Skill");
		this.addSkillButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				SkillScreen.this.addSkillButtonActionPerformed(evt);
			}
		});

		this.deleteSentenceButton.setText("Delete");
		this.deleteSentenceButton.setEnabled(false);
		this.deleteSentenceButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				SkillScreen.this.deleteSentenceButtonActionPerformed(evt);
			}
		});

		this.moveButton.setText("Move");
		this.moveButton.setEnabled(false);
		this.moveButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				SkillScreen.this.moveButtonActionPerformed(evt);
			}
		});

		this.skillComboBox.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		this.skillComboBox.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				SkillScreen.this.skillComboBoxActionPerformed(evt);
			}
		});

		this.detailsList.setModel(new AbstractListModel() {
			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };

			@Override
			public Object getElementAt(int i) {
				return this.strings[i];
			}

			@Override
			public int getSize() {
				return this.strings.length;
			}
		});
		this.detailsList.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				SkillScreen.this.detailsListMouseClicked(evt);
			}
		});
		this.jScrollPane2.setViewportView(this.detailsList);

		this.detailsList1.setModel(new AbstractListModel() {
			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };

			@Override
			public Object getElementAt(int i) {
				return this.strings[i];
			}

			@Override
			public int getSize() {
				return this.strings.length;
			}
		});
		this.detailsList1.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				SkillScreen.this.detailsList1MouseClicked(evt);
			}
		});
		this.jScrollPane3.setViewportView(this.detailsList1);

		this.jLabel1.setText("Sentences");

		this.jLabel2.setText("Senarios");

		javax.swing.GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(this.addScenarioButton, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(this.addSentenceButton, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addGap(18, 18, 18).addComponent(
												this.skillComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 170,
												javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup().addGap(46, 46, 46)
												.addComponent(this.addSkillButton)))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50,
										Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(this.moveButton, javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(this.deleteSentenceButton,
												javax.swing.GroupLayout.Alignment.TRAILING))
								.addGap(36, 36, 36))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addComponent(this.jScrollPane3).addContainerGap())
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addComponent(this.jScrollPane2).addContainerGap())
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(this.jLabel2).addComponent(this.jLabel1))
								.addGap(0, 0, Short.MAX_VALUE)))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(14, 14, 14).addComponent(this.jLabel1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(this.jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(8, 8, 8).addComponent(this.jLabel2)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(this.jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 122,
								javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(8, 8, 8)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(this.addSentenceButton).addComponent(this.deleteSentenceButton)
						.addComponent(this.skillComboBox, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(this.addScenarioButton).addComponent(this.addSkillButton)
						.addComponent(this.moveButton)).addContainerGap(19, Short.MAX_VALUE)));

		this.pack();
	}

	private void moveButtonActionPerformed(ActionEvent evt) {
		this.notifyObservers("moveSentence");
	}

	@Override
	public void notifyObservers(String message) {

		for (Observer ob : this.observers) {
			ob.update(message);
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

	public void setBackgroundColours(String colour) {
		Color c = Color.decode(colour);
		this.setBackground(c);
		this.getContentPane().setBackground(c);

	}

	public void setButtonColours(String colour) {
		Color c = Color.decode(colour);
		this.addSkillButton.setForeground(c);
		this.addScenarioButton.setForeground(c);
		this.addSentenceButton.setForeground(c);
		this.moveButton.setForeground(c);
		this.deleteSentenceButton.setForeground(c);
	}

	public void setScenarioList(Set<String> senario) {
		this.senarioModel.clear();
		for (String s : senario) {
			this.senarioModel.addElement(s);
		}
	}

	public void setSentencesList(Set<String> sentences) {
		this.model.clear();
		for (String s : sentences) {
			this.model.addElement(s);
		}
	}

	public void setSkills(Set<String> skills) {
		this.skillModel.removeAllElements();
		this.skillComboBox.removeAllItems();
		for (String s : skills) {
			this.skillComboBox.addItem(s);
		}
		this.skillComboBox.setSelectedIndex(0);
		this.skillComboBox.repaint();
		this.skillComboBox.updateUI();
		this.repaint();
	}

	public void setTextColours(String colour) {
		Color c = Color.decode(colour);
		this.detailsList.setBackground(c);
		this.detailsList1.setBackground(c);
		this.jLabel1.setForeground(c);
		this.jLabel2.setForeground(c);
	}

	public void showScreen(Set<String> skills) {
		this.initComponents();
		this.setVisible(true);
		this.setSkills(skills);
		this.detailsList.setModel(this.model);
		this.detailsList1.setModel(this.senarioModel);
	}

	private void skillComboBoxActionPerformed(ActionEvent evt) {
		if (this.skillComboBox.getSelectedItem() != null) {
			this.SelectedSkill = this.skillComboBox.getSelectedItem().toString();
			this.notifyObservers("selectSkillSkillOrg");
		}
	}

}
