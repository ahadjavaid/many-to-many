package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
//            createCourseAndStudent(appDAO);
//            findCourseAndStudents(appDAO);
//            findStudentAndCourses(appDAO);
            addMoreCoursesForStudents(appDAO);
		};
	}

    private void addMoreCoursesForStudents(AppDAO appDAO) {
         int theId = 2;
         Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

         // create more courses

        Course tempCourse1 = new Course("Applied Physics - 1");
        Course tempCourse2 = new Course("Data Structures & Algorithms");

        // add courses to Student

        tempStudent.addCourse(tempCourse1);
        tempStudent.addCourse(tempCourse2);

        System.out.println("Updating student: " + tempStudent);
        System.out.println("Associated courses: " + tempStudent.getCourses());

        appDAO.update(tempStudent);

        System.out.println("Done!");
    }

    private void findStudentAndCourses(AppDAO appDAO) {
        int id = 2;
        Student tempStudent = appDAO.findStudentAndCoursesByStudentId(id);
        System.out.println("Student: " + tempStudent);
        System.out.println("Associated courses: " + tempStudent.getCourses());
        System.out.println("Done!");
    }

    private void findCourseAndStudents(AppDAO appDAO) {
        int id = 10;
        Course tempCourse = appDAO.findCourseAndStudentsByCourseId(id);
        System.out.println("Courses: " + tempCourse);
        System.out.println("Associated students: " + tempCourse.getStudents());
        System.out.println("Done!");
    }

    private void createCourseAndStudent(AppDAO appDAO) {

        // create a course
        Course tempCourse = new Course("Pacman - How to Score One Million Points");

        // create the students
        Student tempStudent1 = new Student("John", "Doe","john@gmail.com");
        Student tempStudent2 = new Student("Peter", "Parker","peter@gmail.com");

        // add student to the course
        tempCourse.addStudent(tempStudent1);
        tempCourse.addStudent(tempStudent2);

        // save the course and associated students
        System.out.println("Saving the course: " + tempCourse);
        System.out.println("associated students: " + tempCourse.getStudents());

        appDAO.save(tempCourse);
        System.out.println("Done!");
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {

        int theId = 10;
        System.out.println("Deleting course id: " + theId);
        appDAO.deleteCourseById(theId);
        System.out.println("Done!");
    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {
        // get the course and reviews
        int theId = 10;
        Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

        // print the course
        System.out.println(tempCourse);
        // print the reviews
        System.out.println(tempCourse.getReviews());
        System.out.println("Done!");
    }

    private void createCourseAndReviews(AppDAO appDAO) {

        // create a course

        Course tempCourse = new Course("Pacman - How to score one million points");

        // add some reviews
        tempCourse.addReview(new Review("Great Course ... loved it!"));
        tempCourse.addReview(new Review("Cool Course ... job well done."));
        tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));

        // save the course
        System.out.println("Saving the course");
        System.out.println(tempCourse);
        System.out.println(tempCourse.getReviews());

        appDAO.save(tempCourse);
        System.out.println("Done!");
    }

    private void deleteCourseById(AppDAO appDAO) {
        int theId = 13;

        System.out.println("Deleting course id: " + theId);

        appDAO.deleteCourseById(theId);

        System.out.println("Done!");
    }

    private void updateCourse(AppDAO appDAO) {
        int theId = 10;
        System.out.println("Finding course id: " + theId);
        Course tempCourse = appDAO.findCourseById(theId);
        System.out.println("updating course title");
        tempCourse.setTitle("Enjoy the Simple Things");
        appDAO.update(tempCourse);
        System.out.println("Done!");
    }

    private void updateInstructor(AppDAO appDAO) {
        int theId = 1;
        // find the instructor
        System.out.println("Finding instructor id: " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        // update the instructor
        System.out.println("updating instructor last name");
        tempInstructor.setLastName("Tester");
        appDAO.update(tempInstructor);
        System.out.println("Done!");
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding instructor id: " + theId);
        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);
        System.out.println("temp instructor: " + tempInstructor);
        System.out.println("the associated courses: " + tempInstructor.getCourses());

        System.out.println("Done!");
    }

    private void findCoursesForInstructor(AppDAO appDAO) {
        int theId = 1;
        // find the instructor

        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println("temp instructor: " + tempInstructor);
        // find courses for instructor

        System.out.println("Finding courses for instructor id: " + theId);
        List<Course> courses = appDAO.findCoursesByInstructorId(theId);
        // associate the objects

        tempInstructor.setCourses(courses);
        System.out.println("the associated courses: " + tempInstructor.getCourses());

        System.out.println("Done!");
    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding instructor: " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated courses: " + tempInstructor.getCourses());

        System.out.println("Done!");
    }

    private void createInstructorwithCourse(AppDAO appDAO) {
        // create the instructor

        Instructor tempInstructor = new Instructor("Asad","Ali","aliasad@gmail.com");

        // create the instructor detail

        InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.aliasad.com",
                "Gaming");

        // associate the objects

        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // create some courses
        Course tempCourse1 = new Course("Applied Physics");
        Course tempCourse2 = new Course("Web Development");

        // add courses to instructor
        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        // save the instructor
        //
        // NOTE: this will also save the courses
        // because of CascadeType.PERSIST
        System.out.println("Saving instructor: " + tempInstructor);
        System.out.println("The courses: " + tempInstructor.getCourses());
        appDAO.save(tempInstructor);
        System.out.println("Done!");
    }

    private void deleteInstructorDetailById(AppDAO appDAO) {

        int theId = 7;
        System.out.println("Deleting instructor detail id: " + theId);
        appDAO.deleteInstructorDetailById(theId);
        System.out.println("Done!");
    }

    private void findInstructorDetailById(AppDAO appDAO) {

        // get the instructor detail object
		int id = 1;
		System.out.println("Retrieving InstructorDetail by Id " + id);
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(id);
        // print the instructor detail object
		System.out.println("Retrieved Instructor: " + tempInstructorDetail);
        // print the associated instructor
		System.out.println("The associated instructor only: " + tempInstructorDetail.getInstructor());
        System.out.println("Done!");
	}

	private void deleteInstructorById(AppDAO appDAO) {

		int id = 5;
		System.out.println("Deleting instructor against id: " + id);
		appDAO.deleteInstructorById(id);
		System.out.println("Deletion successful!");
	}

	private void findInstructorById(AppDAO appDAO) {

		int id = 1;
		System.out.println("Retrieving Instructor by Id " + id);
		Instructor retrieveInstructor = appDAO.findInstructorById(id);
		System.out.println("Retrieved Instructor: " + retrieveInstructor);
		System.out.println("the associated instructorDetail only: " + retrieveInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		// create the instructor
		/*
		Instructor tempInstructor = new Instructor("Chad","Darby","darby@luv2code.com");

		// create the instructor detail

		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube",
				"luv2code");


		 */

		// create the instructor

		Instructor tempInstructor = new Instructor("Saad","Suriya","suriya880@gmail.com");

		// create the instructor detail

		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com",
				"Guitar");

		// associate the objects

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor

		// Note: this will also save the details object because of CascadeType.ALL

		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}

}
