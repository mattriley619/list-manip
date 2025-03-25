import java.awt.*;
import java.lang.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.io.*;



public class ListManip
{
    public static void main(String[] args)
    {
        new ListElements();
    }
}

class ListElements extends JFrame
                   implements ActionListener, ListSelectionListener
    {
        JFileChooser chooser;
        File file;
        JButton loadButton;
        JButton saveButton;
        JButton saveAsButton;
        JButton addButton;
        JButton deleteButton;
        JButton sortButton;
        JButton exitButton;
        JButton randomButton;
        JButton editButton;
        JPanel panel;
        JList<TripRecord> list;
        JMenuBar menuBar;
        JMenu listMenu;
        JMenu fileMenu;
        JMenuItem addItem;
        JMenuItem loadItem;
        JMenuItem deleteItem;
        JMenuItem saveItem;
        JMenuItem clearItem;
        TripList tripList;
        JScrollBar scrollBar;
        JScrollPane scrollPane;
        int counter = 0;






        ListElements()
        {
            chooser = new JFileChooser(".");
            file = chooser.getSelectedFile();


            tripList = new TripList();
            list = new JList(tripList);

            list.addListSelectionListener(this);

            scrollPane = new JScrollPane(list);
            scrollBar = new JScrollBar();

            scrollPane.setVerticalScrollBar(scrollBar);

            this.add(scrollPane, BorderLayout.CENTER);


            menuBar = new JMenuBar();
            addItem = new JMenuItem("Add Element");
            loadItem = new JMenuItem("Load File");
            deleteItem = new JMenuItem("Delete Element");
            saveItem = new JMenuItem("Save to File");
            clearItem = new JMenuItem("Clear List");
            listMenu = new JMenu("List");
            fileMenu = new JMenu("File");

            addItem.addActionListener(this);
            addItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
            loadItem.addActionListener(this);
            loadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
            deleteItem.addActionListener(this);
            deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
            saveItem.addActionListener(this);
            saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
            clearItem.addActionListener(this);
            clearItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));



            listMenu.add(addItem);
            listMenu.add(deleteItem);
            listMenu.add(clearItem);
            fileMenu.add(loadItem);
            fileMenu.add(saveItem);



            menuBar.add(listMenu);
            menuBar.add(fileMenu);




            panel = new JPanel();

            randomButton = new JButton("Add Random");
            randomButton.addActionListener(this);
            randomButton.setToolTipText("Adds a random record");

            loadButton = new JButton("Load...");
            loadButton.addActionListener(this);
            loadButton.setToolTipText("Load a file");
            panel.add(loadButton);

            saveButton = new JButton("Save");
            saveButton.addActionListener(this);
            saveButton.setToolTipText("Save to a file");
            panel.add(saveButton);

            saveAsButton = new JButton(("Save As..."));
            saveAsButton.addActionListener(this);
            panel.add(saveAsButton);

            addButton = new JButton("Add");
            addButton.addActionListener(this);
            addButton.setToolTipText("Add a new element");
            panel.add(addButton);

            randomButton = new JButton("Add Random");
            randomButton.addActionListener(this);
            randomButton.setToolTipText("Adds a random record");
            panel.add(randomButton);

            editButton = new JButton("Edit");
            editButton.addActionListener(this);
            editButton.setToolTipText("Edit selected element");
            editButton.setEnabled(false);
            panel.add(editButton);


            deleteButton = new JButton("Delete");
            deleteButton.addActionListener(this);
            deleteButton.setToolTipText("Delete selected elements");
            deleteButton.setEnabled(false);
            panel.add(deleteButton);

            sortButton = new JButton("Sort");
            sortButton.addActionListener(this);
            sortButton.setToolTipText("Not currently implemented");
            panel.add(sortButton);

            exitButton = new JButton("Exit");
            exitButton.addActionListener(this);
            exitButton.setToolTipText("Exits the program");
            panel.add(exitButton);

            add(panel, BorderLayout.SOUTH);

            panel = new JPanel();
            panel.add(menuBar);
            add(panel, BorderLayout.EAST);




            setupMainFrame();
        }


        void setupMainFrame()
        {
            Toolkit tk;
            Dimension d;

            setTitle("List");

            tk = Toolkit.getDefaultToolkit();
            d = tk.getScreenSize();
            setSize(d.width / 2, d.height / 2);
            setLocation(d.width / 4, d.height / 4);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setVisible(true);
        }


        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == loadButton)
            {

                chooser.showOpenDialog(this);
                file = chooser.getSelectedFile();
                System.out.println("User selected " + file.getName());

                if (file.exists())
                {
                    try
                    {
                        counter++;
                        DataInputStream dis;
                        dis = new DataInputStream(new FileInputStream(file.getName()));
                        tripList = new TripList(dis);
                        list.setModel(tripList);
                        dis.close();



                    }
                    catch(IOException io)
                    {
                        JOptionPane.showMessageDialog(null, "I/O Error");
                    }
                }
                else
                {
                    System.out.println("File does not exist");
                }

            }
            else if (e.getSource() == saveButton)
            {
                if(counter > 0)
                {
                    try
                    {
                        DataOutputStream dos;
                        dos = new DataOutputStream(new FileOutputStream(file.getName()));
                        tripList.store(dos);
                        dos.close();
                    }
                    catch (IOException io)
                    {
                        JOptionPane.showMessageDialog(null, "I/O Error");
                    }

                }
                else
                {
                    saveAsButton.doClick();
                }

            }
            else if (e.getSource() == saveAsButton)
            {
                counter++;
                chooser.showOpenDialog(this);
                file = chooser.getSelectedFile();
                System.out.println("User selected " + file.getName());
                saveButton.doClick();
            }
            else if (e.getSource() == addButton)
            {
                new TripDialog(tripList);
            }
            else if (e.getSource() == deleteButton)
            {
                int[] indices = list.getSelectedIndices();
                for(int n = indices.length - 1; n >= 0; --n)
                {
                    tripList.removeElementAt(indices[n]);
                }
                deleteButton.setEnabled(false);
            }
            else if (e.getSource() == sortButton)
            {
                System.out.println("Sorting...");
            }
            else if (e.getSource() == exitButton)
            {
                System.exit(0);
            }
            else if (e.getSource() == randomButton)
            {
                tripList.addElement(TripRecord.getRandom());
            }
            else if (e.getSource() == editButton)
            {
                int index = list.getSelectedIndex();
                new TripDialog(tripList, tripList.elementAt(index), index);
            }
            else if (e.getSource() == addItem)
            {
                addButton.doClick();
            }
            else if (e.getSource() == loadItem)
            {
                loadButton.doClick();
            }
            else if (e.getSource() == deleteItem)
            {
                deleteButton.doClick();
            }
            else if (e.getSource() == saveItem)
            {
                saveButton.doClick();
            }
            else if (e.getSource() == clearItem)
            {
                tripList.removeAllElements();
            }
        }

        public void valueChanged(ListSelectionEvent e)
        {
            deleteButton.setEnabled(list.getSelectedIndices().length > 0);
            editButton.setEnabled(list.getSelectedIndices().length == 1);
        }
    }



