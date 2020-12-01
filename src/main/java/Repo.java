public class Repo {
    int id;                 //leo 3 campos (public!!!)
    String name;
    String full_name;

    @Override
    public String toString() {          //to Debug better String
        return id+ " "+name+" > " +full_name;
    }
}
