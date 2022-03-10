package com.ugurbuga.followtvmovie.ui.persons

import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentPersonsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonsFragment : FTMBaseVMFragment<PersonsViewModel, FragmentPersonsBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_persons

    override fun generateViewModel() = PersonsViewModel::class.java

    override fun onInitDataBinding() {

    }

}