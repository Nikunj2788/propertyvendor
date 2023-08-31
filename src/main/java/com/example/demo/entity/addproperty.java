package com.example.demo.entity;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.demo.controller.agentcontroller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class addproperty {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String propertyname;
	private String Description;
	private String location;
	private String sqrt;
	private String bedroom;
	private String feature;
	
	
	@Lob
    private byte[] imageData;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getPropertyname() {
		return propertyname;
	}
	public void setPropertyname(String propertyname) {
		this.propertyname = propertyname;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public byte[] getImageData() {
		return imageData;
	}
	public String getImageString() {
		byte[] image = getImageData();
		String imageSource = Base64.getEncoder().encodeToString(image);
		return imageSource;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	
	public addproperty() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id") // The name of the foreign key column in the addproduct table
    private Agent agent;

	public addproperty(long id, String propertyname, String description, String location, String sqrt, String bedroom,
			String feature, byte[] imageData, Agent agent) {
		super();
		this.id = id;
		this.propertyname = propertyname;
		Description = description;
		this.location = location;
		this.sqrt = sqrt;
		this.bedroom = bedroom;
		this.feature = feature;
		this.imageData = imageData;
		this.agent = agent;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSqrt() {
		return sqrt;
	}
	public void setSqrt(String sqrt) {
		this.sqrt = sqrt;
	}
	public String getBedroom() {
		return bedroom;
	}
	public void setBedroom(String bedroom) {
		this.bedroom = bedroom;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	@Override
	public String toString() {
		return "addproperty [id=" + id + ", propertyname=" + propertyname + ", Description=" + Description
				+ ", location=" + location + ", sqrt=" + sqrt + ", bedroom=" + bedroom + ", feature=" + feature
				+ ", imageData=" + Arrays.toString(imageData) + ", agent=" + agent + "]";
	}
	public Agent getAgent() {
		return agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
   
}



