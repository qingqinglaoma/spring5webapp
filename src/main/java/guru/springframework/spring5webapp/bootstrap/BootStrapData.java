package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher();
        publisher.setCity("Sydney");
        publisher.setName("Yama Education");
        publisher.setState("NSW");
        publisherRepository.save(publisher);

        System.out.println("Publisher Count: " + publisherRepository.count());

        Author yama = new Author("Yama", "Zhang");
        Book mySpring = new Book("My spring", "123");
        yama.getBooks().add(mySpring);
        mySpring.getAuthors().add(yama);

        mySpring.setPublisher(publisher);
        publisher.getBooks().add(mySpring);

        authorRepository.save(yama);
        bookRepository.save(mySpring);
        publisherRepository.save(publisher);

        Author john = new Author("John", "Zhang");
        Book myJava= new Book("My java", "456");
        john.getBooks().add(myJava);
        myJava.getAuthors().add(john);

        myJava.setPublisher(publisher);
        publisher.getBooks().add(myJava);

        authorRepository.save(john);
        bookRepository.save(myJava);
        publisherRepository.save(publisher);

        System.out.println("Started in my Bootstrap");
        System.out.println("Number of authors:" + authorRepository.count());
        System.out.println("Number of books:" + bookRepository.count());
    }
}
