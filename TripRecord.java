import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

class TripRecord
{
    String name;
    Date date;
    String code;
    int initialMileage;
    int returnMileage;
    double rate;
    String comment = "";
    static Random random = new Random();

    void store(DataOutputStream dos) throws java.io.IOException
    {
        dos.writeUTF(name);
        dos.writeUTF(code);
        dos.writeInt(initialMileage);
        dos.writeInt(returnMileage);
        dos.writeDouble(rate);
        dos.writeUTF(comment);
        dos.writeUTF(convertToString(date));

    }

    TripRecord(DataInputStream dis) throws java.io.IOException
    {
        name = dis.readUTF();
        code = dis.readUTF();
        initialMileage = dis.readInt();
        returnMileage = dis.readInt();
        rate = dis.readDouble();
        comment = dis.readUTF();
        try
        {
            date = convertToDate(dis.readUTF());
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error in date conversion");
        }
    }
    TripRecord(String dateStr, String name, String code, int initialMileage, int returnMileage, double rate, String comment)
    {
        try
        {
            date = convertToDate(dateStr);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error in date conversion");
        }
        this.name = name;
        this.code = code;
        this.initialMileage = initialMileage;
        this.returnMileage = returnMileage;
        this.rate = rate;
        this.comment = comment;
    }
    TripRecord()
    {

    }

    Date convertToDate (String dateStr)
    {
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        date = sdf.parse(dateStr,pos);
        sdf.setLenient(false);
        if (pos.getIndex() != dateStr.length())
        {
            JOptionPane.showMessageDialog(null, "Error in date/string parsing");
        }
        return date;
    }
    String convertToString(Date date)
    {
        return String.format("%tm/%te/%ty", date, date, date);
    }

    static TripRecord getRandom()
    {
        String[] codeList = {"A0428", "A0429", "A0427", "A0434"};
        String[] firstNameList = {"John", "George", "Amy", "Matt"};
        String[] lastNameList = {"Smith", "Johnson", "Williams", "Riley"};
        TripRecord rec;
        rec = new TripRecord();
        rec.code = codeList[random.nextInt(codeList.length)];
        rec.name = firstNameList[random.nextInt(firstNameList.length)] + " " + lastNameList[random.nextInt(lastNameList.length)];
        rec.initialMileage = 10000 + random.nextInt(100000);
        rec.returnMileage = rec.initialMileage + random.nextInt(200);
        rec.rate = 100 + random.nextInt(1000) + random.nextDouble();
        rec.date = new Date(random.nextLong());

        return rec;
    }

    void writeToConsole(TripRecord rec)
    {
        System.out.println("Name: " + rec.name);
        System.out.println("Date: " + String.format("%tm/%te/%ty", rec.date,rec.date,rec.date));
        System.out.println("Service Code: " + rec.code);
        System.out.println("Initial Mileage: " + rec.initialMileage);
        System.out.println("Return Mileage: " + rec.returnMileage);
        System.out.println("Billing Rate: " + rec.rate);
        System.out.println("Comment: " + rec.comment);
    }
    @Override
     public String toString()
    {
        return String.format("%tm/%te/%ty  %-30S %-5S  %-7d  %-7d  $%.2f  %-75S", date,date,date,name,code,initialMileage,returnMileage,rate,comment);
    }
}
