package Collection.Dictionary;

import Exception.ToyLanguageInterpreterException;

import java.io.BufferedReader;
import java.util.*;
import java.util.stream.Collectors;

public class MyFileTable<K,V> implements MyIDictionary<K,V>{
    private final HashMap<K,V> dictionary;

    public MyFileTable(){
        this.dictionary = new HashMap<K,V>();
    }

    @Override
    public V get(K key) throws ToyLanguageInterpreterException {
        if(!containsKey(key))
            throw new ToyLanguageInterpreterException(key+" is not defined");
        return this.dictionary.get(key);
    }

    @Override
    public K getKey(V value){
        for (K key: this.dictionary.keySet()){
            if (this.dictionary.get(key).equals(value))
                return key;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        return this.dictionary.put(key,value);
    }

    @Override
    public int size(){
        return this.dictionary.size();
    }

    @Override
    public boolean containsKey(K key){
        return this.dictionary.containsKey(key);
    }

    @Override
    public boolean containsValue(V value){
        return this.dictionary.containsValue(value);
    }


    public void remove(K key) throws ToyLanguageInterpreterException {
        if(!containsKey(key))
            throw new ToyLanguageInterpreterException(key+" is not defined");
        this.dictionary.remove(key);    }

    @Override
    public Collection<V> values(){
        return this.dictionary.values();
    }

    @Override
    public Set<K> keySet(){
        return this.dictionary.keySet();
    }

    @Override
    public void setContent(Set<Map.Entry<K,V>> set) {

        this.dictionary.clear();

        for (Map.Entry<K,V> entry : set)
            this.put(entry.getKey(), entry.getValue());
    }

    @Override
    public Set<Map.Entry<K,V>> entrySet(){
        return this.dictionary.entrySet();
    }

    @Override
    public MyIDictionary<K, V> clone_dict() {

        MyIDictionary<K, V> di = new MyFileTable<>();
        for(K key : this.keySet())
            di.put(key, this.dictionary.get(key));

        return di;
    }

    @Override
    public boolean isDefined(K key) {
        return this.dictionary.containsKey((K) key);
    }

    @Override
    public void update(K key, V value) {
        this.dictionary.put((K) key, (V) value);
    }

    @Override
    public void delete(Object key) throws ToyLanguageInterpreterException {
        if(!this.dictionary.containsKey((K) key))
            throw new ToyLanguageInterpreterException("Key doesn't exist");
        this.dictionary.remove((K) key);
    }

    @Override
    public V lookup(Object key) throws ToyLanguageInterpreterException{
        if(!this.dictionary.containsKey((K) key))
            throw new ToyLanguageInterpreterException("Key doesn't exist");
        return this.dictionary.get((K) key);
    }
    @Override
    public String toString(){
        Iterator<Map.Entry<K,V>> i = entrySet().iterator();
        if (! i.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (;;) {
            Map.Entry<K,V> e = i.next();
            K key = e.getKey();
            BufferedReader value = (BufferedReader) e.getValue();
            sb.append(key   == this ? "(this Map)" : key);
            sb.append('=');
            sb.append(value.lines().collect(Collectors.joining()));
            if (! i.hasNext())
                return sb.append('}').toString();
            sb.append(',').append(' ');
        }
    }
}
