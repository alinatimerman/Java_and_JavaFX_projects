package Repository;

import Model.Student;

import java.io.*;

public class StudentInFileRepository extends StudentInMemoRepository{
    private String file;
    private static int idGenerator = 0;

    public StudentInFileRepository(String file, ClassRepository classRepository){
        this.file = file;
        retrieveStudent();
    }

    private void retrieveStudent() {
        try(BufferedReader buffer = new BufferedReader(new FileReader(file))){
            String line = buffer.readLine();
            try{
                idGenerator=Integer.parseInt(line);
            }catch (NumberFormatException ex){
                System.err.println("Invalid Value for idGenerator");
            }
            while((line = buffer.readLine()) != null){
                String[] attributes = line.split("; ");
                if (attributes.length != 6){
                    System.out.println(line);
                    System.out.println(attributes.length);
                    System.err.println("Invalid line ..." + line);
                    continue;
                }
                try{
                    int id = Integer.parseInt(attributes[0]);
                    int class_id = Integer.parseInt(attributes[2]);
                    int grade = Integer.parseInt(attributes[3]);
                    int age = Integer.parseInt(attributes[4]);
                    Student o = new Student(id, attributes[1],class_id,grade,age,attributes[5]);
                    super.add(o);
                }catch(NumberFormatException ex){
                    System.err.println("Error converting number");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }catch(IOException exception){
            throw new RepositoryException("Reading error " + exception);
        }

    }

    private void storeStudent() {
        try(BufferedWriter buffer = new BufferedWriter(new FileWriter(file))){
            buffer.write("" + idGenerator);
            buffer.newLine();
            for(Student o : findAll()){
                buffer.write(o.toString());
                buffer.newLine();
            }
        } catch (IOException exception) {
            throw new RepositoryException("Writing error " + exception);
        }
    }

    @Override
    public Student add(Student o){
        o.setId(getNextId());
        super.add(o);
        storeStudent();
        return o;
    }

    @Override
    public void delete(Student o){
        super.delete(o);
        storeStudent();
    }

    @Override
    public void update(Integer id, Student o){
        super.update(id, o);
        storeStudent();
    }

    public int getNextId(){
        return idGenerator++;
    }

    public int getIdGenerator(){
        return idGenerator;
    }
}
