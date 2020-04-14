package ro.secret.entity;

import ro.secret.exception.EmployerExtractException;

import javax.persistence.*;

@Entity
public class Pair  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="from_id")
    private int fromId;

    @Column(name="to_id")
    private int toId;

    public Pair() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }
}
