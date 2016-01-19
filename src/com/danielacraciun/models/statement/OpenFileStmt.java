package com.danielacraciun.models.statement;

import com.danielacraciun.models.dictionary.Dictionary;
import com.danielacraciun.models.expression.DivisionByZeroException;
import com.danielacraciun.models.expression.UninitializedVarException;
import com.danielacraciun.models.filetable.Buffer;
import com.danielacraciun.models.prgstate.PrgState;

public class OpenFileStmt implements IStmt {
    private String filename;

    public OpenFileStmt(String filename) {
        this.filename = filename;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, UninitializedVarException {
        Dictionary<String, Buffer> crtft = state.getFileTable();
        if (crtft.containsKey(filename)) {
            if (crtft.get(filename) == null) {
                crtft.put(filename, new Buffer(state.getState_id(), new Integer[2]));
            } else {
                if (crtft.get(filename).getCrtPrgStateId() == state.getState_id()) {
                    return state;
                } else {
                    state.getExeStack().push(new OpenFileStmt(filename));
                    return state;
                }
            }
        } else {
            crtft.put(filename, new Buffer(state.getState_id(), new Integer[2]));
        }
        return state;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "open_file(" + filename + ')';
    }
}
