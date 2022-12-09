package com.app.model.person;

import com.app.model.training.Training;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@SQLDelete(sql = "UPDATE person SET deleted = true WHERE id = ?")
@Where(clause = "deleted=false")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    private String email;

    private String pesel;

    private boolean deleted = false;
    @ToString.Exclude
    @ManyToMany(mappedBy = "peopleList")
    private List<Training> trainingList;
}
