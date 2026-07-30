package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
    public class College {

        @Id
        private int id;

        private String name;

        // constructors
        public College() {
        }

        public College(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @OneToMany(mappedBy = "college")
        @JsonIgnore
        private List<Student> students;

        // getters & setters
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

}

