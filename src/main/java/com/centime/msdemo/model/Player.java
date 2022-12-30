package com.centime.msdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "players")
@ToString
@Getter
@Setter
@NoArgsConstructor

public class Player {

	@Id
	@Column(name = "id")
	private long id;

	@Column(name = "parent_id")
	private long parentId;

	@Column(name = "name")
	private String name;

	@Column(name = "colour")
	private String colour;

}
