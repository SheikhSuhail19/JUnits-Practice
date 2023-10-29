package com.programming.techie;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {

	ContactManager contactManager;

	private static List<String> phoneNumberList() {
		return Arrays.asList("0123456789", "0243613271", "0125678904");
	}

	@BeforeAll
	public void setupAll() {
		System.out.println("Should print before all Tests");
	}

	@BeforeEach
	public void setup() {
		System.out.println("Should print before each new Test case");
		contactManager = new ContactManager();
	}

	@Test
	//	@Disabled
	public void shouldCreateContact() {
		contactManager.addContact("John", "Locke", "0123456789");

		assertFalse(contactManager.getAllContacts().isEmpty());

		Assertions.assertEquals(1, contactManager.getAllContacts().size());

		Assertions.assertTrue(contactManager.getAllContacts().stream().anyMatch(contact -> contact.getFirstName().equals("John") && contact.getLastName().equals("Locke") && contact.getPhoneNumber().equals("0123456789")));
	}

	@Test
	@DisplayName("Should Not Create Contact When First Name is Null")
	public void shouldThrowRuntimeExceptionWhenFirstNameIsNull() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			contactManager.addContact(null, "Doe", "0123456789");
		});
	}

	@Test
	@DisplayName("Should Not Create Contact When Last Name is Null")
	public void shouldThrowRuntimeExceptionWhenLastNameIsNull() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			contactManager.addContact("John", null, "0123456789");
		});
	}

	@Test
	@DisplayName("Should Not Create Contact When Phone Name is Null")
	public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			contactManager.addContact("John", "Doe", null);
		});
	}

	@AfterEach
	public void tearDown() {
		System.out.println("Should be executed at the end of each Test case");
	}

	@AfterAll
	public void tearDownAll() {
		System.out.println("Should be executed at the end of entire Test class");
	}

	@Test
	@DisplayName("Should Create Contact Only on MAC")
	@EnabledOnOs(value = OS.MAC, disabledReason = "Enabled only on MAC OS")
	public void shouldCreateContactOnlyOnMac() {
		contactManager.addContact("John", "Locke", "0123456789");

		assertFalse(contactManager.getAllContacts().isEmpty());

		Assertions.assertEquals(1, contactManager.getAllContacts().size());

		Assertions.assertTrue(contactManager.getAllContacts().stream().anyMatch(contact -> contact.getFirstName().equals("John") && contact.getLastName().equals("Locke") && contact.getPhoneNumber().equals("0123456789")));
	}

	@Test
	@DisplayName("Should Not Create Contact on Windows OS")
	@DisabledOnOs(value = OS.WINDOWS, disabledReason = "Disabled on Windows OS")
	public void shouldNotCreateContactOnWindows() {
		contactManager.addContact("John", "Locke", "0123456789");

		assertFalse(contactManager.getAllContacts().isEmpty());

		Assertions.assertEquals(1, contactManager.getAllContacts().size());

		Assertions.assertTrue(contactManager.getAllContacts().stream().anyMatch(contact -> contact.getFirstName().equals("John") && contact.getLastName().equals("Locke") && contact.getPhoneNumber().equals("0123456789")));
	}

	@Test
	@DisplayName("Test Contact Creation on Developer Machine")
	public void shouldTestContactCreationOnDEV() {
		Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
//		Assumptions.assumeTrue("TEST".equals(System.getProperty("ENV")));
		contactManager.addContact("John", "Abruzzi", "0987654321");
		assertFalse(contactManager.getAllContacts().isEmpty());
		assertEquals(1, contactManager.getAllContacts().size());
	}

	@DisplayName("Method Source Parametrized Test - Phone Number should match the required format")
	@ParameterizedTest
	@MethodSource("phoneNumberList")
	public void shouldTestContactCreationUsingMethodSource(String phoneNumber) {
		contactManager.addContact("John", "Doe", phoneNumber);
		assertFalse(contactManager.getAllContacts().isEmpty());
		assertEquals(1, contactManager.getAllContacts().size());
	}

	@Nested
	class RepeatedNestedTest {
		@DisplayName("Repeat Contact Creation 5 Times")
		@RepeatedTest(value = 5, name = "Repeating Contact Creation Test {currentRepetition} of {totalRepetitions}")
		public void shouldTestContactCreationRepeatedly() {
			contactManager.addContact("John", "Doe", "0123456789");
			assertFalse(contactManager.getAllContacts().isEmpty());
			assertEquals(1, contactManager.getAllContacts().size());
		}
	}

	@Nested
	class ParametrizedNestedTest {


		@DisplayName("Value Source Parametrized Test - Phone Number should match the required format")
		@ParameterizedTest
		@ValueSource(strings = {"0123456789", "0243613271", "0125678904"})
		public void shouldTestContactCreationUsingValueSource(String phoneNumber) {
			contactManager.addContact("John", "Doe", phoneNumber);
			assertFalse(contactManager.getAllContacts().isEmpty());
			assertEquals(1, contactManager.getAllContacts().size());
		}


		@DisplayName("CSV Source Parametrized Test - Phone Number should match the required format")
		@ParameterizedTest
		@CsvSource({"0123456789", "0243613271", "0125678904"})
		public void shouldTestContactCreationUsingCSVSource(String phoneNumber) {
			contactManager.addContact("John", "Doe", phoneNumber);
			assertFalse(contactManager.getAllContacts().isEmpty());
			assertEquals(1, contactManager.getAllContacts().size());
		}

		@DisplayName("CSV File Source Parametrized Test - Phone Number should match the required format")
		@ParameterizedTest
		@CsvFileSource(resources = "/data.csv")
		public void shouldTestContactCreationUsingCSVFileSource(String phoneNumber) {
			contactManager.addContact("John", "Doe", phoneNumber);
			assertFalse(contactManager.getAllContacts().isEmpty());
			assertEquals(1, contactManager.getAllContacts().size());
		}
	}

}