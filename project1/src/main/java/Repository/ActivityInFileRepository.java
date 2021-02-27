package Repository;

import Model.Activity;

import java.io.*;


public class ActivityInFileRepository extends ActivityInMemoRepository{
    private String file;
    private static int idGenerator = 0;

    public ActivityInFileRepository(String file){
        this.file = file;
        retrieveActivity();
    }

    private void retrieveActivity() {
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
                    int min_age = Integer.parseInt(attributes[2]);
                    int max_age = Integer.parseInt(attributes[3]);
                    Activity p = new Activity(id,attributes[1],min_age,max_age,attributes[4] );
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

    private void storeActivity() {
        try(BufferedWriter buffer = new BufferedWriter(new FileWriter(file))){
            buffer.write("" + idGenerator);
            buffer.newLine();
            for(Activity p : findAll()){
                buffer.write(p.toString());
                buffer.newLine();
            }
        } catch (IOException exception) {
            throw new RepositoryException("Writing error " + exception);
        }
    }

    @Override
    public Activity add(Activity p){
        p.setId(getNextId());
        super.add(p);
        storeActivity();
        return p;
    }

    @Override
    public void delete(Activity p){
        super.delete(p);
        storeActivity();
    }

    @Override
    public void update(Integer id, Activity p){
        super.update(id, p);
        storeActivity();
    }

    public int getNextId(){
        return idGenerator++;
    }

    public int getIdGenerator(){
        return idGenerator;
    }
}


