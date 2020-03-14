package 迭代器;

public class BookShelfIterator implements Iterator {
    BookShelf bookShelf;
      int index;
      public BookShelfIterator(BookShelf bookShelf){
          this.bookShelf=bookShelf;
          index=0;
     }

    public boolean hasNext() {
    if(index<this.bookShelf.getlength()){
      return true;
     }
      return false;
    }

    public Object next() {
        return bookShelf.findBookAt(index++);
    }
}
