package com.cognodyne.dw.example.api.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jersey.repackaged.com.google.common.base.MoreObjects;

@Entity
@Table(name = "groups", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Group extends Persistent {
    private static final long serialVersionUID = 3846555533378656505L;
    private String            name;
    private String            description;
    private Set<User>         users;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonBackReference
    @ManyToMany
    @JoinTable(name = "user_group_xref", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "user_id"), uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "group_id" }) })
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())//
                .add("id", this.id)//
                .add("name", this.name)//
                .add("description", this.description)//
                .toString();
    }
}
