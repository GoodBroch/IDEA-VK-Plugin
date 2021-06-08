package VKBot;

public class VKPerson {
    private long id;
    private String Name;
    private String LastName;

    public VKPerson()
    {
        id = 1;
        Name = "";
        LastName = "";
    }

    public VKPerson(long id, String Name, String LastName)
    {
        this.id = id;
        this.Name = Name;
        this.LastName = LastName;
    }

    public long GetId() {return id;}
    public String GetName() {return Name;}
    public String  GetLastName() {return LastName;}
    public String toString()
    {
        return LastName + " " + (Name.length() > 1? Name.substring(0, 1) : "") + ".";
    }
}
