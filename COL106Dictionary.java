import java.util.Hashtable;
import java.util.LinkedList;

import javax.management.openmbean.KeyAlreadyExistsException;

import Includes.DictionaryEntry;
import Includes.HashTableEntry;
import Includes.KeyAlreadyExistException;
import Includes.KeyNotFoundException;
import Includes.NullKeyException;

import java.lang.reflect.Array;

public class COL106Dictionary<K, V> {

    private LinkedList<DictionaryEntry<K, V>> dict;
    /*
     * dict is a Linked-List, where every node of linked-list is of type DictionaryEntry.
     * DictionaryEntry is a key-value pair, where the type of key and value is K and V respectively.
     */ 
    public LinkedList<HashTableEntry<K, V>>[] hashTable;
    int sizee=0;
    int hashTableSize;
    /*
     * hashTable is an array of Linked-Lists which is initialized by the COL106Dictionary constructor.
     * Each index of hashTable stores a linked-list whose nodes are of type HashTableEntry
     * HashTableEntry is a key-address pair, where the type of key is K and the corresponding address is the address of the DictionaryEntry in the linked-list corresponding to the key of HashTableEntry
     */ 
    
    @SuppressWarnings("unchecked")
    COL106Dictionary(int hashTableSize) {
        this.hashTableSize = hashTableSize;
        dict = new LinkedList<DictionaryEntry<K, V>>();
        // This statement initiailizes a linked-list where each node is of type DictionaryEntry with key and value of type K and V respectively.
        hashTable = (LinkedList<HashTableEntry<K, V>>[]) Array.newInstance(LinkedList.class, hashTableSize);
        // This statement initiailizes the hashTable with an array of size hashTableSize where at each index the element is an instance of LinkedList class and
        // this array is type-casted to an array of LinkedList where the LinkedList contains nodes of type HashTableEntry with key of type K. 
        
    }

    public void insert(K key, V value) throws KeyAlreadyExistException, NullKeyException {
        /*
         * To be filled in by the student
         * Input: A key of type K and it corresponding value of type V
         * Working: Inserts the argumented key-value pair in the Dictionary in O(1)
         */
        
        if(key==null){
            throw new NullKeyException();
        }
            int index = hash(key);
        if(hashTable[hash(key)]==null){

            hashTable[index] =new LinkedList<HashTableEntry<K,V>>();
        }
            boolean find=false;
        // LinkedList<HashTableEntry<K,V>> y = hashTable[hash(key)];
        
            for(int i=0;i<hashTable[index].size();i++){
                if(hashTable[index].get(i).key.equals(key)){
                    find=true;

                    }}
        if(find==true){
            throw new KeyAlreadyExistException();
        }
        DictionaryEntry<K,V> d1=new DictionaryEntry<K,V>(key, value);
        HashTableEntry<K,V> h=new HashTableEntry<K,V>(key, d1);
        // LinkedList<HashTableEntry<K,V>> x=hashTable[hash(key)];
        //dict.add(d1);
        hashTable[hash(key)].add(h);
        dict.add(d1);

        //  throw new KeyAlreadyExistException();
    }
        
        
    

    public V delete(K key) throws NullKeyException, KeyNotFoundException{
        /*
         * To be filled in by the student
         * Input: A key of type K
         * Return: Returns the associated value of type V with the argumented key
         * Working: Deletes the key-value pair from the Dictionary in O(1)
         */

        if(key==null){
            throw new NullKeyException();
        }
        int index = hash(key);
            boolean find=false;
            V c=null;
            HashTableEntry<K,V> h1=null;
        // LinkedList<HashTableEntry<K,V>> y = hashTable[hash(key)];
        
            for(int i=0;i<hashTable[index].size();i++){
                if(hashTable[index].get(i).key.equals(key)){
                     c=hashTable[index].get(i).dictEntry.value;
                     h1= hashTable[index].get(i);
                    
                    
                    find=true;

                }
            }
            if(find==true){
                DictionaryEntry<K,V> d1=h1.dictEntry;
                hashTable[index].remove(h1);
                dict.remove(d1);
                return c;
            }else{
            throw new KeyNotFoundException();
        }}
        
        


    public V update(K key, V value) throws NullKeyException, KeyNotFoundException{
        if(key==null){
            throw new NullKeyException();
        } V c = null ;
        int index = hash(key);
            boolean updated=false;
        
           
        // LinkedList<HashTableEntry<K,V>> y = hashTable[hash(key)];
        
            for(int i=0;i<hashTable[index].size();i++){
                if(hashTable[index].get(i).key.equals(key)){


                 c=hashTable[index].get(i).dictEntry.value;
                hashTable[index].get(i).dictEntry.value= value;
                updated=true;
                break;
               
                
            }
        }
        if(updated==false){
            throw new KeyNotFoundException();
        }
        else{
            return c;
        }
        
       
    }
        
        /*
         * To be filled in by the student
         * Input: A key of type K
         * Return: Returns the previously associated value of type V with the argumented key
         * Working: Updates the value associated with argumented key with the argumented value in O(1)
         */
        
    

    public V get(K key) throws NullKeyException, KeyNotFoundException {
        if(key==null){
            throw new NullKeyException();
        }
        boolean found = false;
        int h=0;
        LinkedList<HashTableEntry<K,V>> f=hashTable[hash(key)];
            for(int z=0;z<f.size();z++){
                if(f.get(z).equals(key)){
                     found =true;
                     h=z;
                     break;
                    
                    

                }
            }

        if(found==false){
        throw new KeyNotFoundException();}
        else{
            return f.get(h).dictEntry.value;
        }
    }
        
        

        /*
         * To be filled in by the student
         * Input: A key of type K
         * Return: Returns the associated value of type V with the argumented key in O(1)
         */
    public Boolean exist(K key) throws NullKeyException{
        if(key==null){
            throw new NullKeyException();}
            boolean found = false;
        LinkedList<HashTableEntry<K,V>> f=hashTable[hash(key)];
            for(int z=0;z<f.size();z++){
                if(f.get(z).key.equals(key)){
                     found =true;
                    break;}
        }
        if(found==true){
            return true;
        }else{
            return false;
        }}

    
    

    public int size() {
        /*
         * To be filled in by the student
         * Return: Returns the size of the Dictionary in O(1)
         */
        return dict.size();
    }

    @SuppressWarnings("unchecked")
    public K[] keys(Class<K> cls) {
        K arr[]=(K[])Array.newInstance(cls, dict.size());
        for(int i=0;i<dict.size();i++){
            arr[i]=dict.get(i).key;
        }


        /*
         * To be filled in by the student
         * Return: Returns array of keys stored in dictionary.
         */

        return arr;
    }

    @SuppressWarnings("unchecked")
    public V[] values(Class<V> cls) {
        V[] array=(V[])Array.newInstance(cls, dict.size());
        for(int i=0;i<dict.size();i++){
            array[i]=dict.get(i).value;
        }
        /*
         * To be filled in by the student
         * Return: Returns array of keys stored in dictionary.
         */

        return array;
    }

    public int hash(K key) {

        String s= (String)key;
        int p=1;
        int hash=0;
        int m = hashTableSize;
        for(int i=0;i< s.length();i++){
            // int a=(int)s.charAt(i)+1;
            hash=hash%m+(((int)s.charAt(i)+1)%m*(p%m))%m;
            p= ((p%m)*(131%m))%m;


        }
        
        hash=hash % m;
        /*
         * To be filled in by the student
         * Input: A key of type K
         * Return: Returns the hash of the argumented key using the Polynomial Rolling
         * Hash Function.
         */

        return hash;
    }
    // public static void main(String[] args)  throws KeyAlreadyExistException,KeyNotFoundException,NullKeyException{
    //     COL106Dictionary<String , Integer > obj = new COL106Dictionary<>(100);
    //     //System.out.println(obj.hash("hellow"));
    //     //System.out.println(obj.hash("apple"));
    //     //System.out.println(obj.hash("@#h7A"));
    //     obj.insert("Hello" , 2);
    //     obj.insert("Col106" , 5);
    //     obj.insert("IITD",10);
    //     obj.update("IITD",1);
    //     obj.delete("Hello");
    //     String[] s = obj.keys(String.class);
    //     Integer[] i= obj.values(Integer.class);
    //     for(int l =0 ; l<s.length;l++){
    //          System.out.print(s[l]);
    //          System.out.print(" ");
    //          System.out.println(i[l]);
    //     }
        
    }


