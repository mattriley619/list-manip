import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class TripDialog extends JDialog
                        implements ActionListener
{
    JButton submitButton;
    JButton cancelButton;

    JTextField dateField;
    JTextField nameField ;
    JTextField codeField;
    JTextField initialMileageField;
    JTextField returnMileageField;
    JTextField rateField;
    JTextField commentField;
    TripRecord newRecord;
    DataManager list;
    int index;
    boolean editCall;



    TripDialog(DataManager list)
    {
        editCall = false;
        this.list = list;
        buildGUI(editCall, new TripRecord());
        setModalityType(ModalityType.APPLICATION_MODAL);
        setVisible(true);
        list.add(newRecord);
    }
    TripDialog(DataManager list, TripRecord rec, int index)
    {
        editCall = true;
        this.index = index;
        this.list = list;
        buildGUI(editCall, rec);
        populateFields(rec);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setVisible(true);
    }

    void buildGUI(boolean editCall, TripRecord rec)
    {
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);


        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        dateField = new JTextField();
        dateField.setInputVerifier(new DateVerifier());
        nameField = new JTextField();
        codeField = new JTextField();
        codeField.setInputVerifier(new StringVerifier());
        initialMileageField = new JTextField();
        initialMileageField.setInputVerifier(new IntVerifier(500000,0));
        returnMileageField = new JTextField();
        returnMileageField.setInputVerifier(new IntVerifier(505000,0));
        rateField = new JTextField();
        rateField.setInputVerifier(new DoubleVerifier(2000,0));
        commentField = new JTextField();


        JLabel dateLabel = new JLabel("Date (mm/dd/yyyy)");
        JLabel nameLabel = new JLabel("Name");
        JLabel codeLabel = new JLabel("Service Code");
        JLabel initialMileageLabel = new JLabel("Initial Mileage");
        JLabel returnMileageLabel = new JLabel("Mileage on Return");
        JLabel rateLabel = new JLabel("Billing Rate");
        JLabel commentLabel = new JLabel("Comments");


        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(dateLabel).addComponent(nameLabel)
                .addComponent(codeLabel).addComponent(initialMileageLabel)
                .addComponent(returnMileageLabel).addComponent(rateLabel)
                .addComponent(commentLabel));

        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(dateField).addComponent(nameField)
                .addComponent(codeField).addComponent(initialMileageField)
                .addComponent(rateField).addComponent(returnMileageField)
                .addComponent(commentField));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(dateLabel).addComponent(dateField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(nameLabel).addComponent(nameField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(codeLabel).addComponent(codeField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(initialMileageLabel).addComponent(initialMileageField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(returnMileageLabel).addComponent(returnMileageField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(rateLabel).addComponent(rateField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(commentLabel).addComponent(commentField));
        layout.setVerticalGroup(vGroup);

        add(panel,GroupLayout.DEFAULT_SIZE);

        panel = new JPanel();

        submitButton.addActionListener(this);
        panel.add(submitButton);

        cancelButton.addActionListener(this);
        cancelButton.setVerifyInputWhenFocusTarget(false);
        panel.add(cancelButton);

        add(panel,BorderLayout.SOUTH);

        setTitle("Add Element");

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setupMainFrame();


    }

    void populateFields(TripRecord rec)
    {
        dateField.setText(rec.convertToString(rec.date));
        nameField.setText(rec.name);
        codeField.setText(rec.code);
        initialMileageField.setText(String.format("%d", rec.initialMileage));
        returnMileageField.setText(String.format("%d", rec.returnMileage));
        rateField.setText(String.format("%f", rec.rate));
        commentField.setText(rec.comment);
    }
    void setupMainFrame()
    {
        Toolkit tk;
        Dimension d;

        tk = Toolkit.getDefaultToolkit();
        d = tk.getScreenSize();
        setSize(d.width / 3, d.height / 3);
        setLocation(d.width / 3, d.height / 3);

        //setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

       // setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource() == submitButton)
        {
            if (submit(editCall))
            {
                cancelButton.doClick();
            }
        }
        else if(e.getSource() == cancelButton)
        {
            dispose();
            setVisible(false);
        }

    }


    boolean submit(boolean editCall)
    {



        if(dateField.getText().trim().equals("") || codeField.getText().trim().equals("") || initialMileageField.getText().trim().equals("")
                || returnMileageField.getText().trim().equals("") || rateField.getText().trim().equals(""))
        {
            JOptionPane.showMessageDialog(null,"1 or more Required Fields are empty.");
            dateField.requestFocus();
            return false;
        }
        else if (Integer.parseInt(initialMileageField.getText()) > Integer.parseInt(returnMileageField.getText()))
        {
            JOptionPane.showMessageDialog(null,"Initial Mileage cannot be greater than Return Mileage");
            initialMileageField.requestFocus();
            return false;
        }
        else
        {
            newRecord = new TripRecord(dateField.getText(), nameField.getText(), codeField.getText(), Integer.parseInt(initialMileageField.getText()),
                    Integer.parseInt(returnMileageField.getText()), Double.parseDouble(rateField.getText()), commentField.getText());
            if(editCall)
                list.replace(newRecord,index);
            return true;
        }

    }






}
