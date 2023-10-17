package com.example.injection.rest;

import com.example.injection.model.Developer;
import com.example.injection.tax.Taxable;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DeveloperController {
    private Map<Integer, Developer> developers;
    private Taxable tax;

    @Autowired
    public DeveloperController (Taxable tax){
        this.tax=tax;
    }

    @PostConstruct
    private void init() {

        developers = new HashMap<>();
    }

    @RequestMapping(value = "/developers",method = RequestMethod.GET)
    public List<Developer> getAllDevelopers() {
        return new ArrayList<>(developers.values());
    }

    @RequestMapping(value = "/developers/{id}",method = RequestMethod.GET)
    public Developer getById(@PathVariable int id){
        return developers.get(id);
    }


    @RequestMapping(value = "/developers", method = RequestMethod.POST)
    public Developer addDeveloper(@RequestBody Developer request) {
        int id = Math.toIntExact(request.getId());
        String name = request.getName();
        double salary = request.getSalary();
        String experience = request.getExperience();

        double updatedSalary = salary;

        if ("JUNIOR".equals(experience)) {
            updatedSalary -= salary * tax.getSimpleTaxRate();
        } else if ("MID".equals(experience)) {
            updatedSalary -= salary * tax.getMiddleTaxRate();
        } else if ("SENIOR".equals(experience)) {
            updatedSalary -= salary * tax.getUpperTaxRate();
        }

        Developer developer = new Developer((long) id, name, updatedSalary, experience);
        developers.put(id, developer);
        return developer;
    }

    @RequestMapping(value = "/developers/{id}", method = RequestMethod.PUT)
    public String putAnimal(@PathVariable int id, @RequestBody Developer updatedDeveloper) {

        Developer existingDeveloper = developers.get(id);

        if (existingDeveloper != null) {
            existingDeveloper.setName(updatedDeveloper.getName());
            existingDeveloper.setSalary(updatedDeveloper.getSalary());
            existingDeveloper.setExperience(updatedDeveloper.getExperience());
            return "Succesful";
        } else {
            return "Id not found";
        }
    }

    @RequestMapping(value = "/developers/{id}", method = RequestMethod.DELETE)
    public String deleteDeveloper(@PathVariable int id) {
        Developer existingDeveloper = developers.get(id);

        if (existingDeveloper != null) {
            developers.remove(id);
            return "Başarıyla silindi.";
        } else {
            return "Id not found";
        }
    }


}
