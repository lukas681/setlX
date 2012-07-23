package org.randoom.setlx.expressions;

import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.exceptions.TermConversionException;
import org.randoom.setlx.types.Term;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.TermConverter;

/*
grammar rule:
reduce
    : factor ('+/' [..])*
    ;

implemented here as:
      ======              ======
     mNeutral           mCollection
*/

public class SumMembersBinary extends Expr {
    // functional character used in terms (MUST be class name starting with lower case letter!)
    private final static String FUNCTIONAL_CHARACTER = "^sumMembersBinary";
    // precedence level in SetlX-grammar
    private final static int    PRECEDENCE           = 1800;

    private final Expr mNeutral;
    private final Expr mCollection;

    public SumMembersBinary(final Expr neutral, final Expr collection) {
        mNeutral    = neutral;
        mCollection = collection;
    }

    protected Value evaluate() throws SetlException {
        return mCollection.eval().sumMembers(mNeutral.eval());
    }

    /* string operations */

    public void appendString(final StringBuilder sb, final int tabs) {
        mNeutral.appendString(sb, tabs);
        sb.append(" +/ ");
        mCollection.appendString(sb, tabs);
    }

    /* term operations */

    public Term toTerm() {
        final Term result = new Term(FUNCTIONAL_CHARACTER, 2);
        result.addMember(mNeutral.toTerm());
        result.addMember(mCollection.toTerm());
        return result;
    }

    public static SumMembersBinary termToExpr(final Term term) throws TermConversionException {
        if (term.size() != 2) {
            throw new TermConversionException("malformed " + FUNCTIONAL_CHARACTER);
        } else {
            final Expr neutral    = TermConverter.valueToExpr(PRECEDENCE, false, term.firstMember());
            final Expr collection = TermConverter.valueToExpr(PRECEDENCE, false, term.lastMember());
            return new SumMembersBinary(neutral, collection);
        }
    }

    // precedence level in SetlX-grammar
    public int precedence() {
        return PRECEDENCE;
    }
}
