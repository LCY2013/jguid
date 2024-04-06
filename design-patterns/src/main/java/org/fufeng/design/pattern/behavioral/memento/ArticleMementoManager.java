package org.fufeng.design.pattern.behavioral.memento;

import java.util.Stack;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 16:27
 */
public class ArticleMementoManager {
    private final Stack<ArticleMemento> ARTICLES = new Stack<>();

    public ArticleMemento getMemento() {
        return ARTICLES.pop();
    }

    public void addMemento(ArticleMemento articleMemento) {
        ARTICLES.push(articleMemento);
    }
}
