package com.jimenard.scrummerstest.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jimenard.scrummerstest.R
import com.jimenard.scrummerstest.databinding.FragmentCategoryListBinding
import com.jimenard.scrummerstest.utils.Status
import com.jimenard.scrummerstest.utils.adapters.ACategory
import com.jimenard.scrummerstest.utils.base.BaseFragment
import com.jimenard.scrummerstest.utils.base.BaseRecyclerAdapter
import com.jimenard.scrummerstest.utils.base.ScrummersViewModelFactory
import com.jimenard.scrummerstest.viewmodels.CarViewModel
import java.util.*
import javax.inject.Inject


class CategoryListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ScrummersViewModelFactory<CarViewModel>
    private val viewModel: CarViewModel by lazy { this.viewModelFactory.get() }
    private lateinit var binding: FragmentCategoryListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_category_list, container, false)
        this.binding.viewModel = this.viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        this.viewModel.getAllCategories()
            .observe(this.viewLifecycleOwner, Observer { resourceResponse ->
                resourceResponse?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            resource.data
                            this.binding.carList.apply {
                                adapter = BaseRecyclerAdapter(
                                    resource.data!!,
                                    ACategory(),
                                    notifier = { view, category, _ ->
                                        viewModel.selectedCategory.set(category.nameCategory)
                                        view.findNavController().popBackStack()
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
                            Log.e("CategoryListFragment", resource.message!!)
                        }
                        Status.LOADING -> {
                        }
                    }
                }
            })
        this.binding.addNewCategory.setOnClickListener { addNewCategory() }
    }

    private fun addNewCategory() {
        val editText = EditText(requireContext())
        editText.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        ).apply {
            setMargins(10, 10, 10, 10)
        }
        AlertDialog.Builder(requireContext())
            .setTitle("ADD NEW CATEGORY")
            .setMessage("Add new category name")
            .setView(editText)
            .setPositiveButton("ADD") { _, _ ->
                viewModel.saveCategory(editText.text.toString().toUpperCase(Locale.ROOT))
                    .observe(requireActivity(), Observer { resourceResponse ->
                        resourceResponse?.let { resource ->
                            when (resource.status) {
                                Status.SUCCESS -> {
                                    resource.data
                                    initView()
                                    Log.d(
                                        "CategoryListFragment",
                                        "NEW CATEGORY ADDED ${editText.text}"
                                    )
                                }
                                Status.ERROR -> {
                                    Log.e("CategoryListFragment", resource.message!!)
                                }
                                Status.LOADING -> {
                                }
                            }
                        }
                    })
                Log.d("onclick", "NEW CATEGORY ADDED ${editText.text}")
            }.show()

    }
}