import javax.swing.*;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateVerifier extends InputVerifier
{
    Date date;
    String dateStr;

    DateVerifier()
    {

    }

    public boolean verify(JComponent in)
    {
        JTextField tf;
        tf = (JTextField)in;
        dateStr = tf.getText();

        if (dateStr.equals(""))
            return true;

        try
        {
            ParsePosition pos = new ParsePosition(0);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            date = sdf.parse(dateStr,pos);
            sdf.setLenient(false);
            if (pos.getIndex() != dateStr.length())
            {
                JOptionPane.showMessageDialog(null, "Invalid date");
                return false;
            }

            return true;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error in date parsing");
            return false;
        }


    }
}
