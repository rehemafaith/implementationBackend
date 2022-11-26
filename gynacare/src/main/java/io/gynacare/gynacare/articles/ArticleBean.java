package io.gynacare.gynacare.articles;

import java.math.BigDecimal;

public class ArticleBean {
    private BigDecimal articleId;
    private String articleTitle;
    private String articleBody;
    private String articlePubDatetimeString;
    private BigDecimal articleGynaId;
    public BigDecimal getArticleId() {
        return articleId;
    }
    public void setArticleId(BigDecimal articleId) {
        this.articleId = articleId;
    }
    public String getArticleTitle() {
        return articleTitle;
    }
    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }
    public String getArticleBody() {
        return articleBody;
    }
    public void setArticleBody(String articleBody) {
        this.articleBody = articleBody;
    }
    public String getArticlePubDatetimeString() {
        return articlePubDatetimeString;
    }
    public void setArticlePubDatetimeString(String articlePubDatetimeString) {
        this.articlePubDatetimeString = articlePubDatetimeString;
    }
    public BigDecimal getArticleGynaId() {
        return articleGynaId;
    }
    public void setArticleGynaId(BigDecimal articleGynaId) {
        this.articleGynaId = articleGynaId;
    }


}
