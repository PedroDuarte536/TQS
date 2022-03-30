a) Identify a couple of examples on the use of AssertJ expressive methods chaining.
assertThat(fromDb.getEmail()).isEqualTo( emp.getEmail());
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName());
assertThat(found).extracting(Employee::getName).containsOnly("bob");
assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");

b) Identify an example in which you mock the behavior of the repository (and avoid involving a database).
When the test scope is the service/domain login.

c) What is the difference between standard @Mock and @MockBean?
MockBean is a Mockito wrapper for the Spring Boot Framework, making use of the existing ApplicationContext.

d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?
That file contains all required database properties, being used in both integration tests (D and E).

e) the sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?
C - The objective is to test the Controllers, using mocks of the required services. The requests are done through an MockMvc instance, not requiring a real database.
D - It is an integration test, using all of the components and requiring a real database but still using MockMvc so as to simulate the HTTP requests and responses.
E - It is an integration test, using all of the components and requiring a real database but this one uses the TestRestTemplate which is a real HTTP client.