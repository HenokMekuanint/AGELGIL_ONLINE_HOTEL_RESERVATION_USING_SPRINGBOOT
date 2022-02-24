package com.agelgil.agelgil.hotel.data.specifications;

import javax.management.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.agelgil.agelgil.hotel.data.models.Hotel;
import com.agelgil.agelgil.hotel.data.models.Service;

import org.springframework.data.jpa.domain.Specification;

public final class HotelSpecifications {


	public static Specification<Hotel> isUserVerified(Boolean verified){

		return (root, query, builder) -> builder.equal(root.get("user").get("verified"), verified);

	}

	public static Specification<Hotel> nameContains(String expression){
	
		return (root, query, builder) -> builder.like(root.get("name"), "%"+expression+"%");
	
	}

	public static Specification<Hotel> cityEquals(String expression){

		return (root, query, builder) -> builder.equal(root.get("location").get("city"), expression);

	}

	public static Specification<Hotel> standardGreaterThan(Integer value){

		return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("standard"), value);

	}

	public static Specification<Hotel> standardLessThan(Integer value){

		return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("standard"), value);

	}

	public static Specification<Hotel> userRatingGreaterThan(Float value){

		return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("rating"), value);

	}

	public static Specification<Hotel> userRatingLessThan(Float value){

		return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("rating"), value);

	}

	public static Specification<Hotel> roomPriceGreaterThan(Float value){

		return new Specification<Hotel>(){

			@Override
			public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				
				Subquery<Service> subquery = query.subquery(Service.class);
				Root<Service> subRoot = subquery.from(Service.class);
				subquery.select(subRoot);

				Predicate containsServicePredicate = builder.equal(subRoot.get("hotel").get("id"), root.get("id"));
				Predicate serviceTypePredicate = builder.equal(subRoot.get("serviceType").get("id"), 2); //TODO  REMOVE 0;
				Predicate priceGreaterThanPredicate = builder.greaterThanOrEqualTo(subRoot.get("unitPrice"), value);

				subquery.select(subRoot).where(containsServicePredicate, serviceTypePredicate, priceGreaterThanPredicate);
				
				return builder.exists(subquery);
				
			}

		};

	}

	public static Specification<Hotel> roomPriceLessThan(Float value){

		return new Specification<Hotel>(){
			
			@Override
			public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				
				Subquery<Service> subquery = query.subquery(Service.class);
				Root<Service> subRoot = subquery.from(Service.class);
				subquery.select(subRoot);

				Predicate containsServicePredicate = builder.equal(subRoot.get("hotel").get("id"), root.get("id"));
				Predicate serviceTypePredicate = builder.equal(subRoot.get("serviceType").get("id"), 2); //TODO  REMOVE 0;
				Predicate priceGreaterThanPredicate = builder.lessThanOrEqualTo(subRoot.get("unitPrice"), value);

				subquery.select(subRoot).where(containsServicePredicate, serviceTypePredicate, priceGreaterThanPredicate);
				
				return builder.exists(subquery);
				
			}

		};

	}

	public static Specification<Hotel> hasServiceType(String serviceName){

		return new Specification<Hotel>(){

			@Override
			public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
			
				Subquery<Service> subquery = query.subquery(Service.class);
				Root<Service> subRoot = subquery.from(Service.class);
				subquery.select(subRoot);

				Predicate containsServicePredicate = builder.equal(subRoot.get("hotel").get("id"), root.get("id"));
				Predicate serviceTypePredicate = builder.equal(subRoot.get("serviceType").get("name"), serviceName);

				subquery.select(subRoot).where(containsServicePredicate, serviceTypePredicate);

				return builder.exists(subquery);
				
			}
			
		};

	}



}
