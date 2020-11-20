package com.hogwarts;

import com.hogwarts.page.ContactPage;
import com.hogwarts.page.HomePage;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author fyh
 * @date 2020/11/19
 * @desc 企业微信测试
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WechatTest{

	@ParameterizedTest
	@MethodSource("initMember")
	@Order(1)
	public void testAddMember(String userName, String userPhone){
		HomePage homePage = new HomePage();
		ContactPage contactPage = homePage.contact().addMember(userName, userPhone);
		assertEquals(contactPage.getToast(), "添加成功");
	}

	@ParameterizedTest
	@ValueSource(strings = {"ceshi003", "ceshi004"})
	@Order(2)
	public void testSearchEditMember(String searchName){
		HomePage homePage = new HomePage();
		homePage.contact().searchEditMember(searchName);
	}

	@ParameterizedTest
	@ValueSource(strings = {"ceshi003", "ceshi004"})
	@Order(3)
	public void testSearchDeleteMember(String searchName){
		HomePage homePage = new HomePage();
		homePage.contact().deleteMember(searchName);
	}

	public static Stream<Arguments> initMember(){
		return Stream.of(
				Arguments.arguments("ceshi003", "13900001003"),
				Arguments.arguments("ceshi004", "13900001004"));
	}
}
