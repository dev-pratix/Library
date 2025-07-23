package guru.springframework.spring6webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;

@Component
public class BootstrapData implements CommandLineRunner {

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;

	public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
			PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Author pratik = new Author();
		pratik.setFirstName("Pratik");
		pratik.setLastName("Bhadale");

		Book soloLeveling = new Book();
		soloLeveling.setTitle("Solo Leveling : 1000 year Blood War !");
		soloLeveling.setIsbn("123456");

		Author pratikSaved = authorRepository.save(pratik);
		Book soloSaved = bookRepository.save(soloLeveling);

		Author parth = new Author();
		parth.setFirstName("Parth");
		parth.setLastName("Lathkar");

		Book soloMMO = new Book();
		soloMMO.setTitle("Solo MMO : The half Life");
		soloMMO.setIsbn("654321");
		

		Author parthSaved = authorRepository.save(parth);
		Book soloMMOSaved = bookRepository.save(soloMMO);

		pratikSaved.getBooks().add(soloSaved);
		soloSaved.getAuthors().add(pratikSaved);
		parthSaved.getBooks().add(soloMMOSaved);
		soloMMOSaved.getAuthors().add(parthSaved);

		Publisher pub1 = new Publisher();
		pub1.setAddress("San Diego");
		pub1.setCity("Mexico");
		pub1.setPublisherName("Publisher 1");
		pub1.setState("State of Insergent");
		pub1.setZip("00001");
		Publisher savePub1 = publisherRepository.save(pub1);

		soloSaved.setPublisher(savePub1);
		soloMMOSaved.setPublisher(savePub1);

		authorRepository.save(pratikSaved);
		authorRepository.save(parthSaved);


		bookRepository.save(soloSaved);
		bookRepository.save(soloMMOSaved);
		
		System.out.println("In Bootstrap");
		System.out.println("Author Count : " + authorRepository.count());
		System.out.println("Book Count : " + bookRepository.count());

		System.out.println("Publisher : " + savePub1);
		System.out.println("Publisher Count : " + publisherRepository.count());

	}

}
