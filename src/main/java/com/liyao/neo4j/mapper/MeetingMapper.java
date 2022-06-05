package com.liyao.neo4j.mapper;

import com.liyao.neo4j.eneity.Meeting;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MeetingMapper {
    @Select(value = "select *from meeting")
    public List<Meeting> selectAllMeeting();
    @Select(value = "select *from meeting where id=#{id}")
    public Meeting selectAllMeetingById(long id);
}
