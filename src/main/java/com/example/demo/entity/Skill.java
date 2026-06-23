package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="skills")
public class Skill
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int skillId;
	private String skillName;
	@ManyToMany(mappedBy="skills")
	private List<User> users;
	public Skill() {
		
	}
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	@Override
	public String toString() {
		return "Skill [skillId=" + skillId + ", skillName=" + skillName + "]";
	}
	
	
}
