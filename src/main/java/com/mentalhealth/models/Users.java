package com.mentalhealth.models;


import com.mentalhealth.models.common.BaseUser;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "users")
@SuperBuilder
public class Users extends BaseUser {


}
