package com.cleanarch.codewars.demo.ui.search_user

import androidx.lifecycle.*
import com.cleanarch.codewars.demo.domain.SearchUserUseCase
import com.cleanarch.codewars.demo.domain.UseCaseRunner
import com.cleanarch.codewars.demo.domain.User
import com.cleanarch.codewars.demo.ui.base.BasePresenter
import com.cleanarch.codewars.demo.ui.base.BaseScreenState
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchUserRoutes {
    val userProfileRoute: LiveData<User>
        get() = _userProfileRoute
    private val _userProfileRoute = MutableLiveData<User>()

    fun routeToUserProfileScreen(user: User) {
        _userProfileRoute.value = user
    }
}

// "ViewModel" Design Pattern
class SearchUserScreenState: BaseScreenState()

class SearchUserPresenter(
    state: SearchUserScreenState,
    private val routes: SearchUserRoutes
): BasePresenter<User>(state) {

    override fun onSuccess(data: User) {
        routes.routeToUserProfileScreen(data)
    }
}

// Google ViewModel as a "view-controller"
class SearchUserController @Inject constructor(
    private val state: SearchUserScreenState,
    private val routes: SearchUserRoutes,
    private val presenter: SearchUserPresenter,
    private val useCase: SearchUserUseCase,
    private val runner: UseCaseRunner<String, User>
) : ViewModel() {

    fun handleUserSearch(username: String) {
        viewModelScope.launch {
            runner.run(useCase, username, presenter)
        }
    }
}
