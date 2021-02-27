package Repository;

import Model.Registration;

import java.io.*;

public class RegistrationInFileRepository extends RegistrationInMemoRepository{
    private String file;
    private static int idGenerator = 0;

    public RegistrationInFileRepository(String file,  ActivityRepository activityRepository){
        this.file = file;
        retrieveRegistration();
    }

    private void retrieveRegistration() {
        try(BufferedReader buffer = new BufferedReader(new FileReader(file))){
            String line = buffer.readLine();
            try{
                idGenerator=Integer.parseInt(line);
            }catch (NumberFormatException ex){
                System.err.println("Invalid Value for idGenerator");
            }
            while((line = buffer.readLine()) != null){
                String[] attributes = line.split("; ");
                if (attributes.length != 5){
                    System.out.println(line);
                    System.out.println(attributes.length);
                    System.err.println("Invalid line ..." + line);
                    continue;
                }
                try{
                    int id = Integer.parseInt(attributes[0]);
                    int age=Integer.parseInt(attributes[2]);
                    int activity_id=Integer.parseInt(attributes[4]);
                    Registration o = new Registration(id,attributes[1],age,attributes[3],activity_id);
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

    private void storeRegistration() {
        try(BufferedWriter buffer = new BufferedWriter(new FileWriter(file))){
            buffer.write("" + idGenerator);
            buffer.newLine();
            for(Registration o : findAll()){
                buffer.write(o.toString());
                buffer.newLine();
            }
        } catch (IOException exception) {
            throw new RepositoryException("Writing error " + exception);
        }
    }

    @Override
    public Registration add(Registration o){
        o.setId(getNextId());
        super.add(o);
        storeRegistration();
        return o;
    }

    @Override
    public void delete(Registration o){
        super.delete(o);
        storeRegistration();
    }

    @Override
    public void update(Integer id, Registration o){
        super.update(id, o);
        storeRegistration();
    }

    public int getNextId(){
        return idGenerator++;
    }

    public int getIdGenerator(){
        return idGenerator;
    }
}
