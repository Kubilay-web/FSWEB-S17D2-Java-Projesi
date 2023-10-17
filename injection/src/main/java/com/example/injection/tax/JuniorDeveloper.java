package com.example.injection.tax;

import com.example.injection.model.Developer;
import com.example.injection.model.Experience;

public class JuniorDeveloper extends Developer {
    public JuniorDeveloper(Long id, String name, double salary) {
        super(id, name, salary, String.valueOf(Experience.JUNIOR));
    }
}
