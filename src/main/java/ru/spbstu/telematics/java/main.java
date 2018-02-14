package ru.spbstu.telematics.java;

import java.util.ArrayList;
import java.util.NoSuchElementException;



class HashSet<T> implements Iterable<T> {

    class Node<T> {
        T data;
        int Numb;
        Node<T> next;



        Node(T data) {
            this.data = data;
            Numb = data.hashCode();
            next = null;
        }
    }

    void expand(){
        int newCapacity=this.Allowed*2;
        HashSet<T> temp_set = new HashSet<>(newCapacity);

        Node<T> temp_node;
        for(int i=0;i<this.Allowed;i++) {
            temp_node = buckets.get(i);
            if (temp_node != null) {
                while (temp_node.next != null) {
                    temp_set.add(temp_node.data);
                    temp_node = temp_node.next;
                }
                temp_set.add(temp_node.data);
            }
        }

        this.Allowed = temp_set.Allowed;
        this.buckets = temp_set.buckets;
        this.Size = temp_set.Size;

    }

    @Override
    public Iterator iterator(){
        return new Iterator();
    }



    public class Iterator implements java.util.Iterator<T>{

        public T next() throws NoSuchElementException {
            if(current==null){
                throw new NoSuchElementException();
            }
            last=current;
            Node<T> temp_node=current;
            temp_node = buckets.get(atm);
            if(temp_node.next!=null){
                current=temp_node.next;
                return temp_node.data;
            }

            for(int i=atm+1;i<Allowed;i++){
                if(buckets.get(i)!=null){
                    current=buckets.get(i);
                    atm=i;
                    return temp_node.data;
                }
            }
            throw new NoSuchElementException();
        }

        @Override
        public boolean hasNext() {
            if(current==null){
                throw new NoSuchElementException();
            }
            Node<T> temp_node;
            temp_node = buckets.get(atm);
            if(temp_node.next!=null){
                return true;
            }

            for(int i=atm;i<Allowed;i++){
                if(buckets.get(i)!=null){
                    return true;
                }
            }
            return false;
        }

        Node<T> next, current, last;

        int atm;
        Iterator(){

            Node<T> temp;
            for(int i=0;i<Allowed;i++) {
                temp = buckets.get(i);
                if (temp != null) {
                    current = temp;
                    atm = i;
                    return;
                }
            }
            current=null;
            next=null;

        }






        public void remove(){
            HashSet.this.remove(last.data);
        }

    }






    ArrayList<Node<T>> buckets;

    int Allowed=15;
    int Size;
    HashSet(){
        buckets = new ArrayList<>(Allowed);
        for(int i=0;i<Allowed;i++){
            buckets.add(null);
        }
        Size=0;
    }

    public boolean add(T data){
        int Numb = data.hashCode()%Allowed;
        if(buckets.get(Numb)==null){
            buckets.set(Numb,new Node(data));
            Size++;
            return true;
        }

        Node<T> temp = buckets.get(Numb);
        if(temp.data == data){
            return false;
        }
        while(temp.next != null) {
            if(temp.data == data){
                return false;
            }
            temp = temp.next;
        }
        temp.next = new Node(data);
        Size++;
        if(Size>(7/10)*Allowed){
            this.expand();
        }
        return true;
    }

    HashSet(int Allowed){
        buckets = new ArrayList<>(Allowed);
        for(int i=0;i<Allowed;i++){
            buckets.add(null);
        }
        Size=0;
        this.Allowed = Allowed;
    }

    boolean isEmpty(){
        if(Size==0){
            return true;
        }
        return false;
    }

    int size(){
        return Size;
    }





    boolean contains(T data){
        int Numb = data.hashCode()%Allowed;
        Node<T> temp = buckets.get(Numb);
        if(temp.data == data){
            return true;
        }
        Node<T> prev = temp;
        while(temp.next != null) {
            if(temp.data == data){
                return true;
            }
            prev = temp;
            temp = temp.next;
        }
        if(temp.data == data){
            return true;
        }
        return false;
    }



    public boolean remove(T data){
        int Numb = data.hashCode()%Allowed;
        Node<T> temp = buckets.get(Numb);
        if(temp.data == data){
            buckets.set(Numb,temp.next);
            Size--;

            return true;
        }
        Node<T> prev = temp;
        while(temp.next != null) {
            if(temp.data == data){
                prev.next = temp.next;
                Size--;

                return true;
            }
            prev = temp;
            temp = temp.next;
        }
        if(temp.data == data){
            prev.next = null;
            Size--;
            return true;
        }
        return false;

    }

    // расширяем, когда таблица занята на 70%



}

