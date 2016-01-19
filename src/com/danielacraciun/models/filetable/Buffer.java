package com.danielacraciun.models.filetable;

import java.io.Serializable;
import java.util.Arrays;

public class Buffer implements Serializable {
    Integer crtPrgStateId;
    Integer[] intBuffer;

    public Buffer(Integer crtPrgStateId, Integer[] intBuffer) {
        this.crtPrgStateId = crtPrgStateId;
        this.intBuffer = intBuffer;
    }

    public Integer[] getIntBuffer() {
        return intBuffer;
    }

    public void setIntBuffer(Integer[] intBuffer) {
        this.intBuffer = intBuffer;
    }

    public Integer getCrtPrgStateId() {
        return crtPrgStateId;
    }

    public void setCrtPrgStateId(Integer crtPrgStateId) {
        this.crtPrgStateId = crtPrgStateId;
    }

    @Override
    public String toString() {
        return "opened by: " + crtPrgStateId +
                ", buffer: " + Arrays.toString(intBuffer);
    }
}
