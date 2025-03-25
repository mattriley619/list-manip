import javax.swing.*;

public class StringVerifier extends InputVerifier
{
    StringVerifier()
    {

    }
    public boolean verify(JComponent in)
    {
            if (((JTextField)in).getText().trim().equals(""))
                return true;
            else if (((JTextField)in).getText().trim().equals("A0428")
                    || ((JTextField)in).getText().trim().equals("A0429")
                    || ((JTextField)in).getText().trim().equals("A0427")
                    || ((JTextField)in).getText().trim().equals("A0434"))
            {
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Service Code is invalid.| Possible Options: A0428 , A0429 , A0427 , A0434");
                return false;
            }
        }
    }

