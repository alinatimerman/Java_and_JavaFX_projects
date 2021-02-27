package Model;

public class Registration implements Identifiable<Integer>{
    private Integer Id;
    private String person;
    private int age;
    private String phone;
    private int activity_id;

    public Registration(Integer Id, String person, int age, String phone, int activity_id) {
        this.Id = Id;
        this.person = person;
        this.age = age;
        this.phone = phone;
        this.activity_id = activity_id;
    }

    public Registration(String person, int age, String phone, int activity_id) {
        this.person = person;
        this.age = age;
        this.phone = phone;
        this.activity_id = activity_id;
    }
    public Registration(Integer Id) {
        this.person = "";
        this.age = 0;
        this.phone = "";
        this.activity_id = 0;
    }


    @Override
    public Integer getId() {
        return Id;
    }

    @Override
    public void setId(Integer id) {
        Id = id;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    @Override
    public String toString() {
        final StringBuilder oString = new StringBuilder();
        oString.append(Id).append("; ").append(person).append("; ").append(age).append("; ").append(phone).append("; ").append(activity_id).append("; ");
        return oString.toString();
    }
}
