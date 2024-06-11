package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService
) {

    //전체테스트를 하면 실패됨
    /**
     * 두 테스트는 Spring Context를 공유
     * 위 첫번째테스트에서 유저1명을 넣어주고 조회테스트에서 검증이 2명으로 검증하기때문에 테스트실패됨(조회테스트)
     * 실제로 유저는 총 3명이 있어지게되는 것임
     */
    @AfterEach
    fun clean() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("유저 저장이 정상 동작한다")
    fun saveUserTest() {
        //given
        val request = UserCreateRequest("서동휘", null);

        //when
        userService.saveUser(request);

        //then
        val results = userRepository.findAll();
        assertThat(results).hasSize(1);
        assertThat(results[0].name).isEqualTo(request.name);

        //kotlin 에서는 해당 java 객체의 getAge()값이 null인지 판단을 못함.
        //일단 null이 아니겠지하고 가져감 근데 실제 null이기 떄문에 NPE 발생
        //일다 이것을 해결하려면 해당 객체의 getter 함수에 @Nullable 어노테이션 붙여준다.
        assertThat(results[0].age).isNull();
    }

    @Test
    @DisplayName("유저 조회가 정상 동작한다")
    fun getUsersTest() {
        //given
        userRepository.saveAll(listOf(
            User("A", 20),
            User("B", null),
        ))

        //when
        val results = userService.getUsers();

        //then
        assertThat(results).hasSize(2);
        // ["A", "B"]
        assertThat(results).extracting("name")
            .containsExactlyInAnyOrder("A","B")

        assertThat(results).extracting("age")
            .containsExactlyInAnyOrder(20, null)
    }

    @Test
    @DisplayName("유저 이름 업데이트가 정상 동작한다")
    fun updateUserNameTest() {
        //given
        val savedUser = userRepository.save(User("A", null))
        var request = UserUpdateRequest(savedUser.id!!, "B")

        //when
        userService.updateUserName(request)

        //then
        val result = userRepository.findAll()[0]
        assertThat(result.name).isEqualTo("B")
    }

    @Test
    @DisplayName("유저 삭제가 정상 동작한다")
    fun deleteUserTest() {
        //given
        val savedUser = userRepository.save(User("A", null))

        //when
        userService.deleteUser(savedUser.name);

        //then
        val result = userRepository.findAll()
        assertThat(result).isEmpty();
    }
}