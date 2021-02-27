package Repository;

import Model.Class;

import java.io.*;


public class ClassInFileRepository extends ClassInMemoRepository{
    private String file;
    private static int idGenerator = 0;

    public ClassInFileRepository(String file){
        this.file = file;
        retrieveClass();
    }

    private void retrieveClass() {
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
                if (attributes.length != 5){
                    System.out.println(line);
                    System.out.println(attributes.length);
                    System.err.println("Invalid line ..." + line);
                    continue;
                }
                try{
                    int id = Integer.parseInt(attributes[0]);
                    int credits = Integer.parseInt(attributes[2]);
                    int hours = Integer.parseInt(attributes[4]);
                    Class p = new Class(id, attributes[1], credits,attributes[3], hours);
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

    private void storeClass() {
        try(BufferedWriter buffer = new BufferedWriter(new FileWriter(file))){
            buffer.write("" + idGenerator);
            buffer.newLine();
            for(Class p : findAll()){
                buffer.write(p.toString());
                buffer.newLine();
            }
        } catch (IOException exception) {
            throw new RepositoryException("Writing error " + exception);
        }
    }

    @Override
    public Class add(Class p){
        p.setId(getNextId());
        super.add(p);
        storeClass();
        return p;
    }

    @Override
    public void delete(Class p){
        super.delete(p);
        storeClass();
    }

    @Override
    public void update(Integer id, Class p){
        super.update(id, p);
        storeClass();
    }

    public int getNextId(){
        return idGenerator++;
    }

    public int getIdGenerator(){
        return idGenerator;
    }
}


