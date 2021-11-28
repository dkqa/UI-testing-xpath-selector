package example;

// https://www.bookvoed.ru/books?genre=2

import com.dkqa.selector.base.Selector;

import static com.dkqa.selector.SelectorFactory.div;

public class BookvoedPage {

    public static Selector BOOKS_AREA = div("books");

    public static Selector HIT_ICON = div("Er");
    public static Selector SALE_ICON = div("Qo ap");

    public static Selector BOOK = BOOKS_AREA.descendant(div("Kh")).name("Book");
    public static Selector BOOK_HIT = BOOK.isDescendant(HIT_ICON).name("Book Hit");

    public static class Book {
        public static Selector TITLE = BOOK.descendant(div("Qr Rr").name("Title"));
        public static Selector AUTHOR = BOOK.descendant(div("Ur").name("Author"));
        public static Selector RATING = BOOK.descendant(div("Ir").name("Rating"));
        public static Selector AMOUNT = BOOK.descendant(div("ug").name("Amount"));
        public static Selector BUTTON_BUY = BOOK.descendant(div("ks").name("Button Buy"));
        public static Selector BUTTON_FAVOURITE = BOOK.descendant(div("ls").name("Button Favourite"));
    }

    public static class BookHit {
        public static Selector TITLE = BOOK_HIT.descendant(Book.TITLE);
        public static Selector AUTHOR = BOOK_HIT.descendant(Book.AUTHOR);
        public static Selector RATING = BOOK_HIT.descendant(Book.RATING);
        public static Selector AMOUNT = BOOK_HIT.descendant(Book.AMOUNT);
        public static Selector BUTTON_BUY = BOOK_HIT.descendant(Book.BUTTON_BUY);
        public static Selector BUTTON_FAVOURITE = BOOK_HIT.descendant(Book.BUTTON_FAVOURITE);
    }

}
