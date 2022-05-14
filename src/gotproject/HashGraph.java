package gotproject;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class HashGraph {
    SeparateChainHash<SeriesCharacter,Edge> hashTable;
    
    public class Edge{

        public Edge(SeriesCharacter character2, int weight) {
            this.character2 = character2;
            this.weight = weight;
        }
        
        private SeriesCharacter character2;
        private int weight;

        public SeriesCharacter getCharacter2() {
            return character2;
        }

        public void setCharacter2(SeriesCharacter character2) {
            this.character2 = character2;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "{" + character2.toString() + " " + weight + '}';
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Edge other = (Edge) obj;
            if (!Objects.equals(this.character2, other.character2)) {
                return false;
            }
            return true;
        }
    }

    public HashGraph(String fileName) {
        try {
            this.hashTable = new SeparateChainHash<SeriesCharacter,Edge>(165);
            readFile(fileName);
        } catch (FileNotFoundException ex) {
            System.out.println("File couldn't found");
        }
    }
    
    public void readFile(String fileName) throws FileNotFoundException{
          try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String tempLine;
        while((tempLine = br.readLine()) != null){ // end of the file
            int[] column = new int[2];
            int j = 0;
            for (int i = 0; i < tempLine.length(); i++) {
                if(tempLine.charAt(i) == ',')
                    column[j++] = i;
            }
            String character1 = tempLine.substring(0,column[0]);
            String character2 = tempLine.substring(column[0]+1,column[1]);
            int weight = Integer.parseInt(tempLine.substring(column[1]+1));
           
            this.insertEdge(new SeriesCharacter(character1),new SeriesCharacter(character2),weight);
        }
        } catch (IOException ex) {
    }
}
   
    public void insertEdge(SeriesCharacter character1, SeriesCharacter character2, int weight){
        hashTable.insert(character1,new Edge(character2,weight));
        hashTable.insert(character2,new Edge(character1,weight));
        
    }

    @Override
    public String toString() {
        return hashTable.toString();
    }
    
    public LinkedList<Edge> getEdges(SeriesCharacter k){
        return hashTable.returnValues(k);
    }
    
    public void printClosestCharacters(SeriesCharacter character1, int threshold){
        LinkedList<Edge> list = getEdges(character1);
        if(list == null){
            System.out.println("There is no such a character :(");
            return;
        }else
            System.out.println("Closest characters to " + character1.toString() + ": ");
        for (Edge e: list) {
            if(e.getWeight() < threshold)
                System.out.print(e.toString() + " ");
        }
        System.out.println(" ");
    }
  
    public void printFartherCharacters(SeriesCharacter character1, int threshold){
        LinkedList<Edge> list = getEdges(character1);
        if(list == null){
            System.out.println("There is no such a character :(");
            return;
        }else
        System.out.println("Farther characters to " + character1.toString() + ": ");
        for (Edge e: list) {
            if(e.getWeight() > threshold)
                System.out.print(e.toString() + " ");
        }
        System.out.println(" ");
    }
    
    public boolean isConnected(SeriesCharacter a, SeriesCharacter b){
        LinkedList<Edge> list = getEdges(a);
        if(list == null)
            return false;
        return list.contains(new Edge(b,-1));
    }

    public void delete(SeriesCharacter a, SeriesCharacter b){
        if(hashTable.delete(a,new Edge(b,-1)) != null){ // if there is a connection, from a to b 
            System.out.println("Succesfully deleted " + b.toString() + " "+
                    hashTable.delete(b, new Edge(a,-1)).toString()); // if there is a connection, from b to a
        }else
            System.out.println("There is no connection between " + a + " and " + b + ".");
    }
    
    public void change(SeriesCharacter a, SeriesCharacter b, int newWeight){
        if(hashTable.changeItem(a,new Edge(b,newWeight))){
            hashTable.changeItem(b,new Edge(a,newWeight));
            System.out.println("Succesfully changed connection !");
        }else
            System.out.println("There is no such an edge.");
    }
    
    public LinkedList<Edge> printAllNeighbors(SeriesCharacter a){
        //LinkedList<Edge> list = getEdges(a);
        return getEdges(a);
    }
    
    public void printHowManyNeighbors(SeriesCharacter a){
        System.out.println(getEdges(a).size());
    }
}


