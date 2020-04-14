package ro.secret.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "Name is mandatory")
    private String name;

    @NotEmpty(message = "Wish is mandatory")
    private String wish;

    @NotEmpty(message = "Details are mandatory")
    private String details;

    private String image;

    private Boolean extract;

    public Employer() {
    }

    public Employer(String name, String wish, String details, String image, Boolean extract) {
        this.name = name;
        this.wish = wish;
        this.details = details;
        this.image = image;
        this.extract = extract;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getExtract() {
        return extract;
    }

    public void setExtract(Boolean extract) {
        this.extract = extract;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", wish='" + wish + '\'' +
                ", details='" + details + '\'' +
                ", image='" + image + '\'' +
                ", extract='" + extract + '\'' +
                '}';
    }


}
