package interpreter.boolExpressions;

import interpreter.exceptions.SetlException;
import interpreter.exceptions.TermConversionException;
import interpreter.expressions.Expr;
import interpreter.types.Term;
import interpreter.types.Value;
import interpreter.utilities.TermConverter;

/*
grammar rule:
disjunction
    : conjunction ('||' conjunction)*
    ;

implemented here as:
      ===========       ===========
         mLhs              mRhs
*/

public class Disjunction extends Expr {
    // functional character used in terms (MUST be class name starting with lower case letter!)
    private final static String FUNCTIONAL_CHARACTER = "'disjunction";

    private Expr mLhs;
    private Expr mRhs;

    public Disjunction(Expr lhs, Expr rhs) {
        mLhs = lhs;
        mRhs = rhs;
    }

    public Value evaluate() throws SetlException {
        return mLhs.eval().or(mRhs);
    }

    /* string operations */

    public String toString(int tabs) {
        return mLhs.toString(tabs) + " || " + mRhs.toString(tabs);
    }

    /* term operations */

    public Term toTerm() {
        Term result = new Term(FUNCTIONAL_CHARACTER);
        result.addMember(mLhs.toTerm());
        result.addMember(mRhs.toTerm());
        return result;
    }

    public static Disjunction termToExpr(Term term) throws TermConversionException {
        if (term.size() != 2) {
            throw new TermConversionException("malformed " + FUNCTIONAL_CHARACTER);
        } else {
            Expr lhs = TermConverter.valueToExpr(term.firstMember());
            Expr rhs = TermConverter.valueToExpr(term.lastMember());
            return new Disjunction(lhs, rhs);
        }
    }
}

