package io.gynacare.gynacare.comments;

import java.math.BigDecimal;

public class CommentBean {
    private BigDecimal commentId;
    private BigDecimal commentPatientId;
    private String patientFullName;
    private String commentDatetime;
    private BigDecimal commentArticleId;
    private String commentMessage;
    private BigDecimal commentParentCommentId;
    public BigDecimal getCommentId() {
        return commentId;
    }
    public void setCommentId(BigDecimal commentId) {
        this.commentId = commentId;
    }
    public BigDecimal getCommentPatientId() {
        return commentPatientId;
    }
    public void setCommentPatientId(BigDecimal commentPatientId) {
        this.commentPatientId = commentPatientId;
    }
    public String getPatientFullName() {
        return patientFullName;
    }
    public void setPatientFullName(String patientFullName) {
        this.patientFullName = patientFullName;
    }
    public String getCommentDatetime() {
        return commentDatetime;
    }
    public void setCommentDatetime(String commentDatetime) {
        this.commentDatetime = commentDatetime;
    }
    public BigDecimal getCommentArticleId() {
        return commentArticleId;
    }
    public void setCommentArticleId(BigDecimal commentArticleId) {
        this.commentArticleId = commentArticleId;
    }
    public String getCommentMessage() {
        return commentMessage;
    }
    public void setCommentMessage(String commentMessage) {
        this.commentMessage = commentMessage;
    }
    public BigDecimal getCommentParentCommentId() {
        return commentParentCommentId;
    }
    public void setCommentParentCommentId(BigDecimal commentParentCommentId) {
        this.commentParentCommentId = commentParentCommentId;
    }
    


}
