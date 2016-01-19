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

public class WriteFileStmt implements IStmt {
    private String filename;
    private String var;

    public WriteFileStmt(String filename, String var) {
        this.filename = filename;
        this.var = var;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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
                    if (crtbf.getIntBuffer()[0] == null) {
                        if (!state.getSymTable().containsKey(var)) {
                            throw new UninitializedVarException();
                        }
                        crtbf.getIntBuffer()[0] = state.getSymTable().get(var);
                    } else if (crtbf.getIntBuffer()[1] == null) {
                        if (!state.getSymTable().containsKey(var)) {
                            throw new UninitializedVarException();
                        }
                        crtbf.getIntBuffer()[1] = state.getSymTable().get(var);
                    } else {
                        writeToDisk(crtbf);
                        Integer[] newbuf = new Integer[2];
                        if (!state.getSymTable().containsKey(var)) {
                            throw new UninitializedVarException();
                        }
                        newbuf[0] = state.getSymTable().get(var);
                        newbuf[1] = null;
                        crtbf.setIntBuffer(newbuf);
                    }
                } else {
                    state.getExeStack().push(new WriteFileStmt(filename, var));
                    return state;
                }
            }
        } else {
            throw new FileException();
        }
        return state;
    }

    private void writeToDisk(Buffer crtbf) {
        try {
            FileChannel fc = new RandomAccessFile(filename, "rw").getChannel();
            fc.position(fc.size());
            fc.write(ByteBuffer.wrap(crtbf.getIntBuffer()[0].toString().getBytes()));
            fc.write(ByteBuffer.wrap(crtbf.getIntBuffer()[1].toString().getBytes()));
            fc.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file found");
        } catch (IOException e) {
            System.out.println("Cannot close file");
        }
    }

    @Override
    public String toString() {
        return "write_to_file(" + filename + ", " + var + ')';
    }
}
