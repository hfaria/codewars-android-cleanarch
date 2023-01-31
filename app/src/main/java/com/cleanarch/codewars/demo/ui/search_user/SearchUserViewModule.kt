package com.cleanarch.codewars.demo.ui.search_user

import androidx.lifecycle.*
import com.cleanarch.codewars.demo.domain.User
import com.cleanarch.codewars.demo.ui.base.BasePresenter
import com.cleanarch.codewars.demo.ui.base.BaseScreenState
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
class SearchUserScreenState: BaseScreenState() {
    val username = MutableLiveData<String>()
}

class SearchUserPresenter(
    state: SearchUserScreenState,
    private val routes: SearchUserRoutes
): BasePresenter<User>(state) {

    override fun onSuccess(data: User) {
        routes.routeToUserProfileScreen(data)
    }
}

// Google ViewModel as a "view-controller"
class SearchUserViewModel @Inject constructor(
) : ViewModel() {

    val state = SearchUserScreenState()
    val routes = SearchUserRoutes()
    private val presenter = SearchUserPresenter(state, routes)

    fun handleUserSearch() {
        //viewModelScope.launch {
        //    runner.run(useCase, state.username.value.orEmpty(), presenter)
        //}
        presenter.onSuccess(User(
            "UNAME",
            "FOOBAR"
        ))
    }
}
