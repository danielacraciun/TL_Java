package com.danielacraciun.models.statement;

import com.danielacraciun.models.dictionary.Dictionary;
import com.danielacraciun.models.expression.DivisionByZeroException;
import com.danielacraciun.models.expression.UninitializedVarException;
import com.danielacraciun.models.filetable.Buffer;
import com.danielacraciun.models.prgstate.PrgState;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CloseFileStmt implements IStmt {
    String filename;

    public CloseFileStmt(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "close_file(" + filename + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, UninitializedVarException, FileException {
        Dictionary<String, Buffer> crtft = state.getFileTable();
        if (crtft.containsKey(filename)) {
            if (crtft.get(filename) == null) {
                throw new FileException();
            } else {
                Buffer crtbf = crtft.get(filename);
                if (crtbf.getCrtPrgStateId() == state.getState_id()) {
                    if (crtbf.getIntBuffer()[0] != null) {
                        writeToDisk(crtbf.getIntBuffer()[0]);
                        crtbf.getIntBuffer()[0] = null;
                    }
                    if (crtbf.getIntBuffer()[1] != null) {
                        writeToDisk(crtbf.getIntBuffer()[1]);
                        crtbf.getIntBuffer()[1] = null;
                    } else {
                        crtft.put(filename, null);
                        return state;
                    }
                } else {
                    state.getExeStack().push(new CloseFileStmt(filename));
                    return state;
                }
            }
        } else {
            throw new FileException();
        }
        return state;
    }

    private void writeToDisk(Integer nr) {
        try {
            FileChannel fc = new RandomAccessFile(filename, "rw").getChannel();
            fc.position(fc.size());
            fc.write(ByteBuffer.wrap(nr.toString().getBytes()));
            fc.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file found");
        } catch (IOException e) {
            System.out.println("Cannot close file");
        }
    }
}
