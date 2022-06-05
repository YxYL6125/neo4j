package com.liyao.neo4j.mapper;

import com.liyao.neo4j.eneity.HomeWorkFinish;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HomeWorkFinishmapper {

    @Select(value = "select *from homework_finish")
    List<HomeWorkFinish> getHomeWorkFinishList();

    @Select(value = "select *from homework_finish where student_id=#{id}")
    List<HomeWorkFinish> getHomeWorkFinishListById(long id);
}
