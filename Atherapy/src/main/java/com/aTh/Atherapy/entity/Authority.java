package com.aTh.Atherapy.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "authorities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String authority;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
