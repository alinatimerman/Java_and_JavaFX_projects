package Repository;

import Model.Shelter;

import java.io.*;


public class ShelterInFileRepository extends Repository.ShelterInMemoRepository {
    private String file;
    private static int idGenerator = 0;

    public ShelterInFileRepository(String file){
        this.file = file;
        retrieveShelter();
    }

    private void retrieveShelter() {
        try(BufferedReader buffer = new BufferedReader(new FileReader(file))){
            String line = buffer.readLine();
            try{
                idGenerator=Integer.parseInt(line);
            }catch (NumberFormatException ex){
                System.err.println("Invalid Value for idGenerator");
            }
            while((line = buffer.readLine()) != null){
                String[] attributes =
                        line.split("; ");
                if (attributes.length != 6){
                    System.out.println(line);
                    System.out.println(attributes.length);
                    System.err.println("Invalid line ..." + line);
                    continue;
                }
                try{
                    int id = Integer.parseInt(attributes[0]);
                    int volunteers = Integer.parseInt(attributes[3]);
                    int animals = Integer.parseInt(attributes[4]);
                    int employees= Integer.parseInt(attributes[5]);
                    Shelter p = new Shelter(id,attributes[1],attributes[2],volunteers,animals,employees );
                    super.add(p);
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

    private void storeShelter() {
        try(BufferedWriter buffer = new BufferedWriter(new FileWriter(file))){
            buffer.write("" + idGenerator);
            buffer.newLine();
            for(Shelter p : findAll()){
                buffer.write(p.toString());
                buffer.newLine();
            }
        } catch (IOException exception) {
            throw new RepositoryException("Writing error " + exception);
        }
    }

    @Override
    public Shelter add(Shelter p){
        p.setId(getNextId());
        super.add(p);
        storeShelter();
        return p;
    }

    @Override
    public void delete(Shelter p){
        super.delete(p);
        storeShelter();
    }

    @Override
    public void update(Integer id, Shelter p){
        super.update(id, p);
        storeShelter();
    }

    public int getNextId(){
        return idGenerator++;
    }

    public int getIdGenerator(){
        return idGenerator;
    }
}


