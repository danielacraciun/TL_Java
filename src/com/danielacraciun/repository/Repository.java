package com.danielacraciun.repository;

import com.danielacraciun.models.prgstate.PrgState;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private List<PrgState> prgStates;

    public Repository() {
        prgStates = new ArrayList<>();
    }

    @Override
    public void add(PrgState ps) {
        prgStates.add(ps);
    }

    @Override
    public List<PrgState> getPrgList() {
        return prgStates;
    }

    @Override
    public void setPrgList(List<PrgState> prgs) {
        prgStates = prgs;
    }

    @Override
    public void writeToFile(String filename) {
        try {
            FileChannel fc = new RandomAccessFile(filename, "rw").getChannel();
            fc.position(fc.size());
            fc.write(ByteBuffer.wrap(this.prgStates.toString().getBytes()));
            fc.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file found");
        } catch (IOException e) {
            System.out.println("Cannot close file");
        }
    }

    @Override
    public void serialize() throws RepositoryException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("lastPrgState.ser"));
            out.writeObject(prgStates.get(0));
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

    @Override
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
