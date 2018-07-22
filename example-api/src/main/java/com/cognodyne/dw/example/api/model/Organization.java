package com.cognodyne.dw.example.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jersey.repackaged.com.google.common.base.MoreObjects;

@Entity
@Table(name = "organization")
public class Organization extends Persistent {
    private static final long  serialVersionUID = -3007605895218973429L;
    private String             name;
    private String             abbreviation;
    private Organization       parent;
    private List<Organization> children;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "abbrev")
    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "parent_id")
    public Organization getParent() {
        return parent;
    }

    public void setParent(Organization parent) {
        this.parent = parent;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "parent", cascade = { CascadeType.ALL })
    @OrderBy("name")
    public List<Organization> getChildren() {
        return children;
    }

    public void setChildren(List<Organization> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())//
                .add("id", this.id)//
                .add("name", this.name)//
                .add("abbreviation", this.abbreviation)//
                .add("parent", this.parent)//
                .add("children", this.children)//
                .toString();
    }
    
    
}
