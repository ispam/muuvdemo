package com.example.muuvdemo

import com.example.muuvdemo.main_screen.data.local.UsersRepositoryImpl
import com.example.muuvdemo.main_screen.data.local.UsersViewModel
import com.example.muuvdemo.main_screen.data.local.entities.DataPage
import com.example.muuvdemo.main_screen.data.local.entities.User
import com.example.muuvdemo.main_screen.data.remote.APIService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantExecutorExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsersViewModelTest {

    val dispatcher = TestCoroutineDispatcher()

    @JvmField
    @RegisterExtension
    val coroutineTestExtension = CoroutineTestExtension(dispatcher)

    @Test
    fun `App is initiated, load first set of data`() {
        val list = mutableListOf<User>()
        for (user in 0..5) {
            list.add(User(user, "email@email", "first", "last", "someurlforavatar"))
        }
        val dataPage = DataPage(1, 6, 12, 2, list)

        val service = mockk<APIService>().apply {
            coEvery { getUsers(1) } returns dataPage
        }

        val repository = UsersRepositoryImpl(dispatcher, service)

        val viewModel = UsersViewModel(repository)
        assert(viewModel.userListLivaData.value?.size == 6)
    }

    @Test
    fun `App is initiated, cant load first set of data`() {
        val service = mockk<APIService>().apply {
            coEvery { getUsers(1) } returns null
        }

        val repository = UsersRepositoryImpl(dispatcher, service)

        val viewModel = UsersViewModel(repository)
        assert(viewModel.userListLivaData.value == null)
    }

    @Test
    fun `Data correctly loaded, add more info`() {
        val list = mutableListOf<User>()
        for (user in 0..5) {
            list.add(User(user, "email@email", "first", "last", "someurlforavatar"))
        }
        val dataPage = DataPage(1, 6, 12, 2, list)

        val service = mockk<APIService>().apply {
            coEvery { getUsers(any()) } returns dataPage
        }
        val repository = UsersRepositoryImpl(dispatcher, service)

        val viewModel = UsersViewModel(repository)
        viewModel.loadMoreUsers()

        coVerify(exactly = 2){ service.getUsers(any())}
    }

}
