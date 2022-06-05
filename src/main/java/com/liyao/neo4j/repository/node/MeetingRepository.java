package com.liyao.neo4j.repository.node;

import com.liyao.neo4j.eneity.HomeWork;
import com.liyao.neo4j.eneity.Meeting;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface MeetingRepository  extends Neo4jRepository<Meeting,Long> {
    @Query("merge(n:meeting{" +
            "id::#{#meeting.id}," +
            "meetingName::#{#meeting.meetingName}," +
            "meetingTime::#{#meeting.meetingTime}" +
            "})")
    void addMeetingNode(Meeting meeting);
}
