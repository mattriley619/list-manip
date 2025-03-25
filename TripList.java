import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;

interface DataManager
{
    void add(TripRecord rec);

    void replace(TripRecord rec, int index);

}

class TripList extends DefaultListModel<TripRecord>
                implements DataManager
{
    int numTrips;


    void store(DataOutputStream dos) throws java.io.IOException
    {
        dos.writeInt(size());
        for (int n = 0; n < size(); n++)
            elementAt(n).store(dos);
    }

    TripList(DataInputStream dis) throws java.io.IOException
    {
        numTrips = dis.readInt();
        for (int n = 0; n < numTrips; n++)
            addElement(new TripRecord(dis));
    }
    TripList()
    {

    }

    public void add(TripRecord rec)
    {
        addElement(rec);
    }
    public void replace(TripRecord rec, int index)
    {
        removeElementAt(index);
        insertElementAt(rec, index);
    }

}
