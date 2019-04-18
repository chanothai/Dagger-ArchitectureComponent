package com.ballomo.shared.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*

inline fun <reified VM: ViewModel> FragmentActivity.viewModelProvider(
    provider: ViewModelProvider.Factory
) = ViewModelProviders.of(this, provider).get(VM::class.java)

inline fun <reified  VM: ViewModel> Fragment.viewModelProvider(
    provider: ViewModelProvider.Factory
) = ViewModelProviders.of(this, provider).get(VM::class.java)

inline fun <reified VM: ViewModel> Fragment.activityViewModelProvider(
    provider: ViewModelProvider.Factory
) = ViewModelProviders.of(requireActivity(), provider).get(VM::class.java)

/** Uses `Transformations.map` on a LiveData */
fun <X, Y> LiveData<X>.map(body: (X) -> Y): LiveData<Y> {
    return Transformations.map(this, body)
}


