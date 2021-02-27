package Service;

import Model.*;
import Model.Class;
import Repository.*;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private ClassRepository classRepository;
    private StudentRepository studentRepository;

    public Service(ClassRepository classRepository, StudentRepository studentRepository){
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
    }

//---------------------------------Phone-----------------------------------------------------------//
public List<Class> getClassList(){
    return new ArrayList<>(classRepository.getAll());
}

//-----------------------------------CRUD----------------------------------------------------------//

    public int addClass(String name, int credits, String teacher, int hours) throws ServiceException{
        try{
            Class p = new Class(name, credits,teacher,hours);
            classRepository.add(p);
            return p.getId();
        }catch (RepositoryException exception){
            throw new ServiceException("Error adding class: " + exception);
        }

    }

    public int getClassIdGenerator() {
        if (classRepository instanceof ClassInFileRepository) {
            return ((ClassInFileRepository) classRepository).getIdGenerator();
        }
        else return 0;
    }

    //---------------------------------Order-----------------------------------------------------------//
    public List<Student> getStudentList(){
        return new ArrayList<>(studentRepository.getAll());
    }

//-----------------------------------CRUD----------------------------------------------------------//

    public int addStudent(String name,int class_id, int grade, int age, String passed) throws ServiceException{
        try{
            Student o = new Student(name,class_id,grade,age,passed);
            studentRepository.add(o);
            return o.getId();
        }catch (RepositoryException exception){
            throw new ServiceException("Error adding student: " + exception);
        }

    }

    public int getStudentIdGenerator() {
        if (studentRepository instanceof StudentInFileRepository) {
            return ((StudentInFileRepository) studentRepository).getIdGenerator();
        }
        else return 0;
    }


}
