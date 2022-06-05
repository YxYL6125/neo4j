package com.liyao.neo4j.mapper;

import com.liyao.neo4j.eneity.MeetingConnection;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MeetingConnectionMapper {
    @Select(value = "select * from meeting_connection")
    public List<MeetingConnection> selectAllMeetingConnection();
    @Select(value = "select * from meeting_connection where teacher_id=#{id}")
    public List<MeetingConnection> selectMeetingConnectionByTeacherId(long id);
}
