package com.itakeunconf.codecraft.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sessions")
@SequenceGenerator(name = "seq_gen", sequenceName = "session_seq")
public class PairingSession {

    @Id
    @GeneratedValue(generator = "seq_gen", strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 6, max = 255)
    @NotNull
    private String sessionName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }
}
