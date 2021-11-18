package example;

import org.junit.Test;
import selector.base.Selector;

import static example.BookvoedPage.BOOK;
import static example.BookvoedPage.SALE_ICON;
import static example.BookvoedPage.*;

public class BookvoedTest {

    public static void click(Selector selector) {
        System.out.println("\033[95m [Click] \033[0m Name: \033[36m" + selector.getName() + "\033[0m -> XPath: \033[33m" + selector.toXPath());
    }

    @Test
    public void test() {
        click(BookvoedPage.Book.BUTTON_BUY);
        click(BookvoedPage.BookHit.BUTTON_FAVOURITE);
    }

    @Test
    public void test1() {
        click(BOOK.isDescendant(SALE_ICON).descendant(Book.TITLE));
    }

    @Test
    public void testAuthor() {
        click(BOOK.isDescendantText("Author Name").descendant(Book.BUTTON_BUY));
    }

}


