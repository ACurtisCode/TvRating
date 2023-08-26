package com.alexc.tvratings.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



@Entity
@Table(name="shows")
public class Show {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Column(unique=true)
//	@Unique(message="duplicate message")	
	@NotEmpty(message="show must have an title")
	@Size(min = 3, max = 30, message="show must be at least 3 characters")
	private String title;
	
	@NotEmpty(message="show must have a network")
	@Size(min = 3, max = 30, message="network must be at least 3 characters")
	private String network;
	
	@NotEmpty(message="show must have an description")
	@Size(min=5, max=60, message="description must be between 5 and 60 characters")
	private String description;
	
	
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    @Column(updatable=false)
    @OneToMany(mappedBy="ratedShow", fetch=FetchType.LAZY)
    List<Rating> ratings = new ArrayList<Rating>();
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User showCreator;
    
    public Show() {
    	
    }
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public float getAverageRating() {
		float avg = 0;
		for(Rating rating : this.getRatings()) {
			avg+=rating.getShowRating();
		}
		avg = avg / this.getRatings().size();
		return avg;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public User getShowCreator() {
		return showCreator;
	}

	public void setShowCreator(User showCreator) {
		this.showCreator = showCreator;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
}
