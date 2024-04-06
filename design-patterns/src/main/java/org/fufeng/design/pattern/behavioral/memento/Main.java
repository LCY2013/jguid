package org.fufeng.design.pattern.behavioral.memento;



/**
 * 备忘录模式
 * {@link org.springframework.binding.message.StateManageableMessageContext}
 * {@link org.springframework.binding.message.MessageContext}
 *
 * @author fufeng
 * {@code @Date} 2024-04-06 16:30
 */
public class Main {

    public static void main(String[] args) {
        ArticleMementoManager articleMementoManager = new ArticleMementoManager();
        Article article = new Article("A", "A", "A");
        ArticleMemento articleMemento = article.saveToMemento();
        articleMementoManager.addMemento(articleMemento);

        System.out.println("标题："+article.getTitle() + " 内容：" + article.getContent() + " 图片：" + article.getImgs());
        System.out.println("文章完整信息："+article);

        System.out.println("修改手机start");

        article.setTitle("B");
        article.setContent("B");
        article.setImgs("B");

        System.out.println("修改手机end");
        System.out.println("文章完整信息："+article);

        articleMemento = article.saveToMemento();
        articleMementoManager.addMemento(articleMemento);

        article.setTitle("C");
        article.setContent("C");
        article.setImgs("C");

        System.out.println("暂存回退start");
        System.out.println("回退出栈一次");
        articleMemento = articleMementoManager.getMemento();
        article.undoFromMemento(articleMemento);

        System.out.println("回退出栈两次");
        articleMemento = articleMementoManager.getMemento();
        article.undoFromMemento(articleMemento);
        System.out.println("暂存回退end");
        System.out.println("文章完整信息："+article);
    }

}
