package org.randoom.setlx.expressions;

import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.exceptions.TermConversionException;
import org.randoom.setlx.types.Term;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.State;
import org.randoom.setlx.utilities.TermConverter;

import java.util.List;

/**
 * Implementation of the modulo operator.
 *
 * grammar rule:
 * product
 *     : power ([...] | '%' power)*
 *     ;
 *
 * implemented here as:
 *       =====              =====
 *       mLhs               mRhs
 */
public class Modulo extends Expr {
    // functional character used in terms
    private final static String FUNCTIONAL_CHARACTER = generateFunctionalCharacter(Modulo.class);
    // precedence level in SetlX-grammar
    private final static int    PRECEDENCE           = 1700;

    private final Expr lhs;
    private final Expr rhs;

    public Modulo(final Expr lhs, final Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    protected Value evaluate(final State state) throws SetlException {
        return lhs.eval(state).modulo(state, rhs.eval(state));
    }

    @Override
    protected void collectVariables (
        final List<String> boundVariables,
        final List<String> unboundVariables,
        final List<String> usedVariables
    ) {
        lhs.collectVariablesAndOptimize(boundVariables, unboundVariables, usedVariables);
        rhs.collectVariablesAndOptimize(boundVariables, unboundVariables, usedVariables);
    }

    /* string operations */

    @Override
    public void appendString(final State state, final StringBuilder sb, final int tabs) {
        lhs.appendBracketedExpr(state, sb, tabs, PRECEDENCE, false);
        sb.append(" % ");
        rhs.appendBracketedExpr(state, sb, tabs, PRECEDENCE, true);
    }

    /* term operations */

    @Override
    public Term toTerm(final State state) {
        final Term result = new Term(FUNCTIONAL_CHARACTER, 2);
        result.addMember(state, lhs.toTerm(state));
        result.addMember(state, rhs.toTerm(state));
        return result;
    }

    public static Modulo termToExpr(final Term term) throws TermConversionException {
        if (term.size() != 2) {
            throw new TermConversionException("malformed " + FUNCTIONAL_CHARACTER);
        } else {
            final Expr lhs = TermConverter.valueToExpr(term.firstMember());
            final Expr rhs = TermConverter.valueToExpr(term.lastMember());
            return new Modulo(lhs, rhs);
        }
    }

    // precedence level in SetlX-grammar
    @Override
    public int precedence() {
        return PRECEDENCE;
    }

    public static String functionalCharacter() {
        return FUNCTIONAL_CHARACTER;
    }
}

