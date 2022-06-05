package com.liyao.neo4j.mapper;

import com.liyao.neo4j.eneity.Book;
import com.liyao.neo4j.eneity.Course;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper{
    @Select(value = "select * from book")
    List<Book> selectAllBook();
}
