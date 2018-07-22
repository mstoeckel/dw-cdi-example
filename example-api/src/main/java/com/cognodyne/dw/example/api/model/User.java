package com.cognodyne.dw.example.api.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class User extends Persistent {
    private static final long serialVersionUID = -1119261904170535994L;
    private String            username;
    private UserProfile       profile;
    private Set<Group>        groups;

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "user_profile_id")
    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_group_xref", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"), uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "group_id" }) })
    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}
