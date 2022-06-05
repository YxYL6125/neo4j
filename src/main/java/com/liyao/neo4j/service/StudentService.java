package com.liyao.neo4j.service;

public interface StudentService {

    /**
     * 根据删除学生信息
     * @param id 学生id
     * @return true删除成功
     */
    boolean delStudent(long id);
}
