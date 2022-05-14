package gotproject;
import java.util.LinkedList;

public class SeparateChainHash<Key,Item> {
    int M;
    
    Entry<Key,Item>[] table;
    
    public SeparateChainHash(int M){
        table = new Entry[M];
        for(int ix = 0; ix < M; ix++)
            table[ix] = new Entry<>(null);
        this.M = M;
    }
    
    private class Entry<Key,Item>{
        Key key;
        LinkedList<Item> values;

        public Entry(Key key) {
            this.key = key;
            this.values = new LinkedList();
        }

        public LinkedList<Item> getValues() {
            return values;
        }
        
        public void addValue(Item value){
            if(values.indexOf(value) == -1)
                values.add(value);
        }

        public Key getKey() {
            return key;
        }

        @Override
        public String toString() {
            return  key +"  --> " + values.toString();
        }        
    }
    
    public int hash(Key k){
        return ((k.hashCode() & 0x7ffffff) % M);
    }

    private LinkedList<Item> returnValues(int current, Key k){
       if(table[current].key == null){
          return null;
         }else{
             if(table[current].key.equals(k))
                 return table[current].getValues();
             else {
                 current++;
                 if(current == hash(k)){
                 return null;
                 }
                 if(current > M)
                     current = 0;
                 return returnValues(current,k);
             }
         }
    }
    public boolean changeItem(Key k, Item t){
        if(delete(k,t) != null){
            returnValues(k).add(t);
            return true;
        }
        return false;
    }
    
    public LinkedList<Item> returnValues(Key k){
        return returnValues(hash(k),k);
    }
    
    public void insert(Key k, Item t){
        LinkedList<Item> tempList = returnValues(k);
        if(tempList != null)
            tempList.add(t);
        else
            insert(hash(k),k,t);
    }
    
    private void insert(int current, Key k, Item t){
         if(table[current].key == null){
            table[current] = new Entry<>(k);
            table[current].addValue(t);
         }else{
             if(table[current].key.equals(k)){
                table[current].addValue(t);
             } else {
                 current++;
                 if(current == hash(k)){
                 return;
                 }
                 if(current > M)
                     current = 0;
                 insert(current,k,t);
             }
         }
    }
    
    public Item delete(Key k, Item t){
        LinkedList<Item> list = returnValues(k);
        int index = list.indexOf(t);
        if(index < 0)
            return null;
        return list.remove(index);
    }
    
    @Override
    public String toString(){
        String s = "";
        for (int ix = 0; ix < M; ix++){
            if (table[ix].key != null)
                s +=  table[ix].toString() +"\n";
        }
        return s;
    }
}
