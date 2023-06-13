package com.example.Snippet.Searcher;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.Objects;

@Entity
class Snippet {

    @jakarta.persistence.Id
    private @Id
    @GeneratedValue Long id;
    @Column
    private String name;
    @Column
    private String URL;
    @Column
    private String type;
    @Column
    private String version;
    @Column
    private boolean validate;
    @Column
    private Date date;

    public Snippet() {}

    public Snippet(String name, String URL, String type, String version, boolean validate, Date date) {
        this.name = name;
        this.URL = URL;
        this.type = type;
        this.version = version;
        this.validate = validate;
        this.date = date;
    }

    public String getName() {
        return this.name;
    }

    public String getURL() {
        return this.URL;
    }

    public String getType() {
        return type;
    }

    public String getVersion() {
        return version;
    }

    public boolean isValidate() {
        return validate;
    }

    public Date getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setURL(String role) {
        this.URL = role;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Snippet snippet))
            return false;
        return Objects.equals(this.id, snippet.id) && Objects.equals(this.name, snippet.name)
                && Objects.equals(this.URL, snippet.URL) && Objects.equals(this.type, snippet.type)
                && Objects.equals(this.version, snippet.version) && Objects.equals(this.validate, snippet.validate)
                && Objects.equals(this.date, snippet.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.URL, this.type, this.version, this.validate, this.date);
    }

    @Override
    public String toString() {
        return "Snippet{" + "id=" + this.id + "," +
                " name='" + this.name + '\'' + "," +
                " URL='" + this.URL + '\'' + "," +
                " type='" + this.type + '\'' + "," +
                " version='" + this.version + '\'' + "," +
                " validate='" + this.validate + '\'' + "," +
                " date='" + this.date + '\'' + "," +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
