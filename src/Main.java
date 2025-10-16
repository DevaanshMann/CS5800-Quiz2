//CS5800 - Advanced Software Engineering
// Quiz 2

import java.util.ArrayList;
import java.util.List;

class Book {
    private String title;
    private String author;
    private List<String> chapters;

    public Book(String title, String author, List<String> chapters) {
        this.title = title;
        this.author = author;
        this.chapters = chapters;
    }

    protected Book(Book other, boolean deepCopyChapters) {
        this.title = other.title;
        this.author = other.author;
        if (deepCopyChapters && other.chapters != null) {
            this.chapters = new ArrayList<>(other.chapters);
        } else {
            this.chapters = other.chapters;
        }
    }

    public List<String> getChapters() { return chapters; }
    public void setChapter(int idx, String value) { this.chapters.set(idx, value); }
    public void addChapter(String value) { this.chapters.add(value); }

    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author + "', chapters=" + chapters + "}";
    }
}

class BookShallowCopy extends Book implements Cloneable {
    public BookShallowCopy(String title, String author, List<String> chapters) {
        super(title, author, chapters);
    }

    @Override
    public BookShallowCopy clone() {
        return new BookShallowCopy(this);
    }

    private BookShallowCopy(BookShallowCopy other) {
        super(other, false);
    }
}

/** Part 1 — Deep clone: copies the chapters list content. */
class BookDeepCopy extends Book implements Cloneable {
    public BookDeepCopy(String title, String author, List<String> chapters) {
        super(title, author, chapters);
    }

    @Override
    public BookDeepCopy clone() {
        return new BookDeepCopy(this);
    }

    private BookDeepCopy(BookDeepCopy other) {
        super(other, true);
    }
}

public class Main {
    public static void main(String[] args) {

        List<String> baseChapters = new ArrayList<>();
        baseChapters.add("Ch 1: Intro");
        baseChapters.add("Ch 2: Patterns");
        baseChapters.add("Ch 3: Prototype");

        System.out.println("Part 2: Shallow Copy");
        BookShallowCopy originalShallow = new BookShallowCopy("Design Patterns", "EG, RH, RJ, JV", new ArrayList<>(baseChapters));
        BookShallowCopy cloneShallow = originalShallow.clone();

        originalShallow.setChapter(1, "Ch 2: Structural Patterns");
        originalShallow.addChapter("Ch 4: Extras");

        System.out.println("Original (shallow): " + originalShallow);
        System.out.println("Clone    (shallow): " + cloneShallow);

        System.out.println("\nPart 3: Deep Copy");
        BookDeepCopy originalDeep = new BookDeepCopy("Design Patterns", "EG, RH, RJ, JV", new ArrayList<>(baseChapters));
        BookDeepCopy cloneDeep = originalDeep.clone();

        originalDeep.setChapter(0, "Ch 1: Overview");
        originalDeep.addChapter("Ch 4: Deep Dive");

        System.out.println("Original (deep): " + originalDeep);
        System.out.println("Clone    (deep): " + cloneDeep);
    }
}
