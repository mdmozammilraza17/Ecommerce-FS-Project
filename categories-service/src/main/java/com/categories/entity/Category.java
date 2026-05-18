package com.categories.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn (name = "parent_id")
    @JsonBackReference
    private Category parent;

    @OneToMany (mappedBy = "parent",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Category> children = new ArrayList<>();

    public Category() {
    }

    public Category(Long id, String name, Category parent, List<Category> children) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.children = children;
    }
}
