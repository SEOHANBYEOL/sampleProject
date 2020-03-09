package dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import data.Database;
import vo.UserVO;

public class UserDaoTest {
	
	//테스트 메소드 설정 방법
	//메소드에 @Test 어노테이션을 붙인다. --> 테스트 메소드로 인식
	//@Test 어노테이션이 붙은 테스트 메소드는
	//접근제어자 : public
	//리턴 타입 : avoid
	
	//junit 프레임워크의 실행순서
	//@Before --> @Test -->@After
	
	UserDao userDao;
	
	@Before
	public void setup(){
		userDao = UserDao.getInstance();
	}
	
	//테스트 메소드 이름 : 운영메소드명 + Test
	//insertUser 메소드 테스트
	@Test
	public void insertUserTest() {
		//insertUser메소드를 실행하기 위해 필요한 것
		//1. userDao의 인스턴스 필요
		//2. insertuser메소드의 인자 --> UserVO
		
		//given :  주어진 상황
		//UserDao userDao = UserDao.getInstance();
		
		UserVO userVO = new UserVO();
		
		//사용자로부터 입력받았다고 가정한 값을 userVO에 넣어준다.
		userVO.setId("brown");
		userVO.setPassword("brown_pass");
		userVO.setName("브라운");
		
		//when : 행동 (메소드 실행)
		userDao.insertUser(userVO);
		
		//then : 그 결과 (한건의 사용자를 추가했으므로 기대값 -> 2)
		assertEquals(2,Database.getInstance().tb_user.size());
		
	}
		
		//selectUserList(사용자 전체 리스트 테스트)
		@Test
		public void selectUserListTest(){
			//given(UserDao 인스턴스)
			//UserDao userDao = UserDao.getInstance();
			//when
			ArrayList<UserVO> userList = userDao.selectUserList();

			//then
			assertNotNull(userList);
			assertTrue(userList.size()>=1);
		
	}
		
		//selectUserTest
		@Test
		public void selectUserSuccessTest(){
			//given(userDao 인스턴스 --> @Before, HashMap)
			HashMap<String, String> paramMap = new HashMap<String, String>();
			
			//Database가 초기화 될때 admin이라는 사용자가 tb_user 리스트에 들어가 있다
			paramMap.put("ID", "admin");
			
			//when
			UserVO userVO = userDao.selectUser(paramMap);
			
			//then
			UserVO expectedUserVO = new UserVO();
			expectedUserVO.setId("admin");
			expectedUserVO.setPassword("admin");
			expectedUserVO.setName("관리자");
			
			
			assertNotNull(userVO);
			assertEquals("admin", userVO.getId());
			assertEquals("관리자", userVO.getName());
			assertEquals(expectedUserVO, userVO);
			
			
			//객체의 값을 비교하기 위해서는 equals 메소드를 override 해야한다.
			//객체 동일(동일이면 동치), 동치 (동치이면 동일? X)
			//UserVO userVO = new UserVO();
			//UserVO userVO2 = UserVO();
			//userVO와 userVO2는 동일
			
			
			//UserVO userVO = new UserVO();
			//userVO.setId("admin");
			//userVO.setPassword("admin");
			//userVO.setName("관리자");
			
		
			//UserVO userVO2 = new UserVO();
			//userVO2.setId("admin");
			//userVO2.setPassword("admin");
			//userVO2.setName("관리자");
			
			//userVO와 userVO2는 같은 값을 갖는다.
			//if(userVO == userVO2) -->동일에 대한 비교
			//if(userVO.equals(userVO2)) -->동치에 대한 비교
			
		}
		
		//selectUser의 실패케이스
		@Test
		public void selectUserFailTest(){
			//given : userDao 인스턴스, HashMap
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("ID", "NOT_EXISTS_ID");
			
			//when
			UserVO userVO = userDao.selectUser(paramMap);
			//then
			assertNull(userVO);
		}
		
		@Test
		public void selectUserNotExistsKeyTest(){
			//given : userDao 인스턴스, HashMap
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("NONE", "NOT_EXISTS_KEY");
			
			//when
			UserVO userVO = userDao.selectUser(paramMap);
			//then
			assertNull(userVO);
		}
		

}
