import javax.swing.*;

public class DoubleVerifier extends InputVerifier
{
    double upperLimitInclusive;
    double lowerLimitInclusive;

    DoubleVerifier(double upperLimitInclusive, double lowerLimitInclusive)
    {
        this.upperLimitInclusive = upperLimitInclusive;
        this.lowerLimitInclusive = lowerLimitInclusive;
    }



    public boolean verify(JComponent in)
    {
        if (((JTextField)in).getText().trim().equals(""))
            return true;

        try
        {
            double rate = Double.parseDouble(((JTextField)in).getText());
            if (rate > upperLimitInclusive || rate < lowerLimitInclusive)
            {
                JOptionPane.showMessageDialog(null, "Billing rate is out bounds.| " + lowerLimitInclusive + " - " + upperLimitInclusive);
                return false;
            }
            else
                return true;
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null,"NumberFormatException: Value must be a number.");
            return false;
        }
    }
}
