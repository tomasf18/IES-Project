package ies.lab.quotes.quotes.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

import jakarta.persistence.Column;

@Entity
@Table(name = "movies")
public class Movie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true) // unique constraint
    private String title;

    private int year;

    @OneToMany(mappedBy = "movie")
    private Set<Quote> quotes;

    // standard constructors / setters / getters / toString
    public Movie() {}

    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", title=" + title + ", year=" + year + '}';
    }
}
