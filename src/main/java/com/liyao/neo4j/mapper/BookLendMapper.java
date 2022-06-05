package com.liyao.neo4j.mapper;

import com.liyao.neo4j.eneity.Book;
import com.liyao.neo4j.eneity.Booklend;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookLendMapper {
    @Select(value = "select * from booklend")
    List<Booklend> selectAllBookLend();

    @Select(value = "select *from booklend where student_id=#{id}")
    List<Booklend> getBookLendById(Long id);
}
