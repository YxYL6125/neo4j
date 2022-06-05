package com.liyao.neo4j.repository.node;

import com.liyao.neo4j.eneity.Book;
import com.liyao.neo4j.eneity.Course;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface BookRepository extends Neo4jRepository<Book,Long> {
    @Query("merge(n:book{" +
            "id::#{#book.id}," +
            "bookName::#{#book.bookName}," +
            "bookAuthor::#{#book.bookAuthor}," +
            "publishYear::#{#book.publishYear}" +
            "})")
    void addBookNode(Book book);
}
