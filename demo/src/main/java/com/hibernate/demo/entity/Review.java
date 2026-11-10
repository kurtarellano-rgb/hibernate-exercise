
package com.hibernate.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a review entity in the database.
 * Contains review information, including its unique
 * identifier and comments.
 */
@Entity
@Table(name = "review")
public class Review {

    /** The unique identifier of the review. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /** The comments associated with the review. */
    @Column(name = "comments")
    private String comments;

    /**
     * Constructs a review with no initial values.
     */
    public Review() {
    }

    /**
     * Constructs a review with the specified comments.
     *
     * @param comments the comments for the review
     */
    public Review(String comments) {
        this.comments = comments;
    }

    /**
     * Returns a string representation of the review.
     *
     * @return a string containing the review ID and comments
     */
    @Override
    public String toString() {
        return "Review [id=" + id + ", comments=" + comments + "]";
    }
}
