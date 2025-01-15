School Management System

The School Management System is a comprehensive application designed to manage student information, courses, and grades for educational institutions. This system provides an efficient way to handle various administrative tasks, ensuring that educational institutions can focus on delivering quality education.

Key Features
- Student Management: Add, update, delete, and search for student records.
- Course Management: Add, update, delete, and search for courses offered by the institution.
- Grade Management: Maintain student grades and generate transcripts for academic performance.
- Attendance Reports: Generate attendance reports to track student presence.
- Performance Reports: Generate performance reports to evaluate student progress.

Class Structure
The system is built around the following core classes:

Student
- Properties:
  - id: Unique identifier for each student.
  - name: Name of the student.
  - courses: List of courses the student is enrolled in.
  - grades: List of grades associated with the courses.

Course
- Properties:
  - courseId: Unique identifier for each course.
  - courseName: Name of the course.
  - enrolledStudents: List of students enrolled in the course.

Grade
- Properties:
  - studentId: Identifier for the student.
  - courseId: Identifier for the course.
  - score: Grade received by the student in the course.

Data Structures/Algorithms
- Dynamic Storage: An array list is used to store students and courses, allowing for dynamic access and management.
- Binary Search: Implemented for efficient grade lookups, enabling quick retrieval of student grades.
- Sorting Algorithms: Utilized to organize student names and course lists for better readability and accessibility.
