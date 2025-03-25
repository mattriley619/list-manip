import javax.swing.*;

public class IntVerifier extends InputVerifier
{
    int upperLimitInclusive;
    int lowerLimitInclusive;

    IntVerifier(int upperLimitInclusive, int lowerLimitInclusive)
    {
        this.upperLimitInclusive = upperLimitInclusive;
        this.lowerLimitInclusive = lowerLimitInclusive;
    }

    public boolean verify(JComponent in)
    {
        int j;
        String str;
        str = ((JTextField)in).getText().trim();

        if(str.equals(""))
            return true;

        try
        {
            j = Integer.parseInt(str);
            if(j > upperLimitInclusive || j < lowerLimitInclusive)
            {
                JOptionPane.showMessageDialog(null,"Value outside of bounds.| " + lowerLimitInclusive + " - " + upperLimitInclusive);
                return false;
            }
            else
                return true;
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null,"NumberFormatException - Value must be an Integer");
            return false;
        }
    }




}
