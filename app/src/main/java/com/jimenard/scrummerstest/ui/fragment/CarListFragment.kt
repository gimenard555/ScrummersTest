package com.jimenard.scrummerstest.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.jimenard.scrummerstest.R
import com.jimenard.scrummerstest.databinding.FragmentCarListBinding
import com.jimenard.scrummerstest.utils.Status
import com.jimenard.scrummerstest.utils.adapters.ACar
import com.jimenard.scrummerstest.utils.base.BaseFragment
import com.jimenard.scrummerstest.utils.base.BaseRecyclerAdapter
import com.jimenard.scrummerstest.utils.base.ScrummersViewModelFactory
import com.jimenard.scrummerstest.viewmodels.CarViewModel
import javax.inject.Inject

class CarListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ScrummersViewModelFactory<CarViewModel>
    private val viewModel: CarViewModel by lazy { this.viewModelFactory.get() }
    private lateinit var binding: FragmentCarListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_car_list, container, false)
        this.binding.viewModel = this.viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        updateCarList()
    }

    private fun initView() {
        this.binding.addNewCar.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_main_to_new_car)
        }
    }

    private fun updateCarList() {
        this.viewModel.getAllCars().observe(this.viewLifecycleOwner, Observer { resourceResponse ->
            resourceResponse?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data
                        this.binding.carList.apply {
                            adapter = BaseRecyclerAdapter(
                                resource.data!!,
                                ACar(),
                                notifier = { view, car, _ ->
                                    viewModel.currentCar = car
                                    Navigation.findNavController(view)
                                        .navigate(R.id.action_main_to_details)
                                }
                            )
                            layoutManager = LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                        }
                    }
                    Status.ERROR -> {
                        Log.e("CarListFragment", resource.message!!)
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }
}