package com.liyao.neo4j.mapper;

import com.liyao.neo4j.eneity.HomeWork;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HomeWorkMapper {

    @Select(value = "select *from homework")
    public List<HomeWork> getHomeWorkList();

    @Select(value = "select * from homework where id=#{id}")
    public HomeWork getHomeWorkById(long id);
}
