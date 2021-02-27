package Repository;


import Model.Animal;

import java.io.*;

public class AnimalInFileRepository extends Repository.AnimalInMemoRepository {
    private String file;
    private static int idGenerator = 0;

    public AnimalInFileRepository(String file, ShelterRepository shelterRepository){
        this.file = file;
        retrieveAnimal();
    }

    private void retrieveAnimal() {
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
                    int shelter_id=Integer.parseInt(attributes[2]);
                    int age=Integer.parseInt(attributes[3]);
                    Animal o = new Animal(id,attributes[1],shelter_id,age,attributes[4]);
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

    private void storeAnimal() {
        try(BufferedWriter buffer = new BufferedWriter(new FileWriter(file))){
            buffer.write("" + idGenerator);
            buffer.newLine();
            for(Animal o : findAll()){
                buffer.write(o.toString());
                buffer.newLine();
            }
        } catch (IOException exception) {
            throw new RepositoryException("Writing error " + exception);
        }
    }

    @Override
    public Animal add(Animal o){
        o.setId(getNextId());
        super.add(o);
        storeAnimal();
        return o;
    }

    @Override
    public void delete(Animal o){
        super.delete(o);
        storeAnimal();
    }

    @Override
    public void update(Integer id, Animal o){
        super.update(id, o);
        storeAnimal();
    }

    public int getNextId(){
        return idGenerator++;
    }

    public int getIdGenerator(){
        return idGenerator;
    }
}
