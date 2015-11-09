package com.danielacraciun.models.expression;

import com.danielacraciun.models.dictionary.Dictionary;

public class ArithmExp extends Exp {
    private Exp e1;
    private Exp e2;
    private Character op;

    public ArithmExp(Exp e1, Exp e2, Character op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    public int eval(Dictionary<String, Integer> tbl) throws DivisionByZeroException, UninitializedVarException {
        switch (op) {
            case '+':
                return (e1.eval(tbl) + e2.eval(tbl));
            case '-':
                return (e1.eval(tbl) - e2.eval(tbl));
            case '*':
                return (e1.eval(tbl) * e2.eval(tbl));
            case '/':
                int expr1 = e1.eval(tbl);
                int expr2 = e2.eval(tbl);
                if(expr2 == 0)
                    throw new DivisionByZeroException();
                return (e1.eval(tbl) / e2.eval(tbl));
        }
        return 0;
    }

    public String toString() {
        return e1.toString() + " " + op + " " + e2.toString();
    }

}