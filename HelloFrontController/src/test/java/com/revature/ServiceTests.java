package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.revature.model.Employee;
import com.revature.repositories.EmployeeDAOImpl;
import com.revature.service.EmployeeService;

public class ServiceTests {

	// Our Service layer depends on the DAO
	private EmployeeDAOImpl edaoImpl;
	
	// Before any test method is run, do this
	@Before
	public void setup() {
		
		edaoImpl = mock(EmployeeDAOImpl.class); // Reflection
		
		// set the edao of the Employee Service class = to the mocked dao
		EmployeeService.edao = edaoImpl;
		
		
		
	}
	
	// Everything goes right
	@Test
	public void test_Employee_findByUsername() {
		
		Employee sampleEmp = new Employee(1, "a", "b", "c", "d");
		Employee sampleEmp2 = new Employee(1, "e", "f", "g", "h");
		
		List<Employee> list = new ArrayList<Employee>();
		list.add(sampleEmp);
		list.add(sampleEmp2);
		
		
		// We program our dao to return that data as its fake DB data
		when(edaoImpl.findAll()).thenReturn(list);
		
		String dummyusername = sampleEmp.getUsername();
		
		// findByUsername() method in the service class returns the fetched user!
		Employee returned = EmployeeService.findByUsername(dummyusername);
		
		assertEquals(sampleEmp, returned);
		
		
	}
	
	// Test to make sure that our findByUsername() service method returns null if employee doesn't exist
	@Test
	public void employeeNotPresentInDb() {
		
		List<Employee> emptyList = new ArrayList<Employee>();
		
		when(edaoImpl.findAll()).thenReturn(emptyList);
		
		Employee empFoundByUsername = EmployeeService.findByUsername("test");
		
		// in our logic we said that if the employee doesn't exist we return null
		// checking that no error is thrown instead of returning null
		assertNull(empFoundByUsername);
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
}
