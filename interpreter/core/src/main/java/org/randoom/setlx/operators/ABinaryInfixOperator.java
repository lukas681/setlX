package org.randoom.setlx.operators;

import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.exceptions.TermConversionException;
import org.randoom.setlx.operatorUtilities.OperatorExpression;
import org.randoom.setlx.operatorUtilities.OperatorExpression.OptimizerData;
import org.randoom.setlx.operatorUtilities.Stack;
import org.randoom.setlx.types.Term;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.CodeFragment;
import org.randoom.setlx.utilities.FragmentList;
import org.randoom.setlx.utilities.State;

import java.util.List;

/**
 * Base class for binary infix operators.
 */
public abstract class ABinaryInfixOperator extends AOperator {

    @Override
    public final boolean collectVariablesAndOptimize(State state, List<String> boundVariables, List<String> unboundVariables, List<String> usedVariables) {
        throw new IllegalStateException("Not implemented");
    }

    @Override
    public final OptimizerData collectVariables(State state, List<String> boundVariables, List<String> unboundVariables, List<String> usedVariables, Stack<OptimizerData> optimizerData) {
        OptimizerData rhs = optimizerData.poll();
        OptimizerData lhs = optimizerData.poll();
        return collectVariablesAndOptimize(state, boundVariables, unboundVariables, usedVariables, lhs, rhs);
    }

    /**
     * Gather all bound and unbound variables in this operator and its siblings.
     *
     * @param state            Current state of the running setlX program.
     * @param boundVariables   Variables "assigned" by this operator.
     * @param unboundVariables Variables not present in bound when used
     * @param usedVariables    Variables present in bound when used
     * @param lhs              Data for optimization from left hand side.
     * @param rhs              Data for optimization from right hand side.
     * @return Data for optimization.
     */
    public OptimizerData collectVariablesAndOptimize(State state, List<String> boundVariables, List<String> unboundVariables, List<String> usedVariables, OptimizerData lhs, OptimizerData rhs) {
        return new OptimizerData(
                lhs.isAllowOptimization() && rhs.isAllowOptimization()
        );
    }

    @Override
    public boolean hasArgumentBeforeOperator() {
        return true;
    }

    @Override
    public boolean hasArgumentAfterOperator() {
        return true;
    }

    @Override
    public Value buildTerm(State state, Stack<Value> termFragments) throws SetlException {
        Value rhs = termFragments.poll();
        Value lhs = termFragments.poll();
        Term term = new Term(generateFunctionalCharacter(this.getClass()));
        term.addMember(state, lhs);
        term.addMember(state, rhs);
        return term;
    }

    /**
     * Append arguments of the operator represented by a term to the supplied operator stack.
     *
     * @param state                    Current state of the running setlX program.
     * @param term                     Term to convert.
     * @param operatorStack            Operator to append to.
     * @param operatorClass            Class of the operator (used for error messages).
     * @throws TermConversionException If term is malformed.
     */
    protected static void appendArgumentsToOperatorStack(State state, Term term, FragmentList<AOperator> operatorStack, Class<? extends ABinaryInfixOperator> operatorClass) throws TermConversionException {
        if (term.size() != 2) {
            throw new TermConversionException("malformed " + generateFunctionalCharacter(operatorClass));
        } else {
            OperatorExpression.appendFromTerm(state, term.firstMember(), operatorStack);
            OperatorExpression.appendFromTerm(state, term.lastMember(), operatorStack);
        }
    }

    @Override
    public int compareTo(CodeFragment other) {
        if (this == other || this.getClass() == other.getClass()) {
            return 0;
        } else {
            return (this.compareToOrdering() < other.compareToOrdering())? -1 : 1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || this.getClass() == obj.getClass();
    }
}
