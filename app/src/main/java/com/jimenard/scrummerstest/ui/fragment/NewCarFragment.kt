package com.jimenard.scrummerstest.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.jimenard.scrummerstest.R
import com.jimenard.scrummerstest.databinding.FragmentNewCarBinding
import com.jimenard.scrummerstest.utils.Status
import com.jimenard.scrummerstest.utils.base.BaseFragment
import com.jimenard.scrummerstest.utils.base.ScrummersViewModelFactory
import com.jimenard.scrummerstest.viewmodels.CarViewModel
import javax.inject.Inject

class NewCarFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ScrummersViewModelFactory<CarViewModel>
    private val viewModel: CarViewModel by lazy { this.viewModelFactory.get() }
    private lateinit var binding: FragmentNewCarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_new_car, container, false)
        this.binding.viewModel = this.viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!this.viewModel.selectedCategory.get().isNullOrEmpty()) {
            when (this.viewModel.selectedCategory.get()) {
                "ELECTRONIC" -> {
                    this.binding.extraData.hint = "Battery capacity"
                    this.binding.extraData.visibility = View.VISIBLE
                }
                "COMMERCIAL" -> {
                    this.binding.extraData.hint = "Space capacity"
                    this.binding.extraData.visibility = View.VISIBLE
                }
                "TRUCK" -> {
                    this.binding.extraData.hint = "Max available payload"
                    this.binding.extraData.visibility = View.VISIBLE
                }
                else -> return
            }
            initView()
            if (!this.viewModel.price.get().isNullOrEmpty()) {
                editCar()
            }
        } else {
            initView()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun editCar() {
        this.binding.newCar.text = "UPDATE CAR"
        if (this.viewModel.selectedCategory.get().equals("ELECTRONIC")) {
            this.binding.categoryCar.isEnabled = false
            this.binding.newCar.isEnabled = false
            this.binding.isNew.isEnabled = false
            this.binding.model.isEnabled = false
            this.binding.price.isEnabled = false
            this.binding.releasedDate.isEnabled = false
            this.binding.seatsUp.isEnabled = false
        } else {
            this.binding.newCar.setOnClickListener { button ->
                this.viewModel.updateCar()
                    .observe(this.viewLifecycleOwner, Observer { resourceResponse ->
                        resourceResponse?.let { resource ->
                            when (resource.status) {
                                Status.SUCCESS -> {
                                    Log.i("NewCarFragment", "Updated Car Successful")
                                    viewModel.cleanForm()
                                    button.findNavController().popBackStack()
                                }
                                Status.ERROR -> {
                                    Log.e("NewCarFragment", resource.message!!)
                                }
                                Status.LOADING -> {
                                }
                            }
                        }
                    })
            }
        }
    }

    private fun initView() {
        this.binding.categoryCar.setOnClickListener { view ->
            Navigation.findNavController(view)
                .navigate(R.id.action_select_category)
        }
        this.binding.newCar.setOnClickListener { button ->
            this.viewModel.saveNewCar()
                .observe(this.viewLifecycleOwner, Observer { resourceResponse ->
                    resourceResponse?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                Log.i("NewCarFragment", "New Car Added Successful")
                                viewModel.cleanForm()
                                button.findNavController().popBackStack()
                            }
                            Status.ERROR -> {
                                Log.e("NewCarFragment", resource.message!!)
                            }
                            Status.LOADING -> {
                            }
                        }
                    }
                })
        }
    }
}