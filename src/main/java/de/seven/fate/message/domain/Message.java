package de.seven.fate.message.domain;

import de.seven.fate.message.enums.MessageType;
import de.seven.fate.message.listener.MessageListener;
import de.seven.fate.person.domain.BaseEntity;
import de.seven.fate.person.domain.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@ToString(exclude = "person")
@EqualsAndHashCode(exclude = {"person"})
//@NamedQueries(@NamedQuery(name = "Message.markMessage", query = "UPDATE Message m SET m.messageType = ?2 WHERE m.id IN (?1)"))
@EntityListeners(value = MessageListener.class)
public class Message extends BaseEntity {

    @NotNull
    @Column(length = 2048)
    private String description;

    @NotNull
    private String image;

    @NotNull
    private String title;

    @NotNull
    @Column(name = "PUB_DATE")
    private Date pubDate;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "MESSAGE_TYPE")
    private MessageType messageType;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private Person person;

}
