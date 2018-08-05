package com.pccwglobal.assessment.marssitest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.noconnor.junitperf.JUnitPerfRule;
import com.github.noconnor.junitperf.JUnitPerfTest;
import com.github.noconnor.junitperf.JUnitPerfTestRequirement;
import com.github.noconnor.junitperf.reporting.providers.ConsoleReportGenerator;
import com.github.noconnor.junitperf.reporting.providers.HtmlReportGenerator;
import com.pccwglobal.assessment.marssitest.pojo.CreateUserRequest;
import com.pccwglobal.assessment.marssitest.pojo.UpdateUserRequest;
import com.pccwglobal.assessment.marssitest.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PerformanceTest {
	@Autowired
	@Qualifier("restfulService")
	private UserService restfulUserService;
	@Rule
	public JUnitPerfRule perfTestRule = new JUnitPerfRule(new ConsoleReportGenerator(), new HtmlReportGenerator("./performanceTestReport.html"));

	@Test
	@JUnitPerfTest(threads = 10, durationMs = 1250, warmUpMs = 10, maxExecutionsPerSecond = 60)
	@JUnitPerfTestRequirement(percentiles = "90:1000,95:1100,98:1100,99:1200", executionsPerSec = 50, allowedErrorPercentage = 0.10f)
	public void testListUsersPerformance() {
		int page = 0;
		int size =0;
		restfulUserService.getUsers(page, size);
	}
	
	@Test
	@JUnitPerfTest(threads = 10, durationMs = 1250, warmUpMs = 10, maxExecutionsPerSecond = 60)
	@JUnitPerfTestRequirement(percentiles = "90:1000,95:1100,98:1100,99:1200", executionsPerSec = 50, allowedErrorPercentage = 0.10f)
	public void testCreateUserPerformance() throws Exception {
		CreateUserRequest userRequest = new CreateUserRequest();
		userRequest.setEmail("newuser@newuser.com");
		userRequest.setName("newuser");
		userRequest.setPassword("67890");
		userRequest.setUsername("newuser");
		restfulUserService.createUser(userRequest);
	}
	
	@Test
	@JUnitPerfTest(threads = 10, durationMs = 1250, warmUpMs = 10, maxExecutionsPerSecond = 60)
	@JUnitPerfTestRequirement(percentiles = "90:1000,95:1100,98:1100,99:1200", executionsPerSec = 50, allowedErrorPercentage = 0.10f)
	public void testGetUserByIdPerformance() throws Exception {
		String id = "0d194926a66a45f1b1291ddd5b10d3d9";
		restfulUserService.getUserById(id);
		
	}
	
	@Test
	@JUnitPerfTest(threads = 10, durationMs = 1250, warmUpMs = 10, maxExecutionsPerSecond = 60)
	@JUnitPerfTestRequirement(percentiles = "90:1000,95:1100,98:1100,99:1200", executionsPerSec = 50, allowedErrorPercentage = 0.10f)
	public void testUpdateUserPerformance() throws Exception {
		String id = "406ceab93557456183f01fd2862b3c07";
		UpdateUserRequest userRequest = new UpdateUserRequest();
		userRequest.setEmail("updateduser3@updateduser2.com");
		userRequest.setName("updateduser3");
		userRequest.setPassword("78901");
		userRequest.setUsername("updateduser3");
		restfulUserService.updateUser(id, userRequest);
	}
	
	@Test
	@JUnitPerfTest(threads = 10, durationMs = 1250, warmUpMs = 10, maxExecutionsPerSecond = 60)
	@JUnitPerfTestRequirement(percentiles = "90:1000,95:1100,98:1100,99:1200", executionsPerSec = 50, allowedErrorPercentage = 0.10f)
	public void testDeleteUserByIdPerformance() throws Exception {
		String id = "d9160ab59af646f195eefe4550d2f5e7";
		restfulUserService.deleteUserById(id);
	}
}
