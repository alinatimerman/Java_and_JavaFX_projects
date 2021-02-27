package Model;

public class Student implements Identifiable<Integer> {
    private Integer Id;
    private String name;
    private int class_id;
    private int grade;
    private int age;
    private String passed;

//------------------------------------CONSTRUCTORS-----------------------------------//

    public Student(Integer id, String name, int class_id, int grade,int age, String passed) {
        Id = id;
        this.name = name;
        this.class_id = class_id;
        this.grade = grade;
        this.age=age;
        this.passed=passed;
    }
    public Student(String name, int class_id, int grade,int age, String passed) {
        this.name = name;
        this.class_id = class_id;
        this.grade = grade;
        this.age=age;
        this.passed=passed;
    }
    public Student(Integer id){
        this.Id=id;
        this.name="";
        this.class_id=0;
        this.grade=0;
        this.age=0;
        this.passed="";

    }



    //-----------------------------getters and setters--------------------------//
    @Override
    public Integer getId() {
        return Id;
    }

    @Override
    public void setId(Integer id) {
        Id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade (int grade) {
        this.grade = grade;
    }

    //-------------------------toString------------------------

    @Override
    public String toString() {
        final StringBuilder pString = new StringBuilder();
        pString.append(Id).append("; ").append(name).append("; ").append(class_id).append("; ").append(grade).append("; ").append(age).append("; ").append(passed).append("; ");
        return pString.toString();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassed() {
        return passed;
    }

    public void setPassed(String passed) {
        this.passed = passed;
    }
}

