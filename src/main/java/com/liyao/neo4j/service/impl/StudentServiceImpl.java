package com.liyao.neo4j.service.impl;

import com.liyao.neo4j.mapper.StudentMapper;
import com.liyao.neo4j.repository.node.StudentRepositroy;
import com.liyao.neo4j.service.StudentService;
import com.liyao.neo4j.utils.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: neo4j
 * @description:
 * @author: YxYL
 * @create: 2022-06-05 12:59
 **/

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    /**
     * 根据id删除学生信息
     *
     * @param id 学生id
     * @return true 删除成功
     */
    @Override
    public boolean delStudent(long id) {
        if (SystemUtil.isNullOrEmpty(String.valueOf(id))) {//id为空
            return false;
        }
        try {
            studentMapper.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
