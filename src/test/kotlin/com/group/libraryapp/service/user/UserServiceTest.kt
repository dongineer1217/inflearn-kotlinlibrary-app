package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService
) {

    @Test
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

}