package KN_14_5_Serbina.usermanagement.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import KN_14_5_Serbina.usermanagement.User;
import KN_14_5_Serbina.usermanagement.db.DatabaseException;
import KN_14_5_Serbina.usermanagement.util.Messages;

public class AddPanel extends JPanel implements ActionListener {
	 private JPanel buttonPanel;
	private JButton cancelButton;
	private JButton okButton;
	private JPanel fieldPanel;
	protected MainFrame parent;
	private JTextField firstNameField;
    private JTextField dateOfBirthField;
    private JTextField lastNameField;
	private Color bgColor;

	public AddPanel(MainFrame parent) {
	        this.parent = parent;
	        initialize();
	    }

	    private void initialize() {
	        this.setName("addPanel"); //$NON-NLS-1$
	        this.setLayout(new BorderLayout());
	        this.add(getFieldPanel(), BorderLayout.NORTH);
	        this.add(getButtonPanel(), BorderLayout.SOUTH);
	        
	    }

	    private JPanel getFieldPanel() {
	    	 if (fieldPanel == null) {
	             fieldPanel = new JPanel();
	             fieldPanel.setLayout(new GridLayout(3, 2));
	             addLabeledField(fieldPanel, Messages.getString("AddPanel.first_name"), getFirstNameField());  //$NON-NLS-1$
	             addLabeledField(fieldPanel,Messages.getString("AddPanel.last_name"), getLastNameField());  //$NON-NLS-1$
	             addLabeledField(fieldPanel, Messages.getString("AddPanel.date_of_birth"), getDateOfBirthField()); //$NON-NLS-1$
	    	 }
	         return fieldPanel;
		}

		protected JTextField getDateOfBirthField() {
			  if (dateOfBirthField == null) {
		            dateOfBirthField = new JTextField();
		            dateOfBirthField.setName("dateOfBirthField");  //$NON-NLS-1$
		        }
		        return dateOfBirthField;
		}

		protected JTextField getLastNameField() {
			if (lastNameField == null) {
	            lastNameField = new JTextField();
	            lastNameField.setName("lastNameField"); //$NON-NLS-1$
	        }
	        return lastNameField;
		}

		private void addLabeledField(JPanel panel, String labelText, JTextField textField) {
		JLabel label = new JLabel(labelText);
		label.setLabelFor(textField);
		panel.add(label);
		panel.add(textField);
			
		}

		protected JTextField getFirstNameField() {
			if (firstNameField == null) {
	            firstNameField = new JTextField();
	            firstNameField.setName("firstNameField");  //$NON-NLS-1$
	        }
	        return firstNameField;
		}

		private JPanel getButtonPanel() {
	        if (buttonPanel == null) {
	            buttonPanel = new JPanel();
	            buttonPanel.add(getOkButton(), null);
	            buttonPanel.add(getCancelButton(), null);
	        }
	        return buttonPanel;
	    }

	    private JButton getCancelButton() {
	        if (cancelButton == null) {
	            cancelButton = new JButton();
	            cancelButton.setText(Messages.getString("AddPanel.cancel")); //$NON-NLS-1$
	            cancelButton.setName("cancelButton");  //$NON-NLS-1$
	            cancelButton.setActionCommand("cancel");  //$NON-NLS-1$
	            cancelButton.addActionListener(this);
	        }
	        return cancelButton;
	    }

	    private JButton getOkButton() {
	        if (okButton == null) {
	            okButton = new JButton();
	            okButton.setText(Messages.getString("AddPanel.ok")); //$NON-NLS-1$
	            okButton.setName("okButton"); //$NON-NLS-1$
	            okButton.setActionCommand("ok");  //$NON-NLS-1$
	            okButton.addActionListener(this);
	        }
	        return okButton;
	    }
	    
	    

	
		public void actionPerformed(ActionEvent e){
			if ("ok".equalsIgnoreCase(e.getActionCommand())) {
	            User user = new User();
	            user.setFirstName(getFirstNameField().getText());
	            user.setLastName(getLastNameField().getText());
	            DateFormat format = DateFormat.getDateInstance();
	            try {
	                Date date = format.parse(getDateOfBirthField().getText());
	                ((User) user).setDateOfBirth(date);
	            } catch (ParseException e1) {
	                getDateOfBirthField().setBackground(Color.RED);
	                return;
	            }
	            try {
	                parent.getDao().create(user );
	            } catch (DatabaseException e1) {
	                JOptionPane.showMessageDialog(this, e1.getMessage(), "Error",
	                        JOptionPane.ERROR_MESSAGE);
	            }
	        }
			clearFields();
		        this.setVisible(false);
		        ((MainFrame) parent).showBrowsePanel();
		        
			
		}

		protected void clearFields() {
			 getFirstNameField().setText("");
		        getFirstNameField().setBackground(bgColor);
		        
		        getLastNameField().setText("");
		        getLastNameField().setBackground(bgColor);

		        getDateOfBirthField().setText("");
		        getDateOfBirthField().setBackground(bgColor);
		}
			
		}
	

