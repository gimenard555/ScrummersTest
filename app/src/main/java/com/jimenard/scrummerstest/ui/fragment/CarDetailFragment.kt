package com.jimenard.scrummerstest.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jimenard.scrummerstest.R
import com.jimenard.scrummerstest.databinding.FragmentCarDetailBinding
import com.jimenard.scrummerstest.databinding.FragmentCarListBinding
import com.jimenard.scrummerstest.utils.Status
import com.jimenard.scrummerstest.utils.adapters.ACar
import com.jimenard.scrummerstest.utils.base.BaseFragment
import com.jimenard.scrummerstest.utils.base.BaseRecyclerAdapter
import com.jimenard.scrummerstest.utils.base.ScrummersViewModelFactory
import com.jimenard.scrummerstest.viewmodels.CarViewModel
import javax.inject.Inject

class CarDetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ScrummersViewModelFactory<CarViewModel>
    private val viewModel: CarViewModel by lazy { this.viewModelFactory.get() }
    private lateinit var binding: FragmentCarDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_car_detail, container, false)
        this.binding.viewModel = this.viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        this.binding.editCar.setOnClickListener { view ->
            viewModel.updateCarInformation()
            Navigation.findNavController(view)
                .navigate(R.id.action_detail_to_edit_car)
        }
        this.binding.deleteCar.setOnClickListener { button ->
            viewModel.deleteCar().observe(this.viewLifecycleOwner, Observer { resourceResponse ->
                resourceResponse?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            resource.data
                            Log.i("CarDetailFragment", "Car Deleted Successful")
                            viewModel.cleanForm()
                            button.findNavController().popBackStack()
                        }
                        Status.ERROR -> {
                            Log.e("CarDetailFragment", resource.message!!)
                        }
                        Status.LOADING -> {
                        }
                    }
                }
            })
        }
    }
}