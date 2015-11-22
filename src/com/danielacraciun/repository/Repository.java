package com.danielacraciun.repository;

import com.danielacraciun.models.prgstate.PrgState;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Stack;

public class Repository implements IRepository {
    private Stack<PrgState> prgStates;

    public Repository() {
        prgStates = new Stack<>();
    }

    public PrgState getCrtPrg() throws RepositoryException {
        try {
            if (prgStates.size() > 0)
                return this.prgStates.peek();
        } catch (IndexOutOfBoundsException e) {
            throw new RepositoryException();
        }
        throw new RepositoryException();
    }

    public void add(PrgState ps) {
        prgStates.push(ps);
    }

    @Override
    public void writeToFile(String filename) {
        try {
            FileChannel fc = new RandomAccessFile(filename, "rw").getChannel();
            fc.position(fc.size());
            fc.write(ByteBuffer.wrap(this.getCrtPrg().toString().getBytes()));
            fc.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file found");
        } catch (IOException e) {
            System.out.println("Cannot close file");
        } catch (RepositoryException e) {
            System.out.println("Repository error");
        }
    }

    public void serialize() throws RepositoryException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("lastPrgState.ser"));
            out.writeObject(this.getCrtPrg());
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            if (out != null)
                try {
                    out.close();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
        }
    }

    public PrgState deserialize() {
        ObjectInputStream in = null;
        PrgState ps = null;
        try {
            in = new ObjectInputStream(new FileInputStream("lastPrgState.ser"));
            ps = (PrgState)in.readObject();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Error:" + e.getMessage());
        } finally {
            if (in!=null){
                try {
                    in.close();
                } catch (IOException e) {System.err.println(e.getMessage());}
            }
        }
        return ps;
    }

}
