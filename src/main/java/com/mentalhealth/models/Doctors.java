package com.mentalhealth.models;


import com.mentalhealth.models.common.BaseUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctors")
@SuperBuilder
public class Doctors extends BaseUser {

    @Column(nullable = false)
    private String licenceType;

    @Column(nullable = false, unique = true)
    private String licenceNumber;

    @Column(nullable = false)
    private String state;

}
