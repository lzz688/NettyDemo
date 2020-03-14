package 迭代器;

public class BookShelf implements Aggregate {

    private Book[] books;

    int pointer=0;
    public BookShelf(int max_size){
        books=new Book[max_size];
    }

    public void appendBook(Book book){
        books[pointer]=book;
        pointer++;
    }
    public Book findBookAt(int index){
        return books[index];
    }
    public int getlength(){
        return pointer;
    }
    public BookShelfIterator iterator() {
        return new BookShelfIterator(this);
    }
}
