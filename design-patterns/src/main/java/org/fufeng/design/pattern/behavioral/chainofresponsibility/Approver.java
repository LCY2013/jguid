package org.fufeng.design.pattern.behavioral.chainofresponsibility;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 17:57
 */
public abstract class Approver {
    protected Approver approver;

    public void setApprover( Approver approver) {
        this.approver = approver;
    }
    public abstract void deploy(Course course);
}
