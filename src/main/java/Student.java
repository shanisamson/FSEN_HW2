public class Student extends User{
    private String id;
    public Student(String userName, String password,String id) {
        super(userName, password);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
