package com.aTh.Atherapy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "meeting")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meeting_seq_gen")
    @SequenceGenerator(name = "meeting_seq_gen", sequenceName = "meeting_seq", allocationSize = 1)
    private Long id;
    private String title;
    private String description;
    private Time start_time;
    private Time end_time;

    @ManyToMany(mappedBy = "meetings", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserProfile> users = new ArrayList<>();


}
