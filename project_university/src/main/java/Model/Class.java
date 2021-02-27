package Model;

public class Class implements Identifiable<Integer>{
    private Integer Id;
    private String name;
    private int credits;
    private String teacher;
    private int hours;


//----------------------------------constructors--------------------------------------------------------------------//

    public Class(Integer id, String name, int credits, String teacher, int hours) {
        Id = id;
        this.name = name;
        this.credits = credits;
        this.teacher = teacher;
        this.hours=hours;
    }
    public Class( String name, int credits, String teacher, int hours) {
        //Id = id;
        this.name = name;
        this.credits = credits;
        this.teacher = teacher;
        this.hours=hours;

    }
    public Class(int id){
        this.name="";
        this.credits=0;
        this.teacher="";
        this.hours=0;
    }

//---------------------------------getters and setters---------------------------------------------------------------------//
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

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
//-------------------------------------tostring-----------------------------------------------------------------------------------------------//
    @Override
    public String toString() {
        final StringBuilder oString = new StringBuilder();
        oString.append(Id).append("; ").append(name).append("; ").append(credits).append("; ").append(teacher).append("; ").append(hours).append("; ");
        return oString.toString();
    }
}
