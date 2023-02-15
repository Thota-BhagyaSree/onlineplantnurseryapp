package com.cg.onlineplantnursery.review.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.onlineplantnursery.entity.Review;
import com.cg.onlineplantnursery.exceptions.EntityNotFoundException;
import com.cg.onlineplantnursery.service.ReviewServiceImpl;

class ReviewServiceImplTest {
    @InjectMocks   // use on which class to test
    ReviewServiceImpl reviewServiceImpl;
    
    @Mock
    ReviewServiceImpl reviewRepository;
    
    @SuppressWarnings("deprecation")
    @BeforeEach
    public void doInit()
    {
         MockitoAnnotations.initMocks(this);
    }
    
    @Test
    @DisplayName("Test case to test insertion of Course when input values are valid")
    void testAddCourse() {
         // sample input 
    	Review sampleInput = new Review("Kailash","good seeds",1,3,1,5,5);
         // expected output 
    	Review actualOutput = new Review("Kailash","good seeds",1,3,1,5,5);
         actualOutput.setReviewId(0);
         actualOutput.setCustomerName(null);
         
         
         Review expectedOutput = new Review("Kailash","good seeds",1,3,1,5,5);
         expectedOutput.setReviewId(0);
         expectedOutput.setCustomerName(null);
         
         // call actual method and store actual result : actual output
         
     when(reviewRepository.save(sampleInput)).thenReturn(actualOutput);
         
             
         // compare actual output with expected output
         assertEquals(actualOutput, expectedOutput);
         
    }
 
    
    
    
    
    @Test
    @DisplayName("Test case to test insertion of Review when input values are invalid")
    void testAddCourse2() {
    	Review sampleInput = new Review(null,"good seeds",1,3,1,5,5);
         
         // call actual method and store actual result : actual output
         
     when(reviewRepository.save(sampleInput)).thenThrow(EntityNotFoundException.class);
         
         
    }
 
    
   

 



	}


